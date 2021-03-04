package com.rymcu.subject.service;

import com.rymcu.subject.dto.SubjectQuestionDTO;
import com.rymcu.subject.entity.Question;
import com.rymcu.subject.entity.QuestionBase;
import com.rymcu.subject.entity.SubjectQuestion;

import java.util.List;

public interface SubjectQuestionService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectQuestion record);

    int insertSelective(SubjectQuestion record);

    SubjectQuestionDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectQuestion record);

    int updateByPrimaryKey(SubjectQuestion record);

    SubjectQuestionDTO getNextByUserId(Long userId);

    SubjectQuestionDTO getNext();

    /**
     * 获取题列表info
     */
    List<QuestionBase> list();

    /**
     * @param sqId
     * @return
     */
    Question selectBySqId(long sqId);
}
