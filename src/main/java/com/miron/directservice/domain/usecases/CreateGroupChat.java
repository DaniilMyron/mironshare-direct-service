package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.GroupChat;

public interface CreateGroupChat {
    GroupChat execute(String chatName, String username);

}
