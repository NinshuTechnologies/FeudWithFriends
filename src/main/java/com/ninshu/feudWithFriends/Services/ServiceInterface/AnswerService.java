package com.ninshu.feudWithFriends.Services.ServiceInterface;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.model.AnswerListVO;

import java.util.List;

public interface AnswerService {
    public AnswerListVO getAnswerById(int id);
    public List<AnswerListVO> getAnswerByQuestionId(int id);
    public List<AnswerListVO> getAllAnswers();
    public int addAnswerList(AnswerList answerList);
}
