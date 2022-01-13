package com.luizaprestes.framework.views.listener;

import com.luizaprestes.framework.CommandFrame;
import com.luizaprestes.framework.model.command.CommandParser;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public final class CommandListener extends ListenerAdapter {

    private final CommandParser parser;

    public CommandListener(final CommandFrame frame) {
        this.parser = new CommandParser(frame);
    }

    @Override
    public void onGuildMessageReceived(final @NotNull GuildMessageReceivedEvent event) {
        parser.parse(event.getMessage());
    }

}
