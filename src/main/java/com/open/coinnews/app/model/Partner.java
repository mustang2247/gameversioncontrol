package com.open.coinnews.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 伙伴
 */
@Entity
@Table(name = "t_partner")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 真实姓名 */
    @Column(name = "real_name")
    private String realName;

    /** 昵称 */
    @Column(name = "nick_name")
    private String nickName;

    /** 头像地址 */
    @Column(name = "head_img")
    private String headImg;

    /** 微信地址 */
    @Column(name = "wx_img")
    private String wxImg;

    /** 支付宝地址 */
    @Column(name = "zfb_img")
    private String zfbImg;

    /** 备注 */
    @Lob
    private String remark;

    @Lob
    @Column(name = "md_remark")
    private String mdRemark;

    /** 关联的用户Id */
    @Column(name = "user_id")
    private Integer userId;

    /** 关联的用户名 */
    private String username;

    /** 用于排序的序号 */
    @Column(name = "order_no")
    private Integer orderNo;

    /** 状态，1：前台显示；0：前台隐藏 */
    private String status;

    /** 加入日期 */
    @Column(name = "create_date")
    private Date createDate;

    /** 是否显示支付 */
    @Column(name = "show_pay")
    private Integer showPay;

    /** 邮箱 */
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getShowPay() {
        return showPay;
    }

    public void setShowPay(Integer showPay) {
        this.showPay = showPay;
    }

    public String getMdRemark() {
        return mdRemark;
    }

    public void setMdRemark(String mdRemark) {
        this.mdRemark = mdRemark;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getWxImg() {
        return wxImg;
    }

    public void setWxImg(String wxImg) {
        this.wxImg = wxImg;
    }

    public String getZfbImg() {
        return zfbImg;
    }

    public void setZfbImg(String zfbImg) {
        this.zfbImg = zfbImg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
