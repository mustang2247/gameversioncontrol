package com.mybitop.gameversioncontrol.dao;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 热更新部署模板repository
 */
public interface HotUpdateCheckDao extends JpaRepository<Hotupdatecheck, Long> {

    int deleteHotupdatecheckById(Integer id);

    Hotupdatecheck findHotupdatecheckById(Integer id);

    List<Hotupdatecheck> findAll();

    Hotupdatecheck findHotupdatecheckByAppidAndChannelid(String appid, String channelid);

    @Transactional
    @Modifying
    @Query(value = "update  hotupdatecheck set appname=?1,channelname=?2,appversion=?3,updatestrategy=?4,baseurl=?5,apkurl=?6,promptcollection=?7,forcecollection=?8,excludecollection=?9,updateinfo=?10 WHERE id=?11", nativeQuery = true)
    int update(String appname, String channelname, String appVersion, Integer updatestrategy, String baseurl, String apkurl, String promptcollection, String forcecollection, String excludecollection, String updateinfo, Integer id);
}
