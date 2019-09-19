package com.ninshu.feudWithFriends.DAO.DaoInterface;

import com.ninshu.feudWithFriends.Entities.User;

public interface UserDao {
    public User getUserById(int id);
    public User getRandomActiveUser();
}
