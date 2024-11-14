package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberLoginDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberModifyDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MypageDTO;
import net.fullstack7.edusecond.edusecond.mapper.MemberMapper;
import net.fullstack7.edusecond.edusecond.service.member.MemberServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/es/mypage")
@RequiredArgsConstructor
@Log4j2
public class MypageController {
    private final MemberServiceIf memberService;
    private final MemberMapper memberMapper;

    @GetMapping("/myInfo")
    public String myInfo(HttpSession session, Model model) {
        MemberLoginDTO memberLoginDTO = (MemberLoginDTO) session.getAttribute("memberInfo");
        MypageDTO mypageDTO = memberMapper.myProductCount(memberLoginDTO.getUserId());
        log.info(mypageDTO);
        MemberDTO memberDTO = memberService.getMember(memberLoginDTO.getUserId());
        model.addAttribute("mypageDTO", mypageDTO);
        model.addAttribute("member", memberDTO);
        return "mypage/myInfo";
    }

    @GetMapping("/modify")
    public String modify(HttpSession session, Model model) {
        MemberLoginDTO memberLoginDTO = (MemberLoginDTO) session.getAttribute("memberInfo");
        model.addAttribute("member", memberService.getMember(memberLoginDTO.getUserId())); //예시임. 바꿔줘야함
        return "mypage/modify";
    }

    @PostMapping("/modifyOk")
    public String modifyOk(HttpSession session,
                           @Valid MemberModifyDTO memberModifyDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        MemberLoginDTO memberLoginDTO = (MemberLoginDTO) session.getAttribute("memberInfo");
        memberModifyDTO.setUserId(memberLoginDTO.getUserId());  //예시임. 바꿔줘야 함
        int result = memberService.modifyMember(memberModifyDTO);
        if(result > 0){
            return "redirect:/es/mypage/myInfo";
        } else{
            log.info("수정실패");
            return "redirect:/es/mypage/modify";
        }
    }

    @GetMapping("/delete")
    public String delete(HttpSession session) {
        MemberLoginDTO memberLoginDTO = (MemberLoginDTO) session.getAttribute("memberInfo");
        boolean result = memberService.deleteMember(memberLoginDTO.getUserId());
        if(result){
            return "redirect:/main/goMain";
        } else{
            log.info("탈퇴실패");
            return "redirect:/es/mypage/myInfo";
        }
    }



}
