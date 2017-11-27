package com.mybitop.gameversioncontrol.mapper.impl;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;
import com.mybitop.gameversioncontrol.mapper.HotUpdateNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotUpdateNoticeMapperImpl implements HotUpdateNoticeMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Hotupdatenotice findByAppid(String appid) {
        List<Hotupdatenotice> list = jdbcTemplate.query("select * from hotupdatenotice where appid = ?", new Object[]{appid}, new BeanPropertyRowMapper(Hotupdatenotice.class));
        if(list!=null && list.size()>0){
            Hotupdatenotice hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public Hotupdatenotice findById(Integer id) {
        List<Hotupdatenotice> list = jdbcTemplate.query("select * from hotupdatenotice where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Hotupdatenotice.class));
        if(list!=null && list.size()>0){
            Hotupdatenotice hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public int deleteHotupdatenoticeByAppid(String appid) {
        return jdbcTemplate.update("DELETE from TABLE hotupdatenotice where appid=?",appid);
    }

    @Override
    public int deleteHotupdatenoticeById(Integer id) {
        return jdbcTemplate.update("DELETE from TABLE hotupdatenotice where id=?",id);
    }

    @Override
    public int updateHotUpdateCheckOnlineById(Hotupdatenotice record) {
        return jdbcTemplate.update("UPDATE  hotupdatenotice SET appname=?,appversion=?,updatemessage=? WHERE id=?",
                record.getAppname(), record.getAppversion(), record.getUpdatemessage(), record.getId());
    }

    @Override
    public int save(Hotupdatenotice record) {
        return jdbcTemplate.update("insert into hotupdatenotice(appid,appname,appversion,updatemessage,createtime, updatetime) " +
                        "values(?, ?, ?, ?, ?, ?)", record.getAppid(), record.getAppname(), record.getAppversion(), record.getUpdatemessage(), record.getCreatetime(), record.getUpdatetime());
    }

    @Override
    public List<Hotupdatenotice> findAll() {
        List<Hotupdatenotice> list = jdbcTemplate.query("select * from hotupdatenotice", new Object[]{}, new BeanPropertyRowMapper(Hotupdatenotice.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

}
