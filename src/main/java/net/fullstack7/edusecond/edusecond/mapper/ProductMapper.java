package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.domain.product.ProductImageVO;
import net.fullstack7.edusecond.edusecond.domain.product.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    ProductVO selectProductById(Integer productId);
    List<ProductVO> selectAllProducts(Map<String, Object> map);
    List<ProductVO> selectProductsBySeller(String sellerId);
    int insertProduct(ProductVO productVO);
    void insertProductImageMain(ProductImageVO productVO);
    void insertProductImage(ProductImageVO productVO);
    void updateProduct(ProductVO productVO);
    void deleteProduct(Integer productId);
    void updateViewCount(Integer productId);
    int getLastProductId();
    int totalCount(Map<String, Object> map);
}
