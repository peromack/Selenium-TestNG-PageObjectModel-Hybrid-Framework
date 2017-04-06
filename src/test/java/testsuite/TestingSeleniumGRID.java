package testsuite;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.ActionsOnShipments;
import pageobjects.ImportCargoLogin;
import pageobjects.ShipmentDetails;
import pageobjects.ShipmentSummaryDashboard;
import resources.GetClassName;
import resources.Retry;
import resources.Utilities;

public class TestingSeleniumGRID extends Utilities  {
	
		

	
	 @Test
	 public void GRIDTest1(){ //test a scenario passing.
		 
		
		try{
			 explicitWaitwaitForElementToBeVisible("id", "lst-ib", 10);
			 driver.findElement(By.id("lst-ib")).sendKeys("Selenium GRID");
			 driver.findElement(By.id("lst-ib")).clear();
			 //driver.findElement(By.name("btnK")).click();
			 
			 explicitWaitwaitForElementToBeVisible("id", "lst-ib", 10);
		}catch(Exception e){
			takeScreenshot();
		}
		 
	 }
	 
	 @Test
	 public void GRIDTest2(){ //test another scenario passing.
		 
		 
		 
		 boolean GRID = true;
		 
		 Assert.assertTrue(GRID);
		 System.out.println("Console output for GRID Test 2");
		 
	 }
	 
	 @Test
	 public void GRIDTest3(){
		 
		 Assert.fail();
		 
	 }
	 
	 



	 @Parameters({"Run-Type" })
	 @BeforeSuite
	  public void beforeSuite(String RunType) {
		
		 
	  }
	 
	 @Parameters({ "Run-Type" })
	 @AfterSuite
	  public void afterSuite(String RunType) {
		 
		
	  }
	 
	 
	 @BeforeTest
	  public void beforeTest() {
		 
	 }
	 
	 @AfterTest
	  public void afterTest(){
		
		 
	 }
	 
	 @Parameters({ "Browser-Type", "Run-Type", "RunTime-Environment"})
	 @BeforeClass
	  public void beforeClass(String RunTimeBrowser, String RunType, String RunTimeEnv) throws Exception {
		 
		//Supports Cross Browser and GRID test setup////////////////////////////////////////////////////////////////////////
		 if(RunTimeBrowser.contentEquals("Internet Explorer")){
			 
		 	try {
				internetExplorerBrowserSettings(RunType);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
			 			 
		 }else if(RunTimeBrowser.contentEquals("FireFox")){
			 
			 try {
				fireFoxDriverBrowserSettings(RunType);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
			 
			 
		 }else if(RunTimeBrowser.contentEquals("Google Chrome")){
			 
			 try {
				googleChromeDriverSettings(RunType);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
			 
		 }else{ //If an invalid or blank RunTimeBrowser parameter is specified, by default, Internet Explorer will be used.
			 RunTimeBrowser = "Internet Explorer";
			 
			 try {
					internetExplorerBrowserSettings(RunType);
				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
				}
		 }
		 
		 driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // Implicit Wait timeout settings.
		 driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); // Page Load timeout settings.
	     driver.navigate().to("https://www.google.com/");
		 driver.manage().window().maximize();
		 
		 
	 }
	 
	 @AfterClass
	  public void afterClass(){
		
		 tearDownRunTimeBrowsers();
	 }
	 
	 @Parameters({ "Browser-Type", "Run-Type", "RunTime-Environment"})
	 @BeforeMethod
	  public void beforeMethod(String RunTimeBrowser, String RunType, String RunTimeEnv, Method method) {
	
	  }
	 
	 @Parameters({ "Browser-Type", "Run-Type", "RunTime-Environment" })
	 @AfterMethod
	  public void afterMethod(String RunTimeBrowser, String RunType, String RunTimeEnv, Method method, ITestResult result) {
		
		 
	 }
	 
}