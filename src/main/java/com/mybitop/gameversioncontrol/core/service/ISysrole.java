package com.mybitop.gameversioncontrol.core.service;

import com.mybitop.gameversioncontrol.core.entity.Sysrole;

import java.util.List;

public interface ISysrole {
    int deleteByPrimaryKey(Long id);

    int insert(Sysrole record);

    List<Sysrole> select();

    Sysrole selectByPrimaryKey(Long id);

    int update(Sysrole record);
}
