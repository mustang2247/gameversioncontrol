package com.mybitop.gameversioncontrol.entity;

import java.util.Date;

/**
 * 版本控制对象
 */
public class VersionConfig {
    private int id;
    private String appid;
    private String appname;
    private String channelid;
    private String channelname;

    private String appVersion;
    private Date createtime;
    private Date updatetime;

    private String serverIp;
    private String serverPort;
    private String hotfix;//热更新信息
    private String shields;//屏蔽信息

    private String define1;//用户自定义
    private String define2;//用户自定义
    private String params;//热更新信息

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getHotfix() {
        return hotfix;
    }

    public void setHotfix(String hotfix) {
        this.hotfix = hotfix;
    }

    public String getShields() {
        return shields;
    }

    public void setShields(String shields) {
        this.shields = shields;
    }

    public String getDefine1() {
        return define1;
    }

    public void setDefine1(String define1) {
        this.define1 = define1;
    }

    public String getDefine2() {
        return define2;
    }

    public void setDefine2(String define2) {
        this.define2 = define2;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "VersionConfig{" +
                "id=" + id +
                ", appid='" + appid + '\'' +
                ", appname='" + appname + '\'' +
                ", channelid='" + channelid + '\'' +
                ", channelname='" + channelname + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", serverIp='" + serverIp + '\'' +
                ", serverPort='" + serverPort + '\'' +
                ", hotfix='" + hotfix + '\'' +
                ", shields='" + shields + '\'' +
                ", define1='" + define1 + '\'' +
                ", define2='" + define2 + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
