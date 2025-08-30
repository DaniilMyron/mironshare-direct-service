package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;

import java.util.UUID;

public interface DeleteMessage {
    void execute(PersonalChat chat, UUID messageId, String redactedText);
    void execute(GroupChat chat, UUID messageId, String redactedText);
}
