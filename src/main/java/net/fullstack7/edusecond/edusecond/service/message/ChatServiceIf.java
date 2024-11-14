package net.fullstack7.edusecond.edusecond.service.message;

import net.fullstack7.edusecond.edusecond.dto.message.ChatMessageDTO;
import net.fullstack7.edusecond.edusecond.dto.message.ChatRoomDTO;

import java.util.List;

public interface ChatServiceIf {
    // 채팅방 관련
    List<ChatRoomDTO> getChatRoomList(String userId);
    ChatRoomDTO getChatRoom(int roomId);
    int createChatRoom(ChatRoomDTO chatRoomDTO);
    void updateUserChatRoomStatus(int roomId, String userId, String status);

    // 메시지 관련
    void saveMessage(ChatMessageDTO message);
    void updateMessageRead(int roomId, String userId);
    List<ChatMessageDTO> getPreviousMessages(int roomId);

    ChatRoomDTO findExistingChatRoom(int productId, String sellerId, String buyerId);
} 