package net.fullstack7.edusecond.edusecond.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.ProductVO;
import net.fullstack7.edusecond.edusecond.dto.ProductDTO;
import net.fullstack7.edusecond.edusecond.mapper.ProductMapper;
import net.fullstack7.edusecond.edusecond.util.Paging;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductServiceIf {
    private final ProductMapper productMapper;
    private final ModelMapper modelMapper;

    @Override
    public int totalCount(String searchCategory, String searchValue){
        if(searchCategory != null && !searchCategory.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()){
            if (!("productName".equals(searchCategory) || "sellerId".equals(searchCategory))) {
                throw new IllegalArgumentException("검색 카테고리 없음 " + searchCategory);
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchCategory", searchCategory);
        map.put("searchValue", searchValue);
        int result = productMapper.totalCount(map);
        return result;
    }


    @Override
    public List<ProductDTO> list(int pageNo, int pageSize, int blockSize, String searchCategory, String searchValue) {
        Paging paging = new Paging(pageNo, pageSize, blockSize, this.totalCount(searchCategory, searchValue));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", pageSize);
        map.put("startIdx", paging.getStartIdx());
        map.put("searchCategory", searchCategory);
        map.put("searchValue", searchValue);
        List<ProductVO> voList = productMapper.selectAllProducts(map);
        List<ProductDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, ProductDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public ProductDTO view(Integer productId) {
        ProductVO vo = productMapper.selectProductById(productId);
        ProductDTO dto = modelMapper.map(vo, ProductDTO.class);
        return dto;
    }
}
