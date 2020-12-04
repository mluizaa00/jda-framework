package com.luizaprestes.framework.command.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {

    LACK_PERM_MESSAGE("You don't have sufficient permissions to do this.");

    private final String defaultMessage;
}
