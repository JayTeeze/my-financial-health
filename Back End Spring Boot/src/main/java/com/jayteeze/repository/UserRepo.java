package com.jayteeze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jayteeze.entity.User;

@Transactional
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Modifying
	@Query("UPDATE User U set U.firstName = ?1, U.lastName = ?2, U.state = ?3 WHERE U.id = ?4")
	void updateUserDetails(String firstName, String lastName, String state, Integer id);

}
