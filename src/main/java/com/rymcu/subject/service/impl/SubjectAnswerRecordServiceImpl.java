package com.rymcu.subject.service.impl;

import com.rymcu.subject.entity.SubjectAnswerRecord;
import com.rymcu.subject.mapper.SubjectAnswerRecordMapper;
import com.rymcu.subject.service.SubjectAnswerRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SubjectAnswerRecordServiceImpl implements SubjectAnswerRecordService {

    @Resource
    private SubjectAnswerRecordMapper subjectAnswerRecordMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectAnswerRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectAnswerRecord record) {
        return subjectAnswerRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectAnswerRecord record) {
        return subjectAnswerRecordMapper.insertSelective(record);
    }

    @Override
    public SubjectAnswerRecord selectByPrimaryKey(Long id) {
        return subjectAnswerRecordMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<SubjectAnswerRecord> getTodayAnswerRecord(long userId, Date now, boolean everydayFlag) {
        return subjectAnswerRecordMapper.getTodayAnswerRecord(userId, now,everydayFlag);
    }

    @Override
    public List<SubjectAnswerRecord> getAnswerRecord(long userId, Date now) {
        return subjectAnswerRecordMapper.getAnswerRecord(userId, now);
    }

    @Override
    public int insertEveryDayAnswer(SubjectAnswerRecord subjectAnswerRecord) {
        return subjectAnswerRecordMapper.insertEveryDayAnswer(subjectAnswerRecord);
    }

}
