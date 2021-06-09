package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsModelItemDto;

import java.util.List;

public interface CmsModelItemService extends BaseService<CmsModelItemDto,Integer> {

    /**
     * 批量插入
     * @param list
     */
    void batchInsert(List<CmsModelItemDto> list);

    /**
     * 根据模型id和是否栏目模型进行查询
     * @param modelId           模型id
     * @param channelModel      是否栏目模型
     * @return
     */
    List<CmsModelItemDto> getByModelIdAndChannelModel(Integer modelId,Boolean channelModel);

}
