package com.mybitop.gameversioncontrol.mapper.impl;

import com.mybitop.gameversioncontrol.entity.HotupdateCheckOnline;
import com.mybitop.gameversioncontrol.mapper.HotupdateCheckOnlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotupdateCheckOnlineMapperImpl implements HotupdateCheckOnlineMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jdbcTemplate.update("DELETE from TABLE hotupdatecheckonline where id=?",id);
    }

    @Override
    public int insert(HotupdateCheckOnline record) {
        return jdbcTemplate.update("insert into hotupdatecheckonline(appid,appname,channelid,channelname,appVersion," +
                        "updatestrategy,baseurl,apkurl,promptcollection,forcecollection," +
                        "excludecollection,updateinfo,createtime,updatetime) " +
                        "values(?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)",
                record.getAppid(), record.getAppname(),record.getChannelid(), record.getChannelname(), record.getAppVersion(),
                record.getUpdatestrategy(), record.getBaseurl(), record.getApkurl(), record.getPromptcollection(), record.getForcecollection(),
                record.getExcludecollection(),record.getUpdateinfo(), record.getCreatetime(), record.getUpdatetime());
    }

    @Override
    public List<HotupdateCheckOnline> select() {
        List<HotupdateCheckOnline> list = jdbcTemplate.query("select * from hotupdatecheckonline", new Object[]{}, new BeanPropertyRowMapper(HotupdateCheckOnline.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public HotupdateCheckOnline selectByPrimaryKey(Integer id) {
        List<HotupdateCheckOnline> list = jdbcTemplate.query("select * from hotupdatecheckonline where id = ?", new Object[]{id}, new BeanPropertyRowMapper(HotupdateCheckOnline.class));
        if(list!=null && list.size()>0){
            HotupdateCheckOnline hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public HotupdateCheckOnline selectByConf(String appid, String channelid) {
        List<HotupdateCheckOnline> list = jdbcTemplate.query("select * from hotupdatecheckonline where appid = ? AND channelid = ?", new Object[]{appid, channelid}, new BeanPropertyRowMapper(HotupdateCheckOnline.class));
        if(list!=null && list.size()>0){
            HotupdateCheckOnline hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public int update(HotupdateCheckOnline record) {
        return jdbcTemplate.update("UPDATE  hotupdatecheckonline SET appid=?,appname=?,channelid=?,channelname=?,appVersion=?,updatestrategy=?,baseurl=?,apkurl=?,promptcollection=?,forcecollection=?,excludecollection=?,updateinfo=?,createtime=?,updatetime=? WHERE id=?",
                record.getAppid(), record.getAppname(),record.getChannelid(), record.getChannelname(), record.getAppVersion(),
                record.getUpdatestrategy(), record.getBaseurl(), record.getApkurl(), record.getPromptcollection(), record.getForcecollection(),
                record.getExcludecollection(),record.getUpdateinfo(), record.getCreatetime(), record.getUpdatetime(), record.getId());
    }
}
