package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rymcu.subject.mapper.SubjectImportantRelationMapper;
import com.rymcu.subject.domain.SubjectImportantRelation;
import com.rymcu.subject.service.SubjectImportantRelationService;
@Service
public class SubjectImportantRelationServiceImpl implements SubjectImportantRelationService{

    @Resource
    private SubjectImportantRelationMapper subjectImportantRelationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectImportantRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectImportantRelation record) {
        return subjectImportantRelationMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectImportantRelation record) {
        return subjectImportantRelationMapper.insertSelective(record);
    }

    @Override
    public SubjectImportantRelation selectByPrimaryKey(Long id) {
        return subjectImportantRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectImportantRelation record) {
        return subjectImportantRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectImportantRelation record) {
        return subjectImportantRelationMapper.updateByPrimaryKey(record);
    }

}
