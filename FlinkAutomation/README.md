# Flink Automation Tests

# Framework design

* Page Object model with page factory design pattern followed for the actions to be performed on each page. 
* Under this model, AmazonPage class will identify the WebElements of that web page and also contains Page methods which perform operations on those WebElements.


### Tools and Technologies used

* Java (1.8 or higher)
* Selenium (3.141.59)
* TestNG
* Chrome Browser (Version 97.0.4692.99 (Official Build) (64-bit))
* Maven
* Extent Report


### The below functionality covered end-to-end testcases in weather shopper application.

* (com.weathershopper.tests.WeatherShopperTest)
	* Navigate to WeatherShopper application(http://weathershopper.pythonanywhere.com/)
	* Select for moisturizers if the weather is below 19 degrees or select for suncreens if the weather is above 34 degrees.
	* Add two moisturisers/sunscreens to the cart as per the condition given in application.
	* Click on Cart button.
	* Validate the products added are same as the selected products
	* Click on Pay with card button.
	* Enter the credit card details and click on complete payment button.
	* Validate payment success message is displayed
	

### Configuration/setup to be done to run the project

* The URL, browser name, credit card details are configured in config.properies file(available in FlinkAutomation\src\main\java\com\weathershopper\config\config.properties)
	* url = https://weathershopper.pythonanywhere.com/
	* browser = chrome or FF (for firefox) 

* We can pass any test data from config.properties file of our choice.
* Chromedriver or Geckodriver executable files supported for MAC are available in drivers folder.
* BaseTest class (com.weathershopper.base package) is base class for all tests and it provides configurations for driver intialisation, defining properties files, browser launch etc.
* All test classes (com.weathershopper.tests package) extends TestBase class to inherit base configuration data.


### To Run Tests
* testng.xml file is configured with tests and extent reports.
* run the tests by executing the testng.xml file as testng suite.

### To view Report after test execution
* After tests run, open report under test-output/TestResults.html to see interactive HTML report on test execution status.

### Notes
* Test scripts can run on Windows/MAC OS independent of a particular OS.
* Supports parellel execution, to run parallely
	Include parallel tag(based on methods/test) and thread-count(no. of browser instances) tag in testNG.xml file.
	i.e. <suite name="Flink Automation tests" parallel = "methods" thread-count="2">
* Test scripts support in chrome and firefox browsers.


