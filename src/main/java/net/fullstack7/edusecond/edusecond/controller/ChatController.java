package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import net.fullstack7.edusecond.edusecond.dto.message.ChatMessageDTO;
import net.fullstack7.edusecond.edusecond.dto.message.ChatRoomDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.service.message.ChatServiceIf;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
@Log4j2
public class ChatController {

    private final ChatServiceIf chatService;

    @GetMapping("/list")
    public String chatList(Model model, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");
        if (member == null) {
            return "redirect:/member/login";
        }
        
        List<ChatRoomDTO> chatRooms = chatService.getChatRoomList(member.getUserId());
        model.addAttribute("chatRooms", chatRooms);
        return "message/messageList";
    }

    @GetMapping("/chatting")
    public String chatRoom(@RequestParam int roomId, Model model, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");
        if (member == null) {
            return "redirect:/member/login";
        }

        ChatRoomDTO room = chatService.getChatRoom(roomId);
        List<ChatMessageDTO> messages = chatService.getPreviousMessages(roomId);
        
        // 읽지 않은 메시지 읽음 처리
        chatService.updateMessageRead(roomId, member.getUserId());
        
        model.addAttribute("room", room);
        model.addAttribute("messages", messages);
        return "message/chatting";
    }

    @PostMapping("/room/create")
    @ResponseBody
    public Map<String, Object> createRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        // 기존 채팅방이 있는지 확인
        ChatRoomDTO existingRoom = chatService.findExistingChatRoom(
            chatRoomDTO.getProductId(), 
            chatRoomDTO.getSellerId(), 
            chatRoomDTO.getBuyerId()
        );
        
        int roomId;
        if (existingRoom != null) {
            roomId = existingRoom.getRoomId();
        } else {
            roomId = chatService.createChatRoom(chatRoomDTO);
        }
        
        return Collections.singletonMap("roomId", roomId);
    }
}
