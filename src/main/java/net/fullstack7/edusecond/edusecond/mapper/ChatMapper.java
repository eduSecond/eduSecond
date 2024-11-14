package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.dto.message.ChatRoomDTO;
import net.fullstack7.edusecond.edusecond.dto.message.ChatMessageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {
  List<ChatRoomDTO> selectChatRoomList(@Param("userId") String userId);
  ChatRoomDTO selectChatRoom(@Param("roomId") int roomId);
  void insertChatRoom(ChatRoomDTO chatRoom);
  void updateUserChatRoomStatus(@Param("roomId") int roomId,
                                @Param("userType") String userType,
                                @Param("status") String status);

  List<ChatMessageDTO> selectChatMessages(@Param("roomId") int roomId);
  void insertMessage(ChatMessageDTO message);
  void updateMessageRead(@Param("roomId") int roomId, @Param("userId") String userId);
  ChatRoomDTO findExistingChatRoom(@Param("productId") int productId,
                                  @Param("sellerId") String sellerId,
                                  @Param("buyerId") String buyerId);
}
