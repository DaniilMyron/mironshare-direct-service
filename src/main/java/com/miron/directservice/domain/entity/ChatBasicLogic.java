package com.miron.directservice.domain.entity;

public interface ChatBasicLogic {
    Chat addMessage(Message message);
    Chat clearChat();
    Chat deleteMessage(Message message);
    Chat redactMessage(Message message);
}
