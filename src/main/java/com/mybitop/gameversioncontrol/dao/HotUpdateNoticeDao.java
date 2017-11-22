package com.mybitop.gameversioncontrol.dao;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 热更新通知repository
 */
public interface HotUpdateNoticeDao extends JpaRepository<Hotupdatenotice, Long> {
    Hotupdatenotice findByAppid(String appid);

    Hotupdatenotice findById(Integer id);

    int deleteHotupdatenoticeById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "update hotupdatenotice set appname=?1,appversion=?2,updatemessage=?3 where id=?4", nativeQuery = true)
    int updateHotUpdateCheckOnlineById(String appname, String appversion, String updatemessage, Integer id);
}
