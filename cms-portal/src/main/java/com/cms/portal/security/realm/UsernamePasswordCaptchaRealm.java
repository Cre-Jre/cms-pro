package com.cms.portal.security.realm;

import com.cms.service.api.CmsUserRoleService;
import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UsernamePasswordCaptchaRealm extends AuthorizingRealm {

    @Autowired
    private CmsUserService cmsUserService;
    @Autowired
    private CmsUserRoleService cmsUserRoleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        CmsUserDto cmsUserDto = (CmsUserDto) principalCollection.getPrimaryPrincipal();
        simpleAuthorizationInfo.addStringPermissions(cmsUserRoleService.selectPermissionsByUserId(cmsUserDto.getId()));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //现在副表中查找用户是否存在
        CmsUserDto cmsUserDto = cmsUserService.selectByUsername(username);
        if (Objects.isNull(cmsUserDto)) {
            throw new UnknownAccountException();
        }
        //校验用户状态 禁用
        verifyStatus(cmsUserDto.getStatus());
        //查询用户主表信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(cmsUserDto, cmsUserDto.getPassword(),
                ByteSource.Util.bytes(cmsUserDto.getSalt()), getName());
        super.clearCachedAuthenticationInfo(simpleAuthenticationInfo.getPrincipals());
        return simpleAuthenticationInfo;
    }

    /**
     * 校验状态
     *
     * @param userStatus 用户状态
     */
    private void verifyStatus(Boolean userStatus) {
        if (BooleanUtils.isFalse(userStatus)) {
            throw new DisabledAccountException("该账号已被禁用,请联系管理员!");
        }
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        CmsUserDto cmsUserDto = (CmsUserDto) principals.getPrimaryPrincipal();
        //如果是超管的话 不再检查权限
        if(BooleanUtils.isTrue(cmsUserDto.getAdministrator())){
            return true;
        }
        return false;
    }
}
