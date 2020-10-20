package com.rymcu.subject.service;

import com.rymcu.subject.domain.SubjectCollection;
public interface SubjectCollectionService{


    int deleteByPrimaryKey(Long id);

    int insert(SubjectCollection record);

    int insertSelective(SubjectCollection record);

    SubjectCollection selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectCollection record);

    int updateByPrimaryKey(SubjectCollection record);

}
