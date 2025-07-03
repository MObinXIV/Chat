package com.mobi.whatsappcloneapi.message;

import com.mobi.whatsappcloneapi.chat.Chat;
import com.mobi.whatsappcloneapi.chat.ChatRepository;
import com.mobi.whatsappcloneapi.file.FileService;
import com.mobi.whatsappcloneapi.file.FileUtils;
import com.mobi.whatsappcloneapi.notification.Notification;
import com.mobi.whatsappcloneapi.notification.NotificationService;
import com.mobi.whatsappcloneapi.notification.NotificationType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper mapper;
    private final FileService fileService;
    private final NotificationService notificationService;
    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository, MessageMapper mapper, FileService fileService, NotificationService notificationService) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.mapper = mapper;
        this.fileService = fileService;
        this.notificationService = notificationService;
    }

   public void saveMessage(MessageRequest messageRequest){
       Chat chat = chatRepository.findById(messageRequest.getChatId())
               .orElseThrow(() -> new EntityNotFoundException("Chat not found"));
         Message message = new Message();
         message.setContent(messageRequest.getContent());
         message.setChat(chat);
         message.setSenderId(messageRequest.getSenderId());
         message.setReceiverId(messageRequest.getReceiverId());
         message.setType(messageRequest.getType());
         message.setState(MessageState.SENT);
         messageRepository.save(message);
       Notification notification = new Notification();
       notification.setChatId(chat.getId());
       notification.setMessageType(messageRequest.getType());
       notification.setContent(messageRequest.getContent());
       notification.setSenderId(messageRequest.getSenderId());
       notification.setReceiverId(messageRequest.getReceiverId());
       notification.setType(NotificationType.MESSAGE);
       notification.setChatName(chat.getChatName(message.getSenderId()));

       notificationService.sendNotification(messageRequest.getReceiverId(), notification);
   }

   public List<MessageResponse> findChatMessages(String chatId){
        return messageRepository.findMessageByChatId(chatId)
                .stream()
                .map(mapper::toMessageResponse)
                .toList();
   }
   @Transactional
   public void setMessageToSeen(String chatId, Authentication authentication){
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found"));
        final String recipientId = getRecipientId(chat, authentication);
        messageRepository.setMessageToSeen(chatId, MessageState.SEEN);
       Notification notification = new Notification();
       notification.setChatId(chat.getId());
       notification.setSenderId(getSenderId(chat, authentication));
       notification.setReceiverId(recipientId);
       notification.setType(NotificationType.SEEN);

       notificationService.sendNotification(recipientId, notification);
   }

   public void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication){
       Chat chat = chatRepository.findById(chatId)
               .orElseThrow(() -> new EntityNotFoundException("Chat not found"));
       final String senderId = getSenderId(chat, authentication);
         final String recipientId = getRecipientId(chat, authentication);
         final String filePath= fileService.saveFile(file,senderId);
       Message message = new Message();
       message.setChat(chat);
       message.setSenderId(senderId);
       message.setReceiverId(recipientId);
       message.setType(MessageType.IMAGE);
       message.setState(MessageState.SENT);
       message.setMediaFilePath(filePath);
       messageRepository.save(message);
       Notification notification = new Notification();
       notification.setChatId(chat.getId());
       notification.setType(NotificationType.IMAGE);
       notification.setMessageType(MessageType.IMAGE);
       notification.setSenderId(senderId);
       notification.setReceiverId(recipientId);
       notification.setChatName(chat.getChatName(message.getSenderId()));
        notification.setMedia(FileUtils.readFileLocation(filePath));
       notificationService.sendNotification(recipientId, notification);
   }

    private String getSenderId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals(authentication.getName())) {
            return chat.getSender().getId();
        }
        return chat.getRecipient().getId();
    }

    private String getRecipientId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals(authentication.getName())) {
            return chat.getRecipient().getId();
        }
        return chat.getSender().getId();
    }
}
