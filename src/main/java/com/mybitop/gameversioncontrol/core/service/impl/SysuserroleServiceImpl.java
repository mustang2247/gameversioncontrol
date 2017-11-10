package com.mybitop.gameversioncontrol.core.service.impl;

import com.mybitop.gameversioncontrol.core.entity.Sysuserrole;
import com.mybitop.gameversioncontrol.core.mapper.SysuserroleMapper;
import com.mybitop.gameversioncontrol.core.service.ISysuserrole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysuserroleServiceImpl implements ISysuserrole {

    @Autowired
    private SysuserroleMapper sysuserroleMapper;

    @Override
    public int insert(Sysuserrole record) {
        return sysuserroleMapper.insert(record);
    }

    @Override
    public List<Sysuserrole> select(long uid) {
        return sysuserroleMapper.select(uid);
    }
}
