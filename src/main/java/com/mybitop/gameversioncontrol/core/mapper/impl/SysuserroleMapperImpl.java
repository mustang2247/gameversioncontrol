package com.mybitop.gameversioncontrol.core.mapper.impl;

import com.mybitop.gameversioncontrol.core.entity.Sysuserrole;
import com.mybitop.gameversioncontrol.core.mapper.SysuserroleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysuserroleMapperImpl implements SysuserroleMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Sysuserrole record) {
        String sql = "insert into sysuserrole(uid, roleId) values (?, ?)";

        return jdbcTemplate.update(sql, record.getUid(), record.getRoleId());
    }

    @Override
    public List<Sysuserrole> select(long uid) {
        List<Sysuserrole> list = jdbcTemplate.query("select * from sysuserrole where uid = ?", new Object[]{uid}, new BeanPropertyRowMapper(Sysuserrole.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
}
