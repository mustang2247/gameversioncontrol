package com.mybitop.gameversioncontrol.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "hotupdatecheck", schema = "versionservice", catalog = "")
public class HotupdatecheckEntity {
    private int id;
    private String appid;
    private String appname;
    private String channelid;
    private String channelname;
    private String appVersion;
    private int updatestrategy;
    private String baseurl;
    private String apkurl;
    private String promptcollection;
    private String forcecollection;
    private String excludecollection;
    private String updateinfo;
    private Timestamp createtime;
    private Timestamp updatetime;

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
    @Column(name = "updatestrategy")
    public int getUpdatestrategy() {
        return updatestrategy;
    }

    public void setUpdatestrategy(int updatestrategy) {
        this.updatestrategy = updatestrategy;
    }

    @Basic
    @Column(name = "baseurl")
    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    @Basic
    @Column(name = "apkurl")
    public String getApkurl() {
        return apkurl;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }

    @Basic
    @Column(name = "promptcollection")
    public String getPromptcollection() {
        return promptcollection;
    }

    public void setPromptcollection(String promptcollection) {
        this.promptcollection = promptcollection;
    }

    @Basic
    @Column(name = "forcecollection")
    public String getForcecollection() {
        return forcecollection;
    }

    public void setForcecollection(String forcecollection) {
        this.forcecollection = forcecollection;
    }

    @Basic
    @Column(name = "excludecollection")
    public String getExcludecollection() {
        return excludecollection;
    }

    public void setExcludecollection(String excludecollection) {
        this.excludecollection = excludecollection;
    }

    @Basic
    @Column(name = "updateinfo")
    public String getUpdateinfo() {
        return updateinfo;
    }

    public void setUpdateinfo(String updateinfo) {
        this.updateinfo = updateinfo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotupdatecheckEntity that = (HotupdatecheckEntity) o;

        if (id != that.id) return false;
        if (updatestrategy != that.updatestrategy) return false;
        if (appid != null ? !appid.equals(that.appid) : that.appid != null) return false;
        if (appname != null ? !appname.equals(that.appname) : that.appname != null) return false;
        if (channelid != null ? !channelid.equals(that.channelid) : that.channelid != null) return false;
        if (channelname != null ? !channelname.equals(that.channelname) : that.channelname != null) return false;
        if (appVersion != null ? !appVersion.equals(that.appVersion) : that.appVersion != null) return false;
        if (baseurl != null ? !baseurl.equals(that.baseurl) : that.baseurl != null) return false;
        if (apkurl != null ? !apkurl.equals(that.apkurl) : that.apkurl != null) return false;
        if (promptcollection != null ? !promptcollection.equals(that.promptcollection) : that.promptcollection != null)
            return false;
        if (forcecollection != null ? !forcecollection.equals(that.forcecollection) : that.forcecollection != null)
            return false;
        if (excludecollection != null ? !excludecollection.equals(that.excludecollection) : that.excludecollection != null)
            return false;
        if (updateinfo != null ? !updateinfo.equals(that.updateinfo) : that.updateinfo != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (updatetime != null ? !updatetime.equals(that.updatetime) : that.updatetime != null) return false;

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
        result = 31 * result + updatestrategy;
        result = 31 * result + (baseurl != null ? baseurl.hashCode() : 0);
        result = 31 * result + (apkurl != null ? apkurl.hashCode() : 0);
        result = 31 * result + (promptcollection != null ? promptcollection.hashCode() : 0);
        result = 31 * result + (forcecollection != null ? forcecollection.hashCode() : 0);
        result = 31 * result + (excludecollection != null ? excludecollection.hashCode() : 0);
        result = 31 * result + (updateinfo != null ? updateinfo.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        return result;
    }
}
