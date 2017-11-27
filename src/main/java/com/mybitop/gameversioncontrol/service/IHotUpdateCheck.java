package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;

import java.util.List;

public interface IHotUpdateCheck {

    int deleteHotupdatecheckById(Integer id);

    Hotupdatecheck findHotupdatecheckById(Integer id);

    List<Hotupdatecheck> findAll();

    Hotupdatecheck findHotupdatecheckByAppidAndChannelid(String appid, String channelid, String clientversion);

    int insert(Hotupdatecheck record);

    int update(Hotupdatecheck record);

}
