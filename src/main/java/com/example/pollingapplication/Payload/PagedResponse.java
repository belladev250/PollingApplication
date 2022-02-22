package com.example.pollingapplication.Payload;

import java.util.List;

public class PagedResponse <T>{

    private List<T> content;
    private int page;
    private int size;
    private long totalPages;
    private boolean totalElements;
    private boolean last;

    public PagedResponse(List<T> content, int page, int size, long totalPages, boolean totalElements, boolean last) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.last = last;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isTotalElements() {
        return totalElements;
    }

    public void setTotalElements(boolean totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
