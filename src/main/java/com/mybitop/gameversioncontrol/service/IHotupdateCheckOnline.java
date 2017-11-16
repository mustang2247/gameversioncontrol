package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.HotupdateCheckOnline;

import java.util.List;

public interface IHotupdateCheckOnline {

    int deleteByPrimaryKey(Integer id);

    int insert(HotupdateCheckOnline record);

    List<HotupdateCheckOnline> select();

    HotupdateCheckOnline selectByPrimaryKey(Integer id);

    HotupdateCheckOnline selectByConf(String appid, String channelid, String clientversion);

    int update(HotupdateCheckOnline record);

}
