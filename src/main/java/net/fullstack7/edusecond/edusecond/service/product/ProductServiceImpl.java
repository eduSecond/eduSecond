package net.fullstack7.edusecond.edusecond.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.product.ProductImageVO;
import net.fullstack7.edusecond.edusecond.domain.product.ProductVO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductImageDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductRegistDTO;
import net.fullstack7.edusecond.edusecond.mapper.ProductMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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
    public List<ProductDTO> list(int pageNo, int pageSize, int pageNavSize, String searchType, String searchValue) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", (pageNo - 1) * pageSize);
        params.put("limit", pageSize);
        params.put("searchType", searchType);
        params.put("searchValue", searchValue);

        List<ProductVO> voList = productMapper.selectAllProducts(params);
        return voList.stream()
                .map(vo -> {
                    ProductDTO dto = modelMapper.map(vo, ProductDTO.class);
                    // 썸네일 이미지 설정
                    ProductImageVO thumbnailImage = productMapper.selectThumbnailImage(vo.getProductId());
                    if (thumbnailImage != null) {
                        dto.setThumbnail(modelMapper.map(thumbnailImage, ProductImageDTO.class));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO view(Integer productId) {
        ProductVO vo = productMapper.selectProductById(productId);
        if (vo == null) {
            return null;
        }

        ProductDTO dto = modelMapper.map(vo, ProductDTO.class);

        // 썸네일 이미지 설정
        ProductImageVO thumbnailVO = productMapper.selectThumbnailImage(productId);
        if (thumbnailVO != null) {
            dto.setThumbnail(modelMapper.map(thumbnailVO, ProductImageDTO.class));
        }

        // 조회수 증가
        productMapper.updateViewCount(productId);

        return dto;
    }

    @Override
    public int totalCount(String searchCategory, String searchValue) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchCategory", searchCategory);
        map.put("searchValue", searchValue);
        return productMapper.totalCount(map);
    }

    @Override
    public List<ProductImageDTO> getProductImages(int productId) {
        List<ProductImageVO> voList = productMapper.selectProductImages(productId);
        return voList.stream()
                .map(vo -> modelMapper.map(vo, ProductImageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductImageDTO getThumbnailImage(int productId) {
        ProductImageVO vo = productMapper.selectThumbnailImage(productId);
        return vo != null ? modelMapper.map(vo, ProductImageDTO.class) : null;
    }

    @Override
    public int insertProduct(ProductRegistDTO productRegistDTO) {
        return productMapper.insertProduct(modelMapper.map(productRegistDTO, ProductVO.class));
    }

    @Override
    public int getLastProductId(){
        return productMapper.getLastProductId();
    }

    @Override
    public void insertProductImage(int productId, List<String> uploadFilePaths) {
        if (uploadFilePaths == null || uploadFilePaths.isEmpty()) {
            return;
        }

        ProductImageVO mainImage = ProductImageVO.builder()
                .productId(productId)
                .imagePath(uploadFilePaths.get(0))
                .build();
        productMapper.insertProductImageMain(mainImage);


        for (int i = 1; i < uploadFilePaths.size(); i++) {
            ProductImageVO productImage = ProductImageVO.builder()
                    .productId(productId)
                    .imagePath(uploadFilePaths.get(i))
                    .build();
            productMapper.insertProductImage(productImage);
        }
    }


}
