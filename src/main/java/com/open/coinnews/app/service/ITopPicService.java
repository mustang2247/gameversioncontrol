package com.open.coinnews.app.service;

import com.open.coinnews.app.model.TopPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITopPicService extends JpaRepository<TopPic, Integer>, JpaSpecificationExecutor<TopPic> {
}
