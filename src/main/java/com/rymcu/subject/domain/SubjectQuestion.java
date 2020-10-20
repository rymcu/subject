package com.rymcu.subject.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目表 题目表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectQuestion implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 题目类型 1-单选；2-多选;3-填空;4-问答
     */
    private Integer questionType;

    /**
     * 难易程度 1-100
     */
    private Integer questionLevel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 来源网站 网址
     */
    private String questionUrl;

    /**
     * 来源描述 如：牛客网
     */
    private String questionUrlRemark;

    /**
     * 来源类型 0-默认，1-系统，2-管理员，3-用户，4-脚本
     */
    private String srcType;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    private String questionContent;

    private static final long serialVersionUID = 1L;
}