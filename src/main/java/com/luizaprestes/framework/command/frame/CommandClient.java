package com.luizaprestes.framework.command.frame;

import com.luizaprestes.framework.command.holder.MessageHolder;
import com.luizaprestes.framework.command.model.CommandModel;
import net.dv8tion.jda.api.JDA;

import java.util.Collection;
import java.util.EventListener;
import java.util.List;

public interface CommandClient {

    void register(JDA jda);

    String getPrefix();

    void addCommand(CommandModel command);

    void addCommands(CommandModel... commands);

    CommandModel getCommandByName(String name);

    Collection<CommandModel> getCommands();

    CommandListener getListener();

    MessageHolder getMessageHolder();
}
