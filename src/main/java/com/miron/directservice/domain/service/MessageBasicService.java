package com.miron.directservice.domain.service;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.Message;

import java.util.UUID;

public interface MessageBasicService {
    Message createMessage(Message message);
    void deleteMessages(Chat chat);
    void deleteMessage(Message message);
    Message redactMessage(Message message, String redactedText);
    Message findById(UUID id);
}
