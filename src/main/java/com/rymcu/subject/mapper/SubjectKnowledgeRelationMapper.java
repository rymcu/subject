package com.rymcu.subject.mapper;

import com.rymcu.subject.entity.SubjectKnowledgeRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectKnowledgeRelationMapper {
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
    int insert(SubjectKnowledgeRelation record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(SubjectKnowledgeRelation record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    SubjectKnowledgeRelation selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SubjectKnowledgeRelation record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SubjectKnowledgeRelation record);
}
