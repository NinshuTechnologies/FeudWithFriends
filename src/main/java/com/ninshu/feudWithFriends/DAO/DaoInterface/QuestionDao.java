package com.ninshu.feudWithFriends.DAO.DaoInterface;

import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.model.QuestionVO;

import java.util.List;

public interface QuestionDao {
    public QuestionVO getQuestionById(int id);
    public QuestionVO getRandomQuestion();

    public List<QuestionVO> getAllQuestions();
    public int addQuestion(Question question);
}
