package com.rymcu.subject.service;

import com.rymcu.subject.entity.SubjectAnswerRecord;

public interface SubjectAnswerRecordService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectAnswerRecord record);

    int insertSelective(SubjectAnswerRecord record);

    SubjectAnswerRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectAnswerRecord record);

    int updateByPrimaryKey(SubjectAnswerRecord record);

}
