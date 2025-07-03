package com.mobi.whatsappcloneapi.message;

import com.mobi.whatsappcloneapi.chat.Chat;
import com.mobi.whatsappcloneapi.common.BaseAuditingEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Message")
@Table(name = "messages")
@NamedQuery(
        name = MessageConstants.FIND_MESSAGES_BY_CHAT_ID,
        query = "SELECT m FROM Message m WHERE m.chat.id = :chatId ORDER BY m.createdDate"
)
@NamedQuery(
        name = MessageConstants.SET_MESSAGES_TO_SEEN_BY_CHAT,
        query = "UPDATE Message SET state = :newState WHERE chat.id = :chatId"
)
public class Message extends BaseAuditingEntity {

    @Id
    @SequenceGenerator(name = "msg_seq", sequenceName = "msg_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "msg_seq")
    @Column(name = "id")
    private Long id;
    @Column( name = "content",columnDefinition = "TEXT")
    private String content;
    @Enumerated(EnumType.STRING)
    private MessageState state;
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @Column(name = "sender_id",nullable = false)
    private String senderId;
    @Column(name = "receiver_id",nullable = false)
    private String receiverId;

    private String mediaFilePath;


    public Message() {
        super();
    }

    public Message(LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long id, String content, MessageState state, MessageType type, Chat chat, String senderId, String receiverId, String mediaFilePath) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.content = content;
        this.state = state;
        this.type = type;
        this.chat = chat;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.mediaFilePath = mediaFilePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
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

    public MessageState getState() {
        return state;
    }

    public void setState(MessageState state) {
        this.state = state;
    }

    public String getMediaFilePath() {
        return mediaFilePath;
    }

    public void setMediaFilePath(String mediaFilePath) {
        this.mediaFilePath = mediaFilePath;
    }
}
