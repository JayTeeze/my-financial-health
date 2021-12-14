package com.jayteeze.entity;

import javax.persistence.*;

@Entity
@Table(name="liability")
public class Liability {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="description")
	private String description;
	@Column(name="amount")
	private double amount;
	@Column(name="user_id")
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
