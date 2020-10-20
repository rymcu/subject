package com.rymcu.subject.service;

import com.rymcu.subject.domain.SubjectImportantRelation;
public interface SubjectImportantRelationService{


    int deleteByPrimaryKey(Long id);

    int insert(SubjectImportantRelation record);

    int insertSelective(SubjectImportantRelation record);

    SubjectImportantRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectImportantRelation record);

    int updateByPrimaryKey(SubjectImportantRelation record);

}
