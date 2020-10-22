package com.rymcu.subject.controller;

import com.rymcu.subject.dto.AnswerOptionDTO;
import com.rymcu.subject.dto.SubjectOptionDTO;
import com.rymcu.subject.entity.SubjectQuestion;
import com.rymcu.subject.result.GlobalResult;
import com.rymcu.subject.result.GlobalResultGenerator;
import com.rymcu.subject.service.SubjectOptionService;
import com.rymcu.subject.service.SubjectQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题库系统控制服务
 *
 * @author 🐛
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private SubjectQuestionService subjectQuestionService;

    @Autowired
    private SubjectOptionService subjectOptionService;

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    /**
     * 随机出题
     */
    @GetMapping({"/get/next-question/{user-id:\\d+}", "/get/next-question"})
    @ResponseBody
    public GlobalResult getQuestion(
            @PathVariable(name = "user-id",
                          required = false) Long userId
    ) {
        SubjectQuestion subjectQuestion = null;
        Map<String, Object> map = new HashMap<>();
        if (userId == null) {
            subjectQuestion = subjectQuestionService.getNext();
        } else {
            subjectQuestion = subjectQuestionService.getNextByUserId(userId);
        }
        this.setQuestionOption(map, subjectQuestion);
        map.put("subjectQuestion", subjectQuestion);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    /**
     * 通过试题编号获取试题
     */
    @GetMapping({"/get/question/{sq-id:\\d+}", "/get/question"})
    @ResponseBody
    public GlobalResult getQuestionBySqId(
            @PathVariable(name = "sq-id",
                          required = false) Long sqId
    ) {
        SubjectQuestion subjectQuestion = null;
        Map<String, Object> map = new HashMap<>();
        if (sqId == null) {
            subjectQuestion = subjectQuestionService.getNext();
        } else {
            subjectQuestion = subjectQuestionService.selectByPrimaryKey(sqId);
        }
        this.setQuestionOption(map, subjectQuestion);

        map.put("subjectQuestion", subjectQuestion);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    /**
     * 答题
     */
    @PostMapping("/answer/{sq-id:\\d+}")
    @ResponseBody
    public GlobalResult answerQuestion(
            @PathVariable(name = "sq-id") Long sqId,
            @RequestParam(value = "answer",
                          defaultValue = "") String answer

    ) {
        if (answer.isBlank()) {
            return GlobalResultGenerator.genErrorResult("回答错误");
        }

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
        return  GlobalResultGenerator.genErrorResult("unknown  error");
    }

    @GetMapping("/system/menu/menu")
    public String getMenu(Model model) {
        model.addAttribute("qwer", "qwesad");
        return "/system/menu/menu";
    }


    private void setQuestionOption(
            Map<String, Object> map,
            SubjectQuestion subjectQuestion
    ) {
        List<SubjectOptionDTO> subjectOptionList = null;
        final int questionType = subjectQuestion.getQuestionType();
        if (questionType == 5 || questionType == 4) {
            map.put("subjectOptionList", null);
        } else {
            subjectOptionList = subjectOptionService.queryListBySqId(subjectQuestion.getId());
            if (subjectOptionList.size() < 2) {
                map.put("subjectOptionList", null);
            }
            map.put("subjectOptionList", subjectOptionList);
        }
    }
}
