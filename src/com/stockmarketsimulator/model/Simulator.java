package com.stockmarketsimulator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.stockmarketsimulator.utils.RandomGenerator;

public class Simulator {
	
	private List<Company> companies;
	private List<Investor> investors;
	private RandomGenerator generator;
	
	int totalShares, totalBudget;
	
	public Simulator() {
		this.companies = new ArrayList<>();
		this.investors = new ArrayList<>();
		this.generator = new RandomGenerator();
	}
	
	public List<Company> generateCompanies() {
		for (int i = 1; i < 100 + 1; i++) {
			Company company = new Company();
			company.setId(i);
			company.setName(generator.generateName());
			company.setShares(generator.getShare());
			company.setSharePrice(generator.getSharePrice());
			companies.add(company);
			totalShares = totalShares + company.getShares();
		}
		return companies;
	}
	
	public List<Investor> generateInvestors() {
		for (int i = 1; i < 100 + 1; i++) {
			Investor investor = new Investor();
			investor.setId(i);
			investor.setName(generator.generateName());
			investor.setBudget(generator.getBudget());
			investors.add(investor);
			totalBudget = totalBudget + investor.getBudget();
		}
		return investors;
	}

	public static void main(String[] args) {
		
		Simulator simulator = new Simulator();
		
		List<Company> myCompanies = simulator.generateCompanies();
		List<Investor> myInvestors = simulator.generateInvestors();
				
		System.out.println("Total Shares: " + simulator.totalShares);
		System.out.println("Total Budget: " + simulator.totalBudget + "\n");
//		for (Company company : myCompanies) {
//			System.out.println(
//					"Company Id: " + company.getId() + "\n" +
//					"Company name: " + company.getName() + "\n" +
//					"Shares: " + company.getShares() + "\n" + 
//					"Share Price: " + company.getSharePrice() + "\n\n");
//		}
//		
//		for (Investor investor : myInvestors) {
//			System.out.println(
//					"Investor Id: " + investor.getId() + "\n" +
//				    "Investor name: " + investor.getName() + "\n" +
//					"Budget: " + investor.getBudget() + "\n\n");
//		}
			
		tradingDay(myInvestors, myCompanies);
		for (Investor investor : myInvestors) {
			if (investor.getBudget() !=0 ) {
				tradingDay(myInvestors, myCompanies);
			} else {
				for (Company company : myCompanies) {
					if (company.getShares() != 0) {
						tradingDay(myInvestors, myCompanies);
					}
				}
			}
		}
		simulationResults(myInvestors, myCompanies);
		
	}

	

	private static void simulationResults(List<Investor> myInvestors, List<Company> myCompanies) {
		for (Company company : myCompanies) {
			int capital = company.getShares() * company.getSharePrice();
			company.capital = capital;
			
			System.out.println("Company " + company.getId() + ": Capital: $ " + company.capital);

		}
		
	}

	private static void tradingDay(List<Investor> myInvestors, List<Company> myCompanies) {
		Random random = new Random();
		int x = 0;
		for (int i = 0; i < 100; i++) {
			x = getRandomNumber(random, x);
			myInvestors.get(i).buyFrom(myCompanies.get(x));
			System.out.println("Investor " + myInvestors.get(i).getId() + 
					" bougth from " + "Company " + myCompanies.get(x).getId());
			myCompanies.get(x).sharesSold++;
			checkSharesSold(myCompanies, x);	
		}
		
	}

	private static void checkSharesSold(List<Company> myCompanies, int x) {
		if (myCompanies.get(x).sharesSold == 10) {
			myCompanies.get(x).setSharePrice(
					myCompanies.get(x).getSharePrice() * 2);
			for (Company company : myCompanies) {
				if (company.sharesSold == 0) {
					company.setSharePrice(
							(2 / 100) * company.getSharePrice());
				}
			}
		}
	}

	private static int getRandomNumber(Random random, int x) {
		for (int j = 0; j < 100; j++) {
			x = random.nextInt(100 - 1) + 1;
		}
		return x;
	}

}
