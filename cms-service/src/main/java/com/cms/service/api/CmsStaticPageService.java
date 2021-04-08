package com.cms.service.api;

import com.cms.service.dto.CmsContentDto;
import freemarker.template.Configuration;

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

    /**
     * 静态化内容
     * @param cmsContentDto     内容对象
     * @param configuration     configuration对象
     */
    void staticContent(CmsContentDto cmsContentDto, Configuration configuration);
}
