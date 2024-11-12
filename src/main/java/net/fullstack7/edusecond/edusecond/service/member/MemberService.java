package net.fullstack7.edusecond.edusecond.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.MemberVO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberDTO;
import net.fullstack7.edusecond.edusecond.dto.member.MemberRegistDTO;
import net.fullstack7.edusecond.edusecond.mapper.MemberMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;
    
    public MemberDTO getMember(String userId) {
        MemberVO vo = memberMapper.selectMemberById(userId);
        return modelMapper.map(vo, MemberDTO.class);
    }
    
    public void registerMember(MemberRegistDTO registDTO) {
        MemberVO vo = modelMapper.map(registDTO, MemberVO.class);
        memberMapper.insertMember(vo);
    }
}
