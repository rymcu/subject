package com.rymcu.subject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rymcu.subject.dto.AnswerOptionDTO;
import com.rymcu.subject.dto.SubjectOptionDTO;
import com.rymcu.subject.dto.SubjectQuestionDTO;
import com.rymcu.subject.entity.SubjectAnswerRecord;
import com.rymcu.subject.entity.SubjectQuestion;
import com.rymcu.subject.result.GlobalResult;
import com.rymcu.subject.result.GlobalResultGenerator;
import com.rymcu.subject.service.SubjectAnswerRecordService;
import com.rymcu.subject.service.SubjectOptionService;
import com.rymcu.subject.service.SubjectQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

/**
 * 题库系统控制服务
 *
 * @author 🐛
 */
@Api(tags = "题库服务")
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private SubjectQuestionService subjectQuestionService;

    @Autowired
    private SubjectOptionService subjectOptionService;

    @Autowired
    private SubjectAnswerRecordService subjectAnswerRecordService;

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    public static Map<String, Object> getAnswerRecordGlobalResult(PageInfo<SubjectQuestionDTO> pageInfo) {
        Map<String, Object> map = new HashMap(2);
        map.put("answerRecords", pageInfo.getList());
        Map<String, Object> pagination = new HashMap(4);
        pagination.put("pageSize", pageInfo.getPageSize());
        pagination.put("total", pageInfo.getTotal());
        pagination.put("currentPage", pageInfo.getPageNum());
        map.put("pagination", pagination);
        return map;
    }

    /**
     * 随机出题
     */
    @ApiOperation("随机出题")
    @GetMapping({"/next/{user-id:\\d+}", "/next"})
    @ResponseBody
    public GlobalResult getQuestion(
            @PathVariable(name = "user-id",
                    required = false) Long userId
    ) {
        SubjectQuestionDTO subjectQuestionDTO;
        Map<String, Object> map = new HashMap<>();
        if (userId == null) {
            subjectQuestionDTO = subjectQuestionService.getNext();
        } else {
            subjectQuestionDTO = subjectQuestionService.getNextByUserId(userId);
        }
        setQuestionOption(subjectQuestionDTO);
        return GlobalResultGenerator.genSuccessResult(subjectQuestionDTO);
    }

    /**
     * 通过试题编号获取试题
     */
    @GetMapping({"/by/{sq-id:\\d+}", "/by"})
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
        this.setQuestionOption(subjectQuestionDto);

        return GlobalResultGenerator.genSuccessResult(subjectQuestionDto);
    }

    /**
     * @param subjectAnswerRecord 答题vo
     * @return 回答结果：正确or错误
     */
    @PostMapping("/answer")
    @ResponseBody
    public GlobalResult answerQuestion(
            @RequestBody SubjectAnswerRecord subjectAnswerRecord
    ) {
        final var answer = subjectAnswerRecord.getAnswer();
        final var userId = subjectAnswerRecord.getUserId();
        subjectAnswerRecord.setCreatedTime(new Date());
        final long sqId = subjectAnswerRecord.getSubjectQuestionId();
        if (userId == null || userId == 0L) {
            return GlobalResultGenerator.genErrorResult("答题用户编号不能为空");
        }
        if (answer.isBlank()) {
            return GlobalResultGenerator.genErrorResult("格式错误");
        }

        this.subjectAnswerRecordService.insertSelective(subjectAnswerRecord);
        final List<AnswerOptionDTO> questionOptionList = this.subjectOptionService.getSubjectAnswer(sqId);
        if (questionOptionList.size() == 1) {
            if (answer.equals(questionOptionList.get(0).getOptionContent())) {
                GlobalResultGenerator.genSuccessResult("回答正确");
            }
        }
        if (questionOptionList.size() > 1) {
            String[] subjectAnswer = {""};
            questionOptionList.stream()
                    .filter(AnswerOptionDTO::isAnswerFlag)
                    .forEach(questionOption -> subjectAnswer[0] = subjectAnswer[0] + questionOption.getOptionName());
            if (answer.equals(subjectAnswer[0])) {
                GlobalResultGenerator.genSuccessResult("回答正确");
            }
        }
        return GlobalResultGenerator.genErrorResult("回答错误");
    }

    /**
     * 查看答案
     */
    @GetMapping("/show-answer/{sq-id:\\d+}")
    @ResponseBody
    public GlobalResult getAnswer(
            @PathVariable(name = "sq-id") Long sqId
    ) {
        final List<AnswerOptionDTO> questionOptionList = this.subjectOptionService.getSubjectAnswer(sqId);
        if (questionOptionList.size() == 1) {
            return GlobalResultGenerator.genSuccessResult(questionOptionList.get(0).getOptionContent());
        }
        if (questionOptionList.size() > 1) {
            String[] subjectAnswer = {""};
            questionOptionList.stream()
                    .filter(AnswerOptionDTO::isAnswerFlag)
                    .forEach(questionOption -> subjectAnswer[0] = subjectAnswer[0] + questionOption.getOptionName());
            return GlobalResultGenerator.genSuccessResult(subjectAnswer[0]);
        }
        return GlobalResultGenerator.genErrorResult("unknown  error");
    }

    /**
     * 答题记录表
     */
    @PostMapping("/record/{user-id:\\d+}\"")
    @ResponseBody
    public GlobalResult answerRecord(
            @PathVariable("user-id") long userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer rows
    ) {
        final var now = new Date();
        final var todayAnswerRecordList = this.subjectAnswerRecordService.getTodayAnswerRecord(userId, now);
        final var notExists = null == todayAnswerRecordList || todayAnswerRecordList.isEmpty();
        List<SubjectQuestionDTO> subjectQuestionDtoList = new ArrayList<>();
        if (notExists) {
            final var subjectQuestionDto = subjectQuestionService.getNextByUserId(userId);
            setQuestionOption(subjectQuestionDto);
            subjectQuestionDtoList.add(subjectQuestionDto);
        } else {
            todayAnswerRecordList.forEach(answerRecord -> {
                final var subjectQuestionDto = this.subjectQuestionService.selectByPrimaryKey(answerRecord.getSubjectQuestionId());
                setQuestionOption(subjectQuestionDto);
                subjectQuestionDto.setAnswer(answerRecord.getAnswer());
                subjectQuestionDtoList.add(subjectQuestionDto);
            });
        }
        final var pageInfo = new PageInfo(subjectQuestionDtoList);
        PageHelper.startPage(page, rows);
        final var map = getAnswerRecordGlobalResult(pageInfo);
        map.put("notExists", notExists);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    private void setQuestionOption(
            SubjectQuestionDTO subjectQuestionDto
    ) {
        List<SubjectOptionDTO> subjectOptionList;
        final int questionType = subjectQuestionDto.getQuestionType();
        if (questionType == 5 || questionType == 4) {
            subjectQuestionDto.setSubjectOptionDTOList(null);
        } else {
            subjectOptionList = subjectOptionService.queryListBySqId(subjectQuestionDto.getId());
            System.err.println(subjectOptionList.toString());
            if (subjectOptionList.size() < 2) {
                subjectQuestionDto.setSubjectOptionDTOList(null);
            }
            subjectQuestionDto.setSubjectOptionDTOList(subjectOptionList);
        }
    }
}
