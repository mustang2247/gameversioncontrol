package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;

public interface IHotUpdateNotice {
    Hotupdatenotice findByNoticeAppid(String appid);

    Hotupdatenotice findById(Integer id);

    int deleteHotupdatenoticeById(Integer id);

    Hotupdatenotice insert(Hotupdatenotice record);

    int update(Hotupdatenotice record);
}
