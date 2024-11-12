package net.fullstack7.edusecond.edusecond.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVO {
    private int orderId;
    private int productId;
    private String buyerId;
    private int orderQuantity;
    private String paymentMethod;
    private String paymentCompany;
    private String paymentNumber;
    private String orderStatus;
    private String deliveryStatus;
    private LocalDateTime regDate;

}
