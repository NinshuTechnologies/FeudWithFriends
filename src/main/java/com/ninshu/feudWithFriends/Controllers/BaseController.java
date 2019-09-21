package com.ninshu.feudWithFriends.Controllers;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.Entities.User;
import com.ninshu.feudWithFriends.Services.ServiceInterface.AnswerService;
import com.ninshu.feudWithFriends.Services.ServiceInterface.QuestionService;
import com.ninshu.feudWithFriends.Services.ServiceInterface.UserService;
import com.ninshu.feudWithFriends.Utilities.AnswerType;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;

import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@RestController
public class BaseController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;
    //---------------------------------Questions---------------------------------

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/question")
    public int addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
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

    @PostMapping("/answer")
    public int addAnswer(@RequestBody AnswerList answerList) {
        return answerService.addAnswerList(answerList);
    }

    //---------------------------------Users--------------------------------------

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public User getRandomActiveUser() {
        return userService.getRandomActiveUser();
    }

    @GetMapping("/syncData")
    public Response syncDataFromXL() {
        File sourceXL = new File("/Feud-With-Friends.xlsx");
        try {
            Workbook wb = WorkbookFactory.create(sourceXL);
            Sheet sheet = wb.getSheetAt(0);
            System.out.println(sheet);

            int questionId=0;
            for(int i=1; i< sheet.getLastRowNum(); i++) {
                if(sheet.getRow(i).getCell(0) !=null && !sheet.getRow(i).getCell(0).toString().equals("")) {
                    //Todo: Add this to a question object. And whatever answers we find after this in this question
                    String questionString = sheet.getRow(i).getCell(0).getStringCellValue();
                    Question question = new Question();
                    question.setQuestion(questionString);
                    questionId = questionService.addQuestion(question);
                }
                AnswerList answer = new AnswerList();
                answer.setQuestionReferenceId(questionId);
                answer.setCurrentAnswerType(AnswerType.PRIVILEGED.toString());
                answer.setOriginalAnswerType(AnswerType.PRIVILEGED.toString());
                answer.setDisplayAnswer(sheet.getRow(i).getCell(2).getStringCellValue());
                answer.setAnswerWords(sheet.getRow(i).getCell(1).getStringCellValue());
                answerService.addAnswerList(answer);

            }

        } catch (Exception e) {
            System.out.println("Error syncing data from xls file - "+ e.getStackTrace());
            return Response.serverError().build();
        }
        return Response.accepted().build();
    }
}
