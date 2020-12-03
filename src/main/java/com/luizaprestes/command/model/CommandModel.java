package com.luizaprestes.command.model;

import lombok.Data;
import net.dv8tion.jda.api.Permission;

@Data
public class CommandModel {

    private String name;
    private String[] aliases;

    private String requiredRole;
    private Permission[] userPermissions;

}
