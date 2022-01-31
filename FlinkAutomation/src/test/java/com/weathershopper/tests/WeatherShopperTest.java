package com.weathershopper.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.weathershopper.base.BaseTest;
import com.weathershopper.pages.WeatherShopperPage;

public class WeatherShopperTest extends BaseTest{
	
	private WeatherShopperPage weatherShopperPage;
	
	
	public WeatherShopperTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setup(){
		
		initialization();
		weatherShopperPage = new WeatherShopperPage();
		
	}
	
	@Test
	public void TC01_weatherShopperEndToEndTest() throws InterruptedException{
		
		// Select Moisturiser / Sunscreens and add products to cart.
		String actualProduct[] = weatherShopperPage.selectItemBasedOnTemperature();
		
		// click on cart button
		weatherShopperPage.clickOnCartBtn();	
		
		// Retrieve the product names in checkout page and validate the products added are equal to the selected products
		String expectedProduct[] = weatherShopperPage.getProductNamesInCheckoutPage();
		
		Assert.assertEquals(actualProduct[0], expectedProduct[0],"Added product1 is not equal to product in Checout Page");
		Assert.assertEquals(actualProduct[1], expectedProduct[1],"Added product2 is not equal to product in Checout Page");
		
		// click on pay with card button
		weatherShopperPage.clickOnPayWithCard();
		
		// Enter the credit card details
		weatherShopperPage.enterCCDetails(getEmail(), getCardNumber(), getExpiryDate(), getCsv(), getzipCode());
	
		// click on complete payment button
		weatherShopperPage.clickOnPaymentBtn();
		
		// validate the payment is successful message
		weatherShopperPage.verifyPaymentIsSucessfull();
	}
	
	
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.quit();
		
	}
}

