package com.luizaprestes.example;

import com.luizaprestes.example.command.PingCommand;
import com.luizaprestes.framework.command.CommandFrame;
import com.luizaprestes.framework.command.impl.CommandFrameImpl;
import com.luizaprestes.framework.command.message.MessageType;
import com.luizaprestes.framework.event.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class MainExample {

    public static void main(String[] args) throws LoginException, InterruptedException {
        final JDA jda = JDABuilder.createLight("YOUR_TOKEN_HERE")
          .setAutoReconnect(true)
          .setStatus(OnlineStatus.ONLINE)
          .setActivity(Activity.playing("JDA!"))
          .build();

        jda.awaitReady();

        final EventWaiter waiter = new EventWaiter();
        jda.addEventListener(waiter);

        final CommandFrame frame = new CommandFrameImpl(new String[]{
          "!", "-"
        });


        frame.getMessageHolder().setMessage(MessageType.LACK_PERM_MESSAGE, "Custom message :s");

        frame.loadCommands(new PingCommand());
        frame.build(jda);
    }

}
