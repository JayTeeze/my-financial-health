package com.jayteeze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jayteeze.entity.Expense;

@Transactional
@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
	
	@Query("SELECT E FROM Expense E WHERE E.userId = ?1")
	List<Expense> findAllUserExpenses(Integer userId);
	
	@Query("SELECT E FROM Expense E WHERE E.userId = ?1 AND E.date BETWEEN ?2 AND ?3")
	List<Expense> findUserExpensesInRange(Integer userId, String startDate, String endDate);
	
	@Modifying
	@Query("UPDATE Expense E set E.description = ?1, E.amount = ?2, E.date = ?3 WHERE E.id = ?4")
	void updateExpense(String description, Double amount, String date, Integer id);
	
	@Modifying
	@Query("DELETE Expense E WHERE E.id = ?1")
	void deleteExpense(Integer id);

}
