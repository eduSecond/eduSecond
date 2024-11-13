package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.notice.NoticeDTO;
import net.fullstack7.edusecond.edusecond.service.admin.AdminServiceIf;
import net.fullstack7.edusecond.edusecond.service.member.MemberServiceIf;
import net.fullstack7.edusecond.edusecond.service.notice.NoticeServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
@Log4j2
public class NoticeController {
    private final AdminServiceIf adminService;
    private final ProductServiceIf productService;
    private final MemberServiceIf memberService;
    private final NoticeServiceIf noticeService;

    @GetMapping("/noticelist")
    public String noticelist(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchValue,
            Model model) {
        try {
            List<NoticeDTO> notices = noticeService.getList(pageNo, pageSize, searchType, searchValue);
            int totalCount = noticeService.getTotalCount(searchType, searchValue);
            Paging paging = new Paging(pageNo, pageSize, 5, totalCount);

            model.addAttribute("notices", notices);
            model.addAttribute("paging", paging);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchValue", searchValue);

            return "notice/noticelist";
        } catch (Exception e) {
            log.error("공지사항 목록 조회 중 오류 발생: ", e);
            return "redirect:/notice/noticelist";
        }
    }
}
