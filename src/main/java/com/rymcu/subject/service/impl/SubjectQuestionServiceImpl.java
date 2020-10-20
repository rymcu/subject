package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rymcu.subject.domain.SubjectQuestion;
import com.rymcu.subject.mapper.SubjectQuestionMapper;
import com.rymcu.subject.service.SubjectQuestionService;
@Service
public class SubjectQuestionServiceImpl implements SubjectQuestionService{

    @Resource
    private SubjectQuestionMapper subjectQuestionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectQuestionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectQuestion record) {
        return subjectQuestionMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectQuestion record) {
        return subjectQuestionMapper.insertSelective(record);
    }

    @Override
    public SubjectQuestion selectByPrimaryKey(Long id) {
        return subjectQuestionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectQuestion record) {
        return subjectQuestionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectQuestion record) {
        return subjectQuestionMapper.updateByPrimaryKey(record);
    }

    @Override
    public SubjectQuestion getNextByUserId(Long userId) {
        final var nextOrder = Math.next
        return null;
    }

}
