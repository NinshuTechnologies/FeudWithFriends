package com.ninshu.feudWithFriends.Services.ServiceInterface;

import com.ninshu.feudWithFriends.Entities.ExperienceAndCoinData;
import com.ninshu.feudWithFriends.model.ExperienceAndCoinDataVO;

public interface ExperienceAndCoinService {
	
	public ExperienceAndCoinDataVO createExperienceAndCoin(String userId);
	
	public ExperienceAndCoinDataVO fetchExperienceAndCoin(String userId);

	public ExperienceAndCoinDataVO updateExperienceAndCoin(ExperienceAndCoinData experienceAndCoinData);
}
