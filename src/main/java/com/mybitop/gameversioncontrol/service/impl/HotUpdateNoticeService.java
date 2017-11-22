package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.dao.HotUpdateNoticeDao;
import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;
import com.mybitop.gameversioncontrol.service.IHotUpdateNotice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class HotUpdateNoticeService implements IHotUpdateNotice {

    @Resource
    private HotUpdateNoticeDao hotUpdateNoticeDao;

    @Override
    public Hotupdatenotice findByNoticeAppid(String appid) {
        return hotUpdateNoticeDao.findByAppid(appid);
    }
}
