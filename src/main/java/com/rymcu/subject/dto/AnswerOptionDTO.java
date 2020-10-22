package com.rymcu.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 题目选项表 题目选项表、答案表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerOptionDTO implements Serializable {

    /**
     * 选项名 选项：ABCDEFG，or答案
     */
    private String optionName;

    /**
     * 选项内容
     */
    private String optionContent;

    /**
     * 是否正确答案
     */
    private boolean answerFlag;

    private static final long serialVersionUID = 1L;
}
