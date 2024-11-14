package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberLoginDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberModifyDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MypageDTO;
import net.fullstack7.edusecond.edusecond.mapper.MemberMapper;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.service.member.MemberServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.JSFunc;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/es/mypage")
@RequiredArgsConstructor
@Log4j2
public class MypageController {
    private final MemberServiceIf memberService;
    private final MemberMapper memberMapper;
    private final ProductServiceIf productService;

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

    private boolean validateListParameters(int pageNo, String searchType,
                                           String searchValue, HttpServletResponse response) {
        if (pageNo < 1) {
            JSFunc.alertBack("페이지 번호는 1 이상이어야 합니다.", response);
            return false;
        }

        if (searchType != null && !searchType.trim().isEmpty()
                && searchValue != null && !searchValue.trim().isEmpty()) {
            if (!("productName".equals(searchType) || "sellerId".equals(searchType))) {
                JSFunc.alertBack("유효하지 않은 검색 카테고리입니다: " + searchType, response);
                return false;
            }
        }
        return true;
    }

    @GetMapping("/wishList")
    public String viewWishList(Model model,
                               @RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(required = false) String searchType,
                               @RequestParam(required = false) String searchValue,
                               HttpServletResponse response){
        try{
            if(!validateListParameters(pageNo, searchType, searchValue, response)){
                return null;
            }
            List<ProductDTO> pList = productService.selectAllWishByUser(pageNo, 10, 6, searchType, searchValue, "user1");
            int totalCount = productService.totalCount(searchType, searchValue);
            Paging paging = new Paging(pageNo, 10, 6, totalCount);
            model.addAttribute("pList", pList);
            model.addAttribute("paging", paging);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchValue", searchValue);
            log.info("pList.size()" + pList.size());
            return "/mypage/wishList";
        }catch(Exception e){
            log.error("찜 목록 조회 중 오류 발생" + e.getMessage());
            return null;
        }
    }





}
