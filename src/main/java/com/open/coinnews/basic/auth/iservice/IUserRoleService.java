package com.open.coinnews.basic.auth.iservice;

import com.open.coinnews.basic.auth.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRoleService extends JpaRepository<UserRole, Integer> {

    @Query("SELECT ur.rid FROM UserRole ur WHERE ur.uid=:userId")
    public List<Integer> queryRoleIds(@Param("userId") Integer userId);

    public UserRole findByUidAndRid(Integer uid, Integer rid);
}
