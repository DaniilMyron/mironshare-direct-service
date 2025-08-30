package com.miron.directservice.domain.usecases.personalChat;

import com.miron.directservice.domain.exception.UserNotFoundException;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.usecases.CreatePersonalChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.UserRepository;
import com.miron.directservice.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DomainUseCase
@Slf4j
@AllArgsConstructor
public class CreatePersonalChatUseCase implements CreatePersonalChat {
    private final ChatRepository<PersonalChat> personalChatRepository;
    private final UserRepository userRepository;

    @Override
    public PersonalChat execute(User userReceiver, String username) {
        User sender = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("user not found", username));
        User receiver = userRepository.findByUsername(userReceiver.getUsername()).orElseThrow(() -> new UserNotFoundException("user not found", userReceiver.getUsername()));

        if(personalChatRepository.findByUser(sender)
                .removeAll(personalChatRepository.findByUser(receiver))) {
            throw new IllegalStateException("Cannot create chat that already exists");
        }
        return personalChatRepository.save(new PersonalChat(sender, receiver));
    }
}
