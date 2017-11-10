package com.mybitop.gameversioncontrol.core.service.impl;

import com.mybitop.gameversioncontrol.core.entity.Userinfo;
import com.mybitop.gameversioncontrol.core.mapper.UserinfoMapper;
import com.mybitop.gameversioncontrol.core.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public int deleteByPrimaryKey(Long uid) {
        return userinfoMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(Userinfo record) {
        return userinfoMapper.insert(record);
    }

    @Override
    public List<Userinfo> select() {
        return userinfoMapper.select();
    }

    @Override
    public Userinfo selectByPrimaryKey(Long uid) {
        return userinfoMapper.selectByPrimaryKey(uid);
    }

    @Override
    public Userinfo selectByUsername(String username) {
        return userinfoMapper.selectByUsername(username);
    }

    @Override
    public int update(Userinfo record) {
        return userinfoMapper.update(record);
    }
}
