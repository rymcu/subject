package com.rymcu.subject.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目标签表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectTag implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 标签描述
     */
    private String tagDescription;

    /**
     * 标签URI
     */
    private String tagUri;

    /**
     * 创建人
     */
    private Long userId;

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

    private static final long serialVersionUID = 1L;
}
