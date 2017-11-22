package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheckonline;

import java.util.List;

public interface IHotUpdateCheckOnline {

    int deleteHotupdatecheckById(Integer id);

    Hotupdatecheckonline findHotupdatecheckById(Integer id);

    List<Hotupdatecheckonline> findAll();

    Hotupdatecheckonline findHotupdatecheckByAppidAndChannelid(String appid, String channelid, String clientversion);

    int insert(Hotupdatecheckonline record);

    int update(Hotupdatecheckonline record);

}
