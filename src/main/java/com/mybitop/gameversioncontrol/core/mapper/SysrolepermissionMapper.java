package com.mybitop.gameversioncontrol.core.mapper;

import com.mybitop.gameversioncontrol.core.entity.Sysrolepermission;

import java.util.List;

public interface SysrolepermissionMapper {
    int insert(Sysrolepermission record);

    List<Sysrolepermission> select(long roleid);

}