package com.open.coinnews.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 视频
 */
@Entity
@Table(name = "t_video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 标题 */
    private String title;

    /** 内容 */
    @Lob
    private String content;

    /** markdown的内容 */
    @Lob
    private String mdContent;

    /** 导读 */
    @Lob
    private String guide;

    @Column(name = "create_date")
    private Date createDate;

    /** 所在分类Id */
    @Column(name = "cate_id")
    private Integer cateId;

    /** 所在分类名称 */
    @Column(name = "cate_name")
    private String cateName;

    /** 阅读次数 */
    @Column(name = "read_count")
    private Integer readCount;

    /** 标签 */
    private String tags;

    /** 是否前台显示 */
    @Column(name = "is_show")
    private Integer isShow;

    /** 图片路径 */
    @Column(name = "pic_path")
    private String picPath;

    /** 点评次数 */
    @Column(name = "comment_count")
    private Integer commentCount;

    /** 关联用户Id */
    @Column(name = "user_id")
    private Integer userId;

    /** 关联用户昵称 */
    @Column(name = "real_name")
    private String realName;

    /** 视频地址 */
    @Column(name = "video_url")
    private String videoUrl;

    /** 视频高度，可以是百分比，也可以是px */
    @Column(name = "video_height")
    private String videoHeight;

    /** 是否推荐 */
    private Integer recommend;

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(String videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
