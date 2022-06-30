package com.jayteeze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jayteeze.entity.Liability;

@Transactional
@Repository
public interface LiabilityRepo extends JpaRepository<Liability, Integer> {
	
	@Query("SELECT L FROM Liability L WHERE L.userId = ?1")
	List<Liability> findAllUserLiabilities(Integer userId);
	
	@Modifying
	@Query("UPDATE Liability L set L.description = ?1, L.amount = ?2 WHERE L.id = ?3")
	void updateLiability(String description, Double amount, Integer id);
	
	@Modifying
	@Query("DELETE Liability L WHERE L.id = ?1")
	void deleteLiability(Integer id);
	
	@Query("SELECT SUM(amount) FROM Liability")
	Long liabilityTotal();

}
