package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rymcu.subject.mapper.SubjectKnowledgeRelationMapper;
import com.rymcu.subject.domain.SubjectKnowledgeRelation;
import com.rymcu.subject.service.SubjectKnowledgeRelationService;

@Service
public class SubjectKnowledgeRelationServiceImpl implements SubjectKnowledgeRelationService {

    @Resource
    private SubjectKnowledgeRelationMapper subjectImportantRelationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectImportantRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectKnowledgeRelation record) {
        return subjectImportantRelationMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectKnowledgeRelation record) {
        return subjectImportantRelationMapper.insertSelective(record);
    }

    @Override
    public SubjectKnowledgeRelation selectByPrimaryKey(Long id) {
        return subjectImportantRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectKnowledgeRelation record) {
        return subjectImportantRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectKnowledgeRelation record) {
        return subjectImportantRelationMapper.updateByPrimaryKey(record);
    }

}
