package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.dao.HotUpdateCheckOnlineDao;
import com.mybitop.gameversioncontrol.dao.HotUpdateNoticeDao;
import com.mybitop.gameversioncontrol.entity.Hotupdatecheckonline;
import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;
import com.mybitop.gameversioncontrol.service.IHotUpdateCheckOnline;
import com.mybitop.gameversioncontrol.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * 检查更新在线版
 */
@Service
@Transactional
public class HotUpdateCheckOnlineService implements IHotUpdateCheckOnline {

    private static final Logger logger = LoggerFactory.getLogger(HotUpdateCheckOnlineService.class);

    @Resource
    private HotUpdateCheckOnlineDao checkOnlineMapper;

    @Override
    public int deleteHotupdatecheckById(Integer id) {
        Hotupdatecheckonline hotupdateconfig = checkOnlineMapper.findHotupdatecheckById(id);
        Integer resoult = 0;
        if(hotupdateconfig != null){
            resoult = checkOnlineMapper.deleteHotupdatecheckonlineByAppidAndChannelid(hotupdateconfig.getAppid(), hotupdateconfig.getChannelid());
        }
        return resoult;
    }

    @Override
    public int insert(Hotupdatecheckonline record) {
        if (checkOnlineMapper.findHotupdatecheckByAppidAndChannelid(record.getAppid(), record.getChannelid()) != null) {
            return update(record);
        } else {
            insertItem(record);
            return 1;
        }
    }

    public Hotupdatecheckonline insertItem(Hotupdatecheckonline record) {
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
    @Override
    public Hotupdatecheckonline findHotupdatecheckByAppidAndChannelid(String appid, String channelid, String clientversion) {
        Assert.notNull(appid, "appid is null");
        Assert.notNull(channelid, "channelid is null");
        Assert.notNull(clientversion, "clientversion is null");
        Hotupdatecheckonline online = checkOnlineMapper.findHotupdatecheckByAppidAndChannelid(appid, channelid);

        try {
            if (online != null){
                online.setUpdatestrategy(Utils.NOT_UPDATE);
                if (online != null && online.getForcecollection() != null &&
                        !online.getForcecollection().isEmpty()) {
                    if (online.getUpdatestrategy() == Utils.FORCE_UPDATE) {
                        if (online.getAppversion().equals(clientversion)){
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
//                    logger.info("selectByConf3: " + online.getUpdatestrategy());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return online;
    }

    @Override
    public int update(Hotupdatecheckonline record) {
        return checkOnlineMapper.updateHotUpdateCheckOnlineById(
                record.getAppname(),
                record.getChannelname(),
                record.getAppversion(),
                record.getUpdatestrategy(),
                record.getBaseurl(),
                record.getApkurl(),
                record.getPromptcollection(),
                record.getForcecollection(),
                record.getExcludecollection(),
                record.getUpdateinfo(),
                record.getId()
        );
    }


}
