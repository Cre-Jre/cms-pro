package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsUserDto;

public interface CmsUserService extends BaseService<CmsUserDto,Integer> {

    /**
     * 根据用户名查找
     *
     * @param username
     * @return
     */
    CmsUserDto selectByUsername(String username);

    /**
     * 根据邮箱查询
     * @param email   邮箱
     * @return
     */
    CmsUserDto selectByEmail(String email);

    /**
     * 修改用户登录次数
     * @param id   用户id
     */
    void updateLoginCount(Integer id);
}
