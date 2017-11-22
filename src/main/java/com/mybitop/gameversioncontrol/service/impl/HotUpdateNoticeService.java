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

    @Override
    public Hotupdatenotice findById(Integer id) {
        return hotUpdateNoticeDao.findById(id);
    }

    @Override
    public int deleteHotupdatenoticeById(Integer id) {
        return hotUpdateNoticeDao.deleteHotupdatenoticeById(id);
    }

    @Override
    public Hotupdatenotice insert(Hotupdatenotice record) {
        return hotUpdateNoticeDao.save(record);
    }

    @Override
    public int update(Hotupdatenotice record) {
        return hotUpdateNoticeDao.updateHotUpdateCheckOnlineById(record.getAppname(), record.getAppversion(), record.getUpdatemessage(), record.getId());
    }
}
