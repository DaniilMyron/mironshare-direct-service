package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.domain.entity.PersonalChat;

import java.util.UUID;

public interface RedactMessage {
    Message execute(PersonalChat chat, UUID messageId, String messageText);
    Message execute(GroupChat chat, UUID messageId, String messageText);
}
