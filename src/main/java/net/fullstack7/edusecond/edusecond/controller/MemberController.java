package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.service.member.MemberServiceIf;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/es/members")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    private final MemberServiceIf memberService;
}
