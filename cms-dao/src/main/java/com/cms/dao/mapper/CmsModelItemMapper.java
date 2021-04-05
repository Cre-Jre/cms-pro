package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsModelItemEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsModelItemMapper extends BaseMapper<CmsModelItemEntity, Integer> {

    /**
     * ����ģ��id��ģ�����ͽ��в�ѯ
     * @param modelId
     * @param channelModel
     * @return
     */
    List<CmsModelItemEntity> selectByModelIdAndChannelModel(@Param("modelId")Integer modelId,@Param("channelModel") Boolean channelModel);

}
