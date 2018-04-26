package com.open.coinnews.basic.auth.iservice;

import com.open.coinnews.basic.auth.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMenuService extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {

    @Query("SELECT m FROM Menu m WHERE m.display=1 AND m.type='1' AND m.id in (SELECT rm.mid FROM RoleMenu rm WHERE rm.rid IN (SELECT ur.rid FROM UserRole ur where ur.uid=?1))")
    public List<Menu> findByUser(Integer userId);

    public Menu findBySn(String sn);

    @Query("SELECT m.sn FROM Menu m WHERE m.display=1 AND m.id in (SELECT rm.mid FROM RoleMenu rm WHERE rm.rid IN (SELECT ur.rid FROM UserRole ur where ur.uid=?1))")
    public List<String> listAuthByUser(Integer userId);

    @Query("FROM Menu m WHERE m.href is not null AND m.href!='' AND m.href!='#' ")
    public List<Menu> listAllUrlMenu();

    public Menu findById(Integer id);
}
