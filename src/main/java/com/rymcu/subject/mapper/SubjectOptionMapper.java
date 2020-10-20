package com.rymcu.subject.mapper;

import com.rymcu.subject.domain.SubjectOption;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectOptionMapper {
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
    int insert(SubjectOption record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(SubjectOption record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    SubjectOption selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SubjectOption record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SubjectOption record);
}