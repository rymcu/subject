package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rymcu.subject.domain.SubjectTagKnowledgePointsId;
import com.rymcu.subject.mapper.SubjectTagKnowledgePointsIdMapper;
import com.rymcu.subject.service.SubjectTagKnowledgePointsIdService;

@Service
public class SubjectTagKnowledgePointsIdServiceImpl implements SubjectTagKnowledgePointsIdService {

    @Resource
    private SubjectTagKnowledgePointsIdMapper subjectTagKnowledgePointsIdMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectTagKnowledgePointsIdMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectTagKnowledgePointsId record) {
        return subjectTagKnowledgePointsIdMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectTagKnowledgePointsId record) {
        return subjectTagKnowledgePointsIdMapper.insertSelective(record);
    }

    @Override
    public SubjectTagKnowledgePointsId selectByPrimaryKey(Long id) {
        return subjectTagKnowledgePointsIdMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectTagKnowledgePointsId record) {
        return subjectTagKnowledgePointsIdMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectTagKnowledgePointsId record) {
        return subjectTagKnowledgePointsIdMapper.updateByPrimaryKey(record);
    }

}
