package com.jayteeze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jayteeze.entity.BalanceSnapshot;

@Transactional
@Repository
public interface BalanceSnapshotRepo extends JpaRepository<BalanceSnapshot, Integer> {
	
	@Query("SELECT B FROM BalanceSnapshot B WHERE B.userId = ?1")
	List<BalanceSnapshot> findAllUserSnapshots(Integer userId);
	
	@Query("SELECT B FROM BalanceSnapshot B WHERE B.userId = ?1 AND B.date BETWEEN ?2 AND ?3")
	List<BalanceSnapshot> findUserSnapshotsInRange(Integer userId, String startDate, String endDate);
	
	@Modifying
	@Query("UPDATE BalanceSnapshot B set B.amount = ?1, B.date = ?2 WHERE B.id = ?3")
	void updateSnapshot(Double amount, String date, Integer id);

}
