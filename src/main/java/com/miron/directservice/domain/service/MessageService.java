package com.miron.directservice.domain.service;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.spi.MessageRepository;
import com.miron.directservice.domain.springAnnotations.DomainService;
import com.miron.directservice.domain.entity.Message;

import java.util.UUID;


@DomainService
public class MessageService implements MessageBasicService{
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessages(Chat chat) {
        messageRepository.deleteAllChatMessages(chat);
    }

    @Override
    public void deleteMessage(Message message) {
        messageRepository.deleteById(message.getId());
    }

    @Override
    public Message redactMessage(Message message, String redactedText) {
        return messageRepository.save(message.setText(redactedText));
    }

    @Override
    public Message findById(UUID id) {
        return messageRepository.findById(id);
    }
}
