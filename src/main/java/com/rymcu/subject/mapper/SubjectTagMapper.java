package com.rymcu.subject.mapper;

import com.rymcu.subject.domain.SubjectTag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectTagMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(SubjectTag record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(SubjectTag record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    SubjectTag selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SubjectTag record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SubjectTag record);
}