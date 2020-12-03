package com.luizaprestes.example;

import com.luizaprestes.example.service.UserService;
import com.luizaprestes.framework.command.CommandFrame;
import com.luizaprestes.framework.command.frame.CommandClient;
import com.luizaprestes.framework.command.holder.MessageHolder;
import com.luizaprestes.framework.database.MySQLDatabase;
import com.luizaprestes.framework.event.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class MainExample {

    public static void main(String[] args) throws LoginException {
        final JDA jda = JDABuilder.createLight("NzMxOTM2OTc0ODQ3NjA2ODE2.XwtTbQ.cGKG0bgPGhzj8tmPhDMt9k9tSpM")
          .setAutoReconnect(true)
          .setStatus(OnlineStatus.ONLINE)
          .setActivity(Activity.playing("JDA!"))
          .build();

        final EventWaiter waiter = new EventWaiter();
        jda.addEventListener(waiter);

        buildClient(jda);
    }

    public static void buildClient(final JDA jda) {
        final CommandFrame frame = new CommandFrame("-", null);
        frame.getMessageHolder().acceptHolder(holder -> {
           holder.setInvalidArgs("MEU CU");
           holder.setWithoutPerm("VAI SE FUDER");
           holder.setWithoutRoles("ROLA");
        });

        frame.loadCommands(MainExample.class);

        CommandClient commandClient = frame.build();
        commandClient.register(jda);
    }
}
