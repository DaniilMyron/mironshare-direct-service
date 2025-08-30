package com.miron.directservice.domain.repository;

import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainRepository;
import com.miron.directservice.domain.entity.User;

import java.util.*;

@DomainRepository
public class PersonalChatInMemoryRepository implements ChatRepository<PersonalChat> {
    private final Map<UUID, PersonalChat> chats = new HashMap<>();

    @Override
    public PersonalChat save(PersonalChat chat) {
        chats.put(chat.getId(), chat);
        return chats.get(chat.getId());
    }

    @Override
    public PersonalChat findById(UUID id) {
        return chats.get(id);
    }

    @Override
    public List<PersonalChat> findAll() {
        return new ArrayList<>(chats.values());
    }

    @Override
    public void deleteById(UUID id) {
        chats.remove(id);
    }

    @Override
    public List<PersonalChat> findByUser(User user) {
        List<PersonalChat> result = new ArrayList<>();
        for(PersonalChat chat : chats.values()) {
            if(chat.getSenderId().equals(user.getValue()) || chat.getReceiverId().equals(user.getValue())) {
                result.add(chat);
            }
        }
        return result;
    }
}
