package com.luizaprestes.command;

import com.luizaprestes.command.client.impl.CommandClientImpl;
import lombok.Data;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Data
public class CommandContext {

    private final MessageReceivedEvent eventSource;
    private final CommandClientImpl client;

    private String args;

    public CommandContext(MessageReceivedEvent eventSource, String args, CommandClientImpl client) {
        this.eventSource = eventSource;
        this.args = args == null ? "" : args;
        this.client = client;
    }

}
