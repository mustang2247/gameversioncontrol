package com.mybitop.gameversioncontrol.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "hotupdateconfig", schema = "versionservice", catalog = "")
public class HotupdateconfigEntity {
    private int id;
    private String appid;
    private String appname;
    private String channelid;
    private String channelname;
    private String appVersion;
    private Timestamp createtime;
    private Timestamp updatetime;
    private String serverIp;
    private String serverPort;
    private String hotfix;
    private String shields;
    private String define1;
    private String define2;
    private String params;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "appid")
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Basic
    @Column(name = "appname")
    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    @Basic
    @Column(name = "channelid")
    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    @Basic
    @Column(name = "channelname")
    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    @Basic
    @Column(name = "appVersion")
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "updatetime")
    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    @Basic
    @Column(name = "serverIp")
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Basic
    @Column(name = "serverPort")
    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    @Basic
    @Column(name = "hotfix")
    public String getHotfix() {
        return hotfix;
    }

    public void setHotfix(String hotfix) {
        this.hotfix = hotfix;
    }

    @Basic
    @Column(name = "shields")
    public String getShields() {
        return shields;
    }

    public void setShields(String shields) {
        this.shields = shields;
    }

    @Basic
    @Column(name = "define1")
    public String getDefine1() {
        return define1;
    }

    public void setDefine1(String define1) {
        this.define1 = define1;
    }

    @Basic
    @Column(name = "define2")
    public String getDefine2() {
        return define2;
    }

    public void setDefine2(String define2) {
        this.define2 = define2;
    }

    @Basic
    @Column(name = "params")
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotupdateconfigEntity that = (HotupdateconfigEntity) o;

        if (id != that.id) return false;
        if (appid != null ? !appid.equals(that.appid) : that.appid != null) return false;
        if (appname != null ? !appname.equals(that.appname) : that.appname != null) return false;
        if (channelid != null ? !channelid.equals(that.channelid) : that.channelid != null) return false;
        if (channelname != null ? !channelname.equals(that.channelname) : that.channelname != null) return false;
        if (appVersion != null ? !appVersion.equals(that.appVersion) : that.appVersion != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (updatetime != null ? !updatetime.equals(that.updatetime) : that.updatetime != null) return false;
        if (serverIp != null ? !serverIp.equals(that.serverIp) : that.serverIp != null) return false;
        if (serverPort != null ? !serverPort.equals(that.serverPort) : that.serverPort != null) return false;
        if (hotfix != null ? !hotfix.equals(that.hotfix) : that.hotfix != null) return false;
        if (shields != null ? !shields.equals(that.shields) : that.shields != null) return false;
        if (define1 != null ? !define1.equals(that.define1) : that.define1 != null) return false;
        if (define2 != null ? !define2.equals(that.define2) : that.define2 != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (appid != null ? appid.hashCode() : 0);
        result = 31 * result + (appname != null ? appname.hashCode() : 0);
        result = 31 * result + (channelid != null ? channelid.hashCode() : 0);
        result = 31 * result + (channelname != null ? channelname.hashCode() : 0);
        result = 31 * result + (appVersion != null ? appVersion.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (serverIp != null ? serverIp.hashCode() : 0);
        result = 31 * result + (serverPort != null ? serverPort.hashCode() : 0);
        result = 31 * result + (hotfix != null ? hotfix.hashCode() : 0);
        result = 31 * result + (shields != null ? shields.hashCode() : 0);
        result = 31 * result + (define1 != null ? define1.hashCode() : 0);
        result = 31 * result + (define2 != null ? define2.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }
}
