package com.rymcu.subject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
    * 题目选项表 题目选项表、答案表
    */
@Data
@AllArgsConstructor
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
    private String isAnswer;
}