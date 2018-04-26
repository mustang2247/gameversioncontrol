package com.open.coinnews.app.service;

import com.open.coinnews.app.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITagService extends JpaRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {

    public Tag findByName(String name);
}
