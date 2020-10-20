package com.rymcu.subject.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 知识点
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectKnowledgePoints implements Serializable {
    /**
     * id 主键
     */
    private Long id;

    /**
     * 知识点内容
     */
    private String content;

    /**
     * 知识点标题
     */
    private String title;

    /**
     * 来源类型 0-未知，1-系统脚本，2-管理员，3-用户
     */
    private Integer srcType;

    /**
     * 审核过关 0-不过关，1-过关
     */
    private String flag;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    private static final long serialVersionUID = 1L;
}