package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.dao.entity.CmsUserEntity;
import com.cms.service.dto.CmsUserDto;

public interface CmsUserService extends BaseService<CmsUserDto,Integer> {

    /**
     * 根据用户名查找
     *
     * @param username
     * @return
     */
    CmsUserDto selectByUsername(String username);
}
