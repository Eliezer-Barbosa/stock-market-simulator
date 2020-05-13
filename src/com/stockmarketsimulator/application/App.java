package com.stockmarketsimulator.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.stockmarketsimulator.model.Company;
import com.stockmarketsimulator.model.Investor;
import com.stockmarketsimulator.utils.RandomGenerator;

public class App {
	
	private RandomGenerator generator;
	private List<Company> companies;
	private List<Investor> investors;
	
	public App() {
		initInstances();
		createCompaniesAndInvestors();
		showCompaniesCreated();
		showInvestorsCreated();
		startTrading(companies, investors);
		simulationResults(investors, companies);
	}

	private void initInstances() {
		this.generator = new RandomGenerator();
		this.companies = new ArrayList<>();
		this.investors = new ArrayList<>();
	}
	
	private void createCompaniesAndInvestors() {
		companies.addAll(this.generator.generateCompanies(100));
		investors.addAll(this.generator.generateInvestors(100));
	}
	
	private void showCompaniesCreated() {
		System.out.println("COMPANIES CREATED");
		for (Company company : companies) {
			System.out.println(
					"#ID:__" + company.getId()
						+ " " + 
					"Name:__" + company.getName()
						+ " " +
					"Shares:__" + company.getShares()
						+ " " +
					"Share Price:__" + company.getSharePrice() + "\n");
		}
	}
	
	private void showInvestorsCreated() {
		System.out.println("INVESTORS CREATED");
		for (Investor investor : investors) {
			System.out.println(
					"#ID:__" + investor.getId()
						+ " " + 
					"Name:__" + investor.getName()
						+ " " +
					"Budget:__" + investor.getBudget() + "\n");
		}
	}
	
	private void startTrading(List<Company> companies, List<Investor> investors) {
		
		tradingDay(investors, companies);
		
		// reading all investors
		for (Investor investor : investors) {
			// checking if the investor has any budget left to perform a trade
			if (investor.getBudget() != 0 ) {
				// start another trade day
				tradingDay(investors, companies);
			} else {
				// reading all companies
				for (Company company : companies) {
					// checking if the company has shares to sell
					if (company.getShares() != 0) {
						// start another trade day
						tradingDay(investors, companies);
					}
				}
			}
		}
	}
	
    private void tradingDay(List<Investor> investors, List<Company> companies) {
    	Random random = new Random();
		int position = 0;
		// there will be 100 trades per day, so each investor will have the chance to buy from a random company
		for (int i = 0; i < 100; i++) {
			// random company id
			position = getRandomNumber(random, position);
			// investor buying from a random company
			investors.get(i).buyFrom(companies.get(position));
			// show the current transaction between the investor and the company
			//showTradingDayTransactions(investors, companies, position, i);
			// checking how many shares a company has sold
			checkSharesSold(companies, position);	
		}
		
	}
    
    // this method check if a company will get an increase in its share price of double the price
 	// or if it will have its share price reduced in 2%
 	private void checkSharesSold(List<Company> companies, int position) {
 		// verify if the company has sold 10 shares
 		if (companies.get(position).getSharesSold() == 10) {
 			// double up share price
 			companies.get(position).setSharePrice(
 					companies.get(position).getSharePrice() * 2);
 			// reading all companies
 			for (Company company : companies) {
 				// checking if any company haven't sold any share
 				if (company.getSharesSold() == 0) {
 					// reducing share price in 2%
 					company.setSharePrice(
 							(2 / 100) * company.getSharePrice());
 				}
 			}
 		}
 	}
 	
 // this method display the final trade results
 	private static void simulationResults(List<Investor> investors, List<Company> companies) {
 		ArrayList<Integer> highestCapital = new ArrayList<>();
 		ArrayList<Integer> lowestCapital = new ArrayList<>();
 		ArrayList<Integer> shareBoughtsHighest = new ArrayList<>();
 		ArrayList<Integer> shareBoughtsLowest = new ArrayList<>();

 		showCompaniesCapital(companies, highestCapital, lowestCapital);
 		
 		companiesWithTheHighestCapital(companies, highestCapital);	
 		
 		companiesWithTheLowestCapital(companies, lowestCapital);
 		
 		investorsWithTheHighestNumberOfShares(investors, shareBoughtsHighest);
 		
 		investorsWithTheLowestNumberOfShares(investors, shareBoughtsLowest);
 		
 	}

