package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.entity.Versioncontrol;

import java.util.List;

public interface IVersioncontrol {
    int deleteByPrimaryKey(Integer id);

    int insert(Versioncontrol record);

    Versioncontrol selectByPrimaryKey(Integer id);

    int update(Versioncontrol record);

    Versioncontrol findVersionInfo(String appid, String channelid, String appVersion);

    List<Versioncontrol> select();
}
