package com.rymcu.subject.service;

import com.rymcu.subject.domain.SubjectQuestion;

public interface SubjectQuestionService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectQuestion record);

    int insertSelective(SubjectQuestion record);

    SubjectQuestion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectQuestion record);

    int updateByPrimaryKey(SubjectQuestion record);

    SubjectQuestion getNextByUserId(Long userId);

}
