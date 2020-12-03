package com.luizaprestes.command.client.impl;

import com.luizaprestes.command.model.CommandModel;
import com.luizaprestes.command.CommandContext;

public interface CommandListener {

    default void onCommand(CommandContext event, CommandModel command) {}
}
