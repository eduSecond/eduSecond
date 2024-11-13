package net.fullstack7.edusecond.edusecond.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.member.MemberVO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberLoginDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberModifyDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberRegistDTO;
import net.fullstack7.edusecond.edusecond.mapper.MemberMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberServiceIf {
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;
    
    @Override
    public MemberDTO getMember(String userId) {
        MemberVO vo = memberMapper.selectMemberById(userId);
        return modelMapper.map(vo, MemberDTO.class);
    }
    
    @Override
    public boolean registerMember(MemberRegistDTO registDTO) {
        try {
            MemberVO vo = modelMapper.map(registDTO, MemberVO.class);
            return memberMapper.insertMember(vo) > 0;
        } catch(Exception e) {
            log.error("회원가입 실패: ", e);
            return false;
        }
    }
    
    @Override
    public boolean loginMember(String userId, String userPw) {
        MemberVO vo = memberMapper.selectMemberById(userId);
        if(vo != null) {
            return vo.getUserPw().equals(userPw);
        }
        return false;
    }


    
    @Override
    public int getTotalCount(String searchType, String searchValue) {
        return memberMapper.selectTotalCount(searchType, searchValue);
    }
    
    @Override
    public List<MemberDTO> getList(int pageNo, int pageSize, String searchType, String searchValue) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", (pageNo - 1) * pageSize);
        params.put("limit", pageSize);
        params.put("searchType", searchType);
        params.put("searchValue", searchValue);
        
        List<MemberVO> voList = memberMapper.selectList(params);
        return voList.stream()
                .map(vo -> modelMapper.map(vo, MemberDTO.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean updateEnabled(String userId, boolean enabled) {
        return memberMapper.updateEnabled(userId, enabled ? "Y" : "N") > 0;
    }
    
    @Override
    public boolean deleteMember(String userId) {
        return memberMapper.deleteMember(userId) > 0;
    }

    @Override
    public int modifyMember(MemberModifyDTO memberModifyDTO) {
        MemberVO vo = modelMapper.map(memberModifyDTO, MemberVO.class);
        return memberMapper.updateMember(vo);
    }

    @Override
    public MemberLoginDTO getLoginMember(String userId){
        MemberVO vo = memberMapper.getLoginMember(userId);
        return modelMapper.map(vo, MemberLoginDTO.class);
    }
}
