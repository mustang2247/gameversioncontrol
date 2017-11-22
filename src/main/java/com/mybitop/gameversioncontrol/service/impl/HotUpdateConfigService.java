package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.dao.HotUpdateConfigDao;
import com.mybitop.gameversioncontrol.entity.Hotupdateconfig;
import com.mybitop.gameversioncontrol.service.IHotUpdateConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

//@Component
@Service
//@CacheConfig(cacheNames = "versionConfigs")
@Transactional
public class HotUpdateConfigService implements IHotUpdateConfig {

    @Resource
    private HotUpdateConfigDao versioncontrolMapper;

//    @CacheEvict(value = Utils.CACHE_NAME_CONF, key = "#id")
    @Override
    public int deleteHotupdatecheckById(Integer id) {
        Hotupdateconfig hotupdateconfig = versioncontrolMapper.findHotupdatecheckById(id);
        Integer resoult = 0;
        if(hotupdateconfig != null){
            resoult = versioncontrolMapper.deleteHotupdateconfigByAppidAndChannelidAndAppversion(hotupdateconfig.getAppid(), hotupdateconfig.getChannelid(), hotupdateconfig.getAppversion());
        }
        return resoult;
    }

//    @CachePut(value = Utils.CACHE_NAME_CONF, key = "#record.appid + #record.channelid + #record.appVersion")
    @Override
    public int insert(Hotupdateconfig record) {
        if (versioncontrolMapper.findHotupdatecheckByAppidAndChannelidAndAppversion(record.getAppid(), record.getChannelid(), record.getAppversion()) != null) {
            return update(record);
        } else {
            versioncontrolMapper.save(record);
            return 1;
        }

    }

//    @Cacheable(value = Utils.CACHE_NAME_CONF, key = "#id")
    @Override
    public Hotupdateconfig findHotupdatecheckById(Integer id) {
        return versioncontrolMapper.findHotupdatecheckById(id);
    }

    @Override
    public int update(Hotupdateconfig record) {
        return versioncontrolMapper.update(record.getAppname(),record.getChannelname(), record.getServerip(),record.getServerport(), record.getHotfix(),record.getShields(), record.getDefine1(), record.getDefine2(), record.getParams(), record.getId());
    }

//    @Cacheable(value = Utils.CACHE_NAME_CONF, key = "#appid + #channelid + #appVersion")
    @Override
    public Hotupdateconfig findHotupdatecheckByAppidAndChannelidandAndAppVersion(String appid, String channelid, String appVersion) {
        Assert.notNull(appid, "appid is null");
        Assert.notNull(channelid, "channelid is null");
        Assert.notNull(appVersion, "appVersion is null");

        return versioncontrolMapper.findHotupdatecheckByAppidAndChannelidAndAppversion(appid, channelid, appVersion);
    }

    @Override
    public List<Hotupdateconfig> findAll() {
        return versioncontrolMapper.findAll();
    }

}
