package com.miron.directservice.domain.usecases.impl;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.usecases.RetrieveChat;
import com.miron.directservice.domain.entity.Chat;

import java.util.UUID;

@DomainUseCase
public class RetrieveAnyChatUseCase implements RetrieveChat {
    private final ChatRepository<GroupChat> groupChatRepository;
    private final ChatRepository<PersonalChat> personalChatRepository;

    public RetrieveAnyChatUseCase(ChatRepository<GroupChat> groupChatRepository, ChatRepository<PersonalChat> personalChatRepository) {
        this.groupChatRepository = groupChatRepository;
        this.personalChatRepository = personalChatRepository;
    }

    @Override
    public Chat retrieveChat(UUID chatId) {
        //TODO: MAKE NORMAL EXCEPTIONS
        return personalChatRepository.findById(chatId).isPresent()
                ? personalChatRepository.findById(chatId).orElseThrow()
                : groupChatRepository.findById(chatId).orElseThrow();
    }
}
