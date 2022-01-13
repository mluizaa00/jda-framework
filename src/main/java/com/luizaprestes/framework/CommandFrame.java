package com.luizaprestes.framework;

import com.luizaprestes.framework.model.message.MessageHolder;
import com.luizaprestes.framework.model.CommandModel;
import net.dv8tion.jda.api.JDA;

public interface CommandFrame {

    String[] getPrefixes();

    CommandModel getCommand(String name);

    void loadCommands(Object... holders);

    void registerCommands(CommandModel... models);

    MessageHolder getMessageHolder();

    void build(JDA jda);

}
