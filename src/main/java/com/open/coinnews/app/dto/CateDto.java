package com.open.coinnews.app.dto;

/**
 * 类别
 */
public class CateDto {

    private Integer cateId;

    private String cateName;

    private Long amount;

    public CateDto() {}

    public CateDto(Integer cateId, String cateName, Long amount) {
        this.cateId = cateId; this.cateName = cateName;
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public Integer getCateId() {
        return cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
