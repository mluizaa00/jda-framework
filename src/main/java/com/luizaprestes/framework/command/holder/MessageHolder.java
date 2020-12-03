package com.luizaprestes.framework.command.holder;

import lombok.Data;

import java.util.function.Consumer;

@Data
public class MessageHolder {

    private String withoutPerm;
    private String withoutRoles;
    private String invalidArgs;

    public void setupDefault() {
        this.withoutPerm = "You don't have enough permission to do that.";
        this.withoutRoles = "You don't have the roles for this command.";
        this.invalidArgs = "Incorrect usage! Use: {usage}";
    }

    public MessageHolder acceptHolder(Consumer<MessageHolder> consumer) {
        consumer.accept(this);
        return this;
    }
}
