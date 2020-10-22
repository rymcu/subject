package com.rymcu.subject.service;

import com.rymcu.subject.entity.SubjectKnowledgeRelation;

public interface SubjectKnowledgeRelationService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectKnowledgeRelation record);

    int insertSelective(SubjectKnowledgeRelation record);

    SubjectKnowledgeRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectKnowledgeRelation record);

    int updateByPrimaryKey(SubjectKnowledgeRelation record);

}
