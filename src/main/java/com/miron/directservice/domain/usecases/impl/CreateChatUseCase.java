package com.miron.directservice.domain.usecases.impl;

import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.usecases.CreateChat;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.UserRepository;
import com.miron.directservice.domain.valueObject.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@DomainUseCase
public class CreateChatUseCase implements CreateChat {
    private final ChatRepository<PersonalChat> personalChatRepository;
    private final ChatRepository<GroupChat> groupChatRepository;
    private final UserRepository userRepository;

    public CreateChatUseCase(ChatRepository<PersonalChat> personalChatRepository, ChatRepository<GroupChat> groupChatRepository, UserRepository userRepository) {
        this.personalChatRepository = personalChatRepository;
        this.groupChatRepository = groupChatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PersonalChat execute(String template, String username) {
        User sender = userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
        User receiver = convert(template);
        if(userRepository.findByUsername(receiver.getUsername()).isEmpty()) {
            userRepository.save(receiver);
        } else {
            receiver = userRepository.findByUsername(receiver.getUsername()).orElseThrow(IllegalArgumentException::new);
        }

        if(personalChatRepository.findByUser(sender)
                .removeAll(personalChatRepository.findByUser(receiver))) {
            throw new IllegalStateException("Cannot create chat that already exists");
        }
        return personalChatRepository.save(new PersonalChat(sender, receiver));
    }

    @Override
    public GroupChat execute(String template, String chatName, String username) {
        return null; //return groupChatRepository.save(new GroupChat(new ChatName(chatName), chatMembers));
    }

    private User convert(String template) {
        String[] parametersForUser = new String[6];
        template = template.substring(1, template.length() - 1).replace("\"", "");
        var parameters = template.split(",");
        for (int i = 0; i < parameters.length; i++) {
            parametersForUser[i] = parameters[i].substring(parameters[i].indexOf(':') + 1);
        }
        return new User(parametersForUser[0], parametersForUser[1], parametersForUser[2], parametersForUser[4], parametersForUser[5]);
    }
}
