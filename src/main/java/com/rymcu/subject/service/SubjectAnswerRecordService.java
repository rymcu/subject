package com.rymcu.subject.service;

import com.rymcu.subject.entity.SubjectAnswerRecord;

import java.util.Date;
import java.util.List;

public interface SubjectAnswerRecordService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectAnswerRecord record);

    int insertSelective(SubjectAnswerRecord record);

    SubjectAnswerRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectAnswerRecord record);

    int updateByPrimaryKey(SubjectAnswerRecord record);

    List<SubjectAnswerRecord> getTodayAnswerRecord(long userId, Date now, boolean everydayFlag);

    List<SubjectAnswerRecord> getAnswerRecord(long userId, Date now);

    int insertEveryDayAnswer(SubjectAnswerRecord subjectAnswerRecord);
}
