package com.rymcu.subject.service;

import com.rymcu.subject.domain.SubjectTag;
public interface SubjectTagService{


    int deleteByPrimaryKey(Long id);

    int insert(SubjectTag record);

    int insertSelective(SubjectTag record);

    SubjectTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectTag record);

    int updateByPrimaryKey(SubjectTag record);

}
