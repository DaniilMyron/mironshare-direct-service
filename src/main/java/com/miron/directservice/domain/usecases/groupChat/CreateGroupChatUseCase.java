package com.miron.directservice.domain.usecases.groupChat;

import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.spi.UserRepository;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import com.miron.directservice.domain.usecases.CreateGroupChat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DomainUseCase
@Slf4j
@AllArgsConstructor
public class CreateGroupChatUseCase implements CreateGroupChat {
    private final ChatRepository<GroupChat> groupChatRepository;
    private final UserRepository userRepository;


    @Override
    public GroupChat execute(String chatName, String username) {
        //TODO: implement this
        return null; //return groupChatRepository.save(new GroupChat(new ChatName(chatName), chatMembers));
    }
}
