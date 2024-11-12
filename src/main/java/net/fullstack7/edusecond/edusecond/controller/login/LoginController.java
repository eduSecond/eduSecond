package net.fullstack7.edusecond.edusecond.controller.login;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@Log4j2
public class LoginController {

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
}
