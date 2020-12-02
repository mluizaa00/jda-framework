package com.celeste.command.client;

import com.celeste.command.client.impl.CommandFrameImpl;
import com.celeste.command.model.CommandModel;
import com.celeste.command.client.impl.CommandClientImpl;
import com.celeste.command.holder.MessageHolder;
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

    public CommandFrameImpl build() {
        CommandFrameImpl client = new CommandClientImpl(
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
