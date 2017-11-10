package com.mybitop.gameversioncontrol.core.service.impl;

import com.mybitop.gameversioncontrol.core.entity.Sysrolepermission;
import com.mybitop.gameversioncontrol.core.mapper.SysrolepermissionMapper;
import com.mybitop.gameversioncontrol.core.service.ISysrolepermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysrolepermissionServiceImpl implements ISysrolepermission {

    @Autowired
    private SysrolepermissionMapper sysrolepermissionMapper;

    @Override
    public int insert(Sysrolepermission record) {
        return sysrolepermissionMapper.insert(record);
    }

    @Override
    public List<Sysrolepermission> select(long roleid) {
        return sysrolepermissionMapper.select(roleid);
    }
}
