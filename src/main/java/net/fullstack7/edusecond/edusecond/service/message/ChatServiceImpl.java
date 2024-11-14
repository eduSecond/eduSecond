package net.fullstack7.edusecond.edusecond.service.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.message.ChatMessageDTO;
import net.fullstack7.edusecond.edusecond.dto.message.ChatRoomDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import net.fullstack7.edusecond.edusecond.mapper.ChatMapper;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatServiceImpl implements ChatServiceIf {
    
    private final ChatMapper chatMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<ChatRoomDTO> getChatRoomList(String userId) {
        return chatMapper.selectChatRoomList(userId);
    }

    @Override
    public int createChatRoom(ChatRoomDTO chatRoomDTO) {
        log.info("채팅방 생성 시도: {}", chatRoomDTO);
        chatMapper.insertChatRoom(chatRoomDTO);
        log.info("채팅방 생성 완료. roomId: {}", chatRoomDTO.getRoomId());
        return chatRoomDTO.getRoomId();
    }

    @Override
    public ChatRoomDTO getChatRoom(int roomId, String userId) {
        log.info("채팅방 조회 시도. roomId: {}, userId: {}", roomId, userId);
        ChatRoomDTO roomDTO = chatMapper.selectChatRoom(roomId, userId);
        log.info("채팅방 조회 완료: {}", roomDTO);
        return roomDTO;
    }

    @Override
    public void saveMessage(ChatMessageDTO messageDTO) {
        log.info("메시지 저장 시도: {}", messageDTO);
        chatMapper.insertMessage(messageDTO);
        log.info("메시지 저장 완료");
    }

    @Override
    public void updateMessageRead(int roomId, String userId) {
        chatMapper.updateMessageRead(roomId, userId);
    }

    @Override
    public void updateUserChatRoomStatus(int roomId, String userId, String status) {
        String userType = getUserType(roomId, userId);
        chatMapper.updateUserChatRoomStatus(roomId, userType, status);
    }

    @Override
    public List<ChatMessageDTO> getPreviousMessages(int roomId) {
        log.info("이전 메시지 조회 시도. roomId: {}", roomId);
        List<ChatMessageDTO> messages = chatMapper.selectChatMessages(roomId);
        log.info("이전 메시지 조회 완료. 메시지 수: {}", messages.size());
        return messages;
    }

    @Override
    public ChatRoomDTO findExistingChatRoom(int productId, String sellerId, String buyerId) {
        return chatMapper.findExistingChatRoom(productId, sellerId, buyerId);
    }

    // 사용자가 판매자인지 구매자인지 확인
    private String getUserType(int roomId, String userId) {
        ChatRoomDTO chatRoom = getChatRoom(roomId, userId);
        return userId.equals(chatRoom.getSellerId()) ? "SELLER" : "BUYER";
    }
}
