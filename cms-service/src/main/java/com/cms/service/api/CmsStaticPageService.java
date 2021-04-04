package com.cms.service.api;

public interface CmsStaticPageService {
    /**
     * 静态化首页
     */
    void staticIndex();

    /**
     * 删除静态化首页
     * @return      布尔值
     */
    boolean deleteIndex();
}
