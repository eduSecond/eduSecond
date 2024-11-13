package net.fullstack7.edusecond.edusecond.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import net.fullstack7.edusecond.edusecond.domain.member.MemberVO;

@Mapper
public interface MemberMapper {
    MemberVO selectMemberById(String userId);
    int insertMember(MemberVO memberVO);
    int updateMember(MemberVO memberVO);
    int deleteMember(String userId);
    int selectTotalCount(@Param("searchType") String searchType, 
                        @Param("searchValue") String searchValue);
    List<MemberVO> selectList(Map<String, Object> params);
    int updateEnabled(@Param("userId") String userId,
                     @Param("enabled") String enabled);
} 
