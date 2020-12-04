package com.luizaprestes.framework.command;

import com.luizaprestes.framework.command.message.MessageHolder;
import com.luizaprestes.framework.command.model.CommandModel;
import net.dv8tion.jda.api.JDA;

public interface CommandFrame {

    String[] getPrefixes();

    CommandModel getCommand(String name);

    void loadCommands(Object... holders);

    void registerCommands(CommandModel... models);

    MessageHolder getMessageHolder();

    void build(JDA jda);
}
