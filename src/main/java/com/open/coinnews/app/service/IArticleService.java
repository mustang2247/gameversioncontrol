package com.open.coinnews.app.service;

import com.open.coinnews.app.dto.CateDto;
import com.open.coinnews.app.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IArticleService extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

    @Query("SELECT new com.open.coinnews.app.dto.CateDto(a.cateId AS cateId, a.cateName AS cateName, COUNT(id) as amount) FROM Article as a WHERE a.isShow=1 GROUP BY a.cateId")
    public List<CateDto> queryCates();

    @Query("UPDATE Article a SET a.readCount=a.readCount+1 WHERE a.id=?1")
    @Modifying
    @Transactional
    public void updateReadCount(Integer id);

    @Query("UPDATE Article a SET a.commentCount=a.commentCount+?2 WHERE a.id=?1")
    @Modifying
    @Transactional
    public void updateCommentCount(Integer id, Integer amount);

    @Query("SELECT COUNT(a.id) FROM Article a WHERE a.isShow=1")
    Long queryCount();

    @Query("SELECT SUM(a.readCount) FROM Article a")
    Long queryReadCount();

    @Query("SELECT a.userId FROM Article a WHERE a.id=?1")
    Integer queryUserId(Integer id);

    @Query("SELECT a.id FROM Article a WHERE a.title=?1")
    List<Article> queryArticleTitle(String title);
}
