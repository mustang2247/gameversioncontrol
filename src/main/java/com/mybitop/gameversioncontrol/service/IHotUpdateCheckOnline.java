package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheckonline;

import java.util.List;

public interface IHotUpdateCheckOnline {

    int deleteHotupdatecheckById(Integer id);

    Hotupdatecheckonline findHotupdatecheckById(Integer id);

    List<Hotupdatecheckonline> findAll();

    Hotupdatecheckonline findHotupdatecheckonlineByAppidAndChannelid(String appid, String channelid, String clientversion);

    Hotupdatecheckonline insert(Hotupdatecheckonline record);

    Hotupdatecheckonline update(Hotupdatecheckonline record);

}
