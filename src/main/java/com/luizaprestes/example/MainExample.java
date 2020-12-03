package com.luizaprestes.example;

import com.luizaprestes.command.client.CommandClient;
import com.luizaprestes.command.client.CommandFrame;
import com.luizaprestes.command.client.impl.CommandClientImpl;
import com.luizaprestes.command.holder.MessageHolder;
import com.luizaprestes.database.MySQLDatabase;
import com.luizaprestes.event.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class MainExample {

    private static JDA jda;
    private static final EventWaiter eventWaiter = new EventWaiter();

    public static void main(String[] args) throws LoginException {
        connectMySQL();

        jda = JDABuilder.createLight("token")
          .setAutoReconnect(true)
          .setStatus(OnlineStatus.ONLINE)
          .setActivity(Activity.playing("JDA!"))
          .build();

    }

    public static void buildClient() {
        final CommandFrame frame = new CommandFrame();
        final MessageHolder holder = new MessageHolder();

        holder.setInvalidArgs("Example of invalid args message");
        holder.setWithoutPerm("Example of without permissions message");
        holder.setWithoutRoles("Example of without roles message");
        /*
            DEFAULT MESSAGES FOR THE HOLDER : ENGLISH
         */
        holder.setupDefault();

        frame.setPrefix("-");
        frame.setMessageHolder(holder);

        CommandClientImpl commandClient = frame.build();

        jda.addEventListener(
          eventWaiter,
          commandClient
        );

    }

    public static void connectMySQL() {
        MySQLDatabase mySQL = new MySQLDatabase();
        mySQL.connect(
          "jdbc:mysql://localhost:3306/test",
          "root",
          "");
    }
}
