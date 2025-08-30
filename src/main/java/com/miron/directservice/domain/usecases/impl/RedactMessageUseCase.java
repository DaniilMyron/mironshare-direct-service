package com.miron.directservice.domain.usecases.impl;

import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.usecases.RedactMessage;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.domain.service.MessageBasicService;
import com.miron.directservice.domain.spi.ChatRepository;

import java.util.UUID;

@DomainUseCase
public class RedactMessageUseCase implements RedactMessage {
    private final ChatRepository<PersonalChat> personalChatRepository;
    private final ChatRepository<GroupChat> groupChatRepository;
    private final MessageBasicService messageBasicService;

    public RedactMessageUseCase(ChatRepository<PersonalChat> personalChatRepository, ChatRepository<GroupChat> groupChatRepository, MessageBasicService messageBasicService) {
        this.personalChatRepository = personalChatRepository;
        this.groupChatRepository = groupChatRepository;
        this.messageBasicService = messageBasicService;
    }

    @Override
    public Message execute(PersonalChat chat, UUID messageId, String messageText) {
        var message = messageBasicService.findById(messageId);
        message = messageBasicService.redactMessage(message, messageText);
        personalChatRepository.save(chat.redactMessage(message));
        return message;
    }

    @Override
    public Message execute(GroupChat chat, UUID messageId, String messageText) {
        var message = messageBasicService.findById(messageId);
        message = messageBasicService.redactMessage(message, messageText);
        groupChatRepository.save(chat.redactMessage(message));
        return message;
    }
}
