package com.miron.directservice.domain.usecases.impl;

import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.usecases.DeleteMessage;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.service.MessageBasicService;
import com.miron.directservice.domain.spi.ChatRepository;

import java.util.UUID;

@DomainUseCase
public class DeleteMessageUseCase implements DeleteMessage {
    private final ChatRepository<GroupChat> groupChatRepository;
    private final ChatRepository<PersonalChat> personalChatRepository;
    private final MessageBasicService messageService;

    public DeleteMessageUseCase(ChatRepository<GroupChat> groupChatRepository, ChatRepository<PersonalChat> personalChatRepository, MessageBasicService messageService) {
        this.groupChatRepository = groupChatRepository;
        this.personalChatRepository = personalChatRepository;
        this.messageService = messageService;
    }

    @Override
    public void execute(PersonalChat personalChat, UUID messageId, String redactedText) {
        var message = personalChat.getMessages()
                .stream()
                .filter(m -> m.getId()
                        .equals(messageId))
                .findFirst()
                .orElse(null);
        messageService.deleteMessage(message);
        personalChatRepository.save(personalChat.deleteMessage(message));
    }

    @Override
    public void execute(GroupChat groupChat, UUID messageId, String redactedText) {
        var message = groupChat.getMessages()
                .stream()
                .filter(m -> m.getId()
                        .equals(messageId))
                .findFirst()
                .orElse(null);
        messageService.deleteMessage(message);
        groupChatRepository.save(groupChat.deleteMessage(message));
    }
}
