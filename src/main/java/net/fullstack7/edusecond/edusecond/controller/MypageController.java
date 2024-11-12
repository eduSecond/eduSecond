package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/es/mypage")
@RequiredArgsConstructor
@Log4j2
public class MypageController {

    @GetMapping("/myInfo")
    public String myInfo() {
        return "mypage/myInfo";
    }


}
