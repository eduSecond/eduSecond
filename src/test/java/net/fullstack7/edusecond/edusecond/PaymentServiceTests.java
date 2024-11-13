package net.fullstack7.edusecond.edusecond;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.PaymentVO;
import net.fullstack7.edusecond.edusecond.domain.member.MemberVO;
import net.fullstack7.edusecond.edusecond.dto.payment.PaymentDTO;
import net.fullstack7.edusecond.edusecond.mapper.MemberMapper;
import net.fullstack7.edusecond.edusecond.mapper.PaymentMapper;
import net.fullstack7.edusecond.edusecond.service.payment.PaymentServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/config/root-context.xml")
public class PaymentServiceTests {

    @Autowired(required = false)
    private PaymentMapper paymentMapper;
//    @Test
//    public void testPay(){
//        PaymentVO vo = PaymentVO.builder()
//                .orderId(1)
//                .productId(1)
//                .buyerId("tester1")
//                .orderQuantity(2)
//                .paymentMethod("Card")
//                .paymentNumber("1")
//                .orderStatus("결제완료")
//                .deliveryStatus("준비중")
//                .build();
//        paymentMapper.insert(vo);
//        log.info("구매 정보 등록 완료");
//    }
}
