package com.luizaprestes.framework.command.impl;

import com.luizaprestes.framework.command.CommandFrame;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    private final CommandParser parser;

    public CommandListener(CommandFrame frame) {
        this.parser = new CommandParser(frame);
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        parser.parse(event.getMessage());
    }
}
