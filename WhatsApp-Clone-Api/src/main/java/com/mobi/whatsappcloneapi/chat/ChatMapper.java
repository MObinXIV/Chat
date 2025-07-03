package com.mobi.whatsappcloneapi.chat;


import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public ChatResponse toChatResponse(Chat chat, String senderId) {
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setId(chat.getId());
       chatResponse.setName(chat.getChatName(senderId));
       chatResponse.setUnreadCount(chat.getUnreadMessages(senderId));
        chatResponse.setLastMessage(chat.getLastMessage());
        chatResponse.isRecipientOnline(chat.getRecipient().isUserOnline());
        chatResponse.setSenderId(chat.getSender().getId());
        chatResponse.setReceiverId(chat.getRecipient().getId());
        return chatResponse;
    }
}
