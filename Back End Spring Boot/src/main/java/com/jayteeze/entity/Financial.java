package com.jayteeze.entity;

import javax.persistence.*;

@Entity
@Table(name="financial")
public class Financial {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="created_at", insertable=false, updatable=false)
	private String createdAt;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="transaction_date")
	private String transactionDate;
	@Column(name="category")
	private String category;
	@Column(name="amount")
	private double amount;
	@Column(name="updated_at", insertable=false, updatable=false)
	private String updatedAt;
	@Column(name="user_id")
	private int userId;

	public String getCreatedAt() {
		return createdAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

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
