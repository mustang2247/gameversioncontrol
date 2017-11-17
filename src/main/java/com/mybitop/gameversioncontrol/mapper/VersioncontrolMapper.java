package com.mybitop.gameversioncontrol.mapper;

import com.mybitop.gameversioncontrol.entity.Versioncontrol;

import java.util.List;

public interface VersioncontrolMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Versioncontrol record);

    Versioncontrol selectByPrimaryKey(Integer id);

    int update(Versioncontrol record);

    Versioncontrol selectVersionInfo(String appid, String channelid, String appVersion);

    List<Versioncontrol> select();
}