package com.rymcu.subject.service.impl;

import com.rymcu.subject.dto.AnswerOptionDTO;
import com.rymcu.subject.dto.SubjectOptionDTO;
import com.rymcu.subject.entity.SubjectOption;
import com.rymcu.subject.mapper.SubjectOptionMapper;
import com.rymcu.subject.service.SubjectOptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<SubjectOptionDTO> queryListBySqId(Long sqId) {
        return subjectOptionMapper.queryListBySqId(sqId);
    }

    @Override
    public List<AnswerOptionDTO> getSubjectAnswer(Long sqId) {
        return subjectOptionMapper.getSubjectAnswer(sqId);
    }

    /**
     * 通过sqId清空试题选项
     *
     * @param sqId
     * @return
     */
    @Override
    public int deleteBySqId(long sqId) {
        return subjectOptionMapper.deleteBySqId(sqId);
    }

}
