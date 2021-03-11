package com.rymcu.subject.mapper;

import com.rymcu.subject.dto.AnswerOptionDTO;
import com.rymcu.subject.dto.SubjectOptionDTO;
import com.rymcu.subject.entity.SubjectOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjectOptionMapper {
    /**
     * delete by primary key
     *
     * @param id
     *         primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record
     *         the record
     * @return insert count
     */
    int insert(SubjectOption record);

    /**
     * insert record to table selective
     *
     * @param record
     *         the record
     * @return insert count
     */
    int insertSelective(SubjectOption record);

    /**
     * select by primary key
     *
     * @param id
     *         primary key
     * @return object by primary key
     */
    SubjectOption selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record
     *         the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SubjectOption record);

    /**
     * update record
     *
     * @param record
     *         the updated record
     * @return update count
     */
    int updateByPrimaryKey(SubjectOption record);

    List<SubjectOptionDTO> queryListBySqId(Long sqId);

    List<AnswerOptionDTO> getSubjectAnswer(Long sqId);

    int deleteBySqId(long sqId);
}
