package com.miron.directservice.domain.api;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface ChatBasicService {
    PersonalChat createPersonalChat(User user, String username);
    GroupChat createGroupChat(String chatName, String username);
    List<Message> findMessages(UUID chatId, String messageText);
    List<Message> retrieveMessages(UUID id);
    Message sendMessage(UUID chatId, String messageText, String username);
    void clearChat(UUID chatId);
    void deleteMessage(UUID messageId, String redactedText, UUID chatId);
    Message redactMessage(UUID chatId, UUID messageId, String redactedText);
    List<Chat> getUserChats(String username);
}
