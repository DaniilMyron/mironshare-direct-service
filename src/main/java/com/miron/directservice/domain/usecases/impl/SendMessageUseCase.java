package com.miron.directservice.domain.usecases.impl;

import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.usecases.SendMessage;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.domain.service.MessageBasicService;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.UserRepository;

import java.util.NoSuchElementException;

@DomainUseCase
public class SendMessageUseCase implements SendMessage {
    private final ChatRepository<PersonalChat> personalChatRepository;
    private final ChatRepository<GroupChat> groupChatRepository;
    private final UserRepository userRepository;
    private final MessageBasicService messageBasicService;

    public SendMessageUseCase(ChatRepository<PersonalChat> personalChatRepository, ChatRepository<GroupChat> groupChatRepository, UserRepository userRepository, MessageBasicService messageBasicService) {
        this.personalChatRepository = personalChatRepository;
        this.groupChatRepository = groupChatRepository;
        this.userRepository = userRepository;
        this.messageBasicService = messageBasicService;
    }

    @Override
    public Message execute(PersonalChat personalChat, String messageText, String username) {
        var user = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        var message = messageBasicService.createMessage(new Message(messageText, user.getValue()));
        personalChatRepository.save(personalChat.addMessage(message));
        return message;
    }

    @Override
    public Message execute(GroupChat groupChat, String messageText, String username) {
        var user = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        var message = messageBasicService.createMessage(new Message(messageText, user.getValue()));
        groupChatRepository.save(groupChat.addMessage(message));
        return message;
    }
}
