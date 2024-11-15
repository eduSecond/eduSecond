package net.fullstack7.edusecond.edusecond.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.service.Like.LikeServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.CommonDateUtil;
import net.fullstack7.edusecond.edusecond.util.JSFunc;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
@Log4j2
public class MainController {
    private final ProductServiceIf productService;

    private boolean validateListParameters(int pageNo, String searchType,
                                           String searchValue, HttpServletResponse response) {
        if (pageNo < 1) {
            JSFunc.alertBack("페이지 번호는 1 이상이어야 합니다.", response);
            return false;
        }

        if (searchType != null && !searchType.trim().isEmpty()
                && searchValue != null && !searchValue.trim().isEmpty()) {
            if (!("productName".equals(searchType) || "sellerId".equals(searchType))) {
                JSFunc.alertBack("유효하지 않은 검색 카테고리입니다: " + searchType, response);
                return false;
            }
        }
        return true;
    }

    @GetMapping("/main")
    public String main(
            Model model,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchValue,
            HttpServletResponse response
    ){
        try {
            if (!validateListParameters(pageNo, searchType, searchValue, response)) {
                return null;
            }

        List<ProductDTO> pList = productService.list(pageNo, 10, 5, searchType, searchValue, "AVAILABLE");
        for (ProductDTO dto : pList) {
            String formatDate = CommonDateUtil.localDateTimeToString(dto.getRegDate(), "yyyy-MM-dd");
            dto.setFormatRegDate(formatDate);
        }
        int totalCount = productService.totalCount(searchType, searchValue, "AVAILABLE");
            Paging paging = new Paging(pageNo, 10, 5, totalCount);

            model.addAttribute("pList", pList);
            model.addAttribute("paging", paging);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchValue", searchValue);

            return "main/main";
        } catch (Exception e) {
            log.error("상품 목록 조회 중 오류 발생: ", e);
            JSFunc.alertBack("상품 목록을 불러오는 중 오류가 발생했습니다.", response);
            return null;
        }
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
