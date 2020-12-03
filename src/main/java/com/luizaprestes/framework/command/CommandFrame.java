package com.luizaprestes.framework.command;

import com.luizaprestes.framework.command.annotation.Command;
import com.luizaprestes.framework.command.frame.CommandClient;
import com.luizaprestes.framework.command.frame.CommandListener;
import com.luizaprestes.framework.command.frame.impl.CommandClientImpl;
import com.luizaprestes.framework.command.frame.impl.CommandContext;
import com.luizaprestes.framework.command.holder.MessageHolder;
import com.luizaprestes.framework.command.model.CommandModel;
import lombok.Getter;
import lombok.Setter;
import org.reflections8.Reflections;
import org.reflections8.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


public class CommandFrame {

    private final Collection<CommandModel> commands;
    private final String prefix;

    @Getter
    private CommandListener listener;

    @Getter
    private MessageHolder messageHolder;

    public CommandFrame(String prefix, CommandListener listener) {
        this.prefix = prefix;
        this.commands = new HashSet<>();

        this.messageHolder = new MessageHolder();

        if(listener == null) {
            listener = /* TODO */ null;
        }

        this.listener = listener;
    }

    public CommandClient build() {
        final Map<String, CommandModel> commandMap = new HashMap<>();
        for (CommandModel command : commands) {
            commandMap.put(command.getName(), command);
        }

        return new CommandClientImpl(
          prefix,
          commandMap,
          listener,
          messageHolder
        );
    }

    public <T> void loadCommands(Class<T> main) {
        try {
            final Reflections reflections = new Reflections(
              main.getPackage().getName(),
              new MethodAnnotationsScanner()
            );

            for (Method method : reflections.getMethodsAnnotatedWith(Command.class)) {
                final CommandModel model = read(method.getDeclaringClass().newInstance(), method);

                if (model != null) {
                    commands.add(model);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private CommandModel read(Object holder, Method method) {
        final Command command = method.getAnnotation(Command.class);

        if (command == null || !Modifier.isPrivate(method.getModifiers())) return null;

        return new CommandModel(
          command.name(),
          command.aliases(),
          command.requiredRole(),
          command.userPermissions()
        ) {
            @Override
            public void execute(CommandContext context, String[] args) throws InvocationTargetException, IllegalAccessException {
                method.invoke(holder, method);
            }
        };
    }

}
