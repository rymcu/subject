package com.rymcu.subject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 试题
 *
 * @author 🐛
 * @date Created in 2021/3/4 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 来源类型 0-默认，1-系统，2-管理员，3-用户，4-脚本

     */
    private String srcType;

    /**
     * 错题标志： 1-错题，0-正常
     */
    private Boolean errorFlag;

    /**
     * 展示标志： 1-展示，0-隐藏
     */
    private Boolean showFlag;
    /**
     * 试题内容
     */
    private String questionContent;

    /**
     * 选择项
     */
    private List<SubjectOption> options;
}
