package com.mybitop.gameversioncontrol.mapper.impl;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.mapper.HotupdatecheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotupdatecheckMapperImpl implements HotupdatecheckMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jdbcTemplate.update("DELETE from TABLE hotupdatecheck where id=?",id);
    }

    @Override
    public int insert(Hotupdatecheck record) {
        return jdbcTemplate.update("insert into hotupdatecheck(appid,appname,channelid,channelname,appVersion," +
                        "updatestrategy,baseurl,apkurl,promptcollection,forcecollection," +
                        "excludecollection,updateinfo,createtime,updatetime) " +
                        "values(?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)",
                record.getAppid(), record.getAppname(),record.getChannelid(), record.getChannelname(), record.getAppVersion(),
                record.getUpdatestrategy(), record.getBaseurl(), record.getApkurl(), record.getPromptcollection(), record.getForcecollection(),
                record.getExcludecollection(),record.getUpdateinfo(), record.getCreatetime(), record.getUpdatetime());
    }

    @Override
    public List<Hotupdatecheck> select() {
        List<Hotupdatecheck> list = jdbcTemplate.query("select * from hotupdatecheck", new Object[]{}, new BeanPropertyRowMapper(Hotupdatecheck.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Hotupdatecheck selectByPrimaryKey(Integer id) {
        List<Hotupdatecheck> list = jdbcTemplate.query("select * from hotupdatecheck where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Hotupdatecheck.class));
        if(list!=null && list.size()>0){
            Hotupdatecheck hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public Hotupdatecheck selectByConf(String appid, String channelid, String clientversion) {
        List<Hotupdatecheck> list = jdbcTemplate.query("select * from hotupdatecheck where appid = ? AND channelid = ? AND appVersion = ?", new Object[]{appid, channelid, clientversion}, new BeanPropertyRowMapper(Hotupdatecheck.class));
        if(list!=null && list.size()>0){
            Hotupdatecheck hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public int update(Hotupdatecheck record) {
        return jdbcTemplate.update("UPDATE  hotupdatecheck SET appid=?,appname=?,channelid=?,channelname=?,appVersion=?,updatestrategy=?,baseurl=?,apkurl=?,promptcollection=?,forcecollection=?,excludecollection=?,updateinfo=?,createtime=?,updatetime=? WHERE id=?",
                record.getAppid(), record.getAppname(),record.getChannelid(), record.getChannelname(), record.getAppVersion(),
                record.getUpdatestrategy(), record.getBaseurl(), record.getApkurl(), record.getPromptcollection(), record.getForcecollection(),
                record.getExcludecollection(),record.getUpdateinfo(), record.getCreatetime(), record.getUpdatetime(), record.getId());
    }
}
