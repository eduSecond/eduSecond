package net.fullstack7.edusecond.edusecond.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.product.LikeDTO;
import net.fullstack7.edusecond.edusecond.service.Like.LikeServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/like")
@Log4j2
@RequiredArgsConstructor
@AllArgsConstructor
public class LikeController {
    private LikeServiceIf likeService;

    @PostMapping("/insert")
    @ResponseBody
    public void insertLike(@RequestParam String userId, @RequestParam Integer productId){
        log.info("1");
        likeService.insertLike(userId, productId);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void deleteLike(@RequestParam int wishId){
        likeService.deleteLike(wishId);
    }


}
