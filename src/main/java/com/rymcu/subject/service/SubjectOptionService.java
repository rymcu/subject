package com.rymcu.subject.service;

import com.rymcu.subject.domain.SubjectOption;
public interface SubjectOptionService{


    int deleteByPrimaryKey(Long id);

    int insert(SubjectOption record);

    int insertSelective(SubjectOption record);

    SubjectOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectOption record);

    int updateByPrimaryKey(SubjectOption record);

}
