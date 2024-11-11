package net.fullstack7.edusecond.edusecond.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonSearchDTO {
    private String searchType;
    private String keyword;
    private String sortBy = "regDate";
    private String sortDirection = "DESC";
    private int page = 1;
    private int size = 10;
    
    public int getOffset() {
        return (page - 1) * size;
    }
} 