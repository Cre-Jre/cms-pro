package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsTopicDto;

import java.util.List;

public interface CmsTopicService extends BaseService<CmsTopicDto,Integer> {
    /**
     * ����ҳ�������ѯ
     * @param count        ����
     * @return              list
     */
    List<CmsTopicDto> getListByTag(int count);
}
