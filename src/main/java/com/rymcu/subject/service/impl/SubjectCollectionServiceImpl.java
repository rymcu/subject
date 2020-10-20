package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rymcu.subject.mapper.SubjectCollectionMapper;
import com.rymcu.subject.domain.SubjectCollection;
import com.rymcu.subject.service.SubjectCollectionService;
@Service
public class SubjectCollectionServiceImpl implements SubjectCollectionService{

    @Resource
    private SubjectCollectionMapper subjectCollectionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectCollectionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectCollection record) {
        return subjectCollectionMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectCollection record) {
        return subjectCollectionMapper.insertSelective(record);
    }

    @Override
    public SubjectCollection selectByPrimaryKey(Long id) {
        return subjectCollectionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectCollection record) {
        return subjectCollectionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectCollection record) {
        return subjectCollectionMapper.updateByPrimaryKey(record);
    }

}
