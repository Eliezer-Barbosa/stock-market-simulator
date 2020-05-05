package com.stockmarketsimulator.utils;

import java.util.Random;

public class RandomGenerator {
	
	private int minimumShare = 500;
	private int maximumShare = 1000;
	
	private int minimumSharePrice = 10;
	private int maximumSharePrice = 100;
	
	private int minimumBudget = 1000;
	private int maximumBudget = 10000;
	
	private int share, sharePrice, budget;
	
	Random random = new Random();
	
	public int getRandomId() {
		return random.nextInt(100 - 1 + 1) + 1;
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
	
}
