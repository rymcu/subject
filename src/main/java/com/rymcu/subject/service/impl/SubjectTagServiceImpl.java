package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rymcu.subject.domain.SubjectTag;
import com.rymcu.subject.mapper.SubjectTagMapper;
import com.rymcu.subject.service.SubjectTagService;
@Service
public class SubjectTagServiceImpl implements SubjectTagService{

    @Resource
    private SubjectTagMapper subjectTagMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectTagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectTag record) {
        return subjectTagMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectTag record) {
        return subjectTagMapper.insertSelective(record);
    }

    @Override
    public SubjectTag selectByPrimaryKey(Long id) {
        return subjectTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectTag record) {
        return subjectTagMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectTag record) {
        return subjectTagMapper.updateByPrimaryKey(record);
    }

}
