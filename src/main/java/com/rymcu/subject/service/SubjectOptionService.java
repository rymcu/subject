package com.rymcu.subject.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rymcu.subject.domain.SubjectOption;
import com.rymcu.subject.mapper.SubjectOptionMapper;
@Service
public class SubjectOptionService{

    @Resource
    private SubjectOptionMapper subjectOptionMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return subjectOptionMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(SubjectOption record) {
        return subjectOptionMapper.insert(record);
    }

    
    public int insertSelective(SubjectOption record) {
        return subjectOptionMapper.insertSelective(record);
    }

    
    public SubjectOption selectByPrimaryKey(Long id) {
        return subjectOptionMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(SubjectOption record) {
        return subjectOptionMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(SubjectOption record) {
        return subjectOptionMapper.updateByPrimaryKey(record);
    }

}
