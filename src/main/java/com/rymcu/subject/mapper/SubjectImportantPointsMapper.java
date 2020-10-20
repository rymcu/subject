package com.rymcu.subject.mapper;

import com.rymcu.subject.domain.SubjectImportantPoints;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectImportantPointsMapper {
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
    int insert(SubjectImportantPoints record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(SubjectImportantPoints record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    SubjectImportantPoints selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SubjectImportantPoints record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SubjectImportantPoints record);
}