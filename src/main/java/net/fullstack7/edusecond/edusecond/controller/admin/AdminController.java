package net.fullstack7.edusecond.edusecond.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.AdminLoginDTO;
import net.fullstack7.edusecond.edusecond.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/login")
    public String loginPage() {
        return "admin/adminLogin";
    }

    @PostMapping("/login")
    public String loginProcess(AdminLoginDTO loginDTO,
                             HttpSession session,
                             Model model) {

        if(adminService.loginAdmin(loginDTO)) {
            session.setAttribute("adminId", loginDTO.getAdminId());
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
        return "admin/adminLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}