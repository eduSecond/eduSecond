package net.fullstack7.edusecond.edusecond.controller.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/es/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {
    private final ProductServiceIf productService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(defaultValue = "1") int pageNo,
                       @RequestParam(required = false) String searchCategory,
                       @RequestParam(required = false) String searchValue){
        int blockSize = 6;
        int pageSize = 10;
        int totalCnt = productService.totalCount(searchCategory, searchValue);
        Paging paging = new Paging(pageNo, pageSize, blockSize, totalCnt);
        model.addAttribute("pList", productService.list(pageNo, pageSize, blockSize, searchCategory, searchValue));
        model.addAttribute("paging", paging);
        return "product/list";
    }

    @GetMapping("/view")
    public String view(Model model, @RequestParam int productId){
        ProductDTO dto = productService.view(productId);
        model.addAttribute("dto", dto);
        return "product/view";
    }
}
