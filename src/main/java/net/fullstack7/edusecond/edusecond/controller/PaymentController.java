package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.payment.PaymentDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.JSFunc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/es/payment")
@RequiredArgsConstructor
@Log4j2
public class PaymentController {
    private final ProductServiceIf productService;
    @GetMapping("/view")
    public String view(Model model, int productId, HttpServletResponse response){
        try{
            if(productId <= 0){
                JSFunc.alertBack("유효하지 않은 상품입니다", response);

                return null;
            }
            ProductDTO dto =  productService.view(productId);
            if(dto == null){
                JSFunc.alertBack("존재하지 않는 상품입니다", response);
            }
            model.addAttribute("dto", dto);
            return "payment/pay";
        }catch (Exception e){
            log.error("상품 상세 조회 중 오류 발생: ", e);
            JSFunc.alertBack("상품 정보를 불러오는 중 오류가 발생했습니다.", response);
            return null;
        }
    }

    @PostMapping("/pay")
    public String pay(HttpSession session, PaymentDTO paymentDTO, HttpServletResponse response){
        String userId = (String) session.getAttribute("sessionId");

        return "/es/mypage/orderList";
    }


}
