package com.rymcu.subject.mapper;

import com.rymcu.subject.entity.SubjectAnswerRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectAnswerRecordMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(SubjectAnswerRecord record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(SubjectAnswerRecord record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    SubjectAnswerRecord selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SubjectAnswerRecord record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SubjectAnswerRecord record);
}
