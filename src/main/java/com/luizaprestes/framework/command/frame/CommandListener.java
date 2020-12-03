package com.luizaprestes.framework.command.frame;

import com.luizaprestes.framework.command.model.CommandModel;
import com.luizaprestes.framework.command.frame.impl.CommandContext;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface CommandListener {

   void onCommand(CommandContext event, CommandModel command);

   void onNonCommandMessage(GuildMessageReceivedEvent event);
}
