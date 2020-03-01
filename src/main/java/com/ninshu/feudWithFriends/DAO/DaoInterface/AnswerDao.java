package com.ninshu.feudWithFriends.DAO.DaoInterface;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.model.AnswerListVO;

import java.util.List;

public interface AnswerDao {
    public AnswerList getAnswerById(int id);

    public List<AnswerListVO> getAnswerByQuestionId(int questionId);

    public List<AnswerList> getAllAnswers();

    public int addAnswerList(AnswerList answerList);

    public List<AnswerList> getPrivilegedAnswers(long questionId);

    public List<AnswerList> getSlottedAnswers(long questionId);
}
