package com.augmentum.exam.model;

import java.util.List;

import com.augmentum.exam.jdbc.PropertyUtil;

public class PageBean<T> {
    private static final String PAGE_SIZE="pagination.pageSize";
    private int totalCount;
    public  int pageSize = Integer.parseInt(PropertyUtil.getProperty(PAGE_SIZE));
    private int pageCount;
    private int currentPage;
    private List<T> data;

    public PageBean() {
        super();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        if (totalCount < 1) {
            pageCount = 0;
            return pageCount;
        }
        this.pageCount = ((totalCount -1) / getPageSize()) + 1;
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage() {
        if (currentPage < 1){
            currentPage = 1;
        } else if (currentPage >= getTotalCount()) {
            currentPage = totalCount;
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageBean [totalCount=" + totalCount + ", pageCount=" + getPageCount() + ", currentPage=" + currentPage
                + ", data=" + data + "]";
    }
}
