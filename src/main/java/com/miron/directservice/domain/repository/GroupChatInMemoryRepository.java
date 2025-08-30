package com.miron.directservice.domain.repository;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainRepository;
import com.miron.directservice.domain.valueObject.User;

import java.util.*;

@DomainRepository
public class GroupChatInMemoryRepository implements ChatRepository<GroupChat> {
    private final Map<UUID, GroupChat> chats = new HashMap<>();

    @Override
    public GroupChat save(GroupChat chat) {
        chats.put(chat.getId(), chat);
        return chats.get(chat.getId());
    }

    @Override
    public GroupChat findById(UUID id) {
        return chats.get(id);
    }

    @Override
    public List<GroupChat> findAll() {
        return new ArrayList<>(chats.values());
    }

    @Override
    public void deleteById(UUID id) {
        chats.remove(id);
    }

    @Override
    public List<GroupChat> findByUser(User user) {
        List<GroupChat> result = new ArrayList<>();
        for(GroupChat chat : chats.values()) {
            if(chat.getUsers().contains(user)) {
                result.add(chat);
            }
        }
        return result;
    }
}
