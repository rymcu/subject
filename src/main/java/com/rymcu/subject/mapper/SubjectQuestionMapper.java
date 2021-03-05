package com.rymcu.subject.mapper;

import com.rymcu.subject.dto.SubjectQuestionDTO;
import com.rymcu.subject.entity.Question;
import com.rymcu.subject.entity.QuestionBase;
import com.rymcu.subject.entity.SubjectQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubjectQuestionMapper {
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
    int insert(SubjectQuestion record);

    /**
     * insert record to table selective
     *
     * @param record
     *         the record
     * @return insert count
     */
    int insertSelective(SubjectQuestion record);

    /**
     * select by primary key
     *
     * @param id
     *         primary key
     * @return object by primary key
     */
    SubjectQuestionDTO selectByPrimaryKey(@Param("id") Long id);

    /**
     * update record selective
     *
     * @param record
     *         the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SubjectQuestion record);

    /**
     * update record
     *
     * @param record
     *         the updated record
     * @return update count
     */
    int updateByPrimaryKey(SubjectQuestion record);

    /**
     * get show count of all records
     *
     * @return count of all records
     */
    long getShowCount();

    /**
     * get all count of all records
     *
     * @return all count of all records
     */
    long getAllCount();

    /**
     * get sqId by question order
     *
     * @param nextOrder
     *         next question order
     * @return sqId
     */
    long getSqIdByNextOrder(long nextOrder);

    /**
     * check is repeat by sqId and userId
     *
     * @param sqId
     *         subject_question id
     * @param userId
     *         userId
     * @return sqId
     */
    boolean isRepeatBySqIdAndUserId(
            @Param("sqId") long sqId,
            @Param("userId") long userId
    );

    /**
     * 通过题目编号判断题目是否存在
     *
     * @param id
     *         题目编号
     * @return boolean
     */
    boolean existsBySqId(long id);


    /**
     * 查看试题
     *
     * @param sqId
     * @return Question
     */
    Question selectBySqId(long sqId);

    int countList(QuestionBase questionBase);

    List<Question> listBySqId(List<Integer> sqIdList);

    List<Integer> sqIdList(QuestionBase questionBase);
}
