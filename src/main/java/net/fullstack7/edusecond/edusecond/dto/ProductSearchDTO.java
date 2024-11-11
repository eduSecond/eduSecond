package net.fullstack7.edusecond.edusecond.dto;

import net.fullstack7.edusecond.edusecond.util.CommonSearchDTO;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchDTO extends CommonSearchDTO {
    @Pattern(regexp = "^(ALL|SELLER_ID|PRODUCT_NAME|PRICE_RANGE|QUALITY)$", 
            message = "유효하지 않은 검색 유형입니다")
    @Override
    public void setSearchType(String searchType) {
        super.setSearchType(searchType);
    }
    
    @Pattern(regexp = "^(NEW|LIKE_NEW|GOOD|FAIR)$")
    private String quality;
    
    @Pattern(regexp = "^(AVAILABLE|RESERVED|SOLD)$")
    private String productStatus;
    
    @Min(0)
    private Integer minPrice;
    
    @Min(0)
    private Integer maxPrice;
    
    @Min(0)
    private Integer minQuantity;
    
    @Min(0)
    private Integer maxQuantity;
} 