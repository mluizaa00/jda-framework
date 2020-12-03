package com.luizaprestes.framework.event;

import net.dv8tion.jda.api.events.GenericEvent;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class WaitingEvent<T extends GenericEvent> {

    private final Predicate<T> condition;
    private final Consumer<T> action;

    public WaitingEvent(Predicate<T> condition, Consumer<T> action) {
        this.condition = condition;
        this.action = action;
    }

    public boolean attempt(T event) {
        if (condition.test(event)) {
            action.accept(event);
            return true;
        }
        return false;
    }
}