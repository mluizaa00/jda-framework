package com.luizaprestes.framework.command.frame.impl;

import com.luizaprestes.framework.command.frame.CommandClient;
import com.luizaprestes.framework.command.frame.CommandListener;
import com.luizaprestes.framework.command.holder.MessageHolder;
import com.luizaprestes.framework.command.model.CommandModel;
import lombok.Data;
import net.dv8tion.jda.api.JDA;

import java.util.Collection;
import java.util.Map;

@Data
public class CommandClientImpl implements CommandClient {

    private final String prefix;
    private final Map<String, CommandModel> commandIndex;
    private final CommandListener listener;
    private final MessageHolder messageHolder;

    @Override
    public void register(JDA jda) {
        jda.addEventListener(
          new CommandParseListener(this)
        );
    }

    @Override
    public void addCommand(CommandModel command) {
        final String name = command.getName().toLowerCase();
        commandIndex.put(name, command);
    }

    @Override
    public void addCommands(CommandModel... models) {
        for (CommandModel model : models) {
            addCommand(model);
        }
    }

    @Override
    public CommandModel getCommandByName(String name) {
        return commandIndex.get(name);
    }

    @Override
    public Collection<CommandModel> getCommands() {
        return commandIndex.values();
    }

}
