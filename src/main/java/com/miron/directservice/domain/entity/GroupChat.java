package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.ChatName;

import java.util.ArrayList;
import java.util.List;


public class GroupChat extends Chat {
    private ChatName chatName;
    private List<User> users = new ArrayList<>();

    public GroupChat(ChatName name, User... users) {
        this.chatName = name;
        for (User user : users) {
            this.addUser(user);
        }
    }

    public String getName() {
        return chatName.getValue();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if(user != null && !users.contains(user)) {
            users.add(user);
        }
    }

    @Override
    public GroupChat addMessage(Message message) {
        messages.add(message);
        return this;
    }

    @Override
    public GroupChat clearChat() {
        messages.clear();
        return this;
    }

    @Override
    public GroupChat deleteMessage(Message message) {
        messages.remove(message);
        return this;
    }

    @Override
    public GroupChat redactMessage(Message message) {
        messages.set(messages.indexOf(message), message);
        return this;
    }
}
