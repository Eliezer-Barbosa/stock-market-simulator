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
			company.setShares(generator.getShare());
			company.setSharePrice(generator.getSharePrice());
			companies.add(company);
		}
		return companies;
	}
	
	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		List<Company> myCompanies = simulator.generateCompanies();
		for (Company company : myCompanies) {
			System.out.println(
					"Id: " + company.getId() + "\n" + 
					"Shares: " + company.getShares() + "\n" + 
					"Share Price: " + company.getSharePrice() + "\n\n");
		}
	}
	

}
