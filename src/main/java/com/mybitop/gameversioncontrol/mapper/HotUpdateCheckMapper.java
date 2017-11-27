package com.mybitop.gameversioncontrol.mapper;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;

import java.util.List;

public interface HotUpdateCheckMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteHotupdatecheckByAppidAndChannelid(String appid, String channelid);

    int save(Hotupdatecheck record);

    List<Hotupdatecheck> findAll();

    Hotupdatecheck findHotupdatecheckById(Integer id);

    Hotupdatecheck findHotupdatecheckByAppidAndChannelid(String appid, String channelid);

    int update(Hotupdatecheck record);
}