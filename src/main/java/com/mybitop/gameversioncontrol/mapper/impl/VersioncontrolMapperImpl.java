package com.mybitop.gameversioncontrol.mapper.impl;

import com.mybitop.gameversioncontrol.entity.Versioncontrol;
import com.mybitop.gameversioncontrol.mapper.VersioncontrolMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VersioncontrolMapperImpl implements VersioncontrolMapper {
    private static final Logger logger = LoggerFactory.getLogger(VersioncontrolMapperImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jdbcTemplate.update("delete from TABLE versioncontrol where id = ? ",id);
    }

    @Override
    public int insert(Versioncontrol record) {
        String sql = "insert into hotupdateconfig(" +
                "appid, appname, channelid,channelname,appVersion,createtime,updatetime," +
                "serverIp,serverPort,hotfix,shields,define1,define2,params) " +
                "values(?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, record.getAppid(), record.getAppname(), record.getChannelid(), record.getChannelname(), record.getAppVersion(),
                record.getCreatetime(), record.getUpdatetime(), record.getServerIp(), record.getServerPort(), record.getHotfix(),
                record.getShields(), record.getDefine1(), record.getDefine2(), record.getParams());
    }

    @Override
    public Versioncontrol selectByPrimaryKey(Integer id) {
        List<Versioncontrol> list = jdbcTemplate.query("select * from hotupdateconfig where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Versioncontrol.class));
        if(list!=null && list.size()>0){
            Versioncontrol hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public int update(Versioncontrol record) {
        String sql = "UPDATE hotupdateconfig SET " +
                "appid=?, appname=?, channelid=?,channelname=?,appVersion=?,createtime=?,updatetime=?," +
                "serverIp=?,serverPort=?,hotfix=?,shields=?,define1=?,define2=?,params=? WHERE id=?";
        return jdbcTemplate.update(sql, record.getAppid(), record.getAppname(), record.getChannelid(), record.getChannelname(), record.getAppVersion(),
                record.getCreatetime(), record.getUpdatetime(), record.getServerIp(), record.getServerPort(), record.getHotfix(),
                record.getShields(), record.getDefine1(), record.getDefine2(), record.getParams(), record.getId());
    }

    @Override
    public Versioncontrol selectVersionInfo(String appid, String channelid, String appVersion) {
        logger.info("appid:  " + appid + "  channelid:    " + channelid + "  appVersion: " + appVersion);
        List<Versioncontrol> list = jdbcTemplate.query("select * from hotupdateconfig where appid=? AND channelid=? AND appVersion=?", new Object[]{appid, channelid, appVersion}, new BeanPropertyRowMapper(Versioncontrol.class));
        if(list!=null && list.size()>0){
            Versioncontrol hotupdatecheck = list.get(0);
            return hotupdatecheck;
        }else{
            return null;
        }
    }

    @Override
    public List<Versioncontrol> select() {
        List<Versioncontrol> list = jdbcTemplate.query("select * from hotupdateconfig", new Object[]{}, new BeanPropertyRowMapper(Versioncontrol.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
}
