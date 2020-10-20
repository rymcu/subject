package com.rymcu.subject.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 题目要点关联表 
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectImportantRelation implements Serializable {
    /**
    * ID
    */
    private Long id;

    /**
    * 题目表ID
    */
    private Long subjectQuestionId;

    /**
    * 知识点表ID
    */
    private Long subjectKnowledgePointsId;

    private static final long serialVersionUID = 1L;
}