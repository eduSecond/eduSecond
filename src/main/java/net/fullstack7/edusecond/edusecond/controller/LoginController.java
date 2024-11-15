package net.fullstack7.edusecond.edusecond.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberLoginDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberRegistDTO;
import net.fullstack7.edusecond.edusecond.service.member.MemberServiceIf;
import net.fullstack7.edusecond.edusecond.service.member.MemberServiceImpl;
import net.fullstack7.edusecond.edusecond.util.CommonUtil;
import net.fullstack7.edusecond.edusecond.util.JSFunc;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;


@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@Log4j2
public class LoginController extends HttpServlet {
    private final MemberServiceIf MemberService;
    private CommonUtil cUtil = new CommonUtil();
    private JSFunc JSFunc = new JSFunc();


    @GetMapping("/agreeregist")
    public String agreeregist(){
        return "login/agreeregist";
    }
    @GetMapping("/login")
    public String login(){
        return "login/login";
    }
    @GetMapping("/regist")
    public String regist(){
        return "login/regist";
    }

    @PostMapping("/login")
    public String loginOk (
             @RequestParam String userId
            , @RequestParam String password
//          , @RequestParam String saveId
            , HttpServletRequest req, HttpServletResponse res, HttpSession session,
             RedirectAttributes redirectAttributes
    ){
        log.info("============================================");
        log.info("loginOK");
//        if(bindingResult.hasErrors()){
//            log.info("hasErrors");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//            return "redirect:/login/login";
//        }

        try{
//            if("Y".equals(saveId)){
//                session.setAttribute("userId", userId);
//                cUtil.setCookiesInfo(req, res, "/",30 * 24 * 60 * 60,"userId",userId);
//                req.setAttribute("userId", userId);
//            }
            boolean result = MemberService.loginMember(userId, password);
            if(result == true){
                MemberLoginDTO mdto = MemberService.getLoginMember(userId);
                session.setAttribute("memberInfo", mdto);
                return "redirect:/main/main";
            } else{
                redirectAttributes.addFlashAttribute("errorMessage", "존재하지 않는 계정입니다.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("============================================");
        return "redirect:/login/login";
    }

    @PostMapping("/regist")
    public String registOk(
              @RequestParam String userId
            , @RequestParam String userPw
            , @RequestParam String userName
            , @RequestParam String userEmail
            , @RequestParam String userPhone
            , @RequestParam String userAddress
            , @RequestParam String userPostcode
            , @RequestParam String userBirth
            , HttpServletRequest req, HttpServletResponse res
              , RedirectAttributes redirectAttributes
    ){

        try{
            MemberRegistDTO dto = MemberRegistDTO.builder()
                    .userId(userId)
                    .userPw(userPw)
                    .userName(userName)
                    .userEmail(userEmail)
                    .userPhone(userPhone)
                    .userAddress(userAddress)
                    .userPostcode(userPostcode)
                    .userBirth(LocalDate.parse(userBirth))
                    .build();

            boolean result = MemberService.registerMember(dto);
            if(result == true){
                redirectAttributes.addFlashAttribute("errorMessage", "회원가입이 성공했습니다!");
                return "redirect:/main/main";
            }else{
                redirectAttributes.addFlashAttribute("errorMessage", "회원가입이 성공하지 못했습니다");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/login/regist";
    }

    //아이디 중복 확인
//    @GetMapping("/regist")
//    public String idCheck(
//            @RequestParam String userId
//            , RedirectAttributes redirectAttributes
//    ){
//        try{
//            MemberDTO result = MemberService.getMember(userId);
//            if(result == null){
//                redirectAttributes.addFlashAttribute("errorMessage", "사용가능한 아이디 입니다.");
//                return "redirect:/main/regist";
//            } else{
//                redirectAttributes.addFlashAttribute("errorMessage", "이미 존재하는 아이디 입니다.");
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return "login/regist";
//    }
}
