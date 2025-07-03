package com.mobi.whatsappcloneapi.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(String userId,Notification notification){
        log.info("Sending Ws notification to {} with payload {}", userId, notification);
        messagingTemplate.convertAndSendToUser(userId,"/chat" , notification);
    }
}
