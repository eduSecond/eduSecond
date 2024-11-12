package net.fullstack7.edusecond.edusecond.service.Like;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.product.LikeVO;
import net.fullstack7.edusecond.edusecond.dto.product.LikeDTO;
import net.fullstack7.edusecond.edusecond.mapper.LikeMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
@Log4j2
public class LikeServiceImpl implements LikeServiceIf{

    public final ModelMapper modelMapper;
    public final LikeMapper likeMapper;

    @Override
    public void insertLike(String userId, Integer productId) {
        int result = likeMapper.checkExists(userId, productId);
        log.info("2");
        if (result == 0){
            log.info("3");
            likeMapper.insertLike(userId, productId);
        }
        else{
            throw new IllegalArgumentException("이미 좋아요를 누름");
        }
    }

    @Override
    public void deleteLike(Integer wishId) {
        likeMapper.deleteLike(wishId);
    }

    @Override
    public void getLike(Integer productId, String userId) {
        likeMapper.getLike(productId, userId);
    }
}
