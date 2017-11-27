package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.Hotupdateconfig;

import java.util.List;

public interface IHotUpdateConfig {
    int deleteHotupdatecheckById(Integer id);

    Hotupdateconfig findHotupdatecheckById(Integer id);

    List<Hotupdateconfig> findAll();

    Hotupdateconfig findHotupdatecheckByAppidAndChannelidandAndAppVersion(String appid, String channelid, String clientversion);

    int insert(Hotupdateconfig record);

    int update(Hotupdateconfig record);
}
