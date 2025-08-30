package com.miron.directservice.domain.usecases.impl;

import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.usecases.RetrieveChats;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.UserRepository;
import com.miron.directservice.domain.valueObject.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@DomainUseCase
public class RetrieveAllChatsUseCase implements RetrieveChats {
    private final ChatRepository<PersonalChat> personalChatRepository;
    private final ChatRepository<GroupChat> groupChatRepository;
    private final UserRepository userRepository;

    public RetrieveAllChatsUseCase(ChatRepository<PersonalChat> personalChatRepository, ChatRepository<GroupChat> groupChatRepository, UserRepository userRepository) {
        this.personalChatRepository = personalChatRepository;
        this.groupChatRepository = groupChatRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Chat> execute(String username) {
        List<Chat> chats = new ArrayList<>();
        User user = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        chats.addAll(personalChatRepository.findByUser(user));
        chats.addAll(groupChatRepository.findByUser(user));
        return chats;
    }
}
