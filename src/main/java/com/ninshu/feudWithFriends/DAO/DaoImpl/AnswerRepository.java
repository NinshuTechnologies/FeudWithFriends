package com.ninshu.feudWithFriends.DAO.DaoImpl;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerList, Integer> {

}
