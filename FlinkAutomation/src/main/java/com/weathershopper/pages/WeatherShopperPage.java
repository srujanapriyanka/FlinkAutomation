package com.weathershopper.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.weathershopper.base.BaseTest;

public class WeatherShopperPage extends BaseTest{
	
			//Page Factory
			@FindBy(id="temperature")
			WebElement temperature;
			
			@FindBy(xpath="//button[.='Buy moisturizers']")
			WebElement buyMoisturizeers;
			
			@FindBy(xpath="//button[.='Buy sunscreens']")
			WebElement buySunscreens;
			
			@FindBy(xpath="//p[contains(.,'SPF-50')]/following-sibling::button")
			WebElement spf50AddBtn;
			
			@FindBy(xpath="//p[contains(.,'SPF-30')]/following-sibling::button")
			WebElement spf30Btn;
			
			@FindBy(id="cart")
			WebElement cartBtn;
			
			@FindBy(xpath="(//p[contains(.,'SPF-50')])[1]")
			WebElement getSunscreenProd1;
			
			@FindBy(xpath="(//p[contains(.,'SPF-30')])[1]")
			WebElement getSunscreenProd2;
			
			@FindBy(xpath="//table[@class='table table-striped']/tbody/tr[1]/td")
			WebElement getProd1IncheckoutPage;
			
			@FindBy(xpath="//table[@class='table table-striped']/tbody/tr[2]/td")
			WebElement getProd2IncheckoutPage;
			
			@FindBy(xpath="(//p[contains(.,'Aloe')]/following-sibling::button)[1]")
			WebElement aloeLotiontn;
			
			@FindBy(xpath="(//p[contains(.,'Almond')]/following-sibling::button)[1]")
			WebElement almondMstrBtn;
			
			@FindBy(xpath="(//p[contains(.,'Aloe')])[1]")
			WebElement getMstrProduct1;
			
			@FindBy(xpath="(//p[contains(.,'Almond')])[1]")
			WebElement getMstrProduct2;
			
			@FindBy(xpath="//span[.='Pay with Card']")
			WebElement payWithCardBtn;
			
			@FindBy(name = "stripe_checkout_app")
			WebElement iframe;
			
			@FindBy(id="email")
			WebElement emailField;
			
			@FindBy(id="card_number")
			WebElement cardNumberField;		
			
			@FindBy(id="cc-exp")
			WebElement expiryDateField;
			
			@FindBy(id="cc-csc")
			WebElement csvField;
			
			@FindBy(name="zip")
			WebElement postcodeField;
			
			@FindBy(className="iconTick")
			WebElement payAmntBtn;
			
			@FindBy(xpath="//h2[.='PAYMENT SUCCESS']")
			WebElement paymentSuccessMsg;
			
			WebDriverWait wait;
			
			Actions action = new Actions(driver);
			
			//Initializing the Page Objects:
			public WeatherShopperPage(){
				PageFactory.initElements(driver, this);
				wait = new WebDriverWait(driver, 15);
			}
			
			
			public String[] selectItemBasedOnTemperature() {
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				wait.until(ExpectedConditions.visibilityOf(temperature));
				String text = temperature.getText();
				text = text.substring(0, text.indexOf(" "));
				System.out.println("Temperature is "+text);
				int temp = Integer.parseInt(text);
				String[] products = new String[2];
				if(temp<=18) {
					wait.until(ExpectedConditions.visibilityOf(buyMoisturizeers));
					buyMoisturizeers.click();
					selectMoisturisers();
					products[0]=getMstrProduct1.getText();
					products[1]=getMstrProduct2.getText();
				}
				else if (temp>=34) {
					wait.until(ExpectedConditions.visibilityOf(buySunscreens));
					buySunscreens.click();
					selectSunscreens();
					products[0]=getSunscreenProd1.getText();
					products[1]=getSunscreenProd2.getText();
				}
				else
					System.out.println("No products displayed");
				return products;
			} 
			
			public void selectSunscreens() {
				wait.until(ExpectedConditions.visibilityOf(spf50AddBtn));
				spf50AddBtn.click();
				wait.until(ExpectedConditions.visibilityOf(spf30Btn));
				spf30Btn.click();
			}
			
			public void selectMoisturisers() {
				wait.until(ExpectedConditions.visibilityOf(aloeLotiontn));
				aloeLotiontn.click();
				wait.until(ExpectedConditions.visibilityOf(almondMstrBtn));
				almondMstrBtn.click();
			}
			
			public void clickOnCartBtn() {
				wait.until(ExpectedConditions.visibilityOf(cartBtn));
				//driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				cartBtn.click();
			}
			
			public String[] getProductNamesInCheckoutPage() {
				String[] prod = new String[2];
				prod[0]=getProd1IncheckoutPage.getText();
				prod[1]=getProd2IncheckoutPage.getText();
				return prod;
			}			
			
			public void clickOnPayWithCard() {
				wait.until(ExpectedConditions.visibilityOf(payWithCardBtn));
				payWithCardBtn.click();
			}
			
			public void enterCCDetails(String email,String ccard,String expDate,String csv,String code) throws InterruptedException {
				//wait.until(ExpectedConditions.visibilityOf(emailField));
				//driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
				Thread.sleep(2000);		
				driver.switchTo().frame("stripe_checkout_app");				
				emailField.click();
				emailField.sendKeys(email);		
				Actions action = new Actions(driver);
				action.moveToElement(cardNumberField).click().sendKeys(ccard).build().perform();				
				Thread.sleep(1000);
				action.moveToElement(expiryDateField).click().sendKeys(expDate).build().perform();
				csvField.sendKeys(csv);
				postcodeField.sendKeys(code);
				
			}
			
			public void clickOnPaymentBtn() {
				wait.until(ExpectedConditions.visibilityOf(payAmntBtn));
				payAmntBtn.click();
				driver.switchTo().defaultContent();
			}
			
			public boolean verifyPaymentIsSucessfull() {		
				wait.until(ExpectedConditions.visibilityOf(paymentSuccessMsg));
				return paymentSuccessMsg.isDisplayed();
			}
						
}
