package com.mybitop.gameversioncontrol.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 热更新通知
 *
 * @author 
 */
@Entity
@Table(name="hotupdatenotice")
public class Hotupdatenotice implements Serializable {
    @Id
    @GeneratedValue
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
     * 当前应用版本
     */
    @NotEmpty
    private String appversion;

    /**
     * 更新信息
     */
    @NotEmpty
    private String updatemessage;

    /**
     * 创建时间
     */
    @NotEmpty
    private Date createtime;

    /**
     * 更新时间
     */
    @NotEmpty
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

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getUpdatemessage() {
        return updatemessage;
    }

    public void setUpdatemessage(String updatemessage) {
        this.updatemessage = updatemessage;
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
        Hotupdatenotice other = (Hotupdatenotice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppid() == null ? other.getAppid() == null : this.getAppid().equals(other.getAppid()))
            && (this.getAppname() == null ? other.getAppname() == null : this.getAppname().equals(other.getAppname()))
            && (this.getAppversion() == null ? other.getAppversion() == null : this.getAppversion().equals(other.getAppversion()))
            && (this.getUpdatemessage() == null ? other.getUpdatemessage() == null : this.getUpdatemessage().equals(other.getUpdatemessage()))
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
        result = prime * result + ((getAppversion() == null) ? 0 : getAppversion().hashCode());
        result = prime * result + ((getUpdatemessage() == null) ? 0 : getUpdatemessage().hashCode());
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
        sb.append(", appversion=").append(appversion);
        sb.append(", updatemessage=").append(updatemessage);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}