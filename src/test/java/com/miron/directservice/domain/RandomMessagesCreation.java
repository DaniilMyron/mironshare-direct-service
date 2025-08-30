package com.miron.directservice.domain;

import com.miron.directservice.domain.entity.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class RandomMessagesCreation {
    public static List<Message> createRandomMessages(int numberOfMessages) {
        List<Message> messages = new ArrayList<Message>();
        for (int i = 0; i < numberOfMessages; i++) {
            Message message = Message.Builder()
                    .setId()
                    .setText(createRandomString(10))
                    .setSenderId(UUID.randomUUID())
                    .build();
            messages.add(message);
        }
        return messages;
    }

    private static String createRandomString(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = (char) (Random.from(RandomGenerator.getDefault()).nextInt(65, 122));
        }
        return new String(chars);
    }
}
