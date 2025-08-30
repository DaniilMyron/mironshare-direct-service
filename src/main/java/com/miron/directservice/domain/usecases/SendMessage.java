package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.domain.entity.PersonalChat;

public interface SendMessage {
    Message execute(PersonalChat chat, String messageText, String username);
    Message execute(GroupChat chat, String messageText, String username);
}
