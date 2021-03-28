package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsFriendLinkDto;

import java.util.List;

public interface CmsFriendLinkService extends BaseService<CmsFriendLinkDto,Integer> {
    /**
     * ��ȡ���������б�����
     * @param cmsFriendLinkDto
     * @return
     */
    List<CmsFriendLinkDto> getList(CmsFriendLinkDto cmsFriendLinkDto);
}
