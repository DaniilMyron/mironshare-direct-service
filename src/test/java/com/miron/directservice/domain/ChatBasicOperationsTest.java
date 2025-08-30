package com.miron.directservice.domain;

import com.miron.directservice.domain.api.ChatBasicService;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.repository.GroupChatInMemoryRepository;
import com.miron.directservice.domain.repository.MessagesInMemoryRepository;
import com.miron.directservice.domain.repository.PersonalChatInMemoryRepository;
import com.miron.directservice.domain.repository.UserRepositoryInMemory;
import com.miron.directservice.domain.service.ChatManagementService;
import com.miron.directservice.domain.service.MessageBasicService;
import com.miron.directservice.domain.service.MessageService;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.MessageRepository;
import com.miron.directservice.domain.entity.Message;
import com.miron.directservice.domain.spi.UserRepository;
import com.miron.directservice.domain.usecases.impl.*;
import com.miron.directservice.domain.valueObject.ChatName;
import com.miron.directservice.domain.valueObject.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChatBasicOperationsTest {
    static final String template = "{\"accountName\":\"danik\",\"accountPicture\":null,\"userAge\":null,\"userGender\":null,\"userAbout\":null}";
    List<Message> messages = new ArrayList<>();
    ChatBasicService chatBasicService;
    MessageBasicService chatMessageBasicService;
    ChatRepository<PersonalChat> personalChatRepository = new PersonalChatInMemoryRepository();
    ChatRepository<GroupChat> groupChatRepository = new GroupChatInMemoryRepository();
    MessageRepository messageRepository = new MessagesInMemoryRepository();
    MessageBasicService messageBasicService = new MessageService(messageRepository);
    UserRepository userRepository = new UserRepositoryInMemory();

    PersonalChat personalChat;
    GroupChat groupChat;

    @BeforeEach
    public void setUp() {
        messages = RandomMessagesCreation.createRandomMessages(10);
        chatMessageBasicService = new MessageService(messageRepository);
        chatBasicService = new ChatManagementService(new DeleteMessageUseCase(groupChatRepository, personalChatRepository, messageBasicService),
                new CreateChatUseCase(personalChatRepository, groupChatRepository, userRepository),
                new RetrieveAnyChatUseCase(groupChatRepository, personalChatRepository),
                new SendMessageUseCase(personalChatRepository, groupChatRepository, userRepository, messageBasicService),
                new RedactMessageUseCase(personalChatRepository, groupChatRepository, messageBasicService),
                new ClearChatUseCase(personalChatRepository, groupChatRepository, messageBasicService),
                new RetrieveAllChatsUseCase(personalChatRepository, groupChatRepository, userRepository));
        personalChat = chatBasicService.createPersonalChat(template, "miron2");
    }

    @Test
    public void testDeletingFunctionality() {

    }
}
