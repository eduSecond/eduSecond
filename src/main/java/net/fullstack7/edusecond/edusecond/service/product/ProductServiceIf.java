package net.fullstack7.edusecond.edusecond.service.product;

import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductImageDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductRegistDTO;

import java.util.List;

public interface ProductServiceIf {

    List<ProductDTO> list(int pageNo, int pageSize, int blockSize, String searchCategory, String searchValue);

    public ProductDTO view(Integer productId);
    public int totalCount(String searchCategory, String searchValue);
    public int insertProduct(ProductRegistDTO productRegistDTO);
    public void insertProductImage(int productId, List<String> imagePaths);
    public int getLastProductId();
}
