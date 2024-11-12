package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import net.fullstack7.edusecond.edusecond.util.JSFunc;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {
    private final ProductServiceIf productService;

    @GetMapping("/list")
    public String list(Model model,
                  @RequestParam(defaultValue = "1") int pageNo,
                  @RequestParam(required = false) String searchType,
                  @RequestParam(required = false) String searchValue,
                  HttpServletResponse response) {
        try {
            if (!validateListParameters(pageNo, searchType, searchValue, response)) {
                return null;
            }

            List<ProductDTO> pList = productService.list(pageNo, 10, 6, searchType, searchValue);
            int totalCount = productService.totalCount(searchType, searchValue);
            Paging paging = new Paging(pageNo, 10, 6, totalCount);
            
            model.addAttribute("pList", pList);
            model.addAttribute("paging", paging);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchValue", searchValue);
            
            return "product/list";
        } catch (Exception e) {
            log.error("상품 목록 조회 중 오류 발생: ", e);
            JSFunc.alertBack("상품 목록을 불러오는 중 오류가 발생했습니다.", response);
            return null;
        }
    }

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

    @GetMapping("/view")
    public String view(Model model, 
                      @RequestParam int productId,
                      HttpServletResponse response) {
        try {
            if (productId <= 0) {
                JSFunc.alertBack("유효하지 않은 상품 ID입니다.", response);
                return null;
            }

            ProductDTO dto = productService.view(productId);
            if (dto == null) {
                JSFunc.alertBack("존재하지 않는 상품입니다.", response);
                return null;
            }

            model.addAttribute("dto", dto);
            return "product/view";
            
        } catch (Exception e) {
            log.error("상품 상세 조회 중 오류 발생: ", e);
            JSFunc.alertBack("상품 정보를 불러오는 중 오류가 발생했습니다.", response);
            return null;
        }
    }

    @GetMapping("/es/pay")
    public String payment(Model model,
                          @RequestParam int productId,
                          HttpServletResponse response){
        try{
            if (productId <= 0) {
                JSFunc.alertBack("유효하지 않은 상품 ID입니다.", response);
                return null;
            }
            ProductDTO dto = productService.view(productId);
            if (dto == null) {
                JSFunc.alertBack("존재하지 않는 상품입니다.", response);
                return null;
            }
            model.addAttribute("dto", dto);
            return "product/pay";
        }catch(Exception e){
            log.error("상품 상세 조회 중 오류 발생: ", e);
            JSFunc.alertBack("상품 정보를 불러오는 중 오류가 발생했습니다.", response);
            return null;
        }

    }

}
