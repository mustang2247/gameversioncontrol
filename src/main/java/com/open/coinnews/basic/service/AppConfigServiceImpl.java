package com.open.coinnews.basic.service;

import com.open.coinnews.basic.iservice.IAppConfigService;
import com.open.coinnews.basic.model.AppConfig;
import com.open.coinnews.basic.tools.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AppConfigServiceImpl {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private IAppConfigService appConfigService;

    public void addOrUpdate(AppConfig ac) {
        AppConfig a = appConfigService.loadOne();
        if(a==null) {
            appConfigService.save(ac);
        } else {
            MyBeanUtils.copyProperties(ac, a, new String[]{"id"});
            appConfigService.save(a);
        }
    }
}
