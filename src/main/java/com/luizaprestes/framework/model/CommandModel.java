package com.luizaprestes.framework.model;

import lombok.Data;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

@Data
public abstract class CommandModel {

    private final String name;
    private final String[] aliases;

    private final Permission[] permissions;
    private final long role;

    public abstract void onCommand(final Message context, final String[] args);

}
