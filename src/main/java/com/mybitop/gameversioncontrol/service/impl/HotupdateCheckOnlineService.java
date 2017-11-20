package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.entity.HotupdateCheckOnline;
import com.mybitop.gameversioncontrol.mapper.HotupdateCheckOnlineMapper;
import com.mybitop.gameversioncontrol.service.IHotupdateCheckOnline;
import com.mybitop.gameversioncontrol.utils.Utils;
import com.mybitop.gameversioncontrol.web.checkonline.CheckOnlineController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(HotupdateCheckOnlineService.class);

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
        logger.info("selectByConf: " + appid + "  :  " + clientversion);
        HotupdateCheckOnline online = selectByConf(appid, channelid);
        try {
            if (online != null){
//                online.setUpdatestrategy(Utils.FORCE_UPDATE);
                if (online != null && online.getForcecollection() != null &&
                        !online.getForcecollection().isEmpty()) {

//                    logger.info("selectByConf: " + online.getAppVersion());

                    if (online.getUpdatestrategy() == Utils.FORCE_UPDATE) {
                        if (online.getAppVersion().equals(clientversion)){
                            online.setUpdatestrategy(Utils.NOT_UPDATE);
                        }else {
                            online.setUpdatestrategy(Utils.FORCE_UPDATE);
                        }
                    }else if (online.getUpdatestrategy() == Utils.NOT_UPDATE) {
                        online.setUpdatestrategy(Utils.NOT_UPDATE);
                    } else if (online.getForcecollection().indexOf(clientversion) != -1) {
                        online.setUpdatestrategy(Utils.FORCE_UPDATE);
                    } else if (online.getPromptcollection().indexOf(clientversion) != -1) {
                        online.setUpdatestrategy(Utils.TIP_UPDATE);
                    }


                    logger.info("selectByConf3: " + online.getUpdatestrategy());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return online;
    }

//    @CachePut(value = Utils.CACHE_NAME_CHECK_ONLINE, key = "#appid + #channelid")
    public HotupdateCheckOnline selectByConf(String appid, String channelid) {
        return checkOnlineMapper.selectByConf(appid, channelid);
    }

    @Override
    public int update(HotupdateCheckOnline record) {
        return checkOnlineMapper.update(record);
    }
}
