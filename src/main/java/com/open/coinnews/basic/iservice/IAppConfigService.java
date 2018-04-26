package com.open.coinnews.basic.iservice;

import com.open.coinnews.basic.model.AppConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service("appConfigService")
public interface IAppConfigService extends JpaRepository<AppConfig, Integer> {

    @Query("FROM AppConfig ")
    public AppConfig loadOne();
}
