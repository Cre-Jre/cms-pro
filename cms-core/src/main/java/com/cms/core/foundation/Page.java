package com.cms.core.foundation;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    /**
     * 分页条数
     */
    private Long pageCount;

    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 内容
     */
    private List<T> content;

    public Page(Long pageCount, List<T> content) {
        this.pageCount = pageCount;
        this.content = content;
    }

    public Page(Long pageCount,Integer pages, List<T> content) {
        this.pageCount = pageCount;
        this.content = content;
        this.pages = pages;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
