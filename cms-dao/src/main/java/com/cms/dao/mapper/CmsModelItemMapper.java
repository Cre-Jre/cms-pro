package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsModelItemEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsModelItemMapper extends BaseMapper<CmsModelItemEntity, Integer> {

    /**
     * 根据模型id和模型类型进行查询
     * @param modelId
     * @param channelModel
     * @return
     */
    List<CmsModelItemEntity> selectByModelIdAndChannelModel(@Param("modelId")Integer modelId,@Param("channelModel") Boolean channelModel);

}
