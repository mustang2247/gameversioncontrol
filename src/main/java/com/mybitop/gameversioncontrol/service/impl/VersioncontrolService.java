package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.entity.Versioncontrol;
import com.mybitop.gameversioncontrol.mapper.VersioncontrolMapper;
import com.mybitop.gameversioncontrol.service.IVersioncontrol;
import com.mybitop.gameversioncontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class VersioncontrolService implements IVersioncontrol {

    @Autowired
    private VersioncontrolMapper versioncontrolMapper;

    @CacheEvict(value = Utils.CACHE_NAME_CONF, key = "#id")
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return versioncontrolMapper.deleteByPrimaryKey(id);
    }

    @CachePut(value = Utils.CACHE_NAME_CONF, key = "#record.appid + #record.channelid + #record.appVersion")
    @Override
    public int insert(Versioncontrol record) {
        if (findVersionInfo(record.getAppid(), record.getChannelid(), record.getAppVersion()) != null) {
            return update(record);
        } else {
            return versioncontrolMapper.insert(record);
        }

    }

    @Cacheable(value = Utils.CACHE_NAME_CONF, key = "#id")
    @Override
    public Versioncontrol selectByPrimaryKey(Integer id) {
        return versioncontrolMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Versioncontrol record) {
        return versioncontrolMapper.update(record);
    }

//    @Cacheable(value = Utils.CACHE_NAME_CONF, key = "#appid + channelid + appVersion")
    @Cacheable(value = Utils.CACHE_NAME_CONF, key = "#appid + #channelid + #appVersion")
    @Override
    public Versioncontrol findVersionInfo(String appid, String channelid, String appVersion) {
        return versioncontrolMapper.findVersionInfo(appid, channelid, appVersion);
    }

    @Override
    public List<Versioncontrol> select() {
        return versioncontrolMapper.select();
    }
}
