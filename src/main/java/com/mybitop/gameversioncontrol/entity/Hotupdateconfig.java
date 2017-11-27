package com.mybitop.gameversioncontrol.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * 热更新配置文件
 * @author 
 */
//@Entity
//@Table(name="hotupdateconfig")
public class Hotupdateconfig implements Serializable {
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
    private String appname;

    /**
     * 渠道id
     */
    @NotEmpty
    private String channelid;

    /**
     * 渠道名称
     */
    private String channelname;

    /**
     * 当前应用版本
     */
    @NotEmpty
    private String appversion;

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

    /**
     * 服务器IP或者域名
     */
    private String serverip;

    /**
     * 服务器端口
     */
    private String serverport;

    /**
     * 热更新信息
     */
    private String hotfix;

    /**
     * 屏蔽信息
     */
    private String shields;

    /**
     * 用户自定义
     */
    private String define1;

    /**
     * 用户自定义
     */
    private String define2;

    /**
     * 热更新信息
     */
    private String params;

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

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public String getServerport() {
        return serverport;
    }

    public void setServerport(String serverport) {
        this.serverport = serverport;
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
        Hotupdateconfig other = (Hotupdateconfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppid() == null ? other.getAppid() == null : this.getAppid().equals(other.getAppid()))
            && (this.getAppname() == null ? other.getAppname() == null : this.getAppname().equals(other.getAppname()))
            && (this.getChannelid() == null ? other.getChannelid() == null : this.getChannelid().equals(other.getChannelid()))
            && (this.getChannelname() == null ? other.getChannelname() == null : this.getChannelname().equals(other.getChannelname()))
            && (this.getAppversion() == null ? other.getAppversion() == null : this.getAppversion().equals(other.getAppversion()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getServerip() == null ? other.getServerip() == null : this.getServerip().equals(other.getServerip()))
            && (this.getServerport() == null ? other.getServerport() == null : this.getServerport().equals(other.getServerport()))
            && (this.getHotfix() == null ? other.getHotfix() == null : this.getHotfix().equals(other.getHotfix()))
            && (this.getShields() == null ? other.getShields() == null : this.getShields().equals(other.getShields()))
            && (this.getDefine1() == null ? other.getDefine1() == null : this.getDefine1().equals(other.getDefine1()))
            && (this.getDefine2() == null ? other.getDefine2() == null : this.getDefine2().equals(other.getDefine2()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()));
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
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getServerip() == null) ? 0 : getServerip().hashCode());
        result = prime * result + ((getServerport() == null) ? 0 : getServerport().hashCode());
        result = prime * result + ((getHotfix() == null) ? 0 : getHotfix().hashCode());
        result = prime * result + ((getShields() == null) ? 0 : getShields().hashCode());
        result = prime * result + ((getDefine1() == null) ? 0 : getDefine1().hashCode());
        result = prime * result + ((getDefine2() == null) ? 0 : getDefine2().hashCode());
        result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
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
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serverip=").append(serverip);
        sb.append(", serverport=").append(serverport);
        sb.append(", hotfix=").append(hotfix);
        sb.append(", shields=").append(shields);
        sb.append(", define1=").append(define1);
        sb.append(", define2=").append(define2);
        sb.append(", params=").append(params);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}