package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.entity.Versioncontrol;
import com.mybitop.gameversioncontrol.mapper.VersioncontrolMapper;
import com.mybitop.gameversioncontrol.service.IVersioncontrol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersioncontrolService implements IVersioncontrol {

    @Autowired
    private VersioncontrolMapper versioncontrolMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return versioncontrolMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Versioncontrol record) {
        if (findVersionInfo(record.getAppid(), record.getChannelid(), record.getAppVersion()) != null) {
            return update(record);
        } else {
            return versioncontrolMapper.insert(record);
        }

    }

    @Override
    public Versioncontrol selectByPrimaryKey(Integer id) {
        return versioncontrolMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Versioncontrol record) {
        return versioncontrolMapper.update(record);
    }

    @Override
    public Versioncontrol findVersionInfo(String appid, String channelid, String appVersion) {
        return versioncontrolMapper.findVersionInfo(appid, channelid, appVersion);
    }

    @Override
    public List<Versioncontrol> select() {
        return versioncontrolMapper.select();
    }
}
