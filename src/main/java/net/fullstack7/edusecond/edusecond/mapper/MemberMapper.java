package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.domain.member.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberVO selectMemberById(String userId);
    void insertMember(MemberVO memberVO);
    void updateMember(MemberVO memberVO);
    void deleteMember(String userId);
} 