package com.ninshu.feudWithFriends.Services.ServiceInterface;

import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.model.QuestionVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionService {
    public Question getQuestionById(int id);
    public List<Question> getAllQuestions();
    public QuestionVO getRandomQuestion();
    public int addQuestion(Question question);
}
