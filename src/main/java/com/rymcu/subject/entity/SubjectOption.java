package com.rymcu.subject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目选项表 题目选项表、答案表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectOption {
    /**
     * 主键
     */
    private Long id;

    /**
     * 题目表编号 题目表编号
     */
    private Long subjectQuestionId;

    /**
     * 选项名 选项：ABCDEFG，or答案
     */
    private String optionName;

    /**
     * 选项内容
     */
    private String optionContent;

    /**
     * 是否是答案 1-正确选项，0-正常选项
     */
    private String answerFlag;

    private static final long serialVersionUID = 1L;
}
