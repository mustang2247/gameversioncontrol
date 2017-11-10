package com.mybitop.gameversioncontrol.core.service.impl;

import com.mybitop.gameversioncontrol.core.entity.Sysrole;
import com.mybitop.gameversioncontrol.core.mapper.SysroleMapper;
import com.mybitop.gameversioncontrol.core.service.ISysrole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysroleServiceImpl implements ISysrole {

    @Autowired
    private SysroleMapper sysroleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysroleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Sysrole record) {
        return sysroleMapper.insert(record);
    }

    @Override
    public List<Sysrole> select() {
        return sysroleMapper.select();
    }

    @Override
    public Sysrole selectByPrimaryKey(Long id) {
        return sysroleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Sysrole record) {
        return sysroleMapper.update(record);
    }
}
