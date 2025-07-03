package com.mobi.whatsappcloneapi.message;

public class MessageRequest {
    private String content;
    private String senderId;
    private String receiverId;
    private MessageType type;
    private String chatId;


    public MessageRequest(String content, String senderId, String receiverId, MessageType type, String chatId) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.type = type;
        this.chatId = chatId;
    }

    public MessageRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