 	private static void investorsWithTheLowestNumberOfShares(List<Investor> investors, ArrayList<Integer> shareBoughtsLowest) {
 		System.out.println("\nInvestor with the lowest number of shares:".toUpperCase());
 		
 		// reading the investors and getting the lowest bought shares
 		for (int i = 0; i < investors.size(); i++) {
 			shareBoughtsLowest.add(investors.get(i).getShareBoughts());
 			// sorting the list in ascending order, the first index will be the lowest
 			Collections.sort(shareBoughtsLowest);
 		}
 		
 		// reading the investors and getting its name to display
 		for (Investor investor : investors) {
 			if (investor.getShareBoughts() == shareBoughtsLowest.get(0)) {
 				System.out.println("Investor name: " + investor.getName() + " Shares: " + investor.getShareBoughts());
 			}
 		}
 	}

 	private static void investorsWithTheHighestNumberOfShares(List<Investor> investors, ArrayList<Integer> shareBoughtsHighest) {
 		System.out.println("\nInvestor with the highest number of shares:".toUpperCase());
 		
 		for (int i = 0; i < investors.size(); i++) {
 			shareBoughtsHighest.add(investors.get(i).getShareBoughts());
 			// sorting the list in descending order, the first index will be the highest
 			Collections.sort(shareBoughtsHighest, Collections.reverseOrder());
 		}
 		
 		for (Investor investor : investors) {
 			if (investor.getShareBoughts() == shareBoughtsHighest.get(0)) {
 				System.out.println("Investor name: " + investor.getName() + " Shares: " + investor.getShareBoughts());
 			}
 		}
 	}

 	private static void showCompaniesCapital(List<Company> companies, ArrayList<Integer> highestCapital,
 			ArrayList<Integer> lowestCapital) {
 		// reading all companies and retrieving its capital
 		for (Company company : companies) {
 			// capital calculation
 			int capital = company.getSharesSold() * company.getSharePrice();
 			company.setCapital(capital);
 			// adding capital in two distinct list, so I can order it to get the highest and the lowest
 			highestCapital.add(capital);
 			lowestCapital.add(capital);
 			
 			System.out.println("Company " + 
 					company.getId() + " " + 
 					company.getName() + ": Capital: $ " + 
 					company.getCapital());
 			
 			// ordering in the way it will have the first index the lowest
 			Collections.sort(lowestCapital);
 			// ordering in the way it will have the first index the highest
 			Collections.sort(highestCapital, Collections.reverseOrder());
 			System.out.println("");
 			
 		}
 	}

 	private static void companiesWithTheLowestCapital(List<Company> companies, ArrayList<Integer> lowestCapital) {
 		System.out.println("Companies with the lowest capital:".toUpperCase());
 		int qtdlowest = 0;
 		ArrayList<Company> companiesName = new ArrayList<>();

 		for (int i = 0; i < lowestCapital.size(); i++) {
 			// checking if there are more than one company with lowest capital
 			if (lowestCapital.get(0).equals(lowestCapital.get(i))) {
 				qtdlowest++;
 				for (Company c : companies) {
 					// if capital is zero, add it to the list of lowest companies
 					if (c.getCapital() == 0) {
 						companiesName.add(c);
 					}

 				}
 				System.out.println("Company name: " + companiesName.get(i).getName() + " " + " Capital: $ " + lowestCapital.get(0));

 			}

 		}
 	}

 	private static void companiesWithTheHighestCapital(List<Company> companies, ArrayList<Integer> highestCapital) {
 		System.out.println("Company with the highest capital:".toUpperCase());
 		for (int i = 0; i < highestCapital.size(); i++) {
 			if (highestCapital.get(0).equals(highestCapital.get(i))) {
 				for (Company c : companies) {
 					boolean companyName = c.getCapital() == highestCapital.get(0);
 					if (companyName) {
 						System.out.println("Company name: " + c.getName() + " " + " Capital: $ " + highestCapital.get(0) + "\n");
 					}
 				}
 			}
 			
 		}
 	}
    
    // method that generates a random number from 1 to 100 and returns it
 	private int getRandomNumber(Random random, int result) {
 		for (int j = 0; j < 100; j++) {
 			result = random.nextInt(100 - 1) + 1;
 		}
 		return result;
 	}
	
	public static void main(String[] args) {
		new App();
	}

}