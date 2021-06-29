package com.rymcu.subject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 试题
 *
 * @author 🐛
 * @date Created in 2021/3/4 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;


    /**
     * 试题答案
     */
    private String answer;
}

