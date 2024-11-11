package net.fullstack7.edusecond.edusecond.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageVO {
    private Integer imageId;
    private Integer productId;
    private String imagePath;
    private String isMain;    // 'Y' or 'N'
} 