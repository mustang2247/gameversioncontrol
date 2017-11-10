package com.mybitop.gameversioncontrol.core.mapper.impl;

import com.mybitop.gameversioncontrol.core.entity.Sysrolepermission;
import com.mybitop.gameversioncontrol.core.mapper.SysrolepermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysrolepermissionMapperImpl implements SysrolepermissionMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Sysrolepermission record) {
        String sql = "insert into sysrolepermission(roleId, permissionId) values (?, ?)";

        return jdbcTemplate.update(sql, record.getRoleId(), record.getPermissionId());
    }

    @Override
    public List<Sysrolepermission> select(long roleid) {
        List<Sysrolepermission> list = jdbcTemplate.query("select * from sysrolepermission where uid = ?", new Object[]{roleid}, new BeanPropertyRowMapper(Sysrolepermission.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
}
