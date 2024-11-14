package net.fullstack7.edusecond.edusecond;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.service.notice.NoticeServiceIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/config/root-context.xml")
public class InserNoticeTests {
    @Autowired(required = false)
    private NoticeServiceIf noticeService;

//    @Test
//    public void insert() {
//        try {
//            for (int i = 1; i <= 30; i++) {
//                NoticeDTO noticeDTO = new NoticeDTO();
//                noticeDTO.setTitle("공지사항 입니다 " + i);
//                noticeDTO.setContent("공지사항 내용입니다. 아주 중요 합니다. " + i);
//                noticeDTO.setAdminId("admin1");
//                noticeDTO.setNoticeId(i);
//
//                noticeService.create(noticeDTO);
//                log.info("Notice inserted successfully: " + noticeDTO);
//            }
//        } catch (Exception e) {
//            log.error("Error inserting notice", e);
//        }
//    }

}
