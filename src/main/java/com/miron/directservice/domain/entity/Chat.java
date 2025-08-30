package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.ChatId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Chat implements ChatBasicLogic{
    private ChatId id;
    protected List<Message> messages;

    public Chat() {
        this.id = new ChatId();
        this.messages = new ArrayList<>();
    }

    public abstract Chat addMessage(Message message);

    public abstract Chat clearChat();

    public abstract Chat deleteMessage(Message message);

    public abstract Chat redactMessage(Message message);

    public UUID getId() {
        return id.getValue();
    }

    public List<Message> getMessages() {
        return messages;
    }
}
