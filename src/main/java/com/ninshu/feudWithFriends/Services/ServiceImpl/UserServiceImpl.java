package com.ninshu.feudWithFriends.Services.ServiceImpl;

import com.ninshu.feudWithFriends.DAO.DaoInterface.UserDao;
import com.ninshu.feudWithFriends.Entities.User;
import com.ninshu.feudWithFriends.Services.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getRandomActiveUser() {
        return userDao.getRandomActiveUser();
    }
}
