package com.rymcu.subject.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目收藏表 题目收藏表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCollection implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 题目编号
     */
    private Long subjectQuestionId;

    /**
     * 收藏者
     */
    private String userId;

    /**
     * 收藏时间
     */
    private String createdTime;

    private static final long serialVersionUID = 1L;
}
