package com.ninshu.feudWithFriends.DAO.DaoImpl;

import com.ninshu.feudWithFriends.DAO.DaoInterface.AnswerDao;
import com.ninshu.feudWithFriends.Entities.AnswerList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class AnswerDaoImpl implements AnswerDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public AnswerList getAnswerById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from AnswerList where uid=:uid", AnswerList.class);
        query.setParameter("uid", id);
        AnswerList answer = (AnswerList) query.getSingleResult();
        return answer;
    }

    @Override
    public List<AnswerList> getAllAnswers() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from AnswerList", AnswerList.class);
        return query.getResultList();
    }

    @Override
    public int addAnswerList(AnswerList answerList) {
    Session session = entityManager.unwrap(Session.class);
        int id = (int) session.save(answerList);
        return id;
    }
}
