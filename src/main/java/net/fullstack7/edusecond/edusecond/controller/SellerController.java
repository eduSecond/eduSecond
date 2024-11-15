package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.member.MemberVO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberLoginDTO;
import net.fullstack7.edusecond.edusecond.service.member.MemberServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/seller")
@RequiredArgsConstructor
@Log4j2
public class SellerController {
    private final MemberServiceIf MemberService;
    private final ProductServiceIf productService;

    @GetMapping("/sellerpage")
    public String intro(
            @RequestParam String userId
            , RedirectAttributes redirectAttributes
            , Model model
    ){
        try{
            MemberDTO result = MemberService.getMember(userId);
            model.addAttribute("member", result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return "seller/sellerpage";
    }
}
