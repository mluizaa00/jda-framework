package com.luizaprestes.framework.command.frame.impl;

import com.luizaprestes.framework.command.frame.CommandClient;
import lombok.Data;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

@Data
public class CommandContext {

    private final GuildMessageReceivedEvent eventSource;
    private final CommandClient client;

    private final String[] args;

}
