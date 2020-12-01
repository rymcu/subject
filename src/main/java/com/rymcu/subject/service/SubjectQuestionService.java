package com.rymcu.subject.service;

import com.rymcu.subject.dto.SubjectQuestionDTO;
import com.rymcu.subject.entity.SubjectQuestion;

public interface SubjectQuestionService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectQuestion record);

    int insertSelective(SubjectQuestion record);

    SubjectQuestionDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectQuestion record);

    int updateByPrimaryKey(SubjectQuestion record);

    SubjectQuestionDTO getNextByUserId(Long userId);

    SubjectQuestionDTO getNext();

}
