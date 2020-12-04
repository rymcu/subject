package com.rymcu.subject.dto;

import com.rymcu.subject.entity.SubjectAnswerRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目表 题目表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectQuestionDTO implements Serializable {
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
     * 来源类型 0-默认，1-系统，2-管理员，3-用户，4-脚本
     */
    private String srcType;

    private String questionContent;
    private List<SubjectOptionDTO> subjectOptionDTOList;

    private SubjectAnswerRecord subjectAnswerRecord;
}
