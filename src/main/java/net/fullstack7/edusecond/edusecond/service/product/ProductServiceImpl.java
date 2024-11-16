package net.fullstack7.edusecond.edusecond.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.product.ProductImageVO;
import net.fullstack7.edusecond.edusecond.domain.product.ProductVO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductImageDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductRegistDTO;
import net.fullstack7.edusecond.edusecond.dto.product.ProductUpdateDTO;
import net.fullstack7.edusecond.edusecond.mapper.ProductMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import net.fullstack7.edusecond.edusecond.util.CommonFileUtil;

import java.io.IOException;
import java.util.ArrayList;
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
    public List<ProductDTO> list(int pageNo, int pageSize, int pageNavSize, String searchCategory, String searchValue, String productStatus) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", (pageNo - 1) * pageSize);
        params.put("limit", pageSize);
        params.put("searchCategory", searchCategory);
        params.put("searchValue", searchValue);
        params.put("productStatus", productStatus);

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
        ProductVO voInfo = productMapper.selectProductById(productId);
        if (voInfo == null) {
            return null;
        }

        // 상품 정보 가져오기
        ProductDTO dto = modelMapper.map(voInfo, ProductDTO.class);

        //이미지 리스트 가져오기
        List<ProductImageVO> imageVOList = productMapper.selectImagesByProductId(productId);
        List<ProductImageDTO> imageList = new ArrayList<>();
        for(ProductImageVO vo : imageVOList){
            ProductImageDTO imageDTO = modelMapper.map(vo, ProductImageDTO.class);
            imageList.add(imageDTO);
        }
        dto.setImages(imageList);

        // 조회수 증가
        productMapper.updateViewCount(productId);

        return dto;
    }

    @Override
    public int totalCount(String searchCategory, String searchValue, String productStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchCategory", searchCategory);
        map.put("searchValue", searchValue);
        map.put("productStatus", productStatus);
        return productMapper.totalCount(map);
    }

    @Override
    public int totalCountLikedProducts(String searchCategory, String searchValue, String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchCategory", searchCategory);
        map.put("searchValue", searchValue);
        map.put("userId", userId);
        return productMapper.totalCountLikedProducts(map);
    }



    @Override
    public List<ProductImageDTO> getProductImages(int productId) {
        List<ProductImageVO> imageVOs = productMapper.selectProductImages(productId);
        return imageVOs.stream()
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


    @Override
    public List<ProductDTO> selectAllByUser(int pageNo,
                                            int pageSize,
                                            int pageNavSize,
                                            String searchCategory,
                                            String searchValue,
                                            String userId,
                                            String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", (pageNo - 1) * pageSize);
        params.put("limit", pageSize);
        params.put("searchCategory", searchCategory);
        params.put("searchValue", searchValue);
        params.put("userId", userId);

        List<ProductVO> wishList = productMapper.selectAllWishByUser(params);
        List<ProductVO> sellList = productMapper.selectProductsBySeller(params);
        List<ProductVO> list;
        if("wish".equals(type)){
            list = wishList;
        }else{
            list = sellList;
        }

        return list.stream()
                .map(vo -> {
                    ProductDTO dto = modelMapper.map(vo, ProductDTO.class);
                    // 썸���일 이미지 설정
                    ProductImageVO thumbnailImage = productMapper.selectThumbnailImage(vo.getProductId());
                    if (thumbnailImage != null) {
                        dto.setThumbnail(modelMapper.map(thumbnailImage, ProductImageDTO.class));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> selectAllByProductStatus(int pageNo,
                                                     int pageSize,
                                                     int pageNavSize,
                                                     String searchCategory,
                                                     String searchValue,
                                                     String userId,
                                                     String productStatus) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", (pageNo - 1) * pageSize);
        params.put("limit", pageSize);
        params.put("searchCategory", searchCategory);
        params.put("searchValue", searchValue);
        params.put("productStatus", productStatus);
        params.put("userId", userId);
        List<ProductVO> list = productMapper.selectAllByProductStatus(params);
        return list.stream().map(vo -> {
            ProductDTO dto = modelMapper.map(vo, ProductDTO.class);
            // 썸네일 이미지 설정
            ProductImageVO thumbnailImage = productMapper.selectThumbnailImage(vo.getProductId());
            if (thumbnailImage != null) {
                dto.setThumbnail(modelMapper.map(thumbnailImage, ProductImageDTO.class));
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public int totalCountByProductStatus(String searchCategory, String searchValue, String userId, String productStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchCategory", searchCategory);
        map.put("searchValue", searchValue);
        map.put("status", productStatus);
        map.put("userId", userId);
        return productMapper.totalCountByProductStatus(map);
    }

    @Override
    public void deleteProduct(int productId) {
        productMapper.deleteProduct(productId);
    }

    @Override
    @Transactional
    public int updateProduct(ProductUpdateDTO dto, List<MultipartFile> newFiles) throws IOException {
        // 1. 기본 정보 업데이트
        productMapper.updateProduct(dto);

        // 2. 이미지 삭제 처리
        if (dto.getDeleteImageIds() != null && !dto.getDeleteImageIds().isEmpty()) {
            List<ProductImageDTO> currentImages = getProductImages(dto.getProductId());
            
            // 최소 1개의 이미지는 남아있어야 함
            int remainingImages = currentImages.size() - dto.getDeleteImageIds().size();
            int newImageCount = (newFiles != null) ? newFiles.size() : 0;
            
            if (remainingImages + newImageCount < 1) {
                throw new IllegalStateException("최소 1개의 이미지가 필요합니다.");
            }

            for (Integer imageId : dto.getDeleteImageIds()) {
                ProductImageDTO image = modelMapper.map(productMapper.selectImageById(imageId), ProductImageDTO.class);
                if (image != null) {
                    if ("Y".equals(image.getIsMain()) && remainingImages == 0 && newImageCount == 0) {
                        throw new IllegalStateException("대표 이미지는 삭제할 수 없습니다.");
                    }
                    CommonFileUtil.deleteFile(image.getImagePath());
                    productMapper.deleteProductImage(imageId);
                }
            }
        }

        // 3. 새 이미지 추가
        if (newFiles != null && !newFiles.isEmpty()) {
            List<ProductImageDTO> currentImages = getProductImages(dto.getProductId());
            if (currentImages.size() + newFiles.size() > 4) {
                throw new IllegalStateException("이미지는 최대 4개까지만 등록 가능합니다.");
            }

            List<String> uploadFileNames = CommonFileUtil.uploadFiles(newFiles);
            boolean needMainImage = productMapper.hasMainImage(dto.getProductId()) == 0;

            for (int i = 0; i < uploadFileNames.size(); i++) {
                ProductImageDTO productImage = ProductImageDTO.builder()
                        .productId(dto.getProductId())
                        .imagePath(uploadFileNames.get(i))
                        .isMain(needMainImage && i == 0 ? "Y" : "N")
                        .build();
                
                if (needMainImage && i == 0) {
                    productMapper.insertProductImageMain(modelMapper.map(productImage, ProductImageVO.class));
                } else {
                    productMapper.insertProductImage(modelMapper.map(productImage, ProductImageVO.class));
                }
            }
        }

        return 1;
    }
}
