package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.User;

import java.util.UUID;


public class PersonalChat extends Chat{
    private User sender;
    private User receiver;

    public PersonalChat(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getChatName(String currentUsername) {
        if(currentUsername == null) {
            throw new NullPointerException("currentUsername is null");
        }
        if(sender.getUsername().equals(currentUsername)) {
            return receiver.getUsername();
        } else {
            return sender.getUsername();
        }
    }

    @Override
    public PersonalChat addMessage(Message message) {
        if(message.getSenderId() != sender.getValue() && message.getSenderId() != receiver.getValue()){
            throw new RuntimeException("You are not the part of chat");
        }
        messages.add(message);
        return this;
    }

    @Override
    public PersonalChat clearChat() {
        messages.clear();
        return this;
    }

    @Override
    public PersonalChat deleteMessage(Message message) {
        messages.remove(message);
        return this;
    }

    @Override
    public PersonalChat redactMessage(Message message) {
        messages.set(messages.indexOf(message), message);
        return this;
    }

    public UUID getReceiverId() {
        return receiver.getValue();
    }

    public UUID getSenderId() {
        return sender.getValue();
    }
}
