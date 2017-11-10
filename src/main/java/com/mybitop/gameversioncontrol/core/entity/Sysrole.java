package com.mybitop.gameversioncontrol.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 系统角色实体类
 * @author 
 */
public class Sysrole implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    private Boolean available;

    /**
     * 角色描述,UI界面显示使用
     */
    private String description;

    /**
     * 角色标识 程序中判断使用,如"admin",这个是唯一的
     */
    private String role;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    //角色 -- 权限关系： 多对多关系;
//    @ManyToMany(fetch=FetchType.EAGER)
//    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<Syspermission> permissions;

    // 用户 - 角色关系定义;
//    @ManyToMany
//    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<Userinfo> userInfos;// 一个角色对应多个用户

    public List<Syspermission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Syspermission> permissions) {
        this.permissions = permissions;
    }

    public List<Userinfo> getUserInfos() {
        return userInfos;
    }
    public void setUserInfos(List<Userinfo> userInfos) {
        this.userInfos = userInfos;
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
        Sysrole other = (Sysrole) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAvailable() == null ? other.getAvailable() == null : this.getAvailable().equals(other.getAvailable()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAvailable() == null) ? 0 : getAvailable().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", available=").append(available);
        sb.append(", description=").append(description);
        sb.append(", role=").append(role);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}