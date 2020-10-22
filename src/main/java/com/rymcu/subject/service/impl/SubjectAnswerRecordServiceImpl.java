package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rymcu.subject.mapper.SubjectAnswerRecordMapper;
import com.rymcu.subject.entity.SubjectAnswerRecord;
import com.rymcu.subject.service.SubjectAnswerRecordService;

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
    public int updateByPrimaryKeySelective(SubjectAnswerRecord record) {
        return subjectAnswerRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectAnswerRecord record) {
        return subjectAnswerRecordMapper.updateByPrimaryKey(record);
    }

}
