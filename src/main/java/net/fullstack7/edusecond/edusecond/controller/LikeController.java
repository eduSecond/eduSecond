package net.fullstack7.edusecond.edusecond.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.member.MemberLoginDTO;
import net.fullstack7.edusecond.edusecond.dto.product.LikeDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.service.Like.LikeServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.JSFunc;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/es/like")
@Log4j2
@RequiredArgsConstructor
public class LikeController {
    private final LikeServiceIf likeService;
    private final ProductServiceIf productService;

    @GetMapping("/insert")
    public String insertLike(Model model,
                             HttpSession session,
                             @RequestParam Integer productId){
//        log.info("userId: " + userId);
//        log.info("productId: " + productId);
        MemberLoginDTO userDto = (MemberLoginDTO) session.getAttribute("memberInfo");
        try{
            likeService.insertLike(userDto.getUserId(), productId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/product/view?productId=" + productId;
    }

    @GetMapping("/delete")
    public String deleteLike(Model model,
                             HttpSession session,
                             @RequestParam Integer productId,
                             @RequestParam String source){
//        log.info("productId: " + productId);
//        log.info("userId: " + userId);
        MemberLoginDTO loginDto = (MemberLoginDTO) session.getAttribute("memberInfo");
        try{
            likeService.deleteLike(loginDto.getUserId(), productId);
        }catch(Exception e){
            log.error("좋아요 수정 실패:" + e.getMessage());
        }

        if ("mypage".equals(source)) {
            return "redirect:/es/mypage/wishList";
        } else {
            return "redirect:/product/view?productId=" + productId;
        }
        //return "redirect:/product/view?productId=" + productId;
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
}
