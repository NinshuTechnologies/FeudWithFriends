package com.ninshu.feudWithFriends.DAO.DaoImpl;

import com.ninshu.feudWithFriends.DAO.DaoInterface.QuestionDao;
import com.ninshu.feudWithFriends.Entities.Question;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Question getQuestionById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query =  session.createQuery("from Question where uid=:uid", Question.class);
        query.setParameter("uid", id);
        Question question =  (Question) query.getSingleResult();
        return question;
    }

    @Override
    public List<Question> getAllQuestions() {
        Session session = entityManager.unwrap(Session.class);
        Query query =  session.createQuery("from Question", Question.class);
        return query.getResultList();
    }

    @Override
    public Question getRandomQuestion() {
        Session session = entityManager.unwrap(Session.class);
        Query query =  session.createQuery("from Question ORDER BY rand()").setMaxResults(1);
        Question question =  (Question) query.getSingleResult();
        return question;
    }

    @Override
    public int addQuestion(Question question) {
        Session session = entityManager.unwrap(Session.class);
        int id = (int) session.save(question);
        return id;
    }
}
