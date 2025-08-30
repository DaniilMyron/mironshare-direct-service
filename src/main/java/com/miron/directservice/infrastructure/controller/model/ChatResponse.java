package com.miron.directservice.infrastructure.controller.model;

import com.miron.directservice.domain.entity.Chat;

import java.util.UUID;

public record ChatResponse(UUID id) {
    public ChatResponse(Chat chat){
        this(chat.getId());
    }
}
