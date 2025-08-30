package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;

public interface ClearChat {
    void execute(PersonalChat personalChat);
    void execute(GroupChat groupChat);
}
