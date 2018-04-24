package com.open.coinnews.basic.auth.service;

import com.open.coinnews.basic.auth.iservice.IUserRoleService;
import com.open.coinnews.basic.auth.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleServiceImpl {

    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 添加或删除用户角色对应关系，如果存在则删除，如果不存在则添加
     * @param userId 用户Id
     * @param roleId 角色Id
     */
    public void addOrDelete(Integer userId, Integer roleId) {
        UserRole ur = userRoleService.findByUidAndRid(userId, roleId);
        if(ur==null) {
            ur = new UserRole();
            ur.setRid(roleId); ur.setUid(userId);
            userRoleService.save(ur);
        } else {
            userRoleService.delete(ur);
        }
    }
}
