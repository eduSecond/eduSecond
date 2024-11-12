package net.fullstack7.edusecond.edusecond.controller.main;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
@Log4j2
public class MainController {

    @GetMapping("/goMain")
    public String main(){
        return "main/main";
    }
}
