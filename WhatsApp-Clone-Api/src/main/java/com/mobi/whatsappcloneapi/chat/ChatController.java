package com.mobi.whatsappcloneapi.chat;

import com.mobi.whatsappcloneapi.common.StringResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@Tag(name = "Chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<StringResponse> createChat(@RequestParam(name = "sender-id") String senderId,
                                                     @RequestParam(name = "receiver-id") String receiverId) {
        final String chatId = chatService.createChat(senderId, receiverId);
        return ResponseEntity.ok(new StringResponse(chatId));
    }
    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatsByReceiverId(Authentication authentication) {
       return ResponseEntity.ok(chatService.getChatsByReceiverId(authentication));}
}
