package com.ninshu.feudWithFriends.DAO.DaoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;

import com.ninshu.feudWithFriends.DAO.DaoInterface.ExperienceAndCoinDao;
import com.ninshu.feudWithFriends.Entities.ExperienceAndCoinData;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.model.ExperienceAndCoinDataVO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

@Repository
public class ExperienceAndCoinDaoimpl implements ExperienceAndCoinDao{

	@Autowired
    EntityManager entityManager;
	
	public static final String FETCH_EXPERIENCE_AND_COIN_QUERY  = "from ExperienceAndCoinData where user_reference_id = :userReferenceId";
	
	public static final String UPDATE_EXPERIENCE_AND_COIN_QUERY = "update ExperienceAndCoinData as expandcoin set current_experience_value = :currentExperienceValue, current_coin_value = :currentCoinValue where user_reference_id = :userReferenceId";
	
	@Override
	public int updateExperienceAndCoin(ExperienceAndCoinData experienceAndCoinData) {
		Session session = entityManager.unwrap(Session.class);
		
		//Perform an update
		List<ExperienceAndCoinData> results = new ArrayList<ExperienceAndCoinData>();
		Query<ExperienceAndCoinData> query = session.createQuery(UPDATE_EXPERIENCE_AND_COIN_QUERY);
		query.setParameter("currentExperienceValue",experienceAndCoinData.getCurrentExperienceValue());
		query.setParameter("currentCoinValue", experienceAndCoinData.getCurrentCoinValue());
		query.setParameter("userReferenceId", experienceAndCoinData.getUserReferenceId());
		return query.executeUpdate();
	}
	
	@Override
	public ExperienceAndCoinData fetchRecord(String userReferenceId) {
		Session session = entityManager.unwrap(Session.class);
		
		//fetch Records
		Query<ExperienceAndCoinData> query =  session.createQuery(FETCH_EXPERIENCE_AND_COIN_QUERY, ExperienceAndCoinData.class);
		query.setParameter("userReferenceId", userReferenceId);
		return (ExperienceAndCoinData) query.getResultList().stream().findFirst().orElse(null);
	}
	
	@Override
	public int createExperienceAndCoin(ExperienceAndCoinData experienceAndCoinData) {
		 Session session = entityManager.unwrap(Session.class);
	     int id = (int) session.save(experienceAndCoinData);
	     return id;
	}
}
