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
	
	 String billNum;
	 String houseBillNum;
	 String scac;
	 String entryNum;
	 
	 
	
	 public void createSimpleOceanBolaShipment () throws Exception { 
			
		 objDataBot = new DataBot(driver);
		 env = getRunTimeEnv();
		 
		 
		 navigateToDataBot("Ocean");
		 
		 
		 
		 selectTemplateForDataBot("Ocean BOLA Simple");
		 
		 
		 //Fill out respective fields
		 objDataBot.setScac("OCEA");
		 billNum = objDataBot.setLadingNumber(""); 
		 objDataBot.setCurrentTime();
		 objDataBot.setProcessDate();
		 objDataBot.setArrivalDate();
		 objDataBot.setDate();
		 //objDataBot.setPort("1429");
		 //objDataBot.setShipperName("Some Testing Shipper Name Company");
		 //objDataBot.setShipperAddress("123 Fake Address High Street");
		 //objDataBot.setShipperGeo("27260 NC US");
		 //objDataBot.setConveyanceName("Some Conveyance Testing Name");
		 //objDataBot.setConveyanceAddress("456 Another Fake Address");
		 //objDataBot.setConveyanceGeo("27260 NC US");
		 
		
		//Fill out respective destinations if needed
		 if(env.equalsIgnoreCase("DEV")){
			 objDataBot.clickOnDestinations();
			 
			 objDataBot.setEndPointToDev();
			 //objDataBot.setDestinationName("Ocean BOL SAT Queue");
			 //objDataBot.setHostName("ats-s01.cbp.dhs.gov");
			 //objDataBot.setQueueManager("QM_ATSS01");
			 //objDataBot.setQueueChannel("QM_ATSS01.ATSN");
		 }
		 
		 objDataBot.clickOnSendMessageButton();
		 objDataBot.verifyMessageWebConfirmation();		 
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 
		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 establishOracleConnection();
		 try {
			//runAndVerifyCustomSQLQuery("select * from shipment where scac = 'OCEA' and bill_nbr = '" + billNum + "'", "SHPMT_ID", "5008409126", "");
			 runSqlQuery("select * from shipment where scac = 'OCEA' and bill_nbr = '" + billNum + "'", "SHPMT_ID");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 closeConnections();
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
	  }
	 
	 public void createSimpleRailBolaShipment () throws Exception { 
			
		 objDataBot = new DataBot(driver);
		 env = getRunTimeEnv();
		 
		 
		 navigateToDataBot("Rail");
		 
		 
		 
		 selectTemplateForDataBot("Rail BOLA Inbond Hazmat");
		 
		 
		 //Fill out respective fields
		 objDataBot.setScac("RAIL");
		 billNum = objDataBot.setLadingNumber(""); 
		 objDataBot.setCurrentTime();
		 objDataBot.setProcessDate();
		 objDataBot.setArrivalDate();
		 objDataBot.setDate();
		 //objDataBot.setPort("1429");
		 //objDataBot.setShipperName("Some Testing Shipper Name Company");
		 //objDataBot.setShipperAddress("123 Fake Address High Street");
		 //objDataBot.setShipperGeo("27260 NC US");
		 //objDataBot.setConveyanceName("Some Conveyance Testing Name");
		 //objDataBot.setConveyanceAddress("456 Another Fake Address");
		 //objDataBot.setConveyanceGeo("27260 NC US");
		 
		
		//Fill out respective destinations if needed
		 if(env.equalsIgnoreCase("DEV")){
			 objDataBot.clickOnDestinations();
			 
			 objDataBot.setEndPointToDev();
			 //objDataBot.setDestinationName("Ocean BOL SAT Queue");
			 //objDataBot.setHostName("ats-s01.cbp.dhs.gov");
			 //objDataBot.setQueueManager("QM_ATSS01");
			 //objDataBot.setQueueChannel("QM_ATSS01.ATSN");
		 }
		 
		 objDataBot.clickOnSendMessageButton();
		 objDataBot.verifyMessageWebConfirmation();		 
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 
		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 establishOracleConnection();
		 try {
			//runAndVerifyCustomSQLQuery("select * from shipment where scac = 'OCEA' and bill_nbr = '" + billNum + "'", "SHPMT_ID", "5008409126", "");
			 runSqlQuery("select * from shipment where scac = 'RAIL' and bill_nbr = '" + billNum + "'", "SHPMT_ID");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 closeConnections();
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
	  }
	 
	 public void createM1AirShipment () throws Exception { 
			
		 objDataBot = new DataBot(driver);
		 env = getRunTimeEnv();
		 
		 
		 navigateToDataBot("Air");
		 
		 
		 
		 selectTemplateForDataBot("M1 Air Generic Bola");
		 
		 
		 //Fill out respective fields
		 objDataBot.setScac("ABE");
		 billNum = objDataBot.setLadingNumber(""); 
		 //objDataBot.setCurrentTime();
		 objDataBot.setProcessDate();
		 objDataBot.setArrivalDate();
		 objDataBot.setDate();
		 //objDataBot.setPort("1429");
		 //objDataBot.setShipperName("Some Testing Shipper Name Company");
		 //objDataBot.setShipperAddress("123 Fake Address High Street");
		 //objDataBot.setShipperGeo("27260 NC US");
		 //objDataBot.setConveyanceName("Some Conveyance Testing Name");
		 //objDataBot.setConveyanceAddress("456 Another Fake Address");
		 //objDataBot.setConveyanceGeo("27260 NC US");
		 
		
		//Fill out respective destinations if needed
		 if(env.equalsIgnoreCase("DEV")){
			 objDataBot.clickOnDestinations();
			 
			 objDataBot.setEndPointToDev();
			 //objDataBot.setDestinationName("Ocean BOL SAT Queue");
			 //objDataBot.setHostName("ats-s01.cbp.dhs.gov");
			 //objDataBot.setQueueManager("QM_ATSS01");
			 //objDataBot.setQueueChannel("QM_ATSS01.ATSN");
		 }
		 
		 objDataBot.clickOnSendMessageButton();
		 objDataBot.verifyMessageWebConfirmation();		 
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 
		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 establishOracleConnection();
		 try {
			//runAndVerifyCustomSQLQuery("select * from shipment where scac = 'OCEA' and bill_nbr = '" + billNum + "'", "SHPMT_ID", "5008409126", "");
			 runSqlQuery("select * from shipment where scac = 'ABE' and bill_nbr = '" + billNum + "'", "SHPMT_ID");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 closeConnections();
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
	  }
	 
	 public void createTruckShipment () throws Exception { 
			
		 objDataBot = new DataBot(driver);
		 env = getRunTimeEnv();
		 
		 
		 navigateToDataBot("Truck");
		 
		 
		 
		 selectTemplateForDataBot("Truck BOLA");
		 
		 
		 //Fill out respective fields
		 objDataBot.setScac("RFOR");
		 billNum = objDataBot.setLadingNumber("PT28"); 
		 objDataBot.setDepartureDate();
		 objDataBot.setArrivalDate();
		 objDataBot.setProcessDate();
		 
		
		//Fill out respective destinations if needed
		 if(env.equalsIgnoreCase("DEV")){
			 objDataBot.clickOnDestinations();
			 
			 objDataBot.setEndPointToDev();
			 //objDataBot.setDestinationName("Ocean BOL SAT Queue");
			 //objDataBot.setHostName("ats-s01.cbp.dhs.gov");
			 //objDataBot.setQueueManager("QM_ATSS01");
			 //objDataBot.setQueueChannel("QM_ATSS01.ATSN");
		 }
		 
		 objDataBot.clickOnSendMessageButton();
		 objDataBot.verifyMessageWebConfirmation();		 
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 
		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 establishOracleConnection();
		 try {
			//runAndVerifyCustomSQLQuery("select * from shipment where scac = 'OCEA' and bill_nbr = '" + billNum + "'", "SHPMT_ID", "5008409126", "");
			 runSqlQuery("select * from shipment where scac = 'RFOR' and bill_nbr = '" + billNum + "'", "SHPMT_ID");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 closeConnections();
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
	  }
	 
	 public void createACASShipment () throws Exception { 
			
		 objDataBot = new DataBot(driver);
		 env = getRunTimeEnv();
		 
		 
		 navigateToDataBot("Acas");
		 
		 
		 
		 selectTemplateForDataBot("ACAS Master-House Bola");
		 
		 
		 //Fill out respective fields
		 billNum = objDataBot.setAltLadingNum("AE"); 
		 houseBillNum = objDataBot.setAcasHouseLadingNumber("ACASABE");
		 objDataBot.setDate();
		 
		
		//Fill out respective destinations if needed
		 if(env.equalsIgnoreCase("DEV")){
			 objDataBot.clickOnDestinations();
			 
			 objDataBot.setEndPointToDev();
			 //objDataBot.setDestinationName("Ocean BOL SAT Queue");
			 //objDataBot.setHostName("ats-s01.cbp.dhs.gov");
			 //objDataBot.setQueueManager("QM_ATSS01");
			 //objDataBot.setQueueChannel("QM_ATSS01.ATSN");
		 }
		 
		 objDataBot.clickOnSendMessageButton();
		 objDataBot.verifyMessageWebConfirmation();		 
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 
		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 establishOracleConnection();
		 try {
			//runAndVerifyCustomSQLQuery("select * from shipment where scac = 'OCEA' and bill_nbr = '" + billNum + "'", "SHPMT_ID", "5008409126", "");
			 runSqlQuery("select * from ldr_imprt.bol where BILL_NBR = '" + billNum + "-" + houseBillNum + "'", "BILL_NBR");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 closeConnections();
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
	  }
	 
	 public void createSimplifiedEntry () throws Exception { 
			
		 objDataBot = new DataBot(driver);
		 env = getRunTimeEnv();
		 
		 
		 navigateToDataBot("Simplified_Entry");
		 
		 selectTemplateForDataBot("Simplified Entry Regular Ocean, Rail, SAT");
		 
		 //Fill out respective fields
		 entryNum = objDataBot.setEntryNum(""); 
		 //objDataBot.setScac(scac);
		 //objDataBot.setLadingNumberDoNotGenerate(billNum);
		 
		
		//Fill out respective destinations if needed
		 if(env.equalsIgnoreCase("DEV")){
			 objDataBot.clickOnDestinations();
			 
			 objDataBot.setEndPointToDev();
			 //objDataBot.setDestinationName("Ocean BOL SAT Queue");
			 //objDataBot.setHostName("ats-s01.cbp.dhs.gov");
			 //objDataBot.setQueueManager("QM_ATSS01");
			 //objDataBot.setQueueChannel("QM_ATSS01.ATSN");
		 }
		 
		 objDataBot.clickOnSendMessageButton();
		 objDataBot.verifyMessageWebConfirmation();		 
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 
		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 establishOracleConnection();
		 try {
			 //runSqlQuery("select * from shipment where FILER = 'SSH' and ENTRY_NBR = '" + entryNum + "'", "SHPMT_ID");
 			 runSqlQuery("select * from entry where FILER = 'SSH' and ENTRY_NBR = '" + entryNum + "'", "ENTRY_NBR");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 closeConnections();
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
	  }
	 
	 public void createEntrySummary () throws Exception { 
			
		 objDataBot = new DataBot(driver);
		 env = getRunTimeEnv();
		 
		 
		 navigateToDataBot("Entry_Summary");
		 
		 selectTemplateForDataBot("Entry Summary PGA");
		 
		 //Fill out respective fields
		 entryNum = objDataBot.setEntryNum("");
		 //objDataBot.setEntryNumberDoNotGenerate(EntryNbr);
		 //objDataBot.setScac(scac);
		 //objDataBot.setLadingNumberDoNotGenerate(billNum);
		 objDataBot.setPrelimEntrySummaryDate();
		 objDataBot.setEntrSumDate();
		
		//Fill out respective destinations if needed
		 if(env.equalsIgnoreCase("DEV")){
			 objDataBot.clickOnDestinations();
			 
			 objDataBot.setEndPointToDev();
			 //objDataBot.setDestinationName("Ocean BOL SAT Queue");
			 //objDataBot.setHostName("ats-s01.cbp.dhs.gov");
			 //objDataBot.setQueueManager("QM_ATSS01");
			 //objDataBot.setQueueChannel("QM_ATSS01.ATSN");
		 }
		 
		 objDataBot.clickOnSendMessageButton();
		 objDataBot.verifyMessageWebConfirmation();		 
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 
		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 establishOracleConnection();
		 try {
			 //runSqlQuery("select * from shipment where FILER = 'SSH' and ENTRY_NBR = '" + entryNum + "'", "SHPMT_ID");
 			 runSqlQuery("select * from entry where FILER = 'SSH' and ENTRY_NBR = '" + entryNum + "' and ES_LINES > 0", "ES_LINES");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 closeConnections();
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
	  }
	 
	 public void createEntrySummaryFromExistingEntry () throws Exception { 
			
		 objDataBot = new DataBot(driver);
		 env = getRunTimeEnv();
		 
		 
		 navigateToDataBot("Entry_Summary");
		 
		 selectTemplateForDataBot("Entry Summary PGA");
		 
		 //Fill out respective fields
		 //entryNum = objDataBot.setEntryNum("333");
		 objDataBot.setEntryNumberDoNotGenerate(EntryNbr);
		 //objDataBot.setScac(scac);
		 //objDataBot.setLadingNumberDoNotGenerate(billNum);
		 objDataBot.setPrelimEntrySummaryDate();
		 objDataBot.setEntrSumDate();
		
		//Fill out respective destinations if needed
		 if(env.equalsIgnoreCase("DEV")){
			 objDataBot.clickOnDestinations();
			 
			 objDataBot.setEndPointToDev();
			 //objDataBot.setDestinationName("Ocean BOL SAT Queue");
			 //objDataBot.setHostName("ats-s01.cbp.dhs.gov");
			 //objDataBot.setQueueManager("QM_ATSS01");
			 //objDataBot.setQueueChannel("QM_ATSS01.ATSN");
		 }
		 
		 objDataBot.clickOnSendMessageButton();
		 objDataBot.verifyMessageWebConfirmation();		 
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 
		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 establishOracleConnection();
		 try {
			 //runSqlQuery("select * from shipment where FILER = 'SSH' and ENTRY_NBR = '" + entryNum + "'", "SHPMT_ID");
 			 runSqlQuery("select * from entry where FILER = 'SSH' and ENTRY_NBR = '" + entryNum + "' and ES_LINES > 0", "ES_LINES");
 			 runSqlQuery("select * from ats.shipment where FILER = 'SSH' and ENTRY_NBR = '" + entryNum + "'", "SHPMT_ID");
 			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 closeConnections();
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
	  }
	 
	 
	 //////////////////////////////////////////////////REUSABLE METHODS BELOW////////////////////////////////////////////////////////////////////////////
	 public void navigateToDataBot(String selectTeam){
		 
		  
	     
	     js =(JavascriptExecutor)driver;
	 
	     //Navigate to Databot
	     js.executeScript("window.open('https://uxvnwg001a2915.sat.cbp.dhs.gov:9400/ta/cargo/import/databot/#','_blank');");
	     Utilities.switchOrCloseWindowTab(true, 2, false, 2);
	    
	     
		//Page Check
		explicitWaitwaitForElementToBeVisible("xpath", "//*[@id='content']/div/h3", 10);
		explicitWaitwaitForElementToBeVisible("id", "teamSelect", 10);
		
		
		//Select Team
		driver.findElement(By.id("teamSelect")).click();
		explicitWaitwaitForElementToBeVisible("xpath", "//select[@id='teamSelect']/*[@value='TASPD_" + selectTeam + "']", 10);
		driver.findElement(By.xpath("//select[@id='teamSelect']/*[@value='TASPD_" + selectTeam + "']")).click();
		//driver.findElement(By.id("teamSelect")).click();
		
     }
	 
	 
	 /////////////////////////////////////////////////////////////Utilities for DataBot////////////////////////////////////////////////////////////
	
	 public static void selectTemplateForDataBot(String expectedTemplateName){ //sample: //div[@id='content']//a[contains(., 'Ocean BOLA Simple')]
			
			String selectedTemplate = expectedTemplateName;
			String xpath = "//div[@id='content']//a[contains(., '" + selectedTemplate + "')]";
			explicitWaitwaitForElementToBeClickable("xpath", xpath, 10);
			System.out.println(selectedTemplate + " is now visible");
			
			driver.findElement(By.xpath(xpath)).click();
			
			//Verify that correct template has displayed
			explicitWaitwaitForElementToBeVisible("cssSelector", ".message-intro-bar>h3>span", 10);
			explicitWaitwaitForElementToBeVisible("id", "templateTabContent", 10);
			String actualTemplateName = driver.findElement(By.cssSelector(".message-intro-bar>h3>span")).getText();
			Assert.assertEquals(expectedTemplateName, actualTemplateName);
			
			
	  }
	 
	
	 
}