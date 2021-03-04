package com.rymcu.subject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 试题
 *
 * @author 🐛
 * @date Created in 2021/3/4 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question extends QuestionBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 试题内容
     */
    private String questionContent;

    /**
     * 选择项
     */
    private List<SubjectOption> options;
}
