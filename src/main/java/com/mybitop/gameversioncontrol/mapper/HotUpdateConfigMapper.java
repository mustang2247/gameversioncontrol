package com.mybitop.gameversioncontrol.mapper;

import com.mybitop.gameversioncontrol.entity.Hotupdateconfig;

import java.util.List;

public interface HotUpdateConfigMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteHotupdateconfigByAppidAndChannelidAndAppversion(String appid, String channelid, String appVersion);

    int save(Hotupdateconfig record);

    Hotupdateconfig findHotupdatecheckById(Integer id);

    int update(Hotupdateconfig record);

    Hotupdateconfig findHotupdatecheckByAppidAndChannelidAndAppversion(String appid, String channelid, String appVersion);

    List<Hotupdateconfig> findAll();

    List<Hotupdateconfig> findHotupdateconfigByPageCount(int pageCount);
}