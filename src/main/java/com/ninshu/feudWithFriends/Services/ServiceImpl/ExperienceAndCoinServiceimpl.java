package com.ninshu.feudWithFriends.Services.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import com.ninshu.feudWithFriends.Utilities.MapperUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ninshu.feudWithFriends.DAO.DaoInterface.ExperienceAndCoinDao;
import com.ninshu.feudWithFriends.Entities.ExperienceAndCoinData;
import com.ninshu.feudWithFriends.Services.ServiceInterface.ExperienceAndCoinService;
import com.ninshu.feudWithFriends.model.ExperienceAndCoinDataVO;
import com.ninshu.feudWithFriends.Utilities.UserLevel;

@Service
public class ExperienceAndCoinServiceimpl implements ExperienceAndCoinService{
	
	@Autowired
	private ExperienceAndCoinDao experienceAndCoinDao;
	
	@Override
	@Transactional
	public ExperienceAndCoinDataVO fetchExperienceAndCoin(String userId) {
		ExperienceAndCoinData experienceAndCoinRecordData =  experienceAndCoinDao.fetchRecord(userId);
		return MapperUtility.mapExperienceAndCoinDataVO(experienceAndCoinRecordData);
	}
	
	@Override
	@Transactional
	public ExperienceAndCoinDataVO updateExperienceAndCoin(ExperienceAndCoinData experienceAndCoinData) {
		int experienceAndCoinUpdateStatus = experienceAndCoinDao.updateExperienceAndCoin(experienceAndCoinData);
		ExperienceAndCoinData experienceAndCoinRecord = new ExperienceAndCoinData();
		
		if(experienceAndCoinUpdateStatus > 0) {
			experienceAndCoinRecord = experienceAndCoinDao.fetchRecord(experienceAndCoinData.getUserReferenceId());
			return MapperUtility.mapExperienceAndCoinDataVO(experienceAndCoinRecord);
		}else {
			return MapperUtility.mapExperienceAndCoinDataVO(null);
		}
	}
	
	@Override
	@Transactional
	public ExperienceAndCoinDataVO createExperienceAndCoin(String userId) {
		ExperienceAndCoinData experienceAndCoinRecordData = new ExperienceAndCoinData();
		
		//create record data
		experienceAndCoinRecordData.setCurrentCoinValue("0");
		experienceAndCoinRecordData.setCurrentExperienceValue("0");
		experienceAndCoinRecordData.setUserReferenceId(userId);
		experienceAndCoinRecordData.setCurrentUserLevel(UserLevel.LEVEL1.toString());
		
		//get back uid
		experienceAndCoinDao.createExperienceAndCoin(experienceAndCoinRecordData);
		
		return fetchExperienceAndCoin(userId);
		
	}

}
