package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    MemberVO selectMemberById(String userId);
    int insertMember(MemberVO memberVO);
    int updateMember(MemberVO memberVO);
    int deleteMember(String userId);
    int selectTotalCount(@Param("searchType") String searchType, 
                        @Param("searchValue") String searchValue);
    List<MemberVO> selectList(@Param("offset") int offset,
                             @Param("size") int size,
                             @Param("searchType") String searchType,
                             @Param("searchValue") String searchValue);
    int updateEnabled(@Param("userId") String userId,
                     @Param("enabled") String enabled);
} 
