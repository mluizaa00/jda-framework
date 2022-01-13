package com.luizaprestes.framework.model.message;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class MessageHolder {
    
    private final Map<MessageType, String> messageMap;

    public MessageHolder() {
        this.messageMap = new HashMap<>();
        for (MessageType value : MessageType.values()) {
            messageMap.put(value, value.getDefaultMessage());
        }
    }

    public String getMessage(final MessageType type) {
        return messageMap.get(type);
    }

    public void setMessage(final MessageType type, final String message) {
        messageMap.put(type, message);
    }

}
