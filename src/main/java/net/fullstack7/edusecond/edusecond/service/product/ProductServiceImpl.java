package net.fullstack7.edusecond.edusecond.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.mapper.ProductMapper;
import net.fullstack7.edusecond.edusecond.domain.product.ProductVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import net.fullstack7.edusecond.edusecond.util.Paging;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductServiceIf {
    private final ProductMapper productMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDTO> list(int pageNo, int pageSize, int blockSize, 
                               String searchCategory, String searchValue) {
        Paging paging = new Paging(pageNo, pageSize, blockSize, 
                                 this.totalCount(searchCategory, searchValue));
        
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize", pageSize);
        map.put("startIdx", paging.getStartIdx());
        map.put("searchCategory", searchCategory);
        map.put("searchValue", searchValue);
        
        List<ProductVO> voList = productMapper.selectAllProducts(map);
        return voList.stream()
                    .map(vo -> modelMapper.map(vo, ProductDTO.class))
                    .collect(Collectors.toList());
    }

    @Override
    public ProductDTO view(Integer productId) {
        ProductVO vo = productMapper.selectProductById(productId);
        return modelMapper.map(vo, ProductDTO.class);
    }

    @Override
    public int totalCount(String searchCategory, String searchValue) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchCategory", searchCategory);
        map.put("searchValue", searchValue);
        return productMapper.totalCount(map);
    }
}
