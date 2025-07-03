package com.mobi.whatsappcloneapi.message;

import com.mobi.whatsappcloneapi.file.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {
    public MessageResponse toMessageResponse(Message message) {
        MessageResponse response = new MessageResponse();
        response.setMessageId(message.getId());
        response.setContent(message.getContent());
        response.setSenderId(message.getSenderId());
        response.setReceiverId(message.getReceiverId());
        response.setType(message.getType());
        response.setState(message.getState());
        response.setCreatedAt(message.getCreatedDate());
        response.setMedia(FileUtils.readFileLocation(message.getMediaFilePath()));

        return response;
    }
}
