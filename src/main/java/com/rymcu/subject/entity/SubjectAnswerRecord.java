package com.rymcu.subject.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 答题记录表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectAnswerRecord implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 题目编号
     */
    private Long subjectQuestionId;

    /**
     * 我的答案 如abcd，填空值，or简答题的答案
     */
    private String answer;

    /**
     * 答题时间
     */
    private Date createdTime;

    /**
     * 答题人
     */
    private Long createdBy;

    private static final long serialVersionUID = 1L;
}
