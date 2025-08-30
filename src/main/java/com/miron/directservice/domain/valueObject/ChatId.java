package com.miron.directservice.domain.valueObject;

import java.util.UUID;

public class ChatId implements ValueObject<UUID> {
    private UUID id;

    public ChatId() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getValue() {
        return this.id;
    }
}
