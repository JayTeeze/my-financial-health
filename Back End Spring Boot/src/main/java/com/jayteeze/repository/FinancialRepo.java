package com.jayteeze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jayteeze.entity.Financial;

@Transactional
@Repository
public interface FinancialRepo extends JpaRepository<Financial, Integer> {
	
	@Query("SELECT F FROM Financial F WHERE F.userId = ?1")
	List<Financial> findAllUserFinancials(Integer userId);
	
	@Query("SELECT F FROM Financial F WHERE F.userId = ?1 AND F.category = ?2")
	List<Financial> findUserFinancialsByCategory(Integer userId, String category);
	
	@Query(nativeQuery = true, value = "SELECT * FROM financial WHERE user_id = ?1 AND category = ?2 ORDER BY id DESC LIMIT ?3")
	List<Financial> findUserFinancialsByCategory(Integer userId, String category, Integer limit);
	
	@Modifying
	@Query("UPDATE Financial F set F.description = ?1, F.amount = ?2 WHERE F.id = ?3")
	void updateFinancial(String description, Double amount, Integer id);
	
	@Modifying
	@Query("DELETE Financial F WHERE F.id = ?1")
	void deleteFinancial(Integer id);
	
	@Modifying
	@Query("DELETE Financial F WHERE F.id IN ?1")
	void deleteFinancials(List<Integer> ids);
	
	@Query("SELECT SUM(amount) FROM Financial")
	Long financialTotal();

}
