package com.ninshu.feudWithFriends.DAO.DaoImpl;

import com.ninshu.feudWithFriends.DAO.DaoInterface.AnswerDao;
import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.Utilities.AnswerType;
import com.ninshu.feudWithFriends.Utilities.MapperUtility;
import com.ninshu.feudWithFriends.model.AnswerListVO;
import org.apache.catalina.mapper.Mapper;
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
    public AnswerListVO getAnswerById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from AnswerList where uid=:uid", AnswerList.class);
        query.setParameter("uid", id);
        AnswerList answer = (AnswerList) query.getSingleResult();
        return MapperUtility.mapAnswerVO(answer);
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
    public List<AnswerListVO> getAllAnswers() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from AnswerList", AnswerList.class);
        List<AnswerList> answers = (List<AnswerList>) query.getResultList();
        List<AnswerListVO> answerListVOS = new ArrayList<>();
        for(AnswerList answer: answers) {
            AnswerListVO answerListVO = MapperUtility.mapAnswerVO(answer);
            answerListVOS.add(answerListVO);
        }
        return answerListVOS;
    }

    @Override
    public int addAnswerList(AnswerList answerList) {
    Session session = entityManager.unwrap(Session.class);
        int id = (int) session.save(answerList);
        return id;
    }

    @Override
    public List<AnswerList> getPrivilegedAnswers(int questionId) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from AnswerList where questionReferrenceId=:questionId and currentAnswerType=:answerType");
        query.setParameter("answerType", AnswerType.PRIVILEGED.toString());
        query.setParameter("questionId", questionId);
        return query.getResultList();
    }

    @Override
    public List<AnswerList> getSlottedAnswers(int questionId) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from AnswerList where questionReferrenceId=:questionId and currentAnswerType=:answerType");
        query.setParameter("answerType", AnswerType.SLOTTED.toString());
        query.setParameter("questionId", questionId);
        return query.getResultList();
    }
}
