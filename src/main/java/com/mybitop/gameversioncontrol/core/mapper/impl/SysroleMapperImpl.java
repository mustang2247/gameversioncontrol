package com.mybitop.gameversioncontrol.core.mapper.impl;

import com.mybitop.gameversioncontrol.core.entity.Sysrole;
import com.mybitop.gameversioncontrol.core.mapper.SysroleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysroleMapperImpl implements SysroleMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return jdbcTemplate.update("DELETE from TABLE sysrole where id=?",id);
    }

    @Override
    public int insert(Sysrole record) {
        String sql = "insert into sysrole(available, description, role) values (?, ?, ?)";

        return jdbcTemplate.update(sql, record.getAvailable(), record.getDescription(), record.getRole());
    }

    @Override
    public List<Sysrole> select() {
        List<Sysrole> list = jdbcTemplate.query("select * from sysrole", new Object[]{}, new BeanPropertyRowMapper(Sysrole.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Sysrole selectByPrimaryKey(Long id) {
        List<Sysrole> list = jdbcTemplate.query("select * from sysrole where uid = ?", new Object[]{id}, new BeanPropertyRowMapper(Sysrole.class));
        if(list!=null && list.size()>0){
            Sysrole sysrole = list.get(0);
            return sysrole;
        }else{
            return null;
        }
    }

    @Override
    public int update(Sysrole record) {
        return jdbcTemplate.update("UPDATE  sysrole SET available=?, description=?, role=? WHERE id=?",
                record.getAvailable(), record.getDescription(), record.getRole(), record.getId()
        );
    }
}
