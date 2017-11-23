package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.dao.HotUpdateCheckDao;
import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.service.IHotUpdateCheck;
import com.mybitop.gameversioncontrol.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class HotUpdateCheckService implements IHotUpdateCheck {

    @Resource
    private HotUpdateCheckDao hotupdatecheckMapper;

    @Override
    public int deleteHotupdatecheckById(Integer id) {
        Hotupdatecheck hotupdateconfig = hotupdatecheckMapper.findHotupdatecheckById(id);
        Integer resoult = 0;
        if(hotupdateconfig != null){
            resoult = hotupdatecheckMapper.deleteHotupdatecheckByAppidAndChannelid(hotupdateconfig.getAppid(), hotupdateconfig.getChannelid());
        }
        return resoult;
    }

    @Override
    public int insert(Hotupdatecheck record) {
        if (hotupdatecheckMapper.findHotupdatecheckByAppidAndChannelid(record.getAppid(), record.getChannelid()) != null) {
            return update(record);
        } else {
            insertItem(record);
            return 1;
        }
    }

    public Hotupdatecheck insertItem(Hotupdatecheck record) {
        return hotupdatecheckMapper.save(record);
    }

    @Override
    public List<Hotupdatecheck> findAll() {
        return hotupdatecheckMapper.findAll();
    }

    @Override
    public Hotupdatecheck findHotupdatecheckById(Integer id) {
        return hotupdatecheckMapper.findHotupdatecheckById(id);
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
    public Hotupdatecheck findHotupdatecheckByAppidAndChannelid(String appid, String channelid, String clientversion) {
        Assert.notNull(appid, "appid is null");
        Assert.notNull(channelid, "channelid is null");
        Assert.notNull(clientversion, "clientversion is null");

        Hotupdatecheck hotupdatecheck = hotupdatecheckMapper.findHotupdatecheckByAppidAndChannelid(appid, channelid);
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

    @Override
    public int update(Hotupdatecheck record) {
        return hotupdatecheckMapper.update(
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
