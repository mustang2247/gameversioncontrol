package com.mybitop.gameversioncontrol.mapper;


import com.mybitop.gameversioncontrol.entity.Hotupdatecheckonline;

import java.util.List;

public interface HotUpdateCheckOnlineMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteHotupdatecheckonlineByAppidAndChannelid(String appid, String channelid);

    int save(Hotupdatecheckonline record);

    List<Hotupdatecheckonline> findAll();

    List<Hotupdatecheckonline> findHotupdatecheckByPage(int pageCount);

    Hotupdatecheckonline findHotupdatecheckById(Integer id);

    Hotupdatecheckonline findHotupdatecheckonlineByAppidAndChannelid(String appid, String channelid);

    int update(Hotupdatecheckonline record);
}