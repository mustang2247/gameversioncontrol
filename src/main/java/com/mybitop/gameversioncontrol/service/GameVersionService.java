package com.mybitop.gameversioncontrol.service;

import com.mybitop.gameversioncontrol.dao.GameVersionMapper;
import com.mybitop.gameversioncontrol.entity.VersionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版本控制service
 */
@Service
public class GameVersionService {
    @Autowired
    private GameVersionMapper accountMapper;

    public int add(VersionConfig version) {

        if(findVersionInfo(version.getAppid(), version.getChannelid(), version.getAppVersion()) != null){
            return update(version);
        }else {
            return accountMapper.add(version.getAppid(),
                    version.getAppname(),
                    version.getChannelid(),
                    version.getChannelname(),
                    version.getAppVersion(),
                    version.getCreatetime(),
                    version.getUpdatetime(),
                    version.getServerIp(),
                    version.getServerPort(),
                    version.getHotfix(),
                    version.getShields(),
                    version.getDefine1(),
                    version.getDefine2(),
                    version.getParams()
            );
        }
    }

    public int update(VersionConfig version) {
        return accountMapper.update(version.getAppid(),
                version.getAppname(),
                version.getChannelid(),
                version.getChannelname(),
                version.getAppVersion(),
                version.getCreatetime(),
                version.getUpdatetime(),
                version.getServerIp(),
                version.getServerPort(),
                version.getHotfix(),
                version.getShields(),
                version.getDefine1(),
                version.getDefine2(),
                version.getParams(),
                version.getId()
        );
    }

    public int delete(int id) {
        return accountMapper.delete(id);
    }

    public VersionConfig findVersionInfo(String appid, String channelid, String appVersion) {
        return accountMapper.findVersionInfo(appid, channelid, appVersion);
    }

    public List<VersionConfig> findAccountList() {
        return accountMapper.findVersionInfoList();
    }
}