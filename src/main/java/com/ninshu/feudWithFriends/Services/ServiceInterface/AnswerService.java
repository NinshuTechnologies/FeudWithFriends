package com.ninshu.feudWithFriends.Services.ServiceInterface;

import com.ninshu.feudWithFriends.Entities.AnswerList;

import java.util.List;

public interface AnswerService {
    public AnswerList getAnswerById(int id);
    public List<AnswerList> getAllAnswers();
    public int addAnswerList(AnswerList answerList);
}
