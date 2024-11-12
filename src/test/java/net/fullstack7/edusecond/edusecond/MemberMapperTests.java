package net.fullstack7.edusecond.edusecond;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.member.MemberVO;
import net.fullstack7.edusecond.edusecond.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/config/root-context.xml")
public class MemberMapperTests {

    @Autowired(required = false)
    private MemberMapper memberMapper;

    @Test
    public void testGetTime() {
        log.info("memberMapper: " + memberMapper);
        assertNotNull(memberMapper);
    }

    @Test
    public void testInsert() {
        MemberVO member = MemberVO.builder()
                .userId("tester2")
                .userPw("test123!@#")
                .userName("테스터")
                .userEmail("test@test.com")
                .userPhone("010-1234-5678")
                .userAddress("서울시 강남구 테스트동")
                .userPostcode("12345")
                .userBirth(LocalDate.of(1990, 1, 1))
                .build();

        memberMapper.insertMember(member);
        log.info("회원 등록 완료");
    }

    @Test
    public void testSelect() {
        MemberVO member = memberMapper.selectMemberById("tester1");
        assertNotNull(member);
        log.info(member);
        assertEquals("테스터", member.getUserName());
    }
}
