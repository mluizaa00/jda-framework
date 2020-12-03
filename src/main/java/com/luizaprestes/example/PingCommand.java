package com.luizaprestes.example;

import com.luizaprestes.command.CommandContext;
import com.luizaprestes.command.annotation.Command;
import com.luizaprestes.command.annotation.CommandInfo;
import com.luizaprestes.utils.EmbedCreator;
import net.dv8tion.jda.api.entities.TextChannel;
import java.awt.Color;

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
        final TextChannel channel = context.getEventSource().getTextChannel();

        channel.sendMessage(new EmbedCreator("Ping")
          .description("Pong!")
          .color(Color.RED)
          .build()
        );
    }

    @Command(
      name = "ping.pong"
    )
    public void handlePingPongCommand(CommandContext context) {

    }

}
