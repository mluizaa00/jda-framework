package com.luizaprestes.example.command;

import com.luizaprestes.framework.command.frame.impl.CommandContext;
import com.luizaprestes.framework.command.annotation.Command;
import com.luizaprestes.framework.command.annotation.CommandInfo;
import com.luizaprestes.framework.utils.EmbedCreator;
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
        final TextChannel channel = context.getEventSource().getChannel();

        channel.sendMessage(new EmbedCreator("Ping")
          .description("Pong!")
          .color(Color.RED)
          .build()
        ).queue();
    }
}
