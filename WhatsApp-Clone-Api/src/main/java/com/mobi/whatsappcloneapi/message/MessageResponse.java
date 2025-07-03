package com.mobi.whatsappcloneapi.message;

import java.time.LocalDateTime;

public class MessageResponse {
    private Long messageId;
    private String content;
    private MessageType type;
    private String senderId;
    private MessageState state;
    private String receiverId;
    private LocalDateTime createdAt;
    private byte[] media;

    public MessageResponse(Long messageId, String content, MessageType type, String senderId, MessageState state, String receiverId, LocalDateTime createdAt, byte[] media) {
        this.messageId = messageId;
        this.content = content;
        this.type = type;
        this.senderId = senderId;
        this.state = state;
        this.receiverId = receiverId;
        this.createdAt = createdAt;
        this.media = media;
    }

    public MessageResponse() {
    }


    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public MessageState getState() {
        return state;
    }

    public void setState(MessageState state) {
        this.state = state;
    }
}
