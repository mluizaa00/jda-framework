package com.celeste.command.client.impl;

import com.celeste.command.model.CommandModel;
import com.celeste.command.client.CommandListener;

import java.util.List;

public interface CommandFrameImpl {

    String getPrefix();

    void addCommand(CommandModel command);

    void addCommands(CommandModel... commands);

    List<CommandModel> getCommands();

    CommandListener getListener();

    void setListener(CommandListener listener);
}
