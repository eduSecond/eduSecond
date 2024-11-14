package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.member.MemberVO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberLoginDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.service.member.MemberServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.CommonDateUtil;
import net.fullstack7.edusecond.edusecond.util.JSFunc;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/seller")
@RequiredArgsConstructor
@Log4j2
public class SellerController {
    private final MemberServiceIf MemberService;
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

    @GetMapping("/sellerpage")
    public String intro(
            @RequestParam String userId
            , RedirectAttributes redirectAttributes
            , Model model
            , @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchValue,
            HttpServletResponse response
    ){
        try{
            MemberDTO result = MemberService.getMember(userId);
            model.addAttribute("member", result);

            if (!validateListParameters(pageNo, searchType, searchValue, response)) {
                return null;
            }

            List<ProductDTO> pList = productService.list(pageNo, 10, 5, searchType, searchValue);
            for (ProductDTO dto : pList) {
                String formatDate = CommonDateUtil.localDateTimeToString(dto.getRegDate(), "yyyy-MM-dd");
                dto.setFormatRegDate(formatDate);
            }
            int totalCount = productService.totalCount(searchType, searchValue);
            Paging paging = new Paging(pageNo, 10, 5, totalCount);

            model.addAttribute("pList", pList);
            model.addAttribute("paging", paging);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchValue", searchValue);

            return "seller/sellerpage";

        } catch (Exception e) {
            log.error("상품 목록 조회 중 오류 발생: ", e);
            JSFunc.alertBack("상품 목록을 불러오는 중 오류가 발생했습니다.", response);
            throw new RuntimeException(e);
        }

    }


}
