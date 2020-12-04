package com.luizaprestes.framework.command.message;

import java.util.HashMap;
import java.util.Map;

public class MessageHolder {
    
    private final Map<MessageType, String> messageMap;

    public MessageHolder() {
        this.messageMap = new HashMap<>() {{
            for (MessageType value : MessageType.values()) {
                put(value, value.getDefaultMessage());
            }
        }};
    }

    public MessageHolder(Map<MessageType, String> messageMap) {
        this.messageMap = messageMap;
    }

    public String getMessage(MessageType type) {
        return messageMap.get(type);
    }

    public void setMessage(MessageType type, String message) {
        messageMap.put(type, message);
    }
}
