package com.rymcu.subject.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rymcu.subject.domain.SubjectQuestion;
import com.rymcu.subject.mapper.SubjectQuestionMapper;
import com.rymcu.subject.service.SubjectQuestionService;

@Service
public class SubjectQuestionServiceImpl implements SubjectQuestionService {

    @Resource
    private SubjectQuestionMapper subjectQuestionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subjectQuestionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubjectQuestion record) {
        return subjectQuestionMapper.insert(record);
    }

    @Override
    public int insertSelective(SubjectQuestion record) {
        return subjectQuestionMapper.insertSelective(record);
    }

    @Override
    public SubjectQuestion selectByPrimaryKey(Long id) {
        return subjectQuestionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectQuestion record) {
        return subjectQuestionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubjectQuestion record) {
        return subjectQuestionMapper.updateByPrimaryKey(record);
    }

    /**
     * get next subject_question
     * @param userId user id
     * @return next subject_question
     */
    @Override
    public SubjectQuestion getNextByUserId(Long userId) {
        final long  sqId = this.getSqId(userId);
        return this.subjectQuestionMapper.selectByPrimaryKey(sqId);
    }

    /**
     * get question id
     * @param userId
     * @return
     */
    private long getSqId(long userId){
        final long count = this.subjectQuestionMapper.getAllCount();
        final long nextOrder = (long) (Math.random()*count) + 1L;
        final long sqId = this.subjectQuestionMapper.getSqIdByNextOrder(nextOrder);
        final boolean isRepeat = this.subjectQuestionMapper.isRepeatBySqIdAndUserId(sqId,userId);
        while (isRepeat){
            getSqId(userId);
        }
        return sqId;
    }

}
