package com.rymcu.subject.controller;

import com.rymcu.subject.domain.SubjectQuestion;
import com.rymcu.subject.service.SubjectQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class QuestionController {


    @Autowired
    private SubjectQuestionService subjectQuestionService;

    @GetMapping("/question/{id:\\d+}")
    @ResponseBody
    public SubjectQuestion getQuestion(@PathVariable("id") Long id){
        return subjectQuestionService.selectByPrimaryKey(id);
    }

    @GetMapping("/system/menu/menu")
    public String getMenu(Model model){
        model.addAttribute("qwer","qwesad");
        return  "/system/menu/menu";
    }
}
