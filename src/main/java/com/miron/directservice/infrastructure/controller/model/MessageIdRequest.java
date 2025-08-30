package com.miron.directservice.infrastructure.controller.model;

import java.util.UUID;

public record MessageIdRequest(UUID id, String redactedText) {
}
