package com.rymcu.subject.service;

import com.rymcu.subject.dto.AnswerOptionDTO;
import com.rymcu.subject.dto.SubjectOptionDTO;
import com.rymcu.subject.entity.SubjectOption;

import java.util.List;

public interface SubjectOptionService {


    int deleteByPrimaryKey(Long id);

    int insert(SubjectOption record);

    int insertSelective(SubjectOption record);

    SubjectOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectOption record);

    int updateByPrimaryKey(SubjectOption record);

    List<SubjectOptionDTO> queryListBySqId(Long id);

    List<AnswerOptionDTO> getSubjectAnswer(Long sqId);

    int deleteBySqId(long l);
}
