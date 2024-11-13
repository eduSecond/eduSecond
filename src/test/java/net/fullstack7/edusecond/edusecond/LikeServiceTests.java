package net.fullstack7.edusecond.edusecond;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.service.Like.LikeServiceIf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/root-context.xml"
})
public class LikeServiceTests {
    @Autowired(required = false)
    private LikeServiceIf likeService;

//    @Test
//    public void testLike(){
//        likeService.insertLike("user99", 25);
//    }
//
//    @Test
//    public void testLikeCheck(){
//        likeService.checkExists("user99", 25);
//    }
//
//    @Test
//    public void testLikeDelete(){
//        likeService.deleteLike( "user1", 17);
//    }
}
