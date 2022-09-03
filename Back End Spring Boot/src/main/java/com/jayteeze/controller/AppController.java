package com.jayteeze.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jayteeze.entity.BalanceSnapshot;
import com.jayteeze.entity.Credential;
import com.jayteeze.entity.DateRange;
import com.jayteeze.entity.Financial;
import com.jayteeze.entity.User;
import com.jayteeze.repository.BalanceSnapshotRepo;
import com.jayteeze.repository.CredentialRepo;
import com.jayteeze.repository.FinancialRepo;
import com.jayteeze.repository.UserRepo;

@CrossOrigin
@RestController
public class AppController {
	
	@Autowired
	FinancialRepo financialRepo;
	
	@Autowired
	BalanceSnapshotRepo balanceSnapshotRepo;
	
	@Autowired
	CredentialRepo credentialRepo;
	
	@Autowired
	UserRepo userRepo;
	
	// ---------- Credential servlets ----------
	
	// Subsequently creates user entry
	@RequestMapping(value="/createAccount", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void submitUserDetails(@RequestBody Credential creds) {
		credentialRepo.save(creds);
	}
	
	@RequestMapping(value="/login", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private ResponseEntity<User> login(@RequestBody Credential creds) {
		// '.getUser()' is used to get user data
		creds = credentialRepo.authenticate(creds.getEmail(), creds.getPassword());
		
		if (creds != null) {
			User account = creds.getUser();
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/findAllCreds", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<Credential>> findAllCreds() {
		List<Credential> credentials = credentialRepo.findAll();
		return new ResponseEntity<>(credentials, HttpStatus.OK);
	}
	
	// ---------- User data servlets ----------
	
	@RequestMapping(value="/updateUserDetails", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void updateUserDetails(@RequestBody User updatedDetails) {
		userRepo.updateUserDetails(updatedDetails.getFirstName(),
				updatedDetails.getLastName(), updatedDetails.getState(),
				updatedDetails.getId());
	}
	
	// ---------- Financial servlets ----------
	
	@RequestMapping(value="/createFinancialEntry", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void submitFinancialEntry(@RequestBody Financial financial) {
		financialRepo.save(financial);
	}
	
	@RequestMapping(value="/findAllUserFinancials", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<Financial>> findAllUserFinancials(Integer id) {
		List<Financial> financials = financialRepo.findAllUserFinancials(id);
		return new ResponseEntity<>(financials, HttpStatus.OK);
	}
	
	@RequestMapping(value="/findUserFinancialsByCategory", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET,
			 params={"id", "category"})
	@ResponseBody
	private ResponseEntity<List<Financial>> findUserFinancialsByCategory(Integer id, String category) {
		List<Financial> financials = financialRepo.findUserFinancialsByCategory(id, category);
		return new ResponseEntity<>(financials, HttpStatus.OK);
	}
	
	@RequestMapping(value="/findUserFinancialsByCategory", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET,
			 params={"id", "category", "limit"})
	@ResponseBody
	private ResponseEntity<List<Financial>> findUserFinancialsByCategory(Integer id, String category, Integer limit) {
		List<Financial> financials = financialRepo.findUserFinancialsByCategory(id, category, limit);
		return new ResponseEntity<>(financials, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateFinancial", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void updateFinancial(@RequestBody Financial financial) {
		financialRepo.updateFinancial(financial.getDescription(), financial.getAmount(), financial.getId());
	}
	
	@RequestMapping(value="/deleteSelectedFinancial", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private void deleteSelectedFinancial(Integer id) {
		financialRepo.deleteById(id);
	}
	
	@RequestMapping(value="/deleteSelectedFinancials", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void deleteSelectedFinancials(@RequestBody Integer[] ids) {
		financialRepo.deleteFinancials(Arrays.asList(ids));
	}
	
	@RequestMapping(value="/findAllFinancials", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<Financial>> findAllFinancials() {
		List<Financial> financials = financialRepo.findAll();
		return new ResponseEntity<>(financials, HttpStatus.OK);
	}
	
	@RequestMapping(value="/sumFinancialValues", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Long> financialTotal() {
		Long financialSum = financialRepo.financialTotal();
		return new ResponseEntity<>(financialSum, HttpStatus.OK);
	}
	
	// ---------- Balance snapshot servlets ----------
	
	@RequestMapping(value="/createBalanceSnapshot", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void submitBalanceEntry(@RequestBody BalanceSnapshot snapshot) {
		balanceSnapshotRepo.save(snapshot);
	}
	
	@RequestMapping(value="/findAllUserSnapshots", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<BalanceSnapshot>> findAllUserSnapshots(Integer userId) {
		List<BalanceSnapshot> snapshots = balanceSnapshotRepo.findAllUserSnapshots(userId);
		return new ResponseEntity<>(snapshots, HttpStatus.OK);
	}
	
	@RequestMapping(value="/findUserSnapshotsInRange", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private ResponseEntity<List<BalanceSnapshot>> findUserSnapshotsInRange(@RequestBody DateRange range) {
		List<BalanceSnapshot> snapshots = balanceSnapshotRepo.findUserSnapshotsInRange(range.getId(), range.getStartDate(), range.getEndDate());
		return new ResponseEntity<>(snapshots, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateSnapshot", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void updateSnapshot(@RequestBody BalanceSnapshot snapshot) {
		balanceSnapshotRepo.updateSnapshot(snapshot.getAmount(), snapshot.getDate(), snapshot.getId());
	}
	
}
