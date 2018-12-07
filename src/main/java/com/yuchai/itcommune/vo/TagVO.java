package com.yuchai.itcommune.vo;


import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Haven
 * @since 2018-10-08
 */
public class TagVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户
     */
    private String userCode;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 描述
     */
    private String tagDesc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String desc) {
        this.tagDesc = desc;
    }

    @Override
    public String toString() {
        return "Tag{" +
        "id=" + id +
        ", userCode=" + userCode +
        ", tagName=" + tagName +
        ", desc=" + tagDesc +
        "}";
    }
}
