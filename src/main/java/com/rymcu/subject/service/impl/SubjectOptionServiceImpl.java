package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rymcu.subject.domain.SubjectOption;
import com.rymcu.subject.mapper.SubjectOptionMapper;
import com.rymcu.subject.service.SubjectOptionService;

@Service
public class SubjectOptionServiceImpl implements SubjectOptionService {

    @Resource
    private SubjectOptionMapper subjectOptionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectOptionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectOption record) {
        return subjectOptionMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectOption record) {
        return subjectOptionMapper.insertSelective(record);
    }

    @Override
    public SubjectOption selectByPrimaryKey(Long id) {
        return subjectOptionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectOption record) {
        return subjectOptionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectOption record) {
        return subjectOptionMapper.updateByPrimaryKey(record);
    }

}
