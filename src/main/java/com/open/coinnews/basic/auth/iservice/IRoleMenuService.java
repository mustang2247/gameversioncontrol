package com.open.coinnews.basic.auth.iservice;

import com.open.coinnews.basic.auth.model.Role;
import com.open.coinnews.basic.auth.model.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRoleMenuService extends JpaRepository<RoleMenu, Integer>, JpaSpecificationExecutor<Role> {

    @Query("SELECT rm.mid FROM RoleMenu rm WHERE rm.rid=:roleId")
    public List<Integer> queryMenuIds(@Param("roleId") Integer roleId);

    public RoleMenu queryByRidAndMid(Integer rid, Integer mid);
}
