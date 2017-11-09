package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.mapper.HotupdatecheckMapper;
import com.mybitop.gameversioncontrol.service.IHotupdatecheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotupdatecheckService implements IHotupdatecheck {

    @Autowired
    private HotupdatecheckMapper hotupdatecheckMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return hotupdatecheckMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Hotupdatecheck record) {
        return hotupdatecheckMapper.insert(record);
    }

    @Override
    public List<Hotupdatecheck> select() {
        return hotupdatecheckMapper.select();
    }

    @Override
    public Hotupdatecheck selectByPrimaryKey(Integer id) {
        return hotupdatecheckMapper.selectByPrimaryKey(id);
    }

    @Override
    public Hotupdatecheck selectByConf(String appid, String channelid, String clientversion) {
        return hotupdatecheckMapper.selectByConf(appid, channelid, clientversion);
    }

    @Override
    public int update(Hotupdatecheck record) {
        return hotupdatecheckMapper.update(record);
    }
}
