package com.rymcu.subject.service;

import com.rymcu.subject.domain.SubjectImportantPoints;
public interface SubjectImportantPointsService{


    int deleteByPrimaryKey(Long id);

    int insert(SubjectImportantPoints record);

    int insertSelective(SubjectImportantPoints record);

    SubjectImportantPoints selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectImportantPoints record);

    int updateByPrimaryKey(SubjectImportantPoints record);

}
