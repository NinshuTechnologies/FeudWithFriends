package com.ninshu.feudWithFriends.DAO.DaoImpl;

import com.ninshu.feudWithFriends.DAO.DaoInterface.AnswerDao;
import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.Utilities.AnswerType;
import com.ninshu.feudWithFriends.Utilities.MapperUtility;
import com.ninshu.feudWithFriends.model.AnswerListVO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
    public List<AnswerListVO> getAnswerByQuestionId(int questionId) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Question as q where q.uid=:questionId", Question.class);
        query.setParameter("questionId", questionId);
        Question question = (Question) query.getSingleResult();
        List<AnswerListVO> answerListVOS = new ArrayList<>();
        for(AnswerList answerList: question.getAnswerList()) {
            AnswerListVO answerListVO = new AnswerListVO();
            answerListVO = MapperUtility.mapAnswerVO(answerList);
            answerListVOS.add(answerListVO);
        }
        return answerListVOS;
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

    @Override
    public List<AnswerList> getPrivilegedAnswers(long questionId) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from AnswerList where questionReferenceId=:questionId and currentAnswerType=:answerType");
        query.setParameter("answerType", AnswerType.PRIVILEGED.toString());
        query.setParameter("questionId", questionId);
        return query.getResultList();
    }

    @Override
    public List<AnswerList> getSlottedAnswers(long questionId) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from AnswerList where questionReferenceId=:questionId and currentAnswerType=:answerType");
        query.setParameter("answerType", AnswerType.SLOTTED.toString());
        query.setParameter("questionId", questionId);
        return query.getResultList();
    }
}
