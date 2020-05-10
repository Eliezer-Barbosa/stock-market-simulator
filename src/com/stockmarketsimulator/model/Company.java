package com.stockmarketsimulator.model;

public class Company implements Comparable<Company> {
	
	private int sharesSold;
	private int capital;
	private int id;
	private String name;
	private int shares;
	private int sharePrice;
	
	public int getSharesSold() {
		return sharesSold;
	}

	public void setSharesSold(int sharesSold) {
		this.sharesSold = sharesSold;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
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
	
	public int getShares() {
		return shares;
	}
	
	public void setShares(int shares) {
		this.shares = shares;
	}
	
	public int getSharePrice() {
		return sharePrice;
	}
	
	public void setSharePrice(int sharePrice) {
		this.sharePrice = sharePrice;
	}

	@Override
	public int compareTo(Company otherCompany) {
		int result = 0;
		if (otherCompany.capital > this.capital) { result =  -1; }
		if (otherCompany.capital == this.capital) { result =  0; }
		if (otherCompany.capital < this.capital) { result =  1; }
		return result;
	}

}
