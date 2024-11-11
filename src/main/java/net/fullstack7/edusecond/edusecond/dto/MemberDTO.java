package net.fullstack7.edusecond.edusecond.dto;

import java.time.LocalDate;
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
public class MemberDTO {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String userPostcode;
    private LocalDate userBirth;
    
    // 추가 필드
    private int orderCount;
    private int reviewCount;
    private double avgRating;
    private List<ProductDTO> wishlist;
}