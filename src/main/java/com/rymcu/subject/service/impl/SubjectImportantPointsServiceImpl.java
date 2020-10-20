package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rymcu.subject.domain.SubjectImportantPoints;
import com.rymcu.subject.mapper.SubjectImportantPointsMapper;
import com.rymcu.subject.service.SubjectImportantPointsService;
@Service
public class SubjectImportantPointsServiceImpl implements SubjectImportantPointsService{

    @Resource
    private SubjectImportantPointsMapper subjectImportantPointsMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectImportantPointsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectImportantPoints record) {
        return subjectImportantPointsMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectImportantPoints record) {
        return subjectImportantPointsMapper.insertSelective(record);
    }

    @Override
    public SubjectImportantPoints selectByPrimaryKey(Long id) {
        return subjectImportantPointsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectImportantPoints record) {
        return subjectImportantPointsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectImportantPoints record) {
        return subjectImportantPointsMapper.updateByPrimaryKey(record);
    }

}
