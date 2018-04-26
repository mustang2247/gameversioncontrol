package com.open.coinnews.app.model;

import javax.persistence.*;

/**
 * 类别
 */
@Entity
@Table(name = "t_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 名称 */
    private String name;

    /** 描述 */
    @Lob
    private String remark;

    /** 图标，采用bootstrap或fontawesome.io */
    private String icon;

    /** 序号 */
    @Column(name = "order_no")
    private Integer orderNo;

    /** 是否导航显示 */
    @Column(name = "is_nav")
    private Integer isNav;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getIsNav() {
        return isNav;
    }

    public void setIsNav(Integer isNav) {
        this.isNav = isNav;
    }
}
