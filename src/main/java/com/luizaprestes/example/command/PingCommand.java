package com.luizaprestes.example.command;

import com.luizaprestes.framework.command.annotation.Command;
import com.luizaprestes.framework.utils.EmbedCreator;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.Arrays;

public class PingCommand {

    @Command(
      name = "ping"
    )
    public void handlePingCommand(Message context, String[] args) {
        final TextChannel channel = context.getTextChannel();

        channel.sendMessage(new EmbedCreator("Ping")
          .description("Pong!")
          .color(Color.ORANGE)
          .build()
        ).queue();
    }
}
