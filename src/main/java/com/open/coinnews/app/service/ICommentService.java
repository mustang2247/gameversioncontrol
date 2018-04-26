package com.open.coinnews.app.service;

import com.open.coinnews.app.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ICommentService extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {

    @Query("FROM Comment c WHERE c.isShow=1 AND c.artId=?1")
    Page<Comment> findAll(Integer artId, Pageable pageable);
}
