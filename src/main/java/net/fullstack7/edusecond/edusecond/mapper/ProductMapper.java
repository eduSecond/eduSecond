package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.domain.product.ProductVO;
import net.fullstack7.edusecond.edusecond.domain.product.ProductImageVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    ProductVO selectProductById(Integer productId);
    List<ProductVO> selectAllProducts(Map<String, Object> map);
    List<ProductVO> selectProductsBySeller(String sellerId);
    void insertProduct(ProductVO productVO);
    void updateProduct(ProductVO productVO);
    void deleteProduct(Integer productId);
    void updateViewCount(Integer productId);

    int totalCount(Map<String, Object> map);

    List<ProductImageVO> selectProductImages(int productId);

    ProductImageVO selectThumbnailImage(int productId);
}
