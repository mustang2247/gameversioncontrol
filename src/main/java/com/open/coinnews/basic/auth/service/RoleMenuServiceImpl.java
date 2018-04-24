package com.open.coinnews.basic.auth.service;

import com.open.coinnews.basic.auth.iservice.IRoleMenuService;
import com.open.coinnews.basic.auth.model.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMenuServiceImpl {

    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * 添加或删除对象，如果存在则删除，如果不存在则添加
     * @param roleId 角色Id
     * @param menuId 菜单Id
     */
    public void addOrDelete(Integer roleId, Integer menuId) {
        RoleMenu rm = roleMenuService.queryByRidAndMid(roleId, menuId);
        if(rm==null) {
            rm = new RoleMenu();
            rm.setMid(menuId); rm.setRid(roleId);
            roleMenuService.save(rm);
        } else {
            roleMenuService.delete(rm);
        }
    }
}
