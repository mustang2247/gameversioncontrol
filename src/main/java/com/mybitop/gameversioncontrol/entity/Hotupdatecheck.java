package com.mybitop.gameversioncontrol.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 热更新部署模板
 * @author 
 */
//@Entity
//@Table(name="hotupdatecheck")
public class Hotupdatecheck implements Serializable {
//    @Id
//    @GeneratedValue
    private Integer id;

    /**
     * 应用appid
     */
    @NotEmpty
    private String appid;

    /**
     * 应用名称
     */
    @NotEmpty
    private String appname;

    /**
     * 渠道id
     */
    @NotEmpty
    private String channelid;

    /**
     * 渠道名称
     */
    @NotEmpty
    private String channelname;

    /**
     * 当前应用版本
     */
    @NotEmpty
    private String appversion;

    /**
     * 更新策略 1:提示更新 2:强制更新 3:不更新
     */
    @NotNull
    private Integer updatestrategy;

    /**
     * 热更baseUrl
     */
    @NotEmpty
    private String baseurl;

    /**
     * apk跟新地址
     */
    @NotEmpty
    private String apkurl;

    /**
     * 提示更新集合
     */
    private String promptcollection;

    /**
     * 强制更新集合 例如：1.0,1.1
     */
    private String forcecollection;

    /**
     * 排除更新集合
     */
    private String excludecollection;

    /**
     * 更新信息
     */
    private String updateinfo;

    /**
     * 创建时间
     */
//    @NotEmpty
    private Date createtime;

    /**
     * 更新时间
     */
//    @NotEmpty
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public Integer getUpdatestrategy() {
        return updatestrategy;
    }

    public void setUpdatestrategy(Integer updatestrategy) {
        this.updatestrategy = updatestrategy;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public String getApkurl() {
        return apkurl;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }

    public String getPromptcollection() {
        return promptcollection;
    }

    public void setPromptcollection(String promptcollection) {
        this.promptcollection = promptcollection;
    }

    public String getForcecollection() {
        return forcecollection;
    }

    public void setForcecollection(String forcecollection) {
        this.forcecollection = forcecollection;
    }

    public String getExcludecollection() {
        return excludecollection;
    }

    public void setExcludecollection(String excludecollection) {
        this.excludecollection = excludecollection;
    }

    public String getUpdateinfo() {
        return updateinfo;
    }

    public void setUpdateinfo(String updateinfo) {
        this.updateinfo = updateinfo;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Hotupdatecheck other = (Hotupdatecheck) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppid() == null ? other.getAppid() == null : this.getAppid().equals(other.getAppid()))
            && (this.getAppname() == null ? other.getAppname() == null : this.getAppname().equals(other.getAppname()))
            && (this.getChannelid() == null ? other.getChannelid() == null : this.getChannelid().equals(other.getChannelid()))
            && (this.getChannelname() == null ? other.getChannelname() == null : this.getChannelname().equals(other.getChannelname()))
            && (this.getAppversion() == null ? other.getAppversion() == null : this.getAppversion().equals(other.getAppversion()))
            && (this.getUpdatestrategy() == null ? other.getUpdatestrategy() == null : this.getUpdatestrategy().equals(other.getUpdatestrategy()))
            && (this.getBaseurl() == null ? other.getBaseurl() == null : this.getBaseurl().equals(other.getBaseurl()))
            && (this.getApkurl() == null ? other.getApkurl() == null : this.getApkurl().equals(other.getApkurl()))
            && (this.getPromptcollection() == null ? other.getPromptcollection() == null : this.getPromptcollection().equals(other.getPromptcollection()))
            && (this.getForcecollection() == null ? other.getForcecollection() == null : this.getForcecollection().equals(other.getForcecollection()))
            && (this.getExcludecollection() == null ? other.getExcludecollection() == null : this.getExcludecollection().equals(other.getExcludecollection()))
            && (this.getUpdateinfo() == null ? other.getUpdateinfo() == null : this.getUpdateinfo().equals(other.getUpdateinfo()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppid() == null) ? 0 : getAppid().hashCode());
        result = prime * result + ((getAppname() == null) ? 0 : getAppname().hashCode());
        result = prime * result + ((getChannelid() == null) ? 0 : getChannelid().hashCode());
        result = prime * result + ((getChannelname() == null) ? 0 : getChannelname().hashCode());
        result = prime * result + ((getAppversion() == null) ? 0 : getAppversion().hashCode());
        result = prime * result + ((getUpdatestrategy() == null) ? 0 : getUpdatestrategy().hashCode());
        result = prime * result + ((getBaseurl() == null) ? 0 : getBaseurl().hashCode());
        result = prime * result + ((getApkurl() == null) ? 0 : getApkurl().hashCode());
        result = prime * result + ((getPromptcollection() == null) ? 0 : getPromptcollection().hashCode());
        result = prime * result + ((getForcecollection() == null) ? 0 : getForcecollection().hashCode());
        result = prime * result + ((getExcludecollection() == null) ? 0 : getExcludecollection().hashCode());
        result = prime * result + ((getUpdateinfo() == null) ? 0 : getUpdateinfo().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appid=").append(appid);
        sb.append(", appname=").append(appname);
        sb.append(", channelid=").append(channelid);
        sb.append(", channelname=").append(channelname);
        sb.append(", appversion=").append(appversion);
        sb.append(", updatestrategy=").append(updatestrategy);
        sb.append(", baseurl=").append(baseurl);
        sb.append(", apkurl=").append(apkurl);
        sb.append(", promptcollection=").append(promptcollection);
        sb.append(", forcecollection=").append(forcecollection);
        sb.append(", excludecollection=").append(excludecollection);
        sb.append(", updateinfo=").append(updateinfo);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}