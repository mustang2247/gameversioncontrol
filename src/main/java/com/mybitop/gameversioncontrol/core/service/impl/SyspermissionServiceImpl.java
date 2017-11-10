package com.mybitop.gameversioncontrol.core.service.impl;

import com.mybitop.gameversioncontrol.core.entity.Syspermission;
import com.mybitop.gameversioncontrol.core.mapper.SyspermissionMapper;
import com.mybitop.gameversioncontrol.core.service.ISyspermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyspermissionServiceImpl implements ISyspermission {

    @Autowired
    private SyspermissionMapper syspermissionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return syspermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Syspermission record) {
        return syspermissionMapper.insert(record);
    }

    @Override
    public List<Syspermission> select() {
        return syspermissionMapper.select();
    }

    @Override
    public Syspermission selectByPrimaryKey(Long id) {
        return syspermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Syspermission record) {
        return syspermissionMapper.update(record);
    }
}
