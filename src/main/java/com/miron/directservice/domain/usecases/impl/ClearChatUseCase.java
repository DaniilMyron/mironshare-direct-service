package com.miron.directservice.domain.usecases.impl;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.service.MessageBasicService;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.usecases.ClearChat;

@DomainUseCase
public class ClearChatUseCase implements ClearChat {
    private final ChatRepository<PersonalChat> personalChatRepository;
    private final ChatRepository<GroupChat> groupChatRepository;
    private final MessageBasicService messageBasicService;

    public ClearChatUseCase(ChatRepository<PersonalChat> personalChatRepository, ChatRepository<GroupChat> groupChatRepository, MessageBasicService messageBasicService) {
        this.personalChatRepository = personalChatRepository;
        this.groupChatRepository = groupChatRepository;
        this.messageBasicService = messageBasicService;
    }

    @Override
    public void execute(PersonalChat personalChat) {
        messageBasicService.deleteMessages(personalChat);
        personalChatRepository.save(personalChat.clearChat());
    }

    @Override
    public void execute(GroupChat groupChat) {
        messageBasicService.deleteMessages(groupChat);
        groupChatRepository.save(groupChat.clearChat());
    }
}
