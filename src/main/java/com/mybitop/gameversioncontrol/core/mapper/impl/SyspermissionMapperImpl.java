package com.mybitop.gameversioncontrol.core.mapper.impl;

import com.mybitop.gameversioncontrol.core.entity.Syspermission;
import com.mybitop.gameversioncontrol.core.mapper.SyspermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SyspermissionMapperImpl implements SyspermissionMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return jdbcTemplate.update("DELETE from TABLE syspermission where id=?",id);
    }

    @Override
    public int insert(Syspermission record) {
        String sql = "insert into syspermission(available, name, parentId, parentIds, permission, resourceType, url) values (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, record.getAvailable(), record.getName(), record.getParentId(), record.getParentIds(), record.getPermission(), record.getResourceType(), record.getUrl());
    }

    @Override
    public List<Syspermission> select() {
        List<Syspermission> list = jdbcTemplate.query("select * from syspermission", new Object[]{}, new BeanPropertyRowMapper(Syspermission.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Syspermission selectByPrimaryKey(Long id) {
        List<Syspermission> list = jdbcTemplate.query("select * from syspermission where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Syspermission.class));
        if(list!=null && list.size()>0){
            Syspermission syspermission = list.get(0);
            return syspermission;
        }else{
            return null;
        }
    }

    @Override
    public int update(Syspermission record) {
        return jdbcTemplate.update("UPDATE  syspermission SET available=?, name=?, parentId=?, parentIds=?, permission=?, resourceType=?, url=? WHERE id=?",
                record.getAvailable(), record.getName(), record.getParentId(), record.getParentIds(), record.getPermission(), record.getResourceType(), record.getUrl(), record.getId()
        );
    }
}
