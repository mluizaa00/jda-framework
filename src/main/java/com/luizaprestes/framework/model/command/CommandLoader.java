package com.luizaprestes.framework.model.command;

import com.luizaprestes.framework.CommandFrame;
import com.luizaprestes.framework.annotation.Command;
import com.luizaprestes.framework.model.CommandModel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import net.dv8tion.jda.api.entities.Message;

public final class CommandLoader {

  private static final Class<?>[] CLASSES = new Class[]{
      Message.class,
      String[].class
  };

  public void load(final CommandFrame frame, final Object holder) {
    for (Method method : holder.getClass().getMethods()) {
      final Command annotation = method.getAnnotation(Command.class);
        if (annotation == null) {
            continue;
        }

        if (!Arrays.equals(method.getParameterTypes(), CLASSES)
            || !Modifier.isPublic(method.getModifiers())
        ) {
            continue;
        }

      final CommandModel model = new CommandModel(
          annotation.name(),
          annotation.aliases(),
          annotation.permissions(),
          annotation.role()
      ) {
        @Override
        public void onCommand(final Message context, final String[] args) {
          try {
            method.invoke(holder, context, args);
          } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
          }
        }
      };

      frame.registerCommands(model);
    }
  }

}
