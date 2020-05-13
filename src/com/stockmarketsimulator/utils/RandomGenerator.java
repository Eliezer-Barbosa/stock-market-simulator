package com.stockmarketsimulator.utils;

import java.util.ArrayList;
import java.util.Random;

import com.stockmarketsimulator.model.Company;
import com.stockmarketsimulator.model.Investor;

public class RandomGenerator {
	
	private int minimumShare = 500;
	private int maximumShare = 1000;
	
	private int minimumSharePrice = 10;
	private int maximumSharePrice = 100;
	
	private int minimumBudget = 1000;
	private int maximumBudget = 10000;
	
	private int share, sharePrice, budget;
	
	private static Random random = new Random();
	
	public static int getRandomId() {
		int minimun = 1, maximun = 100;
		return minimun + random.nextInt(maximun);
	}
	
	public String generateName() {
		String[] lettersCombination = { "a", "e", "i", "o", "u", "k", "w", "s", "r" };
		String name = "";
		for (int i = 0; i < 5; i++) {
		     int j = (int) (Math.random() * lettersCombination.length);
		     name += lettersCombination[j];
		}
		return name;
	}
	
	public int getShare() {
		share =  random.nextInt(this.maximumShare - this.minimumShare) + minimumShare;
		return share;
	}
	
	public int getSharePrice() {
		sharePrice =  random.nextInt(this.maximumSharePrice - this.minimumSharePrice) + minimumSharePrice;
		return sharePrice;
	}
	
	public int getBudget() {
		Random random = new Random();
		budget =  random.nextInt(this.maximumBudget - this.minimumBudget) + minimumBudget;
		return budget;
	}
	
	public ArrayList<Company> generateCompanies(int quantity) {
		ArrayList<Company> companiesGenerated = new ArrayList<>();
		for (int i = 1; i < quantity + 1; i++) {
			Company company = new Company();
			company.setId(i);
			company.setName(this.generateName());
			company.setSharePrice(this.getSharePrice());
			company.setShares(this.getShare());
			companiesGenerated.add(company);
		}
		return companiesGenerated;
		
	}

	public ArrayList<Investor> generateInvestors(int quantity) {
		ArrayList<Investor> investorsGenerated = new ArrayList<>();
		for (int i = 1; i < quantity + 1; i++) {
			Investor investor = new Investor();
			investor.setId(i);
			investor.setName(this.generateName());
			investor.setBudget(this.getBudget());
			investorsGenerated.add(investor);
		}
		return investorsGenerated;
		
	}
	
}
