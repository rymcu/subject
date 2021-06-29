package com.rymcu.subject.service;

import com.rymcu.subject.dto.SubjectQuestionDTO;
import com.rymcu.subject.entity.Question;
import com.rymcu.subject.entity.QuestionAnswer;
import com.rymcu.subject.entity.QuestionBase;
import com.rymcu.subject.entity.SubjectQuestion;

import java.util.List;

public interface SubjectQuestionService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectQuestion record);

    long insertSelective(SubjectQuestion record);

    SubjectQuestionDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectQuestion record);

    int updateByPrimaryKey(SubjectQuestion record);

    SubjectQuestionDTO getNextByUserId(Long userId);

    SubjectQuestionDTO getNext();

    /**
     * @param sqId
     * @return
     */
    Question selectBySqId(long sqId);

    int countList(QuestionBase questionBase);

    /**
     * 通过题目编号id来获取题列表
     *
     * @param sqIdList
     * @return
     */
    List<Question> listBySqId(List<Integer> sqIdList);

    /**
     * 查询符合条件的题目编号列表
     *
     * @param questionBase
     * @return
     */
    List<Integer> sqIdList(QuestionBase questionBase);


    QuestionAnswer getQuestionAnswer(Long sqId);
}
