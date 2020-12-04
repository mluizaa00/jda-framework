package com.luizaprestes.framework.command.impl;

import com.luizaprestes.framework.command.CommandFrame;
import com.luizaprestes.framework.command.message.MessageType;
import com.luizaprestes.framework.command.model.CommandModel;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;

import java.util.Objects;

public class CommandParser {

    private final CommandFrame frame;

    public CommandParser(CommandFrame frame) {
        this.frame = frame;
    }

    public void parse(final Message message) {
        final String content = message.getContentRaw();

        String prefix = null;

        for (String s : frame.getPrefixes()) {
            if (content.startsWith(s)) {
                prefix = s;
                break;
            }
        }

        if (prefix == null || !content.startsWith(prefix)) return;

        final boolean containsSpace = content.contains(" ");
        final String label = content.substring(prefix.length(), containsSpace ? content.indexOf(" ") : content.length());

        final CommandModel command = frame.getCommand(label);
        if (command == null) return;

        if (!test(Objects.requireNonNull(message.getMember()), command.getPermissions())
          || test(message.getMember(), command.getRole())
        ) {
            message.reply(frame.getMessageHolder().getMessage(MessageType.LACK_PERM_MESSAGE)).queue();
            return;
        }

        final String[] args = containsSpace ? content.substring(prefix.length()
          + label.length() + 1).split(" ") : new String[0];

        command.onCommand(message, args);
    }

    private boolean test(final Member member, final Permission[] permissions) {
        return member.hasPermission(permissions);
    }

    private boolean test(final Member member, final long role) {
        for (Role memberRole : member.getRoles()) {
            if (memberRole.getIdLong() == role) {
                return true;
            }
        }

        return false;
    }
}
