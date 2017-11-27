package com.mybitop.gameversioncontrol.mapper;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;

import java.util.List;

public interface HotUpdateNoticeMapper {
    Hotupdatenotice findByAppid(String appid);

    Hotupdatenotice findById(Integer id);

    int deleteHotupdatenoticeByAppid(String appid);

    int deleteHotupdatenoticeById(Integer id);

    int updateHotUpdateCheckOnlineById(Hotupdatenotice record);

    int save(Hotupdatenotice record);

    List<Hotupdatenotice> findAll();
}
