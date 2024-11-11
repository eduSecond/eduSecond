package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.MemberRegistDTO;
import net.fullstack7.edusecond.edusecond.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/es/members")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    private final MemberService memberService;
}
