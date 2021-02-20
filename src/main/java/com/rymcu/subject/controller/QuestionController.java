package com.rymcu.subject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rymcu.subject.dto.AddSignErrDTO;
import com.rymcu.subject.dto.AnswerOptionDTO;
import com.rymcu.subject.dto.SubjectOptionDTO;
import com.rymcu.subject.dto.SubjectQuestionDTO;
import com.rymcu.subject.entity.SubjectAnswerRecord;
import com.rymcu.subject.result.GlobalResult;
import com.rymcu.subject.result.GlobalResultGenerator;
import com.rymcu.subject.result.RespResult;
import com.rymcu.subject.service.SubjectAnswerRecordService;
import com.rymcu.subject.service.SubjectOptionService;
import com.rymcu.subject.service.SubjectQuestionService;
import com.rymcu.subject.service.SubjectSignErrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

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
    private SubjectSignErrService subjectSignErrService;
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
        pagination.put("answerTotal", pageInfo.getTotal());
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
        setQuestionOption(subjectQuestionDTO, false);
        return GlobalResultGenerator.genSuccessResult(subjectQuestionDTO);
    }

    /**
     * 通过试题编号获取试题
     */
    @ApiOperation("通过试题编号获取试题")
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
        this.setQuestionOption(subjectQuestionDto, false);

        return GlobalResultGenerator.genSuccessResult(subjectQuestionDto);
    }

    /**
     * @param subjectAnswerRecord
     *         答题vo
     * @return 回答结果：正确or错误
     */

    @PostMapping("/answer")
    @ResponseBody
    @ApiOperation("进行答题")
    @Transactional
    public GlobalResult answerQuestion(
            @RequestBody SubjectAnswerRecord subjectAnswerRecord
    ) {
        return this.doAnswer(subjectAnswerRecord, false);
    }

    /**
     * @param subjectAnswerRecord
     *         答题vo
     * @return 回答结果：正确or错误
     */

    @PostMapping("/answer/everyday")
    @ResponseBody
    @ApiOperation("进行答题")
    @Transactional
    public GlobalResult answerEverydayQuestion(
            @RequestBody SubjectAnswerRecord subjectAnswerRecord
    ) {
        final Date now = new Date();
        final List<SubjectAnswerRecord> todayAnswerRecordList = this.subjectAnswerRecordService
                .getTodayAnswerRecord(subjectAnswerRecord.getUserId(), now, true);
        final boolean notExists = null == todayAnswerRecordList || todayAnswerRecordList.isEmpty();
        if (!notExists) {
            return GlobalResultGenerator.genErrorResult("今日已进行答题，不可重复进行答题签到");
        }
        return this.doAnswer(subjectAnswerRecord, true);
    }


    /**
     * 查看答案
     */
    @ApiOperation("查看答案")
    @GetMapping("/show-answer/{sq-id:\\d+}")
    @ResponseBody
    public GlobalResult getAnswer(
            @PathVariable(name = "sq-id") Long sqId
    ) {
        final List<AnswerOptionDTO> questionOptionList = this.subjectOptionService.getSubjectAnswer(sqId);
        if (questionOptionList.isEmpty()) {
            return GlobalResultGenerator.genErrorResult("该题不存在");
        }
        return GlobalResultGenerator.genSuccessResult(new RespResult("正确答案", true, getCorrectAnswer(questionOptionList)));
    }


    /**
     * 答题记录
     */
    @ApiOperation("查看答题记录or随机出题")
    @GetMapping("/record/{user-id:\\d+}")
    @ResponseBody
    public GlobalResult answerRecord(
            @PathVariable("user-id") long userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer rows
    ) {
        final Date now = new Date();
        final List<SubjectAnswerRecord> todayAnswerRecordList = this.subjectAnswerRecordService.getTodayAnswerRecord(userId, now, true);
        final boolean notExists = null == todayAnswerRecordList || todayAnswerRecordList.isEmpty();
        List<SubjectQuestionDTO> subjectQuestionDtoList = new ArrayList<>();
        if (notExists) {
            final SubjectQuestionDTO subjectQuestionDto = subjectQuestionService.getNextByUserId(userId);
            setQuestionOption(subjectQuestionDto, false);
            subjectQuestionDtoList.add(subjectQuestionDto);
        } else {
            AtomicLong oldQuestionId = new AtomicLong();
            todayAnswerRecordList.forEach(subjectAnswerRecord -> {
                long sqId = subjectAnswerRecord.getSubjectQuestionId();
                if (sqId != oldQuestionId.get()) {
                    final List<SubjectAnswerRecord> subjectAnswerRecords = new ArrayList<>();
                    subjectAnswerRecords.add(subjectAnswerRecord);
                    final SubjectQuestionDTO subjectQuestionDTO = this.subjectQuestionService.selectByPrimaryKey(sqId);
                    subjectQuestionDTO.setSubjectAnswerRecords(subjectAnswerRecords);
                    subjectQuestionDtoList.add(subjectQuestionDTO);
                    setQuestionOption(subjectQuestionDtoList.get(subjectQuestionDtoList.size() - 1), true);
                    oldQuestionId.set(sqId);
                } else {
                    subjectQuestionDtoList.get(subjectQuestionDtoList.size() - 1).getSubjectAnswerRecords().add(subjectAnswerRecord);
                }
            });
        }
        final PageInfo pageInfo = new PageInfo(subjectQuestionDtoList);
        PageHelper.startPage(page, rows);
        final Map<String, Object> map = getAnswerRecordGlobalResult(pageInfo);
        map.put("notExists", notExists);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    /**
     * 当前版本仅返回未修复的错题列表
     */
    @ApiOperation("查阅错题列表")
    @GetMapping()
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
            SubjectQuestionDTO subjectQuestionDto,
            boolean setAnswer
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
        if (setAnswer) {
            //设置答案
            if (answerOptionDTOList == null || answerOptionDTOList.isEmpty()) {
                subjectQuestionDto.setCorrectAnswer(null);
            } else {
                subjectQuestionDto.setCorrectAnswer(getCorrectAnswer(answerOptionDTOList));
            }
        }
    }


    private GlobalResult doAnswer(
            SubjectAnswerRecord subjectAnswerRecord,
            boolean everydayFlag
    ) {
        final String answer = subjectAnswerRecord.getAnswer();
        final Long userId = subjectAnswerRecord.getUserId();
        subjectAnswerRecord.setCreatedTime(new Date());
        final long sqId = subjectAnswerRecord.getSubjectQuestionId();
        if (userId == null || userId == 0L) {
            return GlobalResultGenerator.genErrorResult("答题用户编号不能为空");
        }
        if (answer.isBlank()) {
            return GlobalResultGenerator.genErrorResult("格式错误");
        }
        final List<AnswerOptionDTO> questionOptionList = this.subjectOptionService.getSubjectAnswer(sqId);
        if (questionOptionList.isEmpty()) {
            return GlobalResultGenerator.genErrorResult("题库此题记录异常");
        }

        // 如果不是每日答题则普通插入，否则记录答题标志1
        if (!everydayFlag) {
            this.subjectAnswerRecordService.insertSelective(subjectAnswerRecord);
        } else {
            this.subjectAnswerRecordService.insertEveryDayAnswer(subjectAnswerRecord);

        }

        final String correctAnswer = getCorrectAnswer(questionOptionList);
        final boolean answerFlag = correctAnswer.equalsIgnoreCase(answer);
        return GlobalResultGenerator.genResult(true, answerFlag, answerFlag ? "回答正确" : "回答错误");
    }

    private String getCorrectAnswer(List<AnswerOptionDTO> questionOptionList) {
        final String[] correctAnswer = new String[]{""};
        if (questionOptionList.size() == 1) {
            correctAnswer[0] = questionOptionList.get(0).getOptionContent();
        } else {
            questionOptionList.stream()
                              .filter(AnswerOptionDTO::isAnswerFlag)
                              .forEach(questionOption -> correctAnswer[0] = correctAnswer[0] + questionOption.getOptionName());

        }
        return correctAnswer[0];
    }
}
