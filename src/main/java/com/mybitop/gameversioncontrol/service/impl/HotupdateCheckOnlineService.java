package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.entity.HotupdateCheckOnline;
import com.mybitop.gameversioncontrol.mapper.HotupdateCheckOnlineMapper;
import com.mybitop.gameversioncontrol.service.IHotupdateCheckOnline;
import com.mybitop.gameversioncontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 检查更新在线版
 */
@Service
@Component
public class HotupdateCheckOnlineService implements IHotupdateCheckOnline {

    @Autowired
    private HotupdateCheckOnlineMapper checkOnlineMapper;

    @CacheEvict(value = Utils.CACHE_NAME_CHECK_ONLINE, key = "#id")
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return checkOnlineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(HotupdateCheckOnline record) {
        if (selectByConf(record.getAppid(), record.getChannelid()) != null) {
            return update(record);
        } else {
            return insertItem(record);
        }
    }

    @CachePut(value = Utils.CACHE_NAME_CHECK_ONLINE, key = "#record.appid + record.channelid")
    public int insertItem(HotupdateCheckOnline record) {
        return checkOnlineMapper.insert(record);
    }

    @Override
    public List<HotupdateCheckOnline> select() {
        return checkOnlineMapper.select();
    }

    @CachePut(value = Utils.CACHE_NAME_CHECK_ONLINE, key = "#id")
    @Override
    public HotupdateCheckOnline selectByPrimaryKey(Integer id) {
        return checkOnlineMapper.selectByPrimaryKey(id);
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
    public HotupdateCheckOnline selectByConf(String appid, String channelid, String clientversion) {
        HotupdateCheckOnline hotupdatecheck = selectByConf(appid, channelid);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotupdatecheck;
    }

    @CachePut(value = Utils.CACHE_NAME_CHECK_ONLINE, key = "#appid + #channelid")
    public HotupdateCheckOnline selectByConf(String appid, String channelid) {
        return checkOnlineMapper.selectByConf(appid, channelid);
    }

    @Override
    public int update(HotupdateCheckOnline record) {
        return checkOnlineMapper.update(record);
    }
}
