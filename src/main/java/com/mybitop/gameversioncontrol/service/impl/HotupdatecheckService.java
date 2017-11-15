package com.mybitop.gameversioncontrol.service.impl;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.mapper.HotupdatecheckMapper;
import com.mybitop.gameversioncontrol.service.IHotupdatecheck;
import com.mybitop.gameversioncontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotupdatecheckService implements IHotupdatecheck {

    @Autowired
    private HotupdatecheckMapper hotupdatecheckMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return hotupdatecheckMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Hotupdatecheck record) {
        return hotupdatecheckMapper.insert(record);
    }

    @Override
    public List<Hotupdatecheck> select() {
        return hotupdatecheckMapper.select();
    }

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
        Hotupdatecheck hotupdatecheck = hotupdatecheckMapper.selectByConf(appid, channelid);
        try {
            hotupdatecheck.setUpdatestrategy(Utils.NOT_UPDATE);
            if (hotupdatecheck != null && hotupdatecheck.getForcecollection() != null &&
                    !hotupdatecheck.getForcecollection().isEmpty()) {
                if (hotupdatecheck.getUpdatestrategy() == Utils.FORCE_UPDATE) {
                    hotupdatecheck.setUpdatestrategy(Utils.FORCE_UPDATE);
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

//    @Override
//    public Hotupdatecheck selectByConf(String appid, String channelid, String clientversion) {
//        return hotupdatecheckMapper.selectByConf(appid, channelid, clientversion);
//    }

    @Override
    public int update(Hotupdatecheck record) {
        return hotupdatecheckMapper.update(record);
    }
}
