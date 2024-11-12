package net.fullstack7.edusecond.edusecond;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.product.ProductImageVO;
import net.fullstack7.edusecond.edusecond.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/config/root-context.xml")
public class ProductMapperTests {

    @Autowired(required = false)
    private ProductMapper productMapper;

    @Test
    public void testUpdateProductImageMain(){
        ProductImageVO vo = ProductImageVO.builder()
                        .productId(2)
                                .imagePath("testImagePath").build();
        productMapper.insertProductImageMain(vo);
    }
}
