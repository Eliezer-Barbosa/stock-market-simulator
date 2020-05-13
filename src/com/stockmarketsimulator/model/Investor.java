package com.stockmarketsimulator.model;

import java.util.Observable;

public class Investor extends Observable implements Trade {
	
	private int id;
	private String name;
	private int budget;
	private int shareBoughts;
	
	private String action = "";
	private boolean status = true;
	
	@Override
	public void deal() {
		this.action = "deal";
		this.changeState();
	}
	
	private void changeState() {
		setChanged();
		notifyObservers(action);
	}
	
	public void buyFrom(Company company) {
		this.addObserver(company);
		if (this.budget >= company.getSharePrice() && company.getShares() > 0) {
			this.budget -= company.getSharePrice();
			this.shareBoughts++;
			this.deal();
		}
		if (this.budget < 10) {
			// this investor is out
			System.err.println("INVESTOR OUT! ID " + this.getId() + " budget left: " + this.budget);
			this.removeInvestor();
		}
		
		if (company.getShares() == 0) {
			// this company is out
			System.err.println("COMPANY OUT! ID " + company.getId() + " shares left: " + company.getShares());
			company.setStatus(false);
		}
	}
	
	private void removeInvestor() {
		this.status = false;	
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

	public int getShareBoughts() {
		return this.shareBoughts;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
