package com.miron.directservice.infrastructure.controller;

import com.miron.directservice.domain.api.*;
import com.miron.directservice.infrastructure.utils.UserConverter;
import com.miron.directservice.domain.entity.User;
import com.miron.directservice.infrastructure.controller.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/direct")
public class BasicChatController {
    //TODO DELETE THIS]
    private static final String USERNAME = "MIRON1";
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String PROFILE_URI = "http://localhost:8083/api/v1/profile";
    private final ChatBasicService chatBasicService;
    private final UserConverter userConverter = new UserConverter();

    public BasicChatController(ChatBasicService chatBasicService) {
        this.chatBasicService = chatBasicService;
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> retrieveUserChats() {
        var response = chatBasicService.getUserChats(USERNAME)
                .stream()
                .map(ChatResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChatResponse> deleteChatMessages(@PathVariable("id") UUID id) {
        chatBasicService.clearChat(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<MessagesResponse>> retrieveChatMessages(@PathVariable("id") UUID id) {
        var response = chatBasicService.retrieveMessages(id)
                .stream()
                .map(MessagesResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/{id}/find-message")
    public ResponseEntity<List<MessagesResponse>> findMessages(@PathVariable("id") UUID id,
                                                               @RequestBody MessageTextRequest messageTextRequest) {

        var response = chatBasicService.findMessages(id, messageTextRequest.text())
                .stream()
                .map(MessagesResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(response);
    }

    @PatchMapping("/{id}/redact")
    public ResponseEntity<MessagesResponse> redactMessage(@PathVariable("id") UUID id,
                                                          @RequestBody MessageIdRequest messageIdRequest) {
        var response = chatBasicService.redactMessage(id, messageIdRequest.id(), messageIdRequest.redactedText());
        return ResponseEntity.ok()
                .body(new MessagesResponse(response));
    }

    @PostMapping("/{id}/send")
    public ResponseEntity<MessagesResponse> sendMessage(@PathVariable("id") UUID id,
                                                        @RequestBody MessageTextRequest messageTextRequest) {
        var response = chatBasicService.sendMessage(id, messageTextRequest.text(), USERNAME);
        return ResponseEntity.ok()
                .body(new MessagesResponse(response));
    }

    @PostMapping("/create-personal-chat/{id}")
    public ResponseEntity<PersonalChatResponse> createPersonalChat(@PathVariable("id") UUID userId) {
        String template = restTemplate.getForObject(PROFILE_URI + "/%s".formatted(userId), String.class);
        User user = userConverter.apply(template);
        var response = chatBasicService.createPersonalChat(user, USERNAME);
        return ResponseEntity.ok()
                .body(new PersonalChatResponse(response));
    }
    /*
    @PostMapping("/create-group-chat")
    public ResponseEntity<PersonalChatResponse> createGroupChat(@RequestBody ) {
        String template = restTemplate.getForObject(PROFILE_URI + "/%s".formatted(userId), String.class);
        String requestJson = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String template = restTemplate.getForObject(PROFILE_URI + "/retrieve-profiles", String.class, params);
        var response = chatBasicService.createPersonalChat(template, USERNAME);
        return ResponseEntity.ok()
                .body(new PersonalChatResponse(response));
    }
     */

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") UUID id,
                                              @RequestBody MessageIdRequest messageIdRequest) {
        chatBasicService.deleteMessage(messageIdRequest.id(), messageIdRequest.redactedText(), id);
        return ResponseEntity.noContent()
                .build();
    }
}
