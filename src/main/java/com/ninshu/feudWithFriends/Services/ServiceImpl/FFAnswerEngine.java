package com.ninshu.feudWithFriends.Services.ServiceImpl;

import com.ninshu.feudWithFriends.DAO.DaoImpl.AnswerRepository;
import com.ninshu.feudWithFriends.DAO.DaoInterface.AnswerDao;
import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.Utilities.AnswerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class FFAnswerEngine {
    private static Boolean isLevenshteinFilterActive = Boolean.TRUE;
    private static int LEVENSHTEIN_DISTANCE = 1;
    private static int MIN_POOL_SIZE_FOR_ANSWER_REVISION = 200;
    private static int SLOTTED_ANSWER_PROMOTION_THRESHOLD = 50;
    private static int SLOTTED_ANSWER_SIZE = 15;
    private static String BLACKLISTED_WORDS = "fuck, damm, sex, shit, hell";
    // We need to add a hits column on the questions table as well so we can know how many times that question has been asked.
    //For now I am adding this variable but we can delete this later
    private int questionHits = 200;

    @Autowired
    private AnswerRepository answerJpaRepository;

    @Autowired
    private AnswerDao answerDAO;

    @Transactional
    public String getAnswer( long questionID, String userAnswer) {
        List<AnswerList> privilegedAnswers = answerDAO.getPrivilegedAnswers(questionID);
        for (AnswerList answer : privilegedAnswers) {
            String answerArray[] = answer.getAnswerWords().split(",");

            //CSV Filter
            String csvFilterResult = CSVFilter(userAnswer, answerArray);
            if(csvFilterResult ==null && isLevenshteinFilterActive) {

                //Levenshtein Filter
                String levenshteinFilterResult = levenshteinFilter(userAnswer, answerArray);
                if(levenshteinFilterResult != null) {
                    updateHits(answer);
                    return answer.getDisplayAnswer();
                }
            } else {
                updateHits(answer);
                updateAnswersBasedOnUserInputs(questionID);
                return answer.getDisplayAnswer();
            }
        }

        // Store the non accepted answer as the slotted answer
        List<AnswerList> slottedAnswers = answerDAO.getSlottedAnswers(questionID);

        // check if the slotted answers list is full.
        if(slottedAnswers.size()==SLOTTED_ANSWER_SIZE) {

            //remove the LRU answer and add the new answer instead
            List<AnswerList> answersWith1Hit = new ArrayList<>();
            for(AnswerList answer: slottedAnswers) {
                if(answer.getHits() == 1) {
                    answersWith1Hit.add(answer);
                }
            }
        } else {
            //add the answer
            AnswerList newAnswer = new AnswerList();
            newAnswer.setOriginalAnswerType(AnswerType.SLOTTED.toString());
            newAnswer.setAnswerWords(userAnswer);
            newAnswer.setHits(1);
            newAnswer.setDisplayAnswer(userAnswer);
            answerDAO.addAnswerList(newAnswer);
        }
        return null;
    }

    //CSV FILTER
    public String CSVFilter(String userAnswer, String[] answerArray) {
        for(String answerString: answerArray) {
            if(userAnswer.contains(answerString)) {
                return answerString;
            }
        }
        return null;
    }

    //LEVENSHTEIN FILTER
    public String levenshteinFilter(String userAnswer, String[] answerArray) {
        for(String answerString: answerArray) {
            int levenshteinDistance = getLevenshteinDistance(userAnswer, answerString);
            if(levenshteinDistance == LEVENSHTEIN_DISTANCE) {
                return answerString;
            }
        }
        return null;
    }

    @Transactional
    public void updateHits(AnswerList answer) {
        //update hits
        answer.setHits(answer.getHits()+1);
        answerDAO.addAnswerList(answer);
    }

    public int getLevenshteinDistance(String userAnswer, String answerString) {
        int str1Length = userAnswer.length();
        int str2Length = answerString.length();
        // Create a table to store results of subProblems
        int dp[][] = new int[str1Length+1][str2Length+1];

        // Fill d[][] in bottom up manner
        for (int i=0; i<=str1Length; i++)
        {
            for (int j=0; j<=str2Length; j++)
            {
                // If first string is empty, only option is to
                // insert all characters of second string
                if (i==0)
                    dp[i][j] = j;  // Min. operations = j

                    // If second string is empty, only option is to
                    // remove all characters of second string
                else if (j==0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last char
                    // and recur for remaining string
                else if (userAnswer.charAt(i-1) == answerString.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];

                    // If the last character is different, consider all
                    // possibilities and find the minimum
                else
                    dp[i][j] = 1 + min(dp[i][j-1],  // Insert
                            dp[i-1][j],  // Remove
                            dp[i-1][j-1]); // Replace
            }
        }
        return dp[str1Length][str2Length];
    }

    //this should be called if the question hits > minPoolSizeForSlottedAnswerConversion
//    inside this we will have to reset the no of hits the question is getting so we can again start to count till survey pool size.
//    But maintain a separate column in questions table to counts total hots on question all time.
    public void updateAnswersBasedOnUserInputs(long questionId) {
        List<AnswerList> privilegedAnswers = answerDAO.getPrivilegedAnswers(questionId);
        updatePrivilegedAnswerRankings(privilegedAnswers);
        promoteSlottedAnswersToPrivileged(privilegedAnswers);
    }

    private void updatePrivilegedAnswerRankings(List<AnswerList> privilegedAnswers) {
        Collections.sort(privilegedAnswers, new Comparator<AnswerList>() {

            @Override
            public int compare(AnswerList o1, AnswerList o2) {
                if(o1.getHits() < o2.getHits())
                {
                    return 1;
                } else if(o1.getHits() == o2.getHits()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        for(int i=0; i<privilegedAnswers.size(); i++) {
            privilegedAnswers.get(i).setRank(i+1);
        }
        answerJpaRepository.saveAll(privilegedAnswers);
    }

    private void promoteSlottedAnswersToPrivileged(List<AnswerList> privilegedAnswers) {
        List<AnswerList> slottedAnswers = answerDAO.getSlottedAnswers(privilegedAnswers.get(0).getQuestion().getUid());
        List<AnswerList> answerToBeDeleted = new ArrayList<>();
        for(AnswerList slottedAnswer: slottedAnswers) {
            if(slottedAnswer.getHits() > SLOTTED_ANSWER_PROMOTION_THRESHOLD) {
                if(!checkIfAnswerBlacklisted(slottedAnswer.getAnswerWords())) {
                    AnswerList privilegedAnswerToDemote = privilegedAnswers.get(privilegedAnswers.size()-1);
                    //promoting slotted answer
                    slottedAnswer.setCurrentAnswerType(AnswerType.PRIVILEGED.toString());
                    slottedAnswer.setRank(privilegedAnswerToDemote.getRank());
                    //demoting privileged answer
                    privilegedAnswerToDemote.setCurrentAnswerType(AnswerType.SLOTTED.toString());
                    answerDAO.addAnswerList(slottedAnswer);
                    answerDAO.addAnswerList(privilegedAnswerToDemote);
                } else {
                    answerToBeDeleted.add(slottedAnswer);
                }
            }
        }

        //delete blacklisted answers from db
        answerJpaRepository.deleteAll(answerToBeDeleted);
    }

    private Boolean checkIfAnswerBlacklisted(String answer) {
        if(BLACKLISTED_WORDS.contains(answer)) {
            return true;
        }
        return false;
    }
    private int min(int x,int y,int z) {
        if (x <= y && x <= z) return x;
        if (y <= x && y <= z) return y;
        else return z;
    }
}

