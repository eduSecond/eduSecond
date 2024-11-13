package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.domain.message.ChatMessageVO;
import net.fullstack7.edusecond.edusecond.domain.message.ChatRoomVO;
import net.fullstack7.edusecond.edusecond.dto.message.ChatRoomDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<ChatRoomDTO> selectChatRoomList(@Param("userId") String userId);
    ChatRoomVO selectChatRoom(@Param("roomId") int roomId);
    void insertChatRoom(ChatRoomVO chatRoom);

    List<ChatMessageVO> selectChatMessages(@Param("roomId") int roomId);
    void insertMessage(ChatMessageVO message);
    void updateMessageRead(@Param("roomId") int roomId, @Param("userId") String userId);
}
