package net.fullstack7.edusecond.edusecond.dto.payment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
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
