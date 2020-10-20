package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rymcu.subject.domain.SubjectKnowledgePoints;
import com.rymcu.subject.mapper.SubjectKnowledgePointsMapper;
import com.rymcu.subject.service.SubjectKnowledgePointsService;

@Service
public class SubjectKnowledgePointsServiceImpl implements SubjectKnowledgePointsService {

    @Resource
    private SubjectKnowledgePointsMapper subjectImportantPointsMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectImportantPointsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectKnowledgePoints record) {
        return subjectImportantPointsMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectKnowledgePoints record) {
        return subjectImportantPointsMapper.insertSelective(record);
    }

    @Override
    public SubjectKnowledgePoints selectByPrimaryKey(Long id) {
        return subjectImportantPointsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectKnowledgePoints record) {
        return subjectImportantPointsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectKnowledgePoints record) {
        return subjectImportantPointsMapper.updateByPrimaryKey(record);
    }

}
