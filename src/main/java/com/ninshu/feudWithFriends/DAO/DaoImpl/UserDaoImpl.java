package com.ninshu.feudWithFriends.DAO.DaoImpl;

import com.ninshu.feudWithFriends.DAO.DaoInterface.UserDao;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.Entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public User getUserById(int id) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public User getRandomActiveUser() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM User WHERE isUserOnline='TRUE' ORDER BY rand()").setMaxResults(1);
        User user = (User) query.getSingleResult();
        return user;
    }
    
    @Override
    public int createUser(User createdUser) {
    	Session session = entityManager.unwrap(Session.class);
    	int id = (int) session.save(createdUser);
    	return id;
    }
}
