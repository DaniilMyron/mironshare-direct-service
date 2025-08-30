package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.MessageID;

import java.util.UUID;

public class Message {
    private MessageID messageId;
    private String text;
    private UUID senderId;

    public Message() {
    }

    public Message(String text, UUID senderId) {
        this.messageId = new MessageID();
        if(!text.isBlank() && !text.isEmpty())
            this.text = text;
        else
            throw new NullPointerException("name is blank or empty");

        this.senderId = senderId;
    }

    public String getText() {
        return this.text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public UUID getId() {
        return messageId.getValue();
    }

    public UUID getSenderId() {
        return senderId;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Message) {
            Message message = (Message) object;
            return this.messageId.equals(message.messageId);
        }
        return false;
    }

    public static Builder Builder() {
        return new Message().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setId() {
            Message.this.messageId = new MessageID();
            return this;
        }

        public Builder setText(String text) {
            Message.this.text = text;
            return this;
        }

        public Builder setSenderId(UUID senderId) {
            Message.this.senderId = senderId;
            return this;
        }

        public Message build() {
            return Message.this;
        }

    }
}
