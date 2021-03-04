package com.rymcu.subject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rymcu.subject.dto.AddSignErrDTO;
import com.rymcu.subject.dto.AnswerOptionDTO;
import com.rymcu.subject.dto.SubjectOptionDTO;
import com.rymcu.subject.dto.SubjectQuestionDTO;
import com.rymcu.subject.entity.Question;
import com.rymcu.subject.entity.QuestionBase;
import com.rymcu.subject.result.GlobalResult;
import com.rymcu.subject.result.GlobalResultGenerator;
import com.rymcu.subject.service.SubjectOptionService;
import com.rymcu.subject.service.SubjectQuestionService;
import com.rymcu.subject.service.SubjectSignErrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题库服务： 查阅题库、修复错题、审核错题、标记错题
 *
 * @author 🐛
 * @version $
 * @date Created in 2021/2/24 17:30
 */
@Api(tags = "SubjectAnswerApi")
@Controller
@RequestMapping("/question")
public class SubjectController {

    @Autowired
    private SubjectQuestionService subjectQuestionService;
    @Autowired
    private SubjectOptionService subjectOptionService;
    @Autowired
    private SubjectSignErrService subjectSignErrService;

    /**
     * @param current
     *         页码
     * @param pageSize
     *         行数
     * @return
     */
    @ApiOperation("获取试题列表")
    @GetMapping("/list")
    @ResponseBody
    public Map list(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {

        PageHelper.startPage(current, pageSize);
        final List<QuestionBase> infoList = this.subjectQuestionService.list();
        PageInfo<QuestionBase> pageInfo = new PageInfo(infoList);
        final Map antDesignProResultMap = new HashMap<String, Object>();
        antDesignProResultMap.put("data", pageInfo.getList());
        antDesignProResultMap.put("total", pageInfo.getTotal());
        return antDesignProResultMap;
    }

    /**
     * @param sqId
     *         试题编号
     * @return
     */
    @ApiOperation("查看试题详情")
    @GetMapping({"/{sq-id:\\d+}"})
    @ResponseBody
    public GlobalResult getBySqId(
            @PathVariable(name = "sq-id") long sqId
    ) {

        final Question question = this.subjectQuestionService.selectBySqId(sqId);
        return GlobalResultGenerator.genSuccessResult(question);
    }

    /**
     * 当前版本仅返回未修复的错题列表
     */
    @ApiOperation("查阅错题列表")
    @GetMapping("/err")
    public GlobalResult listSignErr() {
        return GlobalResultGenerator.genSuccessResult(this.subjectSignErrService.listSignErr());
    }

    /**
     * 标记错题
     */
    @ApiOperation("标记错题")
    @PostMapping("/sign")
    @ResponseBody
    public GlobalResult postSubjectSignErr(
            @RequestBody AddSignErrDTO signErr
    ) {
        return GlobalResultGenerator.genSuccessResult(this.subjectSignErrService.postSubjectSignErr(signErr));
    }


    private void setQuestionOption(
            SubjectQuestionDTO subjectQuestionDto
    ) {
        if (subjectQuestionDto == null) {
            return;
        }
        List<AnswerOptionDTO> answerOptionDTOList = subjectOptionService.getSubjectAnswer(subjectQuestionDto.getId());
        final int questionType = subjectQuestionDto.getQuestionType();
        if (questionType == 5 || questionType == 4) {
            subjectQuestionDto.setSubjectOptionDTOList(null);
        } else {
            if (answerOptionDTOList.size() < 2) {
                subjectQuestionDto.setSubjectOptionDTOList(null);
            } else {
                final List<SubjectOptionDTO> subjectOptionDTOS = new ArrayList<>();
                answerOptionDTOList.forEach(answerOptionDTO -> {
                    subjectOptionDTOS.add(new SubjectOptionDTO(answerOptionDTO.getOptionName(), answerOptionDTO.getOptionContent()));
                });
                subjectQuestionDto.setSubjectOptionDTOList(subjectOptionDTOS);
            }
        }
    }
}
