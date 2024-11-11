package net.fullstack7.edusecond.edusecond.service;

import net.fullstack7.edusecond.edusecond.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductServiceIf {

    List<ProductDTO> list(int pageNo, int pageSize, int blockSize, String searchCategory, String searchValue);

    public ProductDTO view(Integer productId);
    public int totalCount(String searchCategory, String searchValue);
}
