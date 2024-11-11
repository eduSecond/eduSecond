package net.fullstack7.edusecond.edusecond.dto;

import net.fullstack7.edusecond.edusecond.util.CommonSearchDTO;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSearchDTO extends CommonSearchDTO {
    @Pattern(regexp = "^(ACTIVE|INACTIVE|SUSPENDED)$", message = "회원 상태는 ACTIVE, INACTIVE, SUSPENDED 중 하나여야 합니다")
    private String memberStatus;
    
    @Pattern(regexp = "^(NORMAL|SELLER|ADMIN)$", message = "회원 유형은 NORMAL, SELLER, ADMIN 중 하나여야 합니다")
    private String memberType;
}
