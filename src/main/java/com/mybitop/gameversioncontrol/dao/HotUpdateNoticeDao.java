package com.mybitop.gameversioncontrol.dao;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 热更新通知repository
 */
public interface HotUpdateNoticeDao extends JpaRepository<Hotupdatenotice, Long> {
    public Hotupdatenotice findByAppid(String appid);
}
