package com.celeste.command.client.impl;

import com.celeste.command.client.CommandListener;
import com.celeste.command.model.CommandModel;
import com.celeste.command.holder.MessageHolder;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.events.ReadyEvent;
import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedList;

@Getter
@Setter
public class CommandClientImpl implements CommandFrameImpl, EventListener {

    private String prefix;
    private LinkedList<CommandModel> commands;

    private CommandListener listener;
    private MessageHolder messageHolder;

    private final HashMap<String, Integer> commandIndex;

    public CommandClientImpl(String prefix, LinkedList<CommandModel> commands, CommandListener listener, MessageHolder holder) {
        this.prefix = prefix;
        this.commands = commands;
        this.listener = listener;
        this.messageHolder = holder;

        commandIndex = new HashMap<>();

        setPrefix();

        for (CommandModel command : commands) {
            addCommand(command);
        }
    }

    public void setPrefix() {
        if (prefix == null) {
            prefix = "-";
        }
    }

    @Override
    public void addCommand(CommandModel command) {
        final String name = command.getName().toLowerCase();

        if (commandIndex.containsKey(name)) {
            throw new IllegalArgumentException("Command already has been indexed: " + name + "!");
        }

        for (String alias : command.getAliases()) {
            if (commandIndex.containsKey(alias.toLowerCase())) {
                throw new IllegalArgumentException("Command already has been indexed: " + name + "!");
            }
        }

        commandIndex.put(name, commands.size());
        commands.add(commands.size(), command);
    }

    @Override
    public void addCommands(CommandModel... models) {
        for (CommandModel model : models) {
            addCommand(model);
        }
    }

    private void onReady(ReadyEvent event) {
        if (!event.getJDA().getSelfUser().isBot()) {
            System.out.println("The bot doesn't support client accounts.");
            event.getJDA().shutdown();
        }
    }
}
