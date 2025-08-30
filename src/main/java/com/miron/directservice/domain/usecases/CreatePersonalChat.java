package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.entity.User;

public interface CreatePersonalChat {
    PersonalChat execute(User user, String username);

}
