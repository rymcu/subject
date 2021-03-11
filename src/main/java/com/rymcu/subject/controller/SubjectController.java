package com.rymcu.subject.controller;

import com.github.pagehelper.PageHelper;
import com.rymcu.subject.dto.AddSignErrDTO;
import com.rymcu.subject.entity.PageInfo;
import com.rymcu.subject.entity.Question;
import com.rymcu.subject.entity.QuestionBase;
import com.rymcu.subject.entity.SubjectQuestion;
import com.rymcu.subject.result.GlobalResult;
import com.rymcu.subject.result.GlobalResultGenerator;
import com.rymcu.subject.service.SubjectOptionService;
import com.rymcu.subject.service.SubjectQuestionService;
import com.rymcu.subject.service.SubjectSignErrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rymcu.subject.result.GlobalResultGenerator.genResult;
import static org.apache.logging.log4j.util.Strings.isEmpty;

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
    private SubjectSignErrService subjectSignErrService;

    @Autowired
    private SubjectOptionService subjectOptionService;

    /**
     * @param current  页码
     * @param pageSize 行数
     * @return
     */
    @ApiOperation("获取试题列表")
    @GetMapping("/list")
    @ResponseBody
    public PageInfo<Question> list(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @Nullable QuestionBase questionBase
    ) {
        final int total = this.subjectQuestionService.countList(questionBase);
        PageInfo<Question> infoList = null;
        if (total > 0) {
            PageHelper.startPage(current, pageSize);
            final List<Integer> sqIdList = this.subjectQuestionService.sqIdList(questionBase);
            final List<Question> questionList = this.subjectQuestionService.listBySqId(sqIdList);
            infoList = new PageInfo(questionList);
            infoList.setTotal(total);
        }
        return infoList;
    }

    /**
     * @return
     */
    @ApiOperation("编辑试题")
    @PostMapping("/{sq-id:\\d+}")
    @ResponseBody
    @Transactional
    public GlobalResult edit(
            @PathVariable("sq-id") long sqId,
            @RequestBody Question question
    ) {
        System.err.print(question);
        System.err.print(sqId + "-----------");
        if (isEmpty(question.getAnswer())) {
            return genResult(false, "答案不嫩为空");
        }
        String optionName = "ABCDEFG";
        switch (question.getQuestionType()) {
            case 1:
            case 2:
                if (question.getOptions() == null || question.getOptions().isEmpty() || question.getOptions().size() < 2) {
                    return genResult(false, "单选题、多选题选项必须>1");
                }
                if (optionName.indexOf(question.getAnswer().toUpperCase()) < 0) {
                    return genResult(false, "单选题、多选题答案必须为A~G递增");
                }
                if (question.getOptions().size() > 7) {
                    return genResult(false, "最多允许添加7个选项");
                }
                break;
            case 3:
                if ("1" != question.getAnswer() || "0" != question.getAnswer()) {
                    return genResult(false, "判断题答案必须是1、0");
                }
                if (question.getOptions() == null || question.getOptions().isEmpty()) {
                    break;
                }
                return genResult(false, "判断题：不能存在选项");
            case 4:
            case 5:
                if (question.getOptions() == null || question.getOptions().isEmpty()) {
                    break;
                }
                return genResult(false, "填空、问答：不能存在选项");
            default:
                return genResult(false, "试题类型错误");
        }

        final long[] editSqId = {0};
        if (sqId == 0 && question.getId() == null) {
            editSqId[0] = this.subjectQuestionService.insertSelective(new SubjectQuestion(question, true));
            if (editSqId[0] < 1) {
                return genResult(false, "插入试题失败");
            }
        } else {
            int updateCount = this.subjectQuestionService.updateByPrimaryKeySelective(new SubjectQuestion(question, false));
            if (updateCount < 1) {
                return genResult(false, "更新试题失败");
            }
            editSqId[0] = question.getId();
        }
        this.subjectOptionService.deleteBySqId(editSqId[0]);

        question.getOptions().forEach(obj ->
        {
            final int index = question.getOptions().indexOf(obj);
            obj.setOptionName(String.valueOf(optionName.indexOf(index)));
            obj.setSubjectQuestionId(editSqId[0]);
            this.subjectOptionService.insertSelective(obj);
        });
        return

                genResult(true, "");

    }

    /**
     * @param sqId 试题编号
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

}
