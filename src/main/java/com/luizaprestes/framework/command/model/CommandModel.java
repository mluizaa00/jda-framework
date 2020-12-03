package com.luizaprestes.framework.command.model;

import com.luizaprestes.framework.command.frame.impl.CommandContext;
import lombok.Data;
import net.dv8tion.jda.api.Permission;

import java.lang.reflect.InvocationTargetException;

@Data
public abstract class CommandModel {

    private String name;
    private String[] aliases;

    private String requiredRole;
    private Permission[] userPermissions;

    public CommandModel(String name, String[] aliases, String requiredRole, Permission[] userPermissions) {
    }

    public abstract void execute(CommandContext context, String[] args) throws InvocationTargetException, IllegalAccessException;
}
