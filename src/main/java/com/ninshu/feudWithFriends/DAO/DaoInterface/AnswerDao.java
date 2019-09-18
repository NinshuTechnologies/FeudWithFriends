package com.ninshu.feudWithFriends.DAO.DaoInterface;

import com.ninshu.feudWithFriends.Entities.AnswerList;

import java.util.List;

public interface AnswerDao {
    public AnswerList getAnswerById(int id);
    public List<AnswerList> getAllAnswers();
}
