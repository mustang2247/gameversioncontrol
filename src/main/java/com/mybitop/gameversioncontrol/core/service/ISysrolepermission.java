package com.mybitop.gameversioncontrol.core.service;

import com.mybitop.gameversioncontrol.core.entity.Sysrolepermission;

import java.util.List;

public interface ISysrolepermission {
    int insert(Sysrolepermission record);

    List<Sysrolepermission> select(long roleid);
}
