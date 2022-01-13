package com.luizaprestes.framework.model.command;

import com.luizaprestes.framework.CommandFrame;
import com.luizaprestes.framework.model.CommandModel;
import com.luizaprestes.framework.model.message.MessageType;
import java.util.Objects;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

@AllArgsConstructor
public final class CommandParser {

  private final CommandFrame frame;

  public void parse(final Message message) {
    final String content = message.getContentRaw();

    String prefix = null;

    for (String s : frame.getPrefixes()) {
      if (content.startsWith(s)) {
        prefix = s;
        break;
      }
    }

    if (prefix == null || !content.startsWith(prefix)) {
      return;
    }

    final boolean containsSpace = content.contains(" ");
    final String label = content.substring(prefix.length(),
        containsSpace ? content.indexOf(" ") : content.length());

    final CommandModel command = frame.getCommand(label);
    if (command == null) {
      return;
    }

    if (!test(Objects.requireNonNull(message.getMember()), command.getPermissions())
        || test(message.getMember(), command.getRole())
    ) {
      message.reply(frame.getMessageHolder().getMessage(MessageType.LACK_PERM_MESSAGE)).queue();
      return;
    }

    final String[] args = containsSpace
        ? content.substring(prefix.length() + label.length() + 1).split(" ")
        : new String[0];

    command.onCommand(message, args);
  }

  private boolean test(final Member member, final Permission[] permissions) {
    return member.hasPermission(permissions);
  }

  private boolean test(final Member member, final long role) {
    return member.getRoles().stream()
        .filter(role1 -> role1.getIdLong() == role)
        .findFirst()
        .orElse(null) != null;
  }

}
