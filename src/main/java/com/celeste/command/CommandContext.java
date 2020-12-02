package com.celeste.command;

import com.celeste.command.client.impl.CommandFrameImpl;
import lombok.Data;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Data
public class CommandContext {

    private final MessageReceivedEvent eventSource;
    private final CommandFrameImpl client;

    private String args;

    public CommandContext(MessageReceivedEvent eventSource, String args, CommandFrameImpl client) {
        this.eventSource = eventSource;
        this.args = args == null ? "" : args;
        this.client = client;
    }

}
