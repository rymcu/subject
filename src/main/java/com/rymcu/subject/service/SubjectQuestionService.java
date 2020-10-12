package com.rymcu.subject.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rymcu.subject.domain.SubjectQuestion;
import com.rymcu.subject.mapper.SubjectQuestionMapper;

@Service
public class SubjectQuestionService {

    @Resource
    private SubjectQuestionMapper subjectQuestionMapper;


    public int deleteByPrimaryKey(Long id) {
        return subjectQuestionMapper.deleteByPrimaryKey(id);
    }


    public int insert(SubjectQuestion record) {
        return subjectQuestionMapper.insert(record);
    }


    public int insertSelective(SubjectQuestion record) {
        return subjectQuestionMapper.insertSelective(record);
    }


    public SubjectQuestion selectByPrimaryKey(Long id) {
        return subjectQuestionMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(SubjectQuestion record) {
        return subjectQuestionMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(SubjectQuestion record) {
        return subjectQuestionMapper.updateByPrimaryKey(record);
    }

}


