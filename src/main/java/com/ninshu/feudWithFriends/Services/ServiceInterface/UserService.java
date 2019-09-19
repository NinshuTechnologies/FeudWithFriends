package com.ninshu.feudWithFriends.Services.ServiceInterface;

import com.ninshu.feudWithFriends.Entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User getUserById(int id);
    public User getRandomActiveUser();
}
