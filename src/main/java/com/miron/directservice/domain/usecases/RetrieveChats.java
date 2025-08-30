package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.Chat;

import java.util.List;

public interface RetrieveChats {
    List<Chat> execute(String username);
}
