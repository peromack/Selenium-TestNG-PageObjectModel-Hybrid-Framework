package testsuite;


import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;

import pageobjects.DataBot;
import resources.Utilities;

public class CreateShipments extends Utilities  {
	
	 DataBot objDataBot;
	 String originalHandle;
	 String env;
	 JavascriptExecutor js;
	
	 
	 
	 
	
	 public void createSimpleOceanBolaShipment () throws Exception { 
			
		 objDataBot = new DataBot(driver);
		 env = getRunTimeEnv();
		 
		 navigateToDataBot();
		 
		 selectTemplateForDataBot("Ocean BOLA Simple");
		 
		 
		 //Fill out respective fields
		 objDataBot.setScac("OCEA");
		 objDataBot.setLadingNumber("999"); 
		 objDataBot.setCurrentTime();
		 objDataBot.setProcessDate();
		 objDataBot.setArrivalDate();
		 objDataBot.setPort("1429");
		 objDataBot.setShipperName("Some Testing Shipper Name Company");
		 objDataBot.setShipperAddress("123 Fake Address High Street");
		 objDataBot.setShipperGeo("27260 NC US");
		 objDataBot.setConveyanceName("Some Conveyance Testing Name");
		 objDataBot.setConveyanceAddress("456 Another Fake Address");
		 objDataBot.setConveyanceGeo("27260 NC US");
		 
		
		//Fill out respective destinations if needed
		 if(env.equalsIgnoreCase("SAT")){
			 objDataBot.clickOnDestinations();
			 
			 objDataBot.setDestinationName("Ocean BOL SAT Queue");
			 objDataBot.setHostName("ats-s01.cbp.dhs.gov");
			 objDataBot.setQueueManager("QM_ATSS01");
			 objDataBot.setQueueChannel("QM_ATSS01.ATSN");
		 }
		 
		 objDataBot.clickOnSendMessageButton();
		 objDataBot.verifyMessageWebConfirmation();		 
		 
		 Utilities.switchOrCloseWindowTab(false, 0, true, 1);
		 Reporter.log("Created a Ocean bola shipment");
		 
	  }
	 
	 
	 //////////////////////////////////////////////////REUSABLE METHODS BELOW////////////////////////////////////////////////////////////////////////////
	 public void navigateToDataBot(){
		 
	     
	     js =(JavascriptExecutor)driver;
	 
	     //Navigate to Databot
	     js.executeScript("window.open('https://uxvnwg001a2915.sat.cbp.dhs.gov:9400/ta/cargo/import/databot/#','_blank');");
	     Utilities.switchOrCloseWindowTab(true, 2, false, 2);
	    
	     
		//Page Check
		explicitWaitwaitForElementToBeVisible("xpath", "//*[@id='content']/div/h3", 10);
		explicitWaitwaitForElementToBeVisible("id", "teamSelect", 10);
		
		//Select Team
		driver.findElement(By.id("teamSelect")).click();
		explicitWaitwaitForElementToBeVisible("xpath", "//select[@id='teamSelect']/*[@value='TASPD']", 10);
		driver.findElement(By.xpath("//select[@id='teamSelect']/*[@value='TASPD']")).click();
		driver.findElement(By.id("teamSelect")).click();
		
     }
	 
	 
	 /////////////////////////////////////////////////////////////Utilities for DataBot////////////////////////////////////////////////////////////
	
	 public static void selectTemplateForDataBot(String expectedTemplateName){ //sample: //div[@id='content']//a[contains(., 'Ocean BOLA Simple')]
			
			String selectedTemplate = expectedTemplateName;
			String xpath = "//div[@id='content']//a[contains(., '" + selectedTemplate + "')]";
			explicitWaitwaitForElementToBeVisible("xpath", xpath, 10);
			
			driver.findElement(By.xpath(xpath)).click();
			
			//Verify that correct template has displayed
			explicitWaitwaitForElementToBeVisible("cssSelector", ".message-intro-bar>h3>span", 10);
			String actualTemplateName = driver.findElement(By.cssSelector(".message-intro-bar>h3>span")).getText();
			Assert.assertEquals(expectedTemplateName, actualTemplateName);
			
	  }
	 
	
	 
}