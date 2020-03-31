package com.ninshu.feudWithFriends.Utilities;

import org.springframework.util.StringUtils;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.Entities.ExperienceAndCoinData;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.model.AnswerListVO;
import com.ninshu.feudWithFriends.model.ExperienceAndCoinDataVO;
import com.ninshu.feudWithFriends.model.QuestionVO;

// Mapper Utility
public class MapperUtility {

	private static final String USER_ERROR_MESSAGE = "Something went wrong. Please try again";
	private static final String USER_SUCCESS_MESSAGE = "Transaction was successfull";
	
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
    
    public static ExperienceAndCoinDataVO mapExperienceAndCoinDataVO(ExperienceAndCoinData result) {
    	ExperienceAndCoinDataVO experienceAndCoinDataVO = new ExperienceAndCoinDataVO();
    	if(null != result) {
    		experienceAndCoinDataVO.setCurrentCoinValue(result.getCurrentCoinValue());
    		experienceAndCoinDataVO.setCurrentExperienceValue(result.getCurrentExperienceValue());
    		experienceAndCoinDataVO.setCurrentUserLevel(result.getCurrentUserLevel());
    		experienceAndCoinDataVO.setUserErrorMessage(USER_SUCCESS_MESSAGE);
    		experienceAndCoinDataVO.setTransactionSuccessFull(true);
    		experienceAndCoinDataVO.setUserReferenceId(result.getUserReferenceId());
    	}else {
    		experienceAndCoinDataVO.setTransactionSuccessFull(true);
    		experienceAndCoinDataVO.setUserErrorMessage(USER_ERROR_MESSAGE);
    	}
    	return experienceAndCoinDataVO;
    }
}
