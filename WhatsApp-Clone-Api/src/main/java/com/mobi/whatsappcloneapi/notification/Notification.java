package com.mobi.whatsappcloneapi.notification;

import com.mobi.whatsappcloneapi.message.MessageType;

public class Notification {
    private String chatId;
    private String content;
    private String senderId;
    private String receiverId;
    private String chatName;
    private MessageType messageType;
    private NotificationType type;
    private byte[] media;

    public Notification(String chatId, String content, String senderId, String receiverId, String chatName, MessageType messageType, NotificationType type, byte[] media) {
        this.chatId = chatId;
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.chatName = chatName;
        this.messageType = messageType;
        this.type = type;
        this.media = media;
    }

    public Notification() {
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
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

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }
}
