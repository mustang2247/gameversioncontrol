package com.mybitop.gameversioncontrol.dao;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 热更新通知repository
 */
@CacheConfig(cacheNames = "hotupdatenoticese")
public interface HotUpdateNoticeDao extends JpaRepository<Hotupdatenotice, Long> {

    @Cacheable
    Hotupdatenotice findByAppid(String appid);

    Hotupdatenotice findById(Integer id);

    @CacheEvict
    int deleteHotupdatenoticeByAppid(String appid);

    @CacheEvict
    int deleteHotupdatenoticeById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "update hotupdatenotice set appname=?1,appversion=?2,updatemessage=?3 where id=?4", nativeQuery = true)
    int updateHotUpdateCheckOnlineById(String appname, String appversion, String updatemessage, Integer id);
}
