package net.fullstack7.edusecond.edusecond;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.MemberVO;
import net.fullstack7.edusecond.edusecond.domain.ProductVO;
import net.fullstack7.edusecond.edusecond.dto.ProductDTO;
import net.fullstack7.edusecond.edusecond.service.ProductServiceIf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/config/root-context.xml")
public class ProductServiceTests {

    @Autowired(required = false)
    private ProductServiceIf productService;

//    @Test
//    public void testList(){
//        List<ProductDTO> list = productService.list();
//        Assertions.assertNotNull(list);
//    }

    @Test
    public void testView(){
        ProductDTO product = productService.view(1);
        assertNotNull(product);
        log.info(product);
        assertEquals("공책", product.getProductName());
    }
}


