package com.stockmarketsimulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.stockmarketsimulator.utils.RandomGenerator;

/**
 * @author Eliezer
 *
 */
public class Simulator {
	
	// declaring list of companies and investors
	private List<Company> companies;
	private List<Investor> investors;
	
	// declaring a generator
	private RandomGenerator generator;
	
	// constructor initializing this class instances
	public Simulator() {
		this.companies = new ArrayList<>();
		this.investors = new ArrayList<>();
		this.generator = new RandomGenerator();
	}
	
	// main method
	public static void main(String[] args) {
		
		// creating a new instance of Simulator
		Simulator simulator = new Simulator();
		
		// generating companies and investors
		List<Company> myCompanies = simulator.generateCompanies();
		List<Investor> myInvestors = simulator.generateInvestors();
			
		startTrading(myCompanies, myInvestors);
		
		simulationResults(myInvestors, myCompanies);
		
	}
	
	/**
	 * 
	 * Method that start the trades between investors and companies.
	 * This method receives as parameter a list of companies and a list of investors.
	 * Then the trading day method is called.*/
	private static void startTrading(List<Company> myCompanies, List<Investor> myInvestors) {
		// calling trading day method, this will be the first round of trades
		tradingDay(myInvestors, myCompanies);
		
		// reading all investors
		for (Investor investor : myInvestors) {
			// checking if the investor has any budget left to perform a trade
			if (investor.getBudget() != 0 ) {
				// start another trade day
				tradingDay(myInvestors, myCompanies);
			} else {
				// reading all companies
				for (Company company : myCompanies) {
					// checking if the company has shares to sell
					if (company.getShares() != 0) {
						// start another trade day
						tradingDay(myInvestors, myCompanies);
					}
				}
			}
		}
	}
	
	// method that generates 100 companies and return it
	public List<Company> generateCompanies() {
		// loop from 1 to 100
		for (int i = 1; i < 100 + 1; i++) {
			// create a new company
			Company company = new Company();
			// setting company attributes
			company.setId(i);
			company.setName(generator.generateName());
			company.setShares(generator.getShare());
			company.setSharePrice(generator.getSharePrice());
			// adding company to the list os companies
			companies.add(company);			
		}
		return companies;
	}
	
	// method that generates 100 investors and return it
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
	
	// this method display the final trade results
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
		
	}

	private static void investorsWithTheLowestNumberOfShares(List<Investor> myInvestors, ArrayList<Integer> shareBoughtsLowest) {
		System.out.println("\nInvestor with the lowest number of shares:".toUpperCase());
		
		// reading the investors and getting the lowest bought shares
		for (int i = 0; i < myInvestors.size(); i++) {
			shareBoughtsLowest.add(myInvestors.get(i).getShareBoughts());
			// sorting the list in ascending order, the first index will be the lowest
			Collections.sort(shareBoughtsLowest);
		}
		
		// reading the investors and getting its name to display
		for (Investor investor : myInvestors) {
			if (investor.getShareBoughts() == shareBoughtsLowest.get(0)) {
				System.out.println("Investor name: " + investor.getName() + " Shares: " + investor.getShareBoughts());
			}
		}
	}

	private static void investorsWithTheHighestNumberOfShares(List<Investor> myInvestors, ArrayList<Integer> shareBoughtsHighest) {
		System.out.println("\nInvestor with the highest number of shares:".toUpperCase());
		
		for (int i = 0; i < myInvestors.size(); i++) {
			shareBoughtsHighest.add(myInvestors.get(i).getShareBoughts());
			// sorting the list in descending order, the first index will be the highest
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
		// reading all companies and retrieving its capital
		for (Company company : myCompanies) {
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

	private static void companiesWithTheLowestCapital(List<Company> myCompanies, ArrayList<Integer> lowestCapital) {
		System.out.println("Company with the lowest capital:".toUpperCase());
		int qtdlowest = 0;
		ArrayList<Company> companiesName = new ArrayList<>();

		for (int i = 0; i < lowestCapital.size(); i++) {
			// checking if there are more than one company with lowest capital
			if (lowestCapital.get(0).equals(lowestCapital.get(i))) {
				qtdlowest++;
				for (Company c : myCompanies) {
					// if capital is zero, add it to the list of lowest companies
					if (c.getCapital() == 0) {
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
					boolean companyName = c.getCapital() == highestCapital.get(0);
					if (companyName) {
						System.out.println("Company name: " + c.getName() + " " + " Capital: $ " + highestCapital.get(0) + "\n");
					}
				}
			}
			
		}
	}
	
	// method that perform a trading day, where investor can buy shares from a company
	private static void tradingDay(List<Investor> myInvestors, List<Company> myCompanies) {
		Random random = new Random();
		int position = 0;
		// there will be 100 trades per day, so each investor will have the chance to buy from a random company
		for (int i = 0; i < 100; i++) {
			// random company id
			position = getRandomNumber(random, position);
			// investor buying from a random company
			myInvestors.get(i).buyFrom(myCompanies.get(position));
			// show the current transaction between the investor and the company
			showTradingDayTransactions(myInvestors, myCompanies, position, i);
			// checking how many shares a company has sold
			checkSharesSold(myCompanies, position);	
		}
		
	}

	private static void showTradingDayTransactions(List<Investor> myInvestors, List<Company> myCompanies, int companyPosition, int investorPosition) {
		System.out.println("Investor " + myInvestors.get(investorPosition).getId() + 
				" bougth from " + "Company " + myCompanies.get(companyPosition).getId());
		myCompanies.get(companyPosition).setSharesSold(myCompanies.get(companyPosition).getSharesSold() + 1);
	}
	
	// this method check if a company will get an increase in its share price of double the price
	// or if it will have its share price reduced in 2%
	private static void checkSharesSold(List<Company> myCompanies, int position) {
		// verify if the company has sold 10 shares
		if (myCompanies.get(position).getSharesSold() == 10) {
			// double up share price
			myCompanies.get(position).setSharePrice(
					myCompanies.get(position).getSharePrice() * 2);
			// reading all companies
			for (Company company : myCompanies) {
				// checking if any company haven't sold any share
				if (company.getSharesSold() == 0) {
					// reducing share price in 2%
					company.setSharePrice(
							(2 / 100) * company.getSharePrice());
				}
			}
		}
	}
	
	// method that generates a random number from 1 to 100 and returns it
	private static int getRandomNumber(Random random, int result) {
		for (int j = 0; j < 100; j++) {
			result = random.nextInt(100 - 1) + 1;
		}
		return result;
	}

}
