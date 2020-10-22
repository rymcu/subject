package com.rymcu.subject.mapper;

import com.rymcu.subject.entity.SubjectTagKnowledgePointsId;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectTagKnowledgePointsIdMapper {
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
    int insert(SubjectTagKnowledgePointsId record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(SubjectTagKnowledgePointsId record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    SubjectTagKnowledgePointsId selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SubjectTagKnowledgePointsId record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SubjectTagKnowledgePointsId record);
}
