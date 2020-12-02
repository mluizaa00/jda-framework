package com.celeste.examples;

import com.celeste.command.CommandContext;
import com.celeste.command.annotation.Command;
import com.celeste.command.annotation.CommandInfo;

@CommandInfo(
  name = "Ping",
  description = "See your ping through this command!",
  category = "utilities"
)
public class PingCommand {

    @Command(
        name = "ping"
    )
    public void handlePingCommand(CommandContext context) {

    }

}
