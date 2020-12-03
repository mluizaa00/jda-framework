package com.luizaprestes.command.client.impl;

import com.luizaprestes.command.model.CommandModel;

import java.util.List;

public interface CommandClientImpl {

    String getPrefix();

    void addCommand(CommandModel command);

    void addCommands(CommandModel... commands);

    List<CommandModel> getCommands();

    CommandListener getListener();

    void setListener(CommandListener listener);
}
