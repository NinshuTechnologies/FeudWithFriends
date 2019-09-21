package com.ninshu.feudWithFriends.Services.ServiceInterface;

import com.ninshu.feudWithFriends.Entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionService {
    public Question getQuestionById(int id);
    public List<Question> getAllQuestions();
    public Question getRandomQuestion();
    public int addQuestion(Question question);
}
