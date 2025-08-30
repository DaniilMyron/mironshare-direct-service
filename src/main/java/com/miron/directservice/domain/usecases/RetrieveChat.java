package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.Chat;

import java.util.UUID;

public interface RetrieveChat {
    Chat retrieveChat(UUID chatId);
}
