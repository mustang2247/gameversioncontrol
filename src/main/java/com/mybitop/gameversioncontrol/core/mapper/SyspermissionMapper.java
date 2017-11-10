package com.mybitop.gameversioncontrol.core.mapper;

import com.mybitop.gameversioncontrol.core.entity.Syspermission;

import java.util.List;

public interface SyspermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Syspermission record);

    List<Syspermission> select();

    Syspermission selectByPrimaryKey(Long id);

    int update(Syspermission record);
}