package com.mybitop.gameversioncontrol.mapper.impl;

import com.mybitop.gameversioncontrol.entity.Hotupdateconfig;
import com.mybitop.gameversioncontrol.mapper.HotUpdateConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotUpdateConfigMapperImpl implements HotUpdateConfigMapper {
    private static final Logger logger = LoggerFactory.getLogger(HotUpdateConfigMapperImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jdbcTemplate.update("delete from hotupdateconfig where id = ? ",id);
    }

    @Override
    public int deleteHotupdateconfigByAppidAndChannelidAndAppversion(String appid, String channelid, String appVersion) {
        return jdbcTemplate.update("delete from hotupdateconfig where appid = ? and  channelid = ? and  appversion = ? ",appid, channelid, appVersion);
    }

    @Override
    public int save(Hotupdateconfig record) {
        String sql = "insert into hotupdateconfig(" +
                "appid, appname, channelid,channelname,appversion,createtime,updatetime," +
                "serverip,serverport,hotfix,shields,define1,define2,params) " +
                "values(?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, record.getAppid(), record.getAppname(), record.getChannelid(), record.getChannelname(), record.getAppversion(),
                record.getCreatetime(), record.getUpdatetime(), record.getServerip(), record.getServerport(), record.getHotfix(),
                record.getShields(), record.getDefine1(), record.getDefine2(), record.getParams());
    }

    @Override
    public Hotupdateconfig findHotupdatecheckById(Integer id) {
        List<Hotupdateconfig> list = jdbcTemplate.query("select * from hotupdateconfig where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Hotupdateconfig.class));
        if(list!=null && list.size()>0){
            Hotupdateconfig hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public int update(Hotupdateconfig record) {
        String sql = "UPDATE hotupdateconfig SET " +
                "appid=?, appname=?, channelid=?,channelname=?,appversion=?,createtime=?,updatetime=?," +
                "serverip=?,serverport=?,hotfix=?,shields=?,define1=?,define2=?,params=? WHERE id=?";
        return jdbcTemplate.update(sql, record.getAppid(), record.getAppname(), record.getChannelid(), record.getChannelname(), record.getAppversion(),
                record.getCreatetime(), record.getUpdatetime(), record.getServerip(), record.getServerport(), record.getHotfix(),
                record.getShields(), record.getDefine1(), record.getDefine2(), record.getParams(), record.getId());
    }

    @Override
    public Hotupdateconfig findHotupdatecheckByAppidAndChannelidAndAppversion(String appid, String channelid, String appVersion) {
        logger.info("appid:  " + appid + "  channelid:    " + channelid + "  appVersion: " + appVersion);
        List<Hotupdateconfig> list = jdbcTemplate.query("select * from hotupdateconfig where appid=? AND channelid=? AND appversion=?", new Object[]{appid, channelid, appVersion}, new BeanPropertyRowMapper(Hotupdateconfig.class));
        if(list!=null && list.size()>0){
            Hotupdateconfig hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public List<Hotupdateconfig> findAll() {
        List<Hotupdateconfig> list = jdbcTemplate.query("select * from hotupdateconfig", new Object[]{}, new BeanPropertyRowMapper(Hotupdateconfig.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
}
