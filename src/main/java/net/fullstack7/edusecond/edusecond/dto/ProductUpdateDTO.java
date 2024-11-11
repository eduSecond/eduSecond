package net.fullstack7.edusecond.edusecond.dto;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class ProductUpdateDTO {
    @NotNull(message = "상품 ID는 필수입니다")
    private Integer productId;
    
    private String productName;
    private String productDesc;
    
    @Min(value = 0, message = "가격은 0 이상이어야 합니다")
    private Integer price;
    
    @Min(value = 0, message = "수량은 0 이상이어야 합니다")
    private Integer quantity;
    
    @Pattern(regexp = "^(NEW|LIKE_NEW|GOOD|FAIR)$", message = "상품 상태는 NEW, LIKE_NEW, GOOD, FAIR 중 하나여야 합니다")
    private String quality;
    
    @Pattern(regexp = "^(AVAILABLE|RESERVED|SOLD)$", message = "판매 상태는 AVAILABLE, RESERVED, SOLD 중 하나여야 합니다")
    private String productStatus;
    
    private List<String> imagePaths;
    private List<Integer> deleteImageIds;  // 삭제할 이미지 ID 목록
}
