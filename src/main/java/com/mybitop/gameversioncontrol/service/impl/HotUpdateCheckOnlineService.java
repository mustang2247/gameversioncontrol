package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheckonline;
import com.mybitop.gameversioncontrol.mapper.HotUpdateCheckOnlineMapper;
import com.mybitop.gameversioncontrol.service.IHotUpdateCheckOnline;
import com.mybitop.gameversioncontrol.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 检查更新在线版
 */
//@CacheConfig(cacheNames = Utils.CACHE_NAME_CHECK_ONLINE)
@Service
@Transactional
public class HotUpdateCheckOnlineService implements IHotUpdateCheckOnline {

    private static final Logger logger = LoggerFactory.getLogger(HotUpdateCheckOnlineService.class);

//    @Resource
//    private HotUpdateCheckOnlineDao checkOnlineMapper;
    @Autowired
    private HotUpdateCheckOnlineMapper checkOnlineMapper;

    @Override
    public int deleteHotupdatecheckById(Integer id) {
        return checkOnlineMapper.deleteByPrimaryKey(id);
//        Hotupdatecheckonline hotupdateconfig = checkOnlineMapper.findHotupdatecheckById(id);
//        Integer resoult = 0;
//        if(hotupdateconfig != null){
//            resoult = deleteHotupdatecheckonlineByAppidAndChannelid(hotupdateconfig.getAppid(), hotupdateconfig.getChannelid());
//        }
//        return resoult;
    }

    public int deleteHotupdatecheckonlineByAppidAndChannelid(String appid, String channelid){
        return checkOnlineMapper.deleteHotupdatecheckonlineByAppidAndChannelid(appid, channelid);
    }

//    @CachePut(key = "#record.appid + #record.channelid")
    @Override
    public int insert(Hotupdatecheckonline record) {
        if (checkOnlineMapper.findHotupdatecheckonlineByAppidAndChannelid(record.getAppid(), record.getChannelid()) != null) {
            return update(record);
        } else {
            return insertItem(record);
        }
    }

    public int insertItem(Hotupdatecheckonline record) {
        return checkOnlineMapper.save(record);
    }

    @Override
    public List<Hotupdatecheckonline> findAll() {
        return checkOnlineMapper.findAll();
    }

    @Override
    public Hotupdatecheckonline findHotupdatecheckById(Integer id) {
        return checkOnlineMapper.findHotupdatecheckById(id);
    }

    /**
     * 检查更新
     *
     * @param appid
     * @param channelid
     * @param clientversion
     * @return
     */
//    @Cacheable(key = "#appid + #channelid")
    @Override
    public Hotupdatecheckonline findHotupdatecheckonlineByAppidAndChannelidAndAppversion(String appid, String channelid, String clientversion) {
        Assert.notNull(appid, "appid is null");
        Assert.notNull(channelid, "channelid is null");
        Assert.notNull(clientversion, "clientversion is null");

        Hotupdatecheckonline online = findHotupdatecheckonlineByAppidAndChannelid(appid, channelid);

        try {
            if (online != null){
//                online.setUpdatestrategy(Utils.NOT_UPDATE);
                if (online != null && online.getForcecollection() != null &&
                        !online.getForcecollection().isEmpty()) {
                    // 强更
                    if (online.getUpdatestrategy() == Utils.FORCE_UPDATE) {
                        if (online.getAppversion().equals(clientversion)){
                            online.setUpdatestrategy(Utils.NOT_UPDATE);
                        }else {
                            online.setUpdatestrategy(Utils.FORCE_UPDATE);
                        }
                    }else if (online.getUpdatestrategy() == Utils.NOT_UPDATE) {
                        online.setUpdatestrategy(Utils.NOT_UPDATE);
                    } else{
                        if (online.getForcecollection().indexOf(clientversion) != -1) {
                            online.setUpdatestrategy(Utils.FORCE_UPDATE);
                        } else if (online.getPromptcollection().indexOf(clientversion) != -1) {
                            online.setUpdatestrategy(Utils.TIP_UPDATE);
                        }else {
                            online.setUpdatestrategy(Utils.NOT_UPDATE);
                        }
                    }
//                    logger.info("selectByConf3: " + online.getUpdatestrategy());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return online;
    }

//    @Cacheable(key = "#appid + #channelid")
    @Override
    public Hotupdatecheckonline findHotupdatecheckonlineByAppidAndChannelid(String appid, String channelid){
        return checkOnlineMapper.findHotupdatecheckonlineByAppidAndChannelid(appid, channelid);
    }

    @Override
    public int update(Hotupdatecheckonline record) {
//        try {
//            logger.info("################## Hotupdatecheckonline");
//            checkOnlineMapper.updateHotUpdateCheckOnlineById(
//                    record.getAppname(),
//                    record.getChannelname(),
//                    record.getAppversion(),
//                    record.getUpdatestrategy(),
//                    record.getBaseurl(),
//                    record.getApkurl(),
//                    record.getPromptcollection(),
//                    record.getForcecollection(),
//                    record.getExcludecollection(),
//                    record.getUpdateinfo(),
//                    record.getId()
//            );
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        try {
            logger.info("################## Hotupdatecheckonline");
            return checkOnlineMapper.update(record);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


}
