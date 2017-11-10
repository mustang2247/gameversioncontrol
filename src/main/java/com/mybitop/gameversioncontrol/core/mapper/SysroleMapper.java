package com.mybitop.gameversioncontrol.core.mapper;

import com.mybitop.gameversioncontrol.core.entity.Sysrole;

import java.util.List;

public interface SysroleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sysrole record);

    List<Sysrole> select();

    Sysrole selectByPrimaryKey(Long id);

    int update(Sysrole record);
}