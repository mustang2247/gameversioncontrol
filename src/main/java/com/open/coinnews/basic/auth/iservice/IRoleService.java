package com.open.coinnews.basic.auth.iservice;

import com.open.coinnews.basic.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRoleService extends JpaRepository<Role, Integer> {

    public Role findBySn(String sn);

    public Role findById(Integer id);

    @Query("SELECT rm.mid FROM RoleMenu rm WHERE rm.rid=?1")
    public List<Integer> listRoleMenuIds(Integer roleId);
}
