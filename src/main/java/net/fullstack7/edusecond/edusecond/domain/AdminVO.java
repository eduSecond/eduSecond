package net.fullstack7.edusecond.edusecond.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminVO {
    private String adminId;
    private String adminPw;
}