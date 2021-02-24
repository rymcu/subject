package com.rymcu.subject.controller;

import com.rymcu.subject.dto.SubjectQuestionDTO;
import com.rymcu.subject.result.GlobalResult;
import com.rymcu.subject.result.GlobalResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 题库服务： 查阅题库、修复错题、审核错题、标记错题
 *
 * @author 🐛
 * @version $
 * @date Created in 2021/2/24 17:30
 */
@Api(tags = "SubjectAnswerApi")
@Controller
@RequestMapping("/subject")
public class SubjectController {

    /**
     * 获取试题列表
     */
    @ApiOperation("获取试题列表")
    @GetMapping({"/list"})
    @ResponseBody
    public GlobalResult getQuestionBySqId(
            @PathVariable(name = "sq-id",
                          required = false) Long sqId
    ) {
        SubjectQuestionDTO subjectQuestionDto = null;
        if (sqId == null) {
            subjectQuestionDto = subjectQuestionService.getNext();
        } else {
            subjectQuestionDto = subjectQuestionService.selectByPrimaryKey(sqId);
        }
        this.setQuestionOption(subjectQuestionDto, false);

        return GlobalResultGenerator.genSuccessResult(subjectQuestionDto);
    }
}
