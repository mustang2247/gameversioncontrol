package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.dao.GameVersionMapper;
import com.mybitop.gameversioncontrol.entity.VersionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版本控制service
 */
@Service
public class GameVersionService {
    @Autowired
    private GameVersionMapper accountMapper;

    public int add(String name, double money) {
        return accountMapper.add(name, money);
    }

    public int update(String name, double money, int id) {
        return accountMapper.update(name, money, id);
    }

    public int delete(int id) {
        return accountMapper.delete(id);
    }

    public VersionConfig findAccount(int id) {
        return accountMapper.findAccount(id);
    }

    public List<VersionConfig> findAccountList() {
        return accountMapper.findAccountList();
    }
}