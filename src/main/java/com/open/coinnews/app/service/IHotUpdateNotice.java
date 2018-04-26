package com.open.coinnews.app.service;

import com.open.coinnews.app.model.About;
import com.open.coinnews.app.model.Article;
import com.open.coinnews.app.model.Hotupdatenotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHotUpdateNotice extends JpaRepository<Hotupdatenotice, Integer>, JpaSpecificationExecutor<Hotupdatenotice> {

    @Query("SELECT\n" +
            "\tnotice\n" +
            "FROM\n" +
            "\tHotupdatenotice notice\n" +
            "WHERE\n" +
            "\tnotice.appid = ?1\n" +
            "ORDER BY\n" +
            "\tcreatetime DESC")
    List<Hotupdatenotice> findByNoticeAppid(String appid);

    @Query("select notice from Hotupdatenotice notice ORDER BY notice.createtime DESC")
    Page<Hotupdatenotice> getHotUpdateList(Pageable pageable);
}
