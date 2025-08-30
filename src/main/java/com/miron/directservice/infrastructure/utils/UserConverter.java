package com.miron.directservice.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miron.directservice.domain.entity.User;
import com.miron.directservice.infrastructure.entity.UserEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
public class UserConverter implements Function<String, User> {
    private final ObjectMapper mapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public User apply(String template) {
        log.info("UserConverted template: {}", template);

        UserEntity person = mapper.readValue(template, UserEntity.class);
        log.info("Person: {}", person.getUserInfo());

        return person;
    }
}
