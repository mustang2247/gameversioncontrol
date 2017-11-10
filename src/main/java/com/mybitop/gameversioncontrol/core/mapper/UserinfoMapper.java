package com.mybitop.gameversioncontrol.core.mapper;


import com.mybitop.gameversioncontrol.core.entity.Userinfo;

import java.util.List;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(Userinfo record);

    List<Userinfo> select();

    Userinfo selectByPrimaryKey(Long uid);

    Userinfo selectByUsername(String username);

    int update(Userinfo record);
}