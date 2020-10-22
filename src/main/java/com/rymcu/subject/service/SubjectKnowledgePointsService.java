package com.rymcu.subject.service;

import com.rymcu.subject.entity.SubjectKnowledgePoints;

public interface SubjectKnowledgePointsService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectKnowledgePoints record);

    int insertSelective(SubjectKnowledgePoints record);

    SubjectKnowledgePoints selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectKnowledgePoints record);

    int updateByPrimaryKey(SubjectKnowledgePoints record);

}
