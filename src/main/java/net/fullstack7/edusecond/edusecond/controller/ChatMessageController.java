package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.message.ChatMessageDTO;
import net.fullstack7.edusecond.edusecond.service.message.ChatServiceIf;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatServiceIf chatService;

    @MessageMapping("/chat/message")
    public void handleChatMessage(@Payload ChatMessageDTO message) {
        log.info("채팅 메시지 수신: {}", message);
        
        chatService.saveMessage(message);
        messagingTemplate.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/leave")
    public void handleLeave(@Payload ChatMessageDTO message) {
        log.info("채팅방 나가기 요청: {}", message);
        chatService.updateUserChatRoomStatus(message.getRoomId(), message.getSenderId(), "N");
        
        // 나가기 메시지를 다른 사용자에게 전송
        message.setType(ChatMessageDTO.MessageType.LEAVE);
        message.setMessage(message.getSenderName() + "님이 채팅방을 나갔습니다.");
        messagingTemplate.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }
}
