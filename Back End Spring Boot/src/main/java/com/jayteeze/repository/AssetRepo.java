package com.jayteeze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jayteeze.entity.Asset;

@Transactional
@Repository
public interface AssetRepo extends JpaRepository<Asset, Integer> {
	
	@Query("SELECT A FROM Asset A WHERE A.userId = ?1")
	List<Asset> findAllUserAssets(Integer userId);
	
	@Modifying
	@Query("UPDATE Asset A set A.description = ?1, A.amount = ?2 WHERE A.id = ?3")
	void updateAsset(String description, Double amount, Integer id);
	
	@Modifying
	@Query("DELETE Asset A WHERE A.id = ?1")
	void deleteAsset(Integer id);
	
	@Query("SELECT SUM(amount) FROM Asset")
	Long assetTotal();

}
