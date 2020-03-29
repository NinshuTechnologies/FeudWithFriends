package com.ninshu.feudWithFriends.DAO.DaoInterface;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.model.AnswerListVO;

import java.util.List;

public interface AnswerDao {
    public AnswerListVO getAnswerById(int id);

    public List<AnswerListVO> getAnswerByQuestionId(int questionId);

    public List<AnswerListVO> getAllAnswers();

    public int addAnswerList(AnswerList answerList);

    public List<AnswerList> getPrivilegedAnswers(int questionId);

    public List<AnswerList> getSlottedAnswers(int questionId);
}
