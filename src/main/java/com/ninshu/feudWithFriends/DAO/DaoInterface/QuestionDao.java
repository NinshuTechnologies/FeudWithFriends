package com.ninshu.feudWithFriends.DAO.DaoInterface;

import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.model.QuestionVO;

import java.util.List;

public interface QuestionDao {
    public Question getQuestionById(int id);
    public QuestionVO getRandomQuestion();

    public List<Question> getAllQuestions();
    public int addQuestion(Question question);
}
