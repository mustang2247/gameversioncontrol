package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;

public interface IHotUpdateNotice {
    public Hotupdatenotice findByNoticeAppid(String appid);
}
