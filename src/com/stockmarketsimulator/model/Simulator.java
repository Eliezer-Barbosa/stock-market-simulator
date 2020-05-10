package com.stockmarketsimulator.model;

import java.util.ArrayList;
import java.util.Collections;
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
			
		tradingDay(myInvestors, myCompanies);
		
		for (Investor investor : myInvestors) {
			if (investor.getBudget() != 0 ) {
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
		ArrayList<Integer> highestCapital = new ArrayList<>();
		ArrayList<Integer> lowestCapital = new ArrayList<>();
		ArrayList<Integer> shareBoughtsHighest = new ArrayList<>();
		ArrayList<Integer> shareBoughtsLowest = new ArrayList<>();

		showCompaniesCapital(myCompanies, highestCapital, lowestCapital);
		
		companiesWithTheHighestCapital(myCompanies, highestCapital);	
		
		companiesWithTheLowestCapital(myCompanies, lowestCapital);
		
		investorsWithTheHighestNumberOfShares(myInvestors, shareBoughtsHighest);
		
		investorsWithTheLowestNumberOfShares(myInvestors, shareBoughtsLowest);
		
//		System.out.println("\n");
//		System.out.println("HIGUEST: " + shareBoughtsHighest);
//		System.out.println("LOWEST: " + shareBoughtsLowest);
	}

	private static void investorsWithTheLowestNumberOfShares(List<Investor> myInvestors,
			ArrayList<Integer> shareBoughtsLowest) {
		System.out.println("\nInvestor with the lowest number of shares:".toUpperCase());
		
		for (int i = 0; i < myInvestors.size(); i++) {
			shareBoughtsLowest.add(myInvestors.get(i).getShareBoughts());
			Collections.sort(shareBoughtsLowest);
		}
		
		for (Investor investor : myInvestors) {
			if (investor.getShareBoughts() == shareBoughtsLowest.get(0)) {
				System.out.println("Investor name: " + investor.getName() + " Shares: " + investor.getShareBoughts());
			}
		}
	}

	private static void investorsWithTheHighestNumberOfShares(List<Investor> myInvestors,
			ArrayList<Integer> shareBoughtsHighest) {
		System.out.println("\nInvestor with the highest number of shares:".toUpperCase());
		
		for (int i = 0; i < myInvestors.size(); i++) {
			shareBoughtsHighest.add(myInvestors.get(i).getShareBoughts());
			Collections.sort(shareBoughtsHighest, Collections.reverseOrder());
		}
		
		for (Investor investor : myInvestors) {
			if (investor.getShareBoughts() == shareBoughtsHighest.get(0)) {
				System.out.println("Investor name: " + investor.getName() + " Shares: " + investor.getShareBoughts());
			}
		}
	}

	private static void showCompaniesCapital(List<Company> myCompanies, ArrayList<Integer> highestCapital,
			ArrayList<Integer> lowestCapital) {
		for (Company company : myCompanies) {
			int capital = company.sharesSold * company.getSharePrice();
			company.capital = capital;
			highestCapital.add(capital);
			lowestCapital.add(capital);
			
			System.out.println("Company " + 
					company.getId() + " " + 
					company.getName() + ": Capital: $ " + 
					company.capital);
			
			Collections.sort(lowestCapital);
			Collections.sort(highestCapital, Collections.reverseOrder());
			System.out.println("");
			
		}
	}

	private static void companiesWithTheLowestCapital(List<Company> myCompanies, ArrayList<Integer> lowestCapital) {
		System.out.println("Company with the lowest capital:".toUpperCase());
		int qtdlowest = 0;
		ArrayList<Company> companiesName = new ArrayList<>();

		for (int i = 0; i < lowestCapital.size(); i++) {

			if (lowestCapital.get(0).equals(lowestCapital.get(i))) {
				qtdlowest++;
				for (Company c : myCompanies) {
					if (c.capital == 0) {
						companiesName.add(c);
					}

				}
				System.out.println("Company name: " + companiesName.get(i).getName() + " " + " Capital: $ " + lowestCapital.get(0));

			}

		}
	}

	private static void companiesWithTheHighestCapital(List<Company> myCompanies, ArrayList<Integer> highestCapital) {
		System.out.println("Company with the highest capital:".toUpperCase());
		for (int i = 0; i < highestCapital.size(); i++) {
			if (highestCapital.get(0).equals(highestCapital.get(i))) {
				for (Company c : myCompanies) {
					boolean companyName = c.capital == highestCapital.get(0);
					if (companyName) {
						System.out.println("Company name: " + c.getName() + " " + " Capital: $ " + highestCapital.get(0) + "\n");
					}
				}
			}
			
		}
	}

	private static void tradingDay(List<Investor> myInvestors, List<Company> myCompanies) {
		Random random = new Random();
		int position = 0;
		for (int i = 0; i < 100; i++) {
			position = getRandomNumber(random, position);
			myInvestors.get(i).buyFrom(myCompanies.get(position));
			showTradingDayTransactions(myInvestors, myCompanies, position, i);
			checkSharesSold(myCompanies, position);	
		}
		
	}

	private static void showTradingDayTransactions(List<Investor> myInvestors, List<Company> myCompanies, int position, int i) {
		System.out.println("Investor " + myInvestors.get(i).getId() + 
				" bougth from " + "Company " + myCompanies.get(position).getId());
		myCompanies.get(position).sharesSold++;
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
