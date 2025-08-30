package com.miron.directservice.infrastructure.controller.model;

import com.miron.directservice.domain.entity.Message;

import java.util.UUID;

public record MessagesResponse(String text, UUID senderId) {
    public MessagesResponse(Message message) {
        this(message.getText(), message.getSenderId());
    }
}
