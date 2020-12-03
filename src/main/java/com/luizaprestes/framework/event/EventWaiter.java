package com.luizaprestes.framework.event;

import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.internal.utils.Checks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventWaiter implements EventListener {

    private final HashMap<Class<?>, Set<WaitingEvent>> waitingEvents;
    private final ScheduledExecutorService threadPool;
    private final boolean shutdownAutomatically;

    public EventWaiter() {
        this(Executors.newSingleThreadScheduledExecutor(), true);
    }

    public EventWaiter(ScheduledExecutorService threadPool, boolean shutdownAutomatically) {
        Checks.notNull(threadPool, "ScheduledExecutorService");
        Checks.check(!threadPool.isShutdown(), "Cannot construct EventWaiter with a closed ScheduledExecutorService!");

        this.waitingEvents = new HashMap<>();
        this.threadPool = threadPool;
        this.shutdownAutomatically = shutdownAutomatically;
    }

    public <T extends Event> void waitForEvent(Class<T> classType, Predicate<T> condition, Consumer<T> action, long timeout, TimeUnit unit, Runnable timeoutAction) {
        Checks.check(!isShutdown(), "Attempted to register a WaitingEvent while the EventWaiter's threadPool was already shut down!");
        Checks.notNull(classType, "The provided class type");
        Checks.notNull(condition, "The provided condition predicate");
        Checks.notNull(action, "The provided action consumer");

        WaitingEvent waitingEvent = new WaitingEvent<>(condition, action);
        Set<WaitingEvent> set = waitingEvents.computeIfAbsent(classType, c -> new HashSet<>());
        set.add(waitingEvent);

        if (timeout > 0 && unit != null) {
            threadPool.schedule(() -> {
                if (set.remove(waitingEvent) && timeoutAction != null) {
                    timeoutAction.run();
                }
            }, timeout, unit);
        }
    }

    public final void onEvent(GenericEvent event) {
        Class eventClass = event.getClass();

        while (eventClass != null) {
            if (waitingEvents.containsKey(eventClass)) {
                Set<WaitingEvent> set = waitingEvents.get(eventClass);
                WaitingEvent[] toRemove = set.toArray(new WaitingEvent[set.size()]);

                set.removeAll(Stream.of(toRemove).filter(i -> i.attempt(event)).collect(Collectors.toSet()));
            }

            if (event instanceof ShutdownEvent && shutdownAutomatically) threadPool.shutdown();

            eventClass = eventClass.getSuperclass();
        }
    }

    public void shutdown() {
        if (shutdownAutomatically) {
            throw new UnsupportedOperationException("Shutting down EventWaiters that are set to automatically close is unsupported!");
        }

        threadPool.shutdown();
    }

    public boolean isShutdown() {
        return threadPool.isShutdown();
    }
}
