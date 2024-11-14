package net.fullstack7.edusecond.edusecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.member.MemberLoginDTO;
import net.fullstack7.edusecond.edusecond.dto.payment.PaymentDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.mapper.OrderMapper;
import net.fullstack7.edusecond.edusecond.mapper.ProductMapper;
import net.fullstack7.edusecond.edusecond.service.payment.PaymentServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import net.fullstack7.edusecond.edusecond.util.JSFunc;
import org.mariadb.jdbc.plugin.codec.UuidCodec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/es/payment")
@RequiredArgsConstructor
@Log4j2
public class PaymentController {
    private final ProductServiceIf productService;
    private final PaymentServiceIf paymentService;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
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
    public String pay(HttpSession session, PaymentDTO paymentDTO, @RequestParam int totalQuantity, HttpServletResponse response){

        if(paymentDTO.getOrderQuantity() > totalQuantity){
            JSFunc.alertBack("최대 수량을 넘길 수 없습니다.", response);
            return null;
        }

        MemberLoginDTO memberLoginDTO = (MemberLoginDTO) session.getAttribute("memberInfo");
        paymentDTO.setBuyerId(memberLoginDTO.getUserId());

        String paymentNumber = UUID.randomUUID().toString();
        paymentDTO.setPaymentNumber(paymentNumber);

        int totalPrice = paymentDTO.getUnitPrice() * paymentDTO.getOrderQuantity();
        paymentDTO.setTotalPrice(totalPrice);

        int result = paymentService.insert(paymentDTO);
        if(result > 0){
            //결제 성공 시 product에서 개수만큼 차감시켜 주어야 함.
            int result1 = productMapper.reductionAfterPayment(paymentDTO);// 상품정보에서 개수 차감하는 메서드

            if(result1 > 0){
                log.info("여기 탄 거 아님?");
                return "redirect:/es/mypage/orderList";
            } else{
                log.info("상품 결제 후 상품 차감 실패");
                return "redirect:/es/mypage/orderList";
            }
        } else{
            log.info("상품 결제 중 오류 발생");
            JSFunc.alertBack("상품 결제 실패", response);
            return null;
        }
    }

    @GetMapping("/confirmPurchase")
    public String confirmPurchase(@RequestParam("productId") int productId,
                                  @RequestParam(defaultValue = "1") int pageNo, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        int result = orderMapper.confirmPurchase(productId, "배송완료/구매확정");
        if (result > 0) {
            JSFunc.alertLocation("구매가 확정되었습니다.","/es/mypage/orderList?pageNo="+pageNo, response);
        } else {
            JSFunc.alertBack("구매 확정에 실패했습니다", response);
        }
        return null;
    }


    @GetMapping("/startDelivery")
    public String startDelivery(@RequestParam("productId") int productId,
                                  @RequestParam(defaultValue = "1") int pageNo, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        int result = orderMapper.startDelivery(productId, "배송중");
        if (result > 0) {
            JSFunc.alertLocation("배송 시작.","/es/mypage/orderList_1?pageNo="+pageNo, response);
        } else {
            JSFunc.alertBack("배송 시작 실패", response);
        }
        return null;
    }


}
