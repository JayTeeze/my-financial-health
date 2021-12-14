package com.jayteeze.controller;

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

import com.jayteeze.entity.Asset;
import com.jayteeze.entity.BalanceSnapshot;
import com.jayteeze.entity.Credential;
import com.jayteeze.entity.DateRange;
import com.jayteeze.entity.Expense;
import com.jayteeze.entity.Income;
import com.jayteeze.entity.Liability;
import com.jayteeze.entity.User;
import com.jayteeze.repository.AssetRepo;
import com.jayteeze.repository.BalanceSnapshotRepo;
import com.jayteeze.repository.CredentialRepo;
import com.jayteeze.repository.ExpenseRepo;
import com.jayteeze.repository.IncomeRepo;
import com.jayteeze.repository.LiabilityRepo;
import com.jayteeze.repository.UserRepo;

@CrossOrigin
@RestController
public class AppController {
	
	@Autowired
	AssetRepo assetRepo;
	
	@Autowired
	BalanceSnapshotRepo balanceSnapshotRepo;
	
	@Autowired
	CredentialRepo credentialRepo;
	
	@Autowired
	ExpenseRepo expenseRepo;
	
	@Autowired
	IncomeRepo incomeRepo;
	
	@Autowired
	LiabilityRepo liabilityRepo;
	
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
		User account = credentialRepo.authenticate(creds.getEmail(), creds.getPassword()).getUser();
		if (account != null) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
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
	
	// ---------- Asset servlets ----------
	
	@RequestMapping(value="/createAssetEntry", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void submitAssetEntry(@RequestBody Asset asset) {
		assetRepo.save(asset);
	}
	
	@RequestMapping(value="/findAllUserAssets", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<Asset>> findAllUserAssets(Integer id) {
		List<Asset> assets = assetRepo.findAllUserAssets(id);
		return new ResponseEntity<>(assets, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateAsset", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void updateAsset(@RequestBody Asset asset) {
		assetRepo.updateAsset(asset.getDescription(), asset.getAmount(), asset.getId());
	}
	
	@RequestMapping(value="/deleteSelectedAsset", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private void deleteSelectedAsset(Integer id) {
		assetRepo.deleteById(id);
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
	
	// ---------- Expense servlets ----------
	
	@RequestMapping(value="/createExpenseEntry", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void submitExpenseEntry(@RequestBody Expense expense) {
		expenseRepo.save(expense);
	}
	
	@RequestMapping(value="/findAllUserExpenses", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<Expense>> findAllUserExpenses(Integer userId) {
		List<Expense> expenses = expenseRepo.findAllUserExpenses(userId);
		return new ResponseEntity<>(expenses, HttpStatus.OK);
	}
	
	@RequestMapping(value="/findUserExpensesInRange", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private ResponseEntity<List<Expense>> findUserExpensesInRange(@RequestBody DateRange range) {
		List<Expense> expenses = expenseRepo.findUserExpensesInRange(range.getId(), range.getStartDate(), range.getEndDate());
		return new ResponseEntity<>(expenses, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateExpense", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void updateExpense(@RequestBody Expense expense) {
		expenseRepo.updateExpense(expense.getDescription(), expense.getAmount(), expense.getDate(), expense.getId());
	}
	
	@RequestMapping(value="/deleteSelectedExpense", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private void deleteSelectedExpense(Integer id) {
		expenseRepo.deleteById(id);
	}
	
	// ---------- Income servlets ----------
	
	@RequestMapping(value="/createIncomeEntry", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void submitIncomeEntry(@RequestBody Income income) {
		incomeRepo.save(income);
	}
	
	@RequestMapping(value="/findAllUserIncome", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<Income>> findAllUserIncome(Integer userId) {
		List<Income> income = incomeRepo.findAllUserIncome(userId);
		return new ResponseEntity<>(income, HttpStatus.OK);
	}
	
	@RequestMapping(value="/findUserIncomeInRange", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private ResponseEntity<List<Income>> findUserIncomeInRange(@RequestBody DateRange range) {
		List<Income> income = incomeRepo.findUserIncomeInRange(range.getId(), range.getStartDate(), range.getEndDate());
		return new ResponseEntity<>(income, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateIncome", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void updateIncome(@RequestBody Income income) {
		incomeRepo.updateIncome(income.getDescription(), income.getAmount(), income.getDate(), income.getId());
	}
	
	@RequestMapping(value="/deleteSelectedIncome", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private void deleteIncome(Integer id) {
		incomeRepo.deleteById(id);
	}
	
	// ---------- Liability servlets ----------
	
	@RequestMapping(value="/createLiabilityEntry", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void submitLiabilityEntry(@RequestBody Liability liability) {
		liabilityRepo.save(liability);
	}
	
	@RequestMapping(value="/findAllUserLiabilities", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<Liability>> findAllUserLiabilities(Integer userId) {
		List<Liability> liabilities = liabilityRepo.findAllUserLiabilities(userId);
		return new ResponseEntity<>(liabilities, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateLiability", 
			 consumes=MediaType.APPLICATION_JSON_VALUE, 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.POST)
	private void updateLiability(@RequestBody Liability liability) {
		liabilityRepo.updateLiability(liability.getDescription(), liability.getAmount(), liability.getId());
	}
	
	@RequestMapping(value="/deleteSelectedLiability", 
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 method= RequestMethod.GET)
	@ResponseBody
	private void deleteSelectedLiability(Integer id) {
		liabilityRepo.deleteById(id);
	}

}
