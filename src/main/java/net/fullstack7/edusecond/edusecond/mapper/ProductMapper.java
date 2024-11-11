package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {
    ProductVO selectProductById(Integer productId);
    List<ProductVO> selectAllProducts();
    List<ProductVO> selectProductsBySeller(String sellerId);
    void insertProduct(ProductVO productVO);
    void updateProduct(ProductVO productVO);
    void deleteProduct(Integer productId);
    void updateViewCount(Integer productId);
}
