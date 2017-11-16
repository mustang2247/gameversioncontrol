package com.mybitop.gameversioncontrol.mapper;


import com.mybitop.gameversioncontrol.entity.HotupdateCheckOnline;

import java.util.List;

public interface HotupdateCheckOnlineMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(HotupdateCheckOnline record);

    List<HotupdateCheckOnline> select();

    HotupdateCheckOnline selectByPrimaryKey(Integer id);

    HotupdateCheckOnline selectByConf(String appid, String channelid);

    int update(HotupdateCheckOnline record);
}