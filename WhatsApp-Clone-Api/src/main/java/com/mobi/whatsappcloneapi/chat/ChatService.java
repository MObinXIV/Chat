package com.mobi.whatsappcloneapi.chat;

import com.mobi.whatsappcloneapi.user.User;
import com.mobi.whatsappcloneapi.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    private final UserRepository userRepository;
    public ChatService(ChatRepository chatRepository, ChatMapper chatMapper, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
        this.userRepository = userRepository;
    }

    public String createChat(String senderId, String receiverId) {
        Optional<Chat> existingChat = chatRepository.findChatByReceiverAndSender(senderId,receiverId);
        if(existingChat.isPresent()) {
            return existingChat.get().getId();
        }
        User sender = userRepository.findByPublicId(senderId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + senderId + " not found"));
        User receiver = userRepository.findByPublicId(receiverId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + receiverId + " not found"));

        // create a new chat
        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setRecipient(receiver);
        Chat savedChat = chatRepository.save(chat);
        return savedChat.getId();
    }

    @Transactional(readOnly = true)
    public List<ChatResponse> getChatsByReceiverId(Authentication currentUser){
        final String userId = currentUser.getName();
        return chatRepository.findChatsBySenderId(userId)
                .stream()
                .map(c-> chatMapper.toChatResponse(c,userId))
                .toList();
    }

}
