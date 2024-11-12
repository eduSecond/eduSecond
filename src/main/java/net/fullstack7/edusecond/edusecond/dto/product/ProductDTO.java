package net.fullstack7.edusecond.edusecond.dto.product;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String sellerId;
    private String sellerName;    // 판매자 이름
    private String productName;
    private String productDesc;
    private Integer price;
    private Integer quantity;
    private String quality;
    private String productStatus;
    private Integer viewCount;
    private LocalDateTime regDate;
    private LocalDateTime modifyDate;
    
    // 추가 정보
    private List<String> imagePaths;  // 상품 이미지 경로들
    private Integer wishCount;        // 찜 수
    private Double avgRating;         // 판매자 평균 평점
    private Integer reviewCount;      // 판매자 리뷰 수
}
