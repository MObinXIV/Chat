package com.mobi.whatsappcloneapi.chat;

public class ChatResponse {
    private String id ;
    private String name;
    private Long unreadCount;
    private String lastMessage;
    private String lastMessageTime;
    private boolean isRecipientOnline;
    private String senderId;
    private String receiverId;

    public ChatResponse(String id, String name, Long unreadCount, String lastMessage, String lastMessageTime, boolean isRecipientOnline, String senderId, String receiverId) {
        this.id = id;
        this.name = name;
        this.unreadCount = unreadCount;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.isRecipientOnline = isRecipientOnline;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public ChatResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public boolean isRecipientOnline(boolean userOnline) {
        return isRecipientOnline;
    }

    public void setRecipientOnline(boolean recipientOnline) {
        isRecipientOnline = recipientOnline;
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
}
