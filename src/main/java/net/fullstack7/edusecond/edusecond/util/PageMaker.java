package net.fullstack7.edusecond.edusecond.util;

import lombok.Data;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
@Data
public class PageMaker {
    private String searchType;
    private String keyword;
    private int totalPage;
    private int startPage;
    private int endPage;
    private int currentPage;
    private boolean prev;
    private boolean next;
    private static final int DISPLAY_PAGE_NUM = 5;
    private static final int PAGE_SIZE = 10;
    
    public PageInfo getPageInfo(CommonSearchDTO searchDTO, int totalCount) {
        this.searchType = searchDTO.getSearchType();
        this.keyword = searchDTO.getKeyword();
        this.currentPage = searchDTO.getPage();
        
        this.totalPage = (int) Math.ceil(totalCount / (double)PAGE_SIZE);
        this.startPage = Math.max(1, currentPage - (DISPLAY_PAGE_NUM / 2));
        this.endPage = startPage + DISPLAY_PAGE_NUM - 1;
        
        if (endPage > totalPage) {
            endPage = totalPage;
            startPage = Math.max(1, endPage - DISPLAY_PAGE_NUM + 1);
        }
    
        this.prev = startPage > 1;
        this.next = endPage < totalPage;

        return PageInfo.builder()
                .currentPage(currentPage)
                .totalPage(totalPage)
                .startPage(startPage)
                .endPage(endPage)
                .prev(prev)
                .next(next)
                .searchType(searchType)
                .keyword(keyword)
                .build();
    }
    
    @Data
    @Builder
    public static class PageInfo {
        private int currentPage;
        private int totalPage;
        private int startPage;
        private int endPage;
        private boolean prev;
        private boolean next;
        private String searchType;
        private String keyword;
    }
}
