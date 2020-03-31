package com.ninshu.feudWithFriends.Services.ServiceImpl;

import com.ninshu.feudWithFriends.DAO.DaoInterface.ExperienceAndCoinDao;
import com.ninshu.feudWithFriends.DAO.DaoInterface.UserDao;
import com.ninshu.feudWithFriends.Entities.ExperienceAndCoinData;
import com.ninshu.feudWithFriends.Entities.User;
import com.ninshu.feudWithFriends.Services.ServiceInterface.ExperienceAndCoinService;
import com.ninshu.feudWithFriends.Services.ServiceInterface.UserService;
import com.ninshu.feudWithFriends.Utilities.UserLevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    
    @Autowired
    private ExperienceAndCoinService experienceAndCoinService;
    
    @Autowired
	private ExperienceAndCoinDao experienceAndCoinDao;

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    
    @Override
    @Transactional
    public User createUser() {
    	User createdUser = new User();
    	int generatedUID = userDao.createUser(createdUser);
    	createExperienceAndCoinDataObject(Integer.toString(generatedUID));
    	return userDao.getUserById(generatedUID);
    }

    @Override
    public User getRandomActiveUser() {
        return userDao.getRandomActiveUser();
    }
    
    private ExperienceAndCoinData createExperienceAndCoinDataObject(String userId) {
    	ExperienceAndCoinData experienceAndCoinRecordData = new ExperienceAndCoinData();
		
		//create record data
		experienceAndCoinRecordData.setCurrentCoinValue("0");
		experienceAndCoinRecordData.setCurrentExperienceValue("0");
		experienceAndCoinRecordData.setUserReferenceId(userId);
		experienceAndCoinRecordData.setCurrentUserLevel(UserLevel.LEVEL1.toString());
		
		return experienceAndCoinRecordData;
    }
}
