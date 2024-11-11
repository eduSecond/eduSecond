package net.fullstack7.edusecond.edusecond.dto;

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
public class ProductSearchDTO {
    private String keyword;           // 검색어
    private String quality;           // 상품 상태
    private Integer minPrice;         // 최소 가격
    private Integer maxPrice;         // 최대 가격
    private String sortBy;            // 정렬 기준
    private String sortDirection;     // 정렬 방향
    private Integer page;             // 페이지 번호
    private Integer size;             // 페이지 크기
} 