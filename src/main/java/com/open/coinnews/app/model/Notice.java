package com.open.coinnews.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 通知
 */
@Entity
@Table(name = "t_notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 内容 */
    @Lob
    private String content;

    /** markdown内容 */
    @Column(name = "md_content")
    @Lob
    private String mdContent;

    /** 是否显示 */
    @Column(name = "is_show")
    private Integer isShow;

    /** 序号 */
    @Column(name = "order_no")
    private Integer orderNo;

    @Column(name = "create_date")
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
