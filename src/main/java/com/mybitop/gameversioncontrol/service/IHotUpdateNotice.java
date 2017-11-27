package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;

import java.util.List;

public interface IHotUpdateNotice {
    Hotupdatenotice findByNoticeAppid(String appid);

    Hotupdatenotice findById(Integer id);

    int deleteHotupdatenoticeById(Integer id);

    int insert(Hotupdatenotice record);

    int update(Hotupdatenotice record);

    List<Hotupdatenotice> findAll();
}
