package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;
import com.mybitop.gameversioncontrol.mapper.HotUpdateNoticeMapper;
import com.mybitop.gameversioncontrol.service.IHotUpdateNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotUpdateNoticeService implements IHotUpdateNotice {

//    @Resource
//    private HotUpdateNoticeDao hotUpdateNoticeDao;
    @Autowired
    private HotUpdateNoticeMapper hotUpdateNoticeDao;

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
        Hotupdatenotice hotupdatenotice = hotUpdateNoticeDao.findById(id);
        Integer resoult = 0;
        if(hotupdatenotice != null){
            resoult = hotUpdateNoticeDao.deleteHotupdatenoticeByAppid(hotupdatenotice.getAppid());
        }
        return resoult;
    }

    @Override
    public int insert(Hotupdatenotice record) {
        return hotUpdateNoticeDao.save(record);
    }

    @Override
    public int update(Hotupdatenotice record) {
        return hotUpdateNoticeDao.updateHotUpdateCheckOnlineById(record);
    }

    @Override
    public List<Hotupdatenotice> findAll() {
        return hotUpdateNoticeDao.findAll();
    }
}
