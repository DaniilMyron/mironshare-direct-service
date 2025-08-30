package com.miron.directservice.infrastructure.controller.model;

import com.miron.directservice.domain.entity.PersonalChat;

import java.util.UUID;

public record PersonalChatResponse(UUID senderId, UUID receiverId) {
    public PersonalChatResponse(PersonalChat personalChat) {
        this(personalChat.getSenderId(), personalChat.getReceiverId());
    }
}
