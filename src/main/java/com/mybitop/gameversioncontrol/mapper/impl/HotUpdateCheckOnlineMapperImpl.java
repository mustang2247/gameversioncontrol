package com.mybitop.gameversioncontrol.mapper.impl;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheckonline;
import com.mybitop.gameversioncontrol.mapper.HotUpdateCheckOnlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotUpdateCheckOnlineMapperImpl implements HotUpdateCheckOnlineMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jdbcTemplate.update("DELETE from hotupdatecheckonline where id=?",id);
    }

    @Override
    public int deleteHotupdatecheckonlineByAppidAndChannelid(String appid, String channelid) {
        return jdbcTemplate.update("DELETE from hotupdatecheckonline where appid=? and  channelid=?",appid, channelid);
    }

    @Override
    public int save(Hotupdatecheckonline record) {
        return jdbcTemplate.update("insert into hotupdatecheckonline(appid,appname,channelid,channelname,appversion," +
                        "updatestrategy,jumpappmarket,baseurl,apkurl,promptcollection,forcecollection," +
                        "excludecollection,updateinfo,createtime,updatetime) " +
                        "values(?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)",
                record.getAppid(), record.getAppname(),record.getChannelid(), record.getChannelname(), record.getAppversion(),
                record.getUpdatestrategy(), record.getJumpappmarket(), record.getBaseurl(), record.getApkurl(), record.getPromptcollection(), record.getForcecollection(),
                record.getExcludecollection(),record.getUpdateinfo(), record.getCreatetime(), record.getUpdatetime());
    }

    @Override
    public List<Hotupdatecheckonline> findAll() {
        List<Hotupdatecheckonline> list = jdbcTemplate.query("select * from hotupdatecheckonline", new Object[]{}, new BeanPropertyRowMapper(Hotupdatecheckonline.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Hotupdatecheckonline findHotupdatecheckById(Integer id) {
        List<Hotupdatecheckonline> list = jdbcTemplate.query("select * from hotupdatecheckonline where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Hotupdatecheckonline.class));
        if(list!=null && list.size()>0){
            Hotupdatecheckonline hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public Hotupdatecheckonline findHotupdatecheckonlineByAppidAndChannelid(String appid, String channelid) {
        List<Hotupdatecheckonline> list = jdbcTemplate.query("select * from hotupdatecheckonline where appid = ? AND channelid = ?", new Object[]{appid, channelid}, new BeanPropertyRowMapper(Hotupdatecheckonline.class));
        if(list!=null && list.size()>0){
            Hotupdatecheckonline hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public int update(Hotupdatecheckonline record) {
        return jdbcTemplate.update("UPDATE hotupdatecheckonline SET appname=?,channelname=?,appversion=?,updatestrategy=?,jumpappmarket=?,baseurl=?," +
                        "apkurl=?,promptcollection=?,forcecollection=?,excludecollection=?,updateinfo=? WHERE appid=? AND channelid=?",
                record.getAppname(),record.getChannelname(), record.getAppversion(),
                record.getUpdatestrategy(), record.getJumpappmarket(), record.getBaseurl(), record.getApkurl(), record.getPromptcollection(), record.getForcecollection(),
                record.getExcludecollection(),record.getUpdateinfo(), record.getAppid(), record.getChannelid());
    }
}
