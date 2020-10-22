package com.rymcu.subject.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签与知识点关联表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectTagKnowledgePointsId implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 标签表主键
     */
    private Long subjectTagId;

    /**
     * 知识点表主键
     */
    private Long subjectKnowledgePointsId;

    private static final long serialVersionUID = 1L;
}
