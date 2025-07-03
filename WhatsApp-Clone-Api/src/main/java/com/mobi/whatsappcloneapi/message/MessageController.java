package com.mobi.whatsappcloneapi.message;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@Tag(name = "Message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessage(MessageRequest messageRequest) {
         messageService.saveMessage(messageRequest);
    }

    @PostMapping(value = "/upload-media", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadMedia(@RequestParam(name = "chat-id")String chatId,
                            @Parameter
                            @RequestParam(name = "file") MultipartFile file,
                            Authentication authentication
    ) {
        messageService.uploadMediaMessage(chatId, file, authentication);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setMessageToSeen(@RequestParam(name = "chat-id")String chatId,
                                 Authentication authentication) {
        messageService.setMessageToSeen(chatId, authentication);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageResponse>> getMessages(
            @PathVariable("chat-id") String chatId) {
        return ResponseEntity.ok(messageService.findChatMessages(chatId));
    }
}
