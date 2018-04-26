package com.open.coinnews.app.service;

import com.open.coinnews.app.model.Hotupdatecheckonline;
import com.open.coinnews.app.model.Hotupdatenotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHotUpdateCheckOnline extends JpaRepository<Hotupdatecheckonline, Integer>, JpaSpecificationExecutor<Hotupdatecheckonline> {

    @Query("select online from Hotupdatecheckonline online where online.appid = ?1 AND online.channelid = ?2")
    Hotupdatecheckonline findHotupdatecheckonlineByAppidAndChannelidAndAppversion(String appid, String channelid);

    @Query("select online from Hotupdatecheckonline online where online.appversion = ?1")
    Hotupdatecheckonline findByAppVersion(String appversion);

    @Query("select online from Hotupdatecheckonline online ORDER BY online.createtime DESC")
    Page<Hotupdatecheckonline> getHistoryUpdate(Pageable pageable);

}
