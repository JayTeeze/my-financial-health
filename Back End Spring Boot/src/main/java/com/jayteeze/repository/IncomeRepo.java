package com.jayteeze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jayteeze.entity.Income;

@Transactional
@Repository
public interface IncomeRepo extends JpaRepository<Income, Integer> {
	
	@Query("SELECT I FROM Income I WHERE I.userId = ?1")
	List<Income> findAllUserIncome(Integer userId);
	
	@Query("SELECT I FROM Income I WHERE I.userId = ?1 AND I.date BETWEEN ?2 AND ?3")
	List<Income> findUserIncomeInRange(Integer userId, String startDate, String endDate);
	
	@Modifying
	@Query("UPDATE Income I set I.description = ?1, I.amount = ?2, I.date = ?3 WHERE I.id = ?4")
	void updateIncome(String description, Double amount, String date, Integer id);
	
	@Modifying
	@Query("DELETE Income I WHERE I.id = ?1")
	void deleteIncome(Integer id);
	
	@Query("SELECT SUM(amount) FROM Income")
	Long incomeTotal();

}
