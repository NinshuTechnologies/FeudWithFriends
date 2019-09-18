package com.ninshu.feudWithFriends.Controllers;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.Services.ServiceInterface.AnswerService;
import com.ninshu.feudWithFriends.Services.ServiceInterface.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;
    //---------------------------------Questions---------------------------------

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/question")
    public Question getRandomQuestion() {
        return questionService.getRandomQuestion();
    }

    //---------------------------------Answers------------------------------------
    @GetMapping("/answer/{id}")
    public AnswerList getAnswerById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }

    @GetMapping("/answers")
    public List<AnswerList> getAllAnswers() {
        return answerService.getAllAnswers();
    }
}
