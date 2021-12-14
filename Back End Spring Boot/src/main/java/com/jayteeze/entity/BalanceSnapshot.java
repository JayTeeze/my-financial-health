package com.jayteeze.entity;

import javax.persistence.*;

@Entity
@Table(name="balance_snapshot")
public class BalanceSnapshot {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="amount")
	private double amount;
	@Column(name="date")
	private String date;
	@Column(name="user_id")
	private int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
