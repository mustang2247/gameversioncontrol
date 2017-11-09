package com.mybitop.gameversioncontrol.mapper;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;

import java.util.List;

public interface HotupdatecheckMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Hotupdatecheck record);

    List<Hotupdatecheck> select();

    Hotupdatecheck selectByPrimaryKey(Integer id);

    Hotupdatecheck selectByConf(String appid, String channelid, String clientversion);

    int update(Hotupdatecheck record);
}