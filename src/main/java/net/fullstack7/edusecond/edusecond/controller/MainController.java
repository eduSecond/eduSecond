package net.fullstack7.edusecond.edusecond.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
@Log4j2
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "main/main";
    }

    @GetMapping("/header")
    public String header(){
        return "main/header";
    }

    @GetMapping("/footer")
    public String footer(){
        return "main/footer";
    }

    @GetMapping("/logout")
    public String logout(
            HttpServletRequest req, HttpSession session
    ){

        session = req.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return "main/main";

    }


}
