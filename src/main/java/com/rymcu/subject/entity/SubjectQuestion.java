package com.rymcu.subject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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

    private String questionContent;

    /**
     * 错题标志： 1-错题，0-正常
     */
    private Boolean errorFlag;

    /**
     * 展示标志： 1-展示，0-隐藏
     */
    private Boolean showFlag;

    /**
     * 展示标志： 1-展示，0-隐藏
     */
    private String answer;


    public SubjectQuestion(Question question, boolean createFlag) {
        this.id = question.getId();
        this.questionType = question.getQuestionType();
        this.questionLevel = question.getQuestionLevel();
        this.questionContent = question.getQuestionContent();
        this.remark = question.getRemark();
        this.errorFlag = question.getErrorFlag();
        this.showFlag = question.getShowFlag();
        this.answer = question.getAnswer();
        if (createFlag) {
            this.createdTime = new Date();
            this.userId = 1210L;
        } else {
            this.updatedTime = new Date();
            this.updatedBy = 1211L;
        }
    }

    private static final long serialVersionUID = 1L;
}
