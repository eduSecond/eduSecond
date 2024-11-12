package net.fullstack7.edusecond.edusecond.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Paging {
    private int pageNo;       // 현재 페이지 번호
    private int pageSize;     // 페이지당 데이터 수
    private int blockSize;    // 블록당 페이지 수
    private int totalCnt;     // 전체 데이터 수

    public int getStartIdx() {
        return (pageNo - 1) * pageSize;
    }

    public int getTotalPage() {
        return (int) Math.ceil((double) totalCnt / pageSize);
    }

    public int getStartBlockPage() {
        return ((pageNo - 1) / blockSize) * blockSize + 1;
    }

    public int getEndBlockPage() {
        int endPage = getStartBlockPage() + blockSize - 1;
        return Math.min(endPage, getTotalPage());
    }

    public boolean getPrevBlock() {
        return getStartBlockPage() > 1;
    }

    public boolean getNextBlock() {
        return getEndBlockPage() < getTotalPage();
    }
} 