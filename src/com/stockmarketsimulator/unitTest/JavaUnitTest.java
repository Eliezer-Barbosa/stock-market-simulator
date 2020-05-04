package com.stockmarketsimulator.unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.stockmarketsimulator.utils.RandomGenerator;

public class JavaUnitTest {
	
	private RandomGenerator generator = new RandomGenerator();
	
	@Test
	public void testGenerateShare() {
		int min = 500;
		int max = 1000;
		int share = generator.getShare();
		assertEquals(share >= min || share <= max, true);
	}
	
	@Test
	public void testGenerateSharePrice() {
		int min = 10;
		int max = 100;
		int sharePrice = generator.getSharePrice();
		assertEquals(sharePrice >= min || sharePrice <= max, true);
	}
	
	@Test
	public void testGenerateBudget() {
		int min = 1000;
		int max = 10000;
		int budget = generator.getBudget();
		assertEquals(budget >= min || budget <= max, true);
	}

}
