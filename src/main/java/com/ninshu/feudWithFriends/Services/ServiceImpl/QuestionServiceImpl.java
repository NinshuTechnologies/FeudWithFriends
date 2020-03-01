package com.ninshu.feudWithFriends.Services.ServiceImpl;

import com.ninshu.feudWithFriends.DAO.DaoInterface.QuestionDao;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.Services.ServiceInterface.QuestionService;
import com.ninshu.feudWithFriends.model.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    @Transactional
    public QuestionVO getQuestionById(int id) {
        return questionDao.getQuestionById(id);
    }

    @Override
    @Transactional
    public List<QuestionVO> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    @Override
    @Transactional
    public QuestionVO getRandomQuestion() {
        return questionDao.getRandomQuestion();
    }

    @Override
    @Transactional
    public int addQuestion(Question question) {
        return questionDao.addQuestion(question);
    }


}
