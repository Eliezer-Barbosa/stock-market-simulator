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
	
	public int getShare() {
		Random random = new Random();
		share =  random.nextInt(this.maximumShare - this.minimumShare) + minimumShare;
		return share;
	}
	
	public int getSharePrice() {
		Random random = new Random();
		sharePrice =  random.nextInt(this.maximumSharePrice - this.minimumSharePrice) + minimumSharePrice;
		return sharePrice;
	}
	
	public int getBudget() {
		Random random = new Random();
		budget =  random.nextInt(this.maximumBudget - this.minimumBudget) + minimumBudget;
		return budget;
	}
	
	public static void main(String[] args) {
		RandomGenerator generator = new RandomGenerator();
		System.out.println("share: " + generator.getShare());
		System.out.println("budget: " + generator.getBudget());
		System.out.println("Price: " + generator.getSharePrice());
	}
	
}
