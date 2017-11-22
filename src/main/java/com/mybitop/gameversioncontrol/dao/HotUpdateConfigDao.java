package com.mybitop.gameversioncontrol.dao;

import com.mybitop.gameversioncontrol.entity.Hotupdateconfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 热更新配置文件repository
 */
public interface HotUpdateConfigDao extends JpaRepository<Hotupdateconfig, Long> {
    int deleteHotupdatecheckById(Integer id);

    Hotupdateconfig findHotupdatecheckById(Integer id);

    List<Hotupdateconfig> findAll();

    Hotupdateconfig findHotupdatecheckByAppidAndChannelidAndAppversion(String appid, String channelid, String appVersion);

    @Transactional
    @Modifying
    @Query(value = "update  hotupdateconfig set appname=?1,channelname=?2,serverIp=?3,serverPort=?4,hotfix=?5,shields=?6,define1=?7,define2=?8,params=?9 WHERE id=?10", nativeQuery = true)
    int update(String appname, String channelname, String serverIp, String serverPort, String hotfix, String shields, String define1, String define2, String params, Integer id);
}
