package com.luizaprestes.command.client;

import com.luizaprestes.command.client.impl.CommandClientImpl;
import com.luizaprestes.command.client.impl.CommandListener;
import com.luizaprestes.command.model.CommandModel;
import com.luizaprestes.command.holder.MessageHolder;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class CommandFrame {

    private String prefix;
    private final LinkedList<CommandModel> commands = new LinkedList<>();
    private CommandListener listener;
    private MessageHolder messageHolder;

    public CommandClientImpl build() {
        CommandClientImpl client = new CommandClient(
          getPrefix(),
          getCommands(),
          getListener(),
          getMessageHolder()
        );

        getMessageHolder().setupDefault();

        if (listener != null) {
            client.setListener(listener);
        }

        return client;
    }

}
