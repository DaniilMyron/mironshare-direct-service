package com.miron.directservice.domain.valueObject;

import java.util.UUID;

public class MessageID implements ValueObject<UUID> {
    private UUID id;

    public MessageID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getValue() {
        return this.id;
    }
}
