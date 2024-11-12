package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.AdminLoginDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductImageDTO;
import net.fullstack7.edusecond.edusecond.service.admin.AdminServiceIf;
import net.fullstack7.edusecond.edusecond.service.member.MemberServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminController {
    private final AdminServiceIf adminService;
    private final ProductServiceIf productService;
    private final MemberServiceIf memberService;
    
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

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("productCount", productService.totalCount(null, null));
        model.addAttribute("memberCount", memberService.getTotalCount(null, null));
        return "admin/dashboard";
    }

    @GetMapping("/member/list")
    public String memberList(@RequestParam(defaultValue = "1") int pageNo,
                           @RequestParam(required = false) String searchType,
                           @RequestParam(required = false) String searchValue,
                           Model model) {
        int totalCount = memberService.getTotalCount(searchType, searchValue);
        Paging paging = new Paging(pageNo, 10, 5, totalCount);

        List<MemberDTO> members = memberService.getList(pageNo, 10, searchType, searchValue);

        model.addAttribute("members", members);
        model.addAttribute("paging", paging);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

        return "admin/member/list";
    }

    @GetMapping("/member/status")
    public String updateMemberStatus(@RequestParam String userId,
                                   @RequestParam boolean enabled,
                                   RedirectAttributes rttr) {
        if(memberService.updateEnabled(userId, enabled)) {
            rttr.addFlashAttribute("message", "회원 상태가 변경되었습니다.");
        } else {
            rttr.addFlashAttribute("error", "회원 상태 변경에 실패했습니다.");
        }
        return "redirect:/admin/member/list";
    }

    @GetMapping("/member/delete")
    public String deleteMember(@RequestParam String userId,
                             RedirectAttributes rttr) {
        if(memberService.deleteMember(userId)) {
            rttr.addFlashAttribute("message", "회원이 삭제되었습니다.");
        } else {
            rttr.addFlashAttribute("error", "회원 삭제에 실패했습니다.");
        }
        return "redirect:/admin/member/list";
    }

    @GetMapping("/product/list")
    public String productList(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchValue,
            Model model) {
        try {
            List<ProductDTO> products = productService.list(pageNo, pageSize, 5, searchType, searchValue);
            int totalCount = productService.totalCount(searchType, searchValue);
            Paging paging = new Paging(pageNo, pageSize, 5, totalCount);

            model.addAttribute("products", products);
            model.addAttribute("paging", paging);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchValue", searchValue);

            return "admin/product/list";
        } catch (Exception e) {
            log.error("상품 목록 조회 중 오류 발생: ", e);
            return "redirect:/admin/dashboard";
        }
    }

    @GetMapping("/product/view")
    public String productView(@RequestParam int productId, Model model) {
        try {
            ProductDTO product = productService.view(productId);
            if (product == null) {
                return "redirect:/admin/product/list";
            }

            List<ProductImageDTO> images = productService.getProductImages(productId);
            product.setImages(images);

            model.addAttribute("product", product);
            return "admin/product/view";
        } catch (Exception e) {
            log.error("상품 상세 조회 중 오류 발생: ", e);
            return "redirect:/admin/product/list";
        }
    }
}
