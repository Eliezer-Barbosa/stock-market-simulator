package com.stockmarketsimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Investor {
	
	private int id;
	private String name;
	private int budget;
	private List<ShareBought> shareBoughts;
	
	public Investor() {
		this.shareBoughts = new ArrayList<>();
	}
	
	public void buyFrom (Company company) {
		if (this.budget >= company.getSharePrice() && company.getShares() > 0) {
			ShareBought shareBought = new ShareBought();
			shareBought.setCompany(company);
			shareBought.setInvestor(this);
			this.shareBoughts.add(shareBought);
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBudget() {
		return budget;
	}
	
	public void setBudget(int budget) {
		this.budget = budget;
	}

}
