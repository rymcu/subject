package com.rymcu.subject.controller;

import com.rymcu.subject.result.GlobalResult;
import com.rymcu.subject.result.GlobalResultGenerator;
import com.rymcu.subject.service.SubjectQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 题库系统控制服务
 * @author 🐛
 *
 */
@Controller
@RequestMapping("/question")
public class QuestionController {


    @Autowired
    private SubjectQuestionService subjectQuestionService;

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    /**
     * 随机出题
     * @return
     */
    @GetMapping({"/get/next-question/{user-id:\\d+}","/get/next-question"})
    @ResponseBody
    public GlobalResult getQuestion(
            @PathVariable(name = "user-id",required = false)Long userId
    ){

        return GlobalResultGenerator.genSuccessResult(subjectQuestionService.getNextByUserId(userId));
    }

    /**
     * 获取试题
     * @return
     */
    @GetMapping({"/get/question/{sq-id:\\d+}","/get/question"})
    @ResponseBody
    public GlobalResult getQuestionBySqId(
            @PathVariable(name = "sq-id", required = false) Long sqId)
    {
        return GlobalResultGenerator.genSuccessResult(subjectQuestionService.selectByPrimaryKey(sqId));
    }

    @GetMapping("/system/menu/menu")
    public String getMenu(Model model){
        model.addAttribute("qwer","qwesad");
        return  "/system/menu/menu";
    }
}
