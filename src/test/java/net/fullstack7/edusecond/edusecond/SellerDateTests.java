package net.fullstack7.edusecond.edusecond;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.product.ProductRegistDTO;
import net.fullstack7.edusecond.edusecond.service.notice.NoticeServiceIf;
import net.fullstack7.edusecond.edusecond.service.product.ProductServiceIf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/config/root-context.xml")
public class SellerDateTests {

    @Autowired(required = false)
    private ProductServiceIf productService;

    @Test
    public void productInsert() {

        try{
            for (int i = 1; i <= 10; i++) {
                ProductRegistDTO dto = ProductRegistDTO.builder()
                        .productId(i)
                        .sellerId("tester1")
                        .productName("가방"+ i)
                        .productDesc("예쁜 가방이에요" + i)
                        .price(12000)
                        .quantity(2)
                        .quality("상")
                        .build();
                productService.insertProduct(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void productSeller() {

        productService.SellerList(1, 1,1,"searchType","searchValue","user50");

    }
}
