package com.celeste.command.client;

import com.celeste.command.model.CommandModel;
import com.celeste.command.CommandContext;

public interface CommandListener {

    default void onCommand(CommandContext event, CommandModel command) {}
}
