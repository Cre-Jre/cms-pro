package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsRoleDto;

import java.util.List;

public interface CmsRoleService extends BaseService<CmsRoleDto,Integer> {
    /**
     * 获取角色列表
     * @return
     */
    List<CmsRoleDto> getList();
}
