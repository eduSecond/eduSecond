package net.fullstack7.edusecond.edusecond.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.service.member.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/es/members")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    private final MemberService memberService;
}
