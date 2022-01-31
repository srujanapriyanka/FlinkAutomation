package com.weathershopper.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	
	protected static WebDriver driver;
	protected static Properties prop;
	private static String baseDir=System.getProperty("user.dir");
	
	public BaseTest(){
		
		try {
			
			prop = new Properties();
			FileInputStream ip = new FileInputStream(baseDir+ "/src/main/java/com/weathershopper/config/config.properties");
			prop.load(ip);
		}
		
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} 
		
		catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	
	public static void initialization(){
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			
			System.setProperty("webdriver.chrome.driver", baseDir+"/driver/chromedriver");	
			driver = new ChromeDriver();
			
		}
		else if(browserName.equals("FF")){
			
			System.setProperty("webdriver.gecko.driver", baseDir+"/driver/geckodriver");	
			driver = new FirefoxDriver(); 
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().to(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	public String getUrl() {
		
		String url = prop.getProperty("url");
		return url;		
	}
	
	public String getEmail() {
		
		String url = prop.getProperty("email");
		return url;		
	}

	public String getCardNumber() {
	
		String url = prop.getProperty("cardNumber");
		return url;		
	}

	public String getExpiryDate() {
	
		String url = prop.getProperty("expiryDate");
		return url;		
	}

	public String getCsv() {
	
		String url = prop.getProperty("cvc");
		return url;		
	}
	
	public String getzipCode() {
		
		String url = prop.getProperty("zipcode");
		return url;		
	}
}
