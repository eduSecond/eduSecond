package net.fullstack7.edusecond.edusecond.controller.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductRegistDTO;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.CommonFileUtil;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/es/product")
@RequiredArgsConstructor
@Log4j2
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,        // 10MB
        maxRequestSize = 1024 * 1024 * 50      // 50MB
)
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

    @GetMapping("/regist")
    public String regist(){
        return "product/regist";
    }

    @PostMapping("/registOk")
    public String registOk(
            ProductRegistDTO productRegistDTO,
            HttpServletRequest request) throws ServletException, IOException {

        int result = productService.insertProduct(productRegistDTO); // 상품에 삽입. 여기에는 파일이 존재하지 않음
        int lastProductId = 0;
        if (result > 0){ //상품 테이블에 잘 들어갔을 때 tbl_product insert 성공. 여기에서 파일 업로드 해줘야 함.
            lastProductId = productService.getLastProductId(); // 마지막에 삽입한 ProductId를 가져옴
            String saveDir = "C:\\Users\\Jerry\\Desktop\\java7\\eduSecondUploads";
            List<String> imagePaths = CommonFileUtil.multiFileUpload(request,saveDir,"files");
            productService.insertProductImage(lastProductId, imagePaths);

        } else {
            // 상품 테이블에 등록을 실패함. tbl_product insert 실패
        }





        if(result > 0){
            log.info("[ProductController] >> registOk [SUCCESS] >> /product/list");
            return "product/list";
        } else {
            log.info("[ProductController] >> registOk [FAIL] >> /product/list");
            return "redirect:/es/product/regist";
        }
    }
}
