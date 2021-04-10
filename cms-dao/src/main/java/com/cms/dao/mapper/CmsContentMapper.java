package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsContentEntity;

import java.util.List;

public interface CmsContentMapper extends BaseMapper<CmsContentEntity,Integer> {

    /**
     * 根据指令获取内容列表
     * @param inner
     * @return
     */
    List<CmsContentEntity> getListByDirective(SearchProvider.Inner inner);

}
