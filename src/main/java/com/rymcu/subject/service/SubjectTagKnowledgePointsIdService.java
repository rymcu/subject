package com.rymcu.subject.service;

import com.rymcu.subject.domain.SubjectTagKnowledgePointsId;

public interface SubjectTagKnowledgePointsIdService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectTagKnowledgePointsId record);

    int insertSelective(SubjectTagKnowledgePointsId record);

    SubjectTagKnowledgePointsId selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectTagKnowledgePointsId record);

    int updateByPrimaryKey(SubjectTagKnowledgePointsId record);

}
