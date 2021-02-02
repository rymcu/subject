package com.rymcu.subject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author 🐛
 * @Entity com.rymcu.subject.entity.SubjectSignErr
 */
@Mapper
public interface SubjectSignErrMapper {


    /**
     * 修改错题描述
     *
     * @param id
     *         主键编号
     * @param errDec
     *         错误简述
     * @param errContent
     *         错误描述
     * @param createdBy
     *         修改人
     * @param createdTime
     *         修改时间
     * @return
     */
    int updateSignErr(
            @Param("id") Long id,
            @Param("errDec") String errDec,
            @Param("errContent") String errContent,
            @Param("createdBy") Long createdBy,
            @Param("createdTime") Date createdTime
    );

    /**
     * 记录错题描述
     *
     * @param sqId
     *         错误题号
     * @param errDec
     *         错误简述
     * @param errContent
     *         错误描述
     * @param createdBy
     *         创建人
     * @param createdTime
     *         创建时间
     */
    void insertSignErr(
            @Param("sqId") Long sqId,
            @Param("errDec") String errDec,
            @Param("errContent") String errContent,
            @Param("createdBy") Long createdBy,
            @Param("createdTime") Date createdTime
    );
}
