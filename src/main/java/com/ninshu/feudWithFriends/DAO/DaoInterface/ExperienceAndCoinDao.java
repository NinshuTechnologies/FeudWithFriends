package com.ninshu.feudWithFriends.DAO.DaoInterface;

import java.util.List;

import com.ninshu.feudWithFriends.Entities.ExperienceAndCoinData;
import com.ninshu.feudWithFriends.model.ExperienceAndCoinDataVO;

public interface ExperienceAndCoinDao {
	
	public int createExperienceAndCoin(ExperienceAndCoinData experienceAndCoinData);
	
	public ExperienceAndCoinData fetchRecord(String userReferenceId);
	
	public int updateExperienceAndCoin(ExperienceAndCoinData experienceAndCoinData);

}
