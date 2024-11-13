package net.fullstack7.edusecond.edusecond.service.member;

import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberModifyDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberRegistDTO;
import java.util.List;

public interface MemberServiceIf {
  MemberDTO getMember(String userId);
  boolean registerMember(MemberRegistDTO registDTO);
  boolean loginMember(String userId, String userPw);
  int getTotalCount(String searchType, String searchValue);
  List<MemberDTO> getList(int pageNo, int pageSize, String searchType, String searchValue);
  boolean updateEnabled(String userId, boolean enabled);
  boolean deleteMember(String userId);
  int modifyMember(MemberModifyDTO memberModifyDTO);

}
