package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;

public interface CreateChat {
    PersonalChat execute(String template, String username);
    GroupChat execute(String template, String chatName, String username);
}
