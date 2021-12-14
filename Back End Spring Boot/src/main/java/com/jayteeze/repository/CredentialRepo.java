package com.jayteeze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jayteeze.entity.Credential;

@Repository
public interface CredentialRepo extends JpaRepository<Credential, Integer> {
	
	@Query("SELECT C FROM Credential C WHERE C.email = ?1 AND C.password = ?2")
	Credential authenticate(String email, String password);

}
