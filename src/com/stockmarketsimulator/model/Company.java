package com.stockmarketsimulator.model;

import java.util.Observable;
import java.util.Observer;

public class Company implements Observer, Trade {
	
	private int sharesSold;
	private int capital;
	private int id;
	private String name;
	private int shares;
	private int sharePrice;
	
	private boolean status = true;
	
	// method implementation of Trade interface
		@Override
		public void deal() {
			
			// decrement share by -1
			this.shares -= 1;
			
			// increment shares sold by +1
			this.sharesSold += 1;
			
			alterSharePrice(this.sharesSold);
			
		}

		private void alterSharePrice(int currentSharesSold) {
			if (currentSharesSold == 10) {
				//System.out.println("Double price: before - " + this.sharePrice);
				doubleSharePrice();
				//System.out.println("Double price: after - " + this.sharePrice);
			}		
		}

		private void doubleSharePrice() {
			this.sharePrice *= 2;
		}
		
		// method implementation of Observer interface
		@Override
		public void update(Observable observable, Object object) {
			//Investor investor = (Investor) object;
			String action = String.valueOf(object);
			
			if (action.equals("deal")) {
				this.deal();
			}
			
		}
	
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
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
