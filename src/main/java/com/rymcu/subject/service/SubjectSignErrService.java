package com.rymcu.subject.service;

import com.rymcu.subject.dto.AddSignErrDTO;

import java.util.Map;

/**
 * @author: 🐛
 */
public interface SubjectSignErrService {

    /**
     * 标记错题
     *
     * @param signErr
     * @return 返回标记错题的内容
     */
    Map postSubjectSignErr(AddSignErrDTO signErr);
}
