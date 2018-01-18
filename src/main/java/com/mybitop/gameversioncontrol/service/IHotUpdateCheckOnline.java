package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheckonline;

import java.util.List;

public interface IHotUpdateCheckOnline {

    int deleteHotupdatecheckById(Integer id);

    Hotupdatecheckonline findHotupdatecheckById(Integer id);

    List<Hotupdatecheckonline> findAll();

    List<Hotupdatecheckonline> findHotupdatecheckonlineByPage(int pageCount);

    Hotupdatecheckonline findHotupdatecheckonlineByAppidAndChannelidAndAppversion(String appid, String channelid, String clientversion);

    Hotupdatecheckonline findHotupdatecheckonlineByAppidAndChannelid(String appid, String channelid);

    int insert(Hotupdatecheckonline record);

    int update(Hotupdatecheckonline record);

}
