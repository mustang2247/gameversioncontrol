package com.mybitop.gameversioncontrol.core.mapper.impl;

import com.mybitop.gameversioncontrol.core.entity.Userinfo;
import com.mybitop.gameversioncontrol.core.mapper.UserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserinfoMapperImpl implements UserinfoMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByPrimaryKey(Long uid) {
        return jdbcTemplate.update("DELETE from TABLE userinfo where uid=?",uid);
    }

    @Override
    public int insert(Userinfo record) {
        String sql = "insert into userinfo(name, username, password, salt, state) values (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, record.getName(), record.getUsername(), record.getPassword(), record.getSalt(), record.getState());
    }

    @Override
    public List<Userinfo> select() {
        List<Userinfo> list = jdbcTemplate.query("select * from userinfo", new Object[]{}, new BeanPropertyRowMapper(Userinfo.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Userinfo selectByPrimaryKey(Long uid) {
        List<Userinfo> list = jdbcTemplate.query("select * from userinfo where uid = ?", new Object[]{uid}, new BeanPropertyRowMapper(Userinfo.class));
        if(list!=null && list.size()>0){
            Userinfo userinfo = list.get(0);
            return userinfo;
        }else{
            return null;
        }
    }

    @Override
    public Userinfo selectByUsername(String username) {
        List<Userinfo> list = jdbcTemplate.query("select * from userinfo where username = ?", new Object[]{username}, new BeanPropertyRowMapper(Userinfo.class));
        if(list!=null && list.size()>0){
            Userinfo userinfo = list.get(0);
            return userinfo;
        }else{
            return null;
        }
    }

    @Override
    public int update(Userinfo record) {
        return jdbcTemplate.update("UPDATE  userinfo SET name=?, username=?, password=?, salt=?, state=? WHERE uid=?",
                record.getName(), record.getUsername(), record.getPassword(), record.getSalt(), record.getState(), record.getUid()
                );
    }
}
