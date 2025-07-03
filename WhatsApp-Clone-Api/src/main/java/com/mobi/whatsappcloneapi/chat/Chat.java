package com.mobi.whatsappcloneapi.chat;

import com.mobi.whatsappcloneapi.common.BaseAuditingEntity;
import com.mobi.whatsappcloneapi.message.Message;
import com.mobi.whatsappcloneapi.message.MessageState;
import com.mobi.whatsappcloneapi.message.MessageType;
import com.mobi.whatsappcloneapi.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Chat")
@Table(name = "chat")
@NamedQuery(
         name = ChatConstants.FIND_CHAT_BY_SENDER_ID,
        query = "SELECT c FROM Chat c WHERE c.sender.id = :senderId OR c.recipient.id = :senderId ORDER BY createdDate DESC"
)

@NamedQuery(name = ChatConstants.FIND_CHAT_BY_SENDER_ID_AND_RECEIVER,
        query = "SELECT DISTINCT c FROM Chat c WHERE (c.sender.id = :senderId AND c.recipient.id = :recipientId) OR (c.sender.id = :recipientId AND c.recipient.id = :senderId) ORDER BY createdDate DESC"
)
public class Chat extends BaseAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    @OneToMany(mappedBy = "chat",  fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")// Display the latest message in the top
    private List<Message> messages;
    @Transient
    public String getChatName(final String senderId){
        if(recipient.getId().equals(senderId)){
            return sender.getFirstName() + " " + sender.getLastName();
        }
            return recipient.getFirstName() + " " + recipient.getLastName();
    }

    @Transient
    public long getUnreadMessages(final String senderId){
        return messages.stream()
                .filter(m -> m.getReceiverId().equals(senderId))
                .filter(m -> MessageState.SENT == m.getState())
                .count();
    }

    @Transient
    public String getLastMessage(){
        if(messages!= null && !messages.isEmpty()){
            if (messages.get(0).getType()!= MessageType.TEXT ) return "Attachment";
            return messages.get(0).getContent();
        }
        return null;
    }
    @Transient
    public LocalDateTime getLastMessageTime(){
        if(messages!= null && !messages.isEmpty()){
            return messages.get(0).getCreatedDate();
        }
        return null;
    }

    public Chat(LocalDateTime createdDate, LocalDateTime lastModifiedDate, String id, User sender, User recipient, List<Message> messages) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.messages = messages;
    }

    public Chat() {
        super();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
