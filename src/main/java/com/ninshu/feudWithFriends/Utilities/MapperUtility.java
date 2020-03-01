package com.ninshu.feudWithFriends.Utilities;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.model.AnswerListVO;
import com.ninshu.feudWithFriends.model.QuestionVO;

// Mapper Utility
public class MapperUtility {

    public static AnswerListVO mapAnswerVO(AnswerList answerList) {
        AnswerListVO answerListVO = new AnswerListVO();
        answerListVO.setAnswerWords(answerList.getAnswerWords());
        answerListVO.setCurrentAnswerType(answerList.getCurrentAnswerType());
        answerListVO.setDisplayAnswer(answerList.getDisplayAnswer());
        answerListVO.setHits(answerList.getHits());
        answerListVO.setOriginalAnswerType(answerList.getOriginalAnswerType());
        answerListVO.setRank(answerList.getRank());
        answerListVO.setUid(answerList.getUid());
        return answerListVO;
    }

    public static QuestionVO mapQuestionVO(Question question) {
        QuestionVO questionVO = new QuestionVO();
        questionVO.setUid(question.getUid());
        questionVO.setQuestion(question.getQuestion());
        return questionVO;
    }
}
