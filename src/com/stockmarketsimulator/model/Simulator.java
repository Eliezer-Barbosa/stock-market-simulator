package com.stockmarketsimulator.model;

import java.util.ArrayList;
import java.util.List;

import com.stockmarketsimulator.utils.RandomGenerator;

public class Simulator {
	
	private List<Company> companies;
	private List<Investor> investors;
	private RandomGenerator generator;
	
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
		}
		return investors;
	}

	public static void main(String[] args) {
		
		Simulator simulator = new Simulator();
		
		List<Company> myCompanies = simulator.generateCompanies();
		List<Investor> myInvestors = simulator.generateInvestors();
		
		for (Company company : myCompanies) {
			System.out.println(
					"Company Id: " + company.getId() + "\n" +
					"Company name: " + company.getName() + "\n" +
					"Shares: " + company.getShares() + "\n" + 
					"Share Price: " + company.getSharePrice() + "\n\n");
		}
		
		for (Investor investor : myInvestors) {
			System.out.println(
					"Investor Id: " + investor.getId() + "\n" +
				    "Investor name: " + investor.getName() + "\n" +
					"Budget: " + investor.getBudget() + "\n\n");
		}
		
	}
	

}
