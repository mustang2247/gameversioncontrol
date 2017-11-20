package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.mapper.HotupdatecheckMapper;
import com.mybitop.gameversioncontrol.service.IHotupdatecheck;
import com.mybitop.gameversioncontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@CacheConfig(cacheNames = "checkItems")
public class HotupdatecheckService implements IHotupdatecheck {

    @Autowired
    private HotupdatecheckMapper hotupdatecheckMapper;

//    @CacheEvict(value = Utils.CACHE_NAME_CHECK, key = "#id")
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return hotupdatecheckMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Hotupdatecheck record) {
        if (selectByConf(record.getAppid(), record.getChannelid()) != null) {
            return update(record);
        } else {
            return insertItem(record);
        }
    }

//    @CachePut(value = Utils.CACHE_NAME_CHECK, key = "#record.appid + record.channelid")
    public int insertItem(Hotupdatecheck record) {
        return hotupdatecheckMapper.insert(record);
    }

    @Override
    public List<Hotupdatecheck> select() {
        return hotupdatecheckMapper.select();
    }

//    @CachePut(value = Utils.CACHE_NAME_CHECK, key = "#id")
    @Override
    public Hotupdatecheck selectByPrimaryKey(Integer id) {
        return hotupdatecheckMapper.selectByPrimaryKey(id);
    }

    /**
     * 检查更新
     *
     * @param appid
     * @param channelid
     * @param clientversion
     * @return
     */
    @Override
    public Hotupdatecheck selectByConf(String appid, String channelid, String clientversion) {
        Hotupdatecheck hotupdatecheck = selectByConf(appid, channelid);
        try {
            if (hotupdatecheck != null){
                hotupdatecheck.setUpdatestrategy(Utils.NOT_UPDATE);
                if (hotupdatecheck != null && hotupdatecheck.getForcecollection() != null &&
                        !hotupdatecheck.getForcecollection().isEmpty()) {
                    if (hotupdatecheck.getUpdatestrategy() == Utils.FORCE_UPDATE) {
                        hotupdatecheck.setUpdatestrategy(Utils.FORCE_UPDATE);
                    }else if (hotupdatecheck.getUpdatestrategy() == Utils.NOT_UPDATE) {
                        hotupdatecheck.setUpdatestrategy(Utils.NOT_UPDATE);
                    } else if (hotupdatecheck.getForcecollection().indexOf(clientversion) != -1) {
                        hotupdatecheck.setUpdatestrategy(Utils.FORCE_UPDATE);
                    } else if (hotupdatecheck.getPromptcollection().indexOf(clientversion) != -1) {
                        hotupdatecheck.setUpdatestrategy(Utils.TIP_UPDATE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotupdatecheck;
    }

//    @CachePut(value = Utils.CACHE_NAME_CHECK, key = "#appid + #channelid")
    public Hotupdatecheck selectByConf(String appid, String channelid) {
        return hotupdatecheckMapper.selectByConf(appid, channelid);
    }

    @Override
    public int update(Hotupdatecheck record) {
        return hotupdatecheckMapper.update(record);
    }
}
