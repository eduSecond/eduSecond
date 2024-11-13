package net.fullstack7.edusecond.edusecond.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.product.LikeDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.service.Like.LikeServiceIf;
import net.fullstack7.edusecond.edusecond.util.JSFunc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/es/like")
@Log4j2
@RequiredArgsConstructor
public class LikeController {
    private final LikeServiceIf likeService;

    @GetMapping("/insert")
    public String insertLike(Model model,
                             @RequestParam String userId,
                             @RequestParam Integer productId){
//        log.info("userId: " + userId);
//        log.info("productId: " + productId);
        try{
            likeService.insertLike(userId, productId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/product/view?productId=" + productId;
    }

    @GetMapping("/delete")
    public String deleteLike(Model model,
                             @RequestParam Integer productId,
                             @RequestParam String userId){
        log.info("productId: " + productId);
        log.info("userId: " + userId);
        try{
            likeService.deleteLike(userId, productId);
        }catch(Exception e){
            log.error("좋아요 수정 실패:" + e.getMessage());
        }
        return "redirect:/product/view?productId=" + productId;
    }
}
