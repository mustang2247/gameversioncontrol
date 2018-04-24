package com.open.coinnews.app.service;

import com.open.coinnews.app.model.About;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAboutService extends JpaRepository<About, Integer> {

}
