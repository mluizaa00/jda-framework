package com.luizaprestes.framework.command.impl;

import com.luizaprestes.framework.command.CommandFrame;
import com.luizaprestes.framework.command.annotation.Command;
import com.luizaprestes.framework.command.model.CommandModel;
import net.dv8tion.jda.api.entities.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CommandLoader {

    private static final Class<?>[] CLASSES = new Class[]{
      Message.class,
      String[].class
    };

    public void load(final CommandFrame frame, final Object holder) {
        for (Method method : holder.getClass().getMethods()) {
            final Command annotation = method.getAnnotation(Command.class);
            if(annotation == null) continue;

            if (method.getReturnType() != Void.TYPE
              || method.getParameterTypes() != CLASSES
              || !Modifier.isPublic(method.getModifiers())
            ) continue;

            final CommandModel model = new CommandModel(
              annotation.name(),
              annotation.aliases(),
              annotation.permissions(),
              annotation.role()
            ) {
                @Override
                public void onCommand(Message message, String[] args) {
                    try {
                        method.invoke(holder, message, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            };

            frame.registerCommands(model);
        }
    }
}
