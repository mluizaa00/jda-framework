package com.luizaprestes.example.command;

import com.luizaprestes.framework.command.annotation.Command;
import com.luizaprestes.framework.utils.EmbedCreator;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class PingCommand {

    @Command(
      name = "ping"
    )
    public void handlePingCommand(final Message message, String[] args) {
        final TextChannel channel = message.getTextChannel();

        channel.sendMessage(new EmbedCreator("Ping")
          .description("Pong!")
          .color(Color.RED)
          .build()
        ).queue();
    }
}
