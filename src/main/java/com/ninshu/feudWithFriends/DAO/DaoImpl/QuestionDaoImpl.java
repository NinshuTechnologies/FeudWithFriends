package com.ninshu.feudWithFriends.DAO.DaoImpl;

import com.ninshu.feudWithFriends.DAO.DaoInterface.QuestionDao;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.Utilities.MapperUtility;
import com.ninshu.feudWithFriends.model.QuestionVO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public QuestionVO getQuestionById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query =  session.createQuery("from Question where uid=:uid", Question.class);
        query.setParameter("uid", id);
        Question question =  (Question) query.getSingleResult();
        QuestionVO questionVO = MapperUtility.mapQuestionVO(question);
        return questionVO;
    }

    @Override
    public List<QuestionVO> getAllQuestions() {
        Session session = entityManager.unwrap(Session.class);
        Query query =  session.createQuery("from Question", Question.class);
        List<Question> questions = (List<Question>)query.getResultList();
        List<QuestionVO> questionsVO = new ArrayList<>();
        for(Question question: questions) {
            QuestionVO questionVO = MapperUtility.mapQuestionVO(question);
            questionsVO.add(questionVO);
        }
        return questionsVO;
    }

    @Override
    public QuestionVO getRandomQuestion() {
        Session session = entityManager.unwrap(Session.class);
        Query query =  session.createQuery("from Question ORDER BY rand()").setMaxResults(1);
        Question question =  (Question) query.getSingleResult();
        QuestionVO questionVO = MapperUtility.mapQuestionVO(question);
        return questionVO;
    }

    @Override
    public int addQuestion(Question question) {
        Session session = entityManager.unwrap(Session.class);
        int id = (int) session.save(question);
        return id;
    }
}
