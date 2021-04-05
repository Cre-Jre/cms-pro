package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsModelItemDto;

import java.util.List;

public interface CmsModelItemService extends BaseService<CmsModelItemDto,Integer> {

    /**
     * ��������
     * @param list
     */
    void batchInsert(List<CmsModelItemDto> list);

    /**
     * ����ģ��id���Ƿ���Ŀģ�ͽ��в�ѯ
     * @param modelId           ģ��id
     * @param channelModel      �Ƿ���Ŀģ��
     * @return
     */
    List<CmsModelItemDto> getByModelIdAndChannelModel(Integer modelId,Boolean channelModel);

}
