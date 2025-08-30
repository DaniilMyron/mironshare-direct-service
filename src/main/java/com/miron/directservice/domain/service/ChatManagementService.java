package com.miron.directservice.domain.service;

import com.miron.directservice.domain.api.*;
import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.domain.springAnnotations.DomainService;
import com.miron.directservice.domain.usecases.*;

import java.util.List;
import java.util.UUID;

@DomainService
public class ChatManagementService implements ChatBasicService {
    private final DeleteMessage deleteMessageCommand;
    private final CreateChat createChatCommand;
    private final RetrieveChat retrieveChatCommand;
    private final SendMessage sendMessageCommand;
    private final RedactMessage redactMessageCommand;
    private final ClearChat clearChatCommand;
    private final RetrieveChats retrieveChatsCommand;

    public ChatManagementService(DeleteMessage deleteMessageCommand, CreateChat createChatCommand, RetrieveChat retrieveChatCommand, SendMessage sendMessageCommand, RedactMessage redactMessageCommand, ClearChat clearChatCommand, RetrieveChats retrieveChatsCommand) {
        this.deleteMessageCommand = deleteMessageCommand;
        this.createChatCommand = createChatCommand;
        this.retrieveChatCommand = retrieveChatCommand;
        this.sendMessageCommand = sendMessageCommand;
        this.redactMessageCommand = redactMessageCommand;
        this.clearChatCommand = clearChatCommand;
        this.retrieveChatsCommand = retrieveChatsCommand;
    }

    @Override
    public PersonalChat createPersonalChat(String template, String username) {
        return createChatCommand.execute(template, username);
    }

    @Override
    public GroupChat createGroupChat(String template, String chatName, String username) {
        return createChatCommand.execute(template, chatName, username);
    }

    @Override
    public List<Message> findMessages(UUID chatId, String messageText) {
        Chat chat = retrieveChat(chatId);
        return chat.getMessages()
                .stream()
                .filter(messageValue -> messageValue.getText()
                        .toLowerCase()
                        .contains(messageText
                                .toLowerCase()))
                .toList();
    }

    @Override
    public List<Message> retrieveMessages(UUID id) {
        Chat chat = retrieveChat(id);
        return chat.getMessages();
    }

    @Override
    public Message sendMessage(UUID chatId, String messageText, String username) {
        Chat chat = retrieveChat(chatId);
        Message createdMessage = null;
        if(chat instanceof PersonalChat) {
            createdMessage = sendMessageCommand.execute((PersonalChat) chat, messageText, username);
        } else if(chat instanceof GroupChat) {
            createdMessage = sendMessageCommand.execute((GroupChat) chat, messageText, username);
        }
        return createdMessage;
    }

    @Override
    public void clearChat(UUID chatId) {
        Chat chat = retrieveChat(chatId);
        if(chat instanceof PersonalChat) {
            clearChatCommand.execute((PersonalChat) chat);
        } else if(chat instanceof GroupChat) {
            clearChatCommand.execute((GroupChat) chat);
        }
    }

    @Override
    public void deleteMessage(UUID messageId, String redactedText, UUID chatId) {
        Chat chat = retrieveChat(chatId);
        if(chat instanceof PersonalChat) {
            deleteMessageCommand.execute((PersonalChat) chat, messageId, redactedText);
        } else if(chat instanceof GroupChat) {
            deleteMessageCommand.execute((GroupChat) chat, messageId, redactedText);
        }
    }

    @Override
    public Message redactMessage(UUID chatId, UUID messageId, String redactedText) {
        Chat chat = retrieveChat(chatId);
        Message redactedMessage = null;
        if(chat instanceof PersonalChat) {
            redactedMessage = redactMessageCommand.execute((PersonalChat)chat, messageId, redactedText);
        } else if(chat instanceof GroupChat) {
            redactedMessage = redactMessageCommand.execute((GroupChat)chat, messageId, redactedText);
        }
        return redactedMessage;
    }

    @Override
    public List<Chat> getUserChats(String username) {
        return retrieveChatsCommand.execute(username);
    }

    private Chat retrieveChat(UUID chatId){
        return retrieveChatCommand.retrieveChat(chatId);
    }
}
