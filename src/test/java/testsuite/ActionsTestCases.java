package testsuite;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
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
import pageobjects.ExamFindings;
import pageobjects.ImportCargoLogin;
import pageobjects.ShipmentDetails;
import pageobjects.ShipmentSummaryDashboard;
import resources.GetClassName;
import resources.Retry;
import resources.Utilities;

public class ActionsTestCases extends Utilities  {
	

	 ImportCargoLogin objImportCargoLogin;
	 ShipmentSummaryDashboard objShipmentSummaryDashboard;
	 ActionsOnShipments objActionsOnShipments;
	 ShipmentDetails objShipmentDetails;
	 //TestDataManager objTestDataManager = new TestDataManager();
	 CreateShipments objCreateShipments = new CreateShipments();
	 ExamFindings objExamFindings;
	 
	 boolean bol = true;
	 String previousRunTime, previousEnv, previousGRIDindicator;
	 boolean allpreviousParamsMatch;

	
	 @Test(enabled = false)
	 public void GRIDTest1(){ //test a scenario passing.
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 boolean GRID = true;
		 
		 Assert.assertTrue(GRID);
		 System.out.println("Console output for GRID Test 1");
		 
	 }
	 
	 @Test(enabled = false)
	 public void GRIDTest2(){ //test another scenario passing.
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 boolean GRID = true;
		 
		 Assert.assertTrue(GRID);
		 System.out.println("Console output for GRID Test 2");
		 
	 }
	 
	 
	 @Test(enabled = false)
	 public void GRIDTest3(){ //test a scenario failing.
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 Assert.fail();
		 System.out.println("Console output for GRID Test 3");
		 
	 }
	 
	 
	 @Test (priority = 0, enabled = false, retryAnalyzer = Retry.class) //Place Post Bill Hold 
	 public void placePostBillHold () { 	
			
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 //objCreateShipments.createSimpleOceanBolaShipment();
		 
		 //establishOracleConnection();
		 
		 //runAndVerifyCustomSQLQuery("select * from shipment where scac = 'OCEA' and bill_nbr = '99917306'", "SHPMT_ID", "5008409126", "");
		 
		 //closeConnections();
		 
		 objShipmentSummaryDashboard.clickOnBillHoldTab();
		 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
		 
		 objShipmentSummaryDashboard.grabRunTimeBillNumberFromSummaryDashboard();
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 objShipmentSummaryDashboard.clickOnPostBillHoldOption();
		
		 
		 //Page Check
		 objShipmentSummaryDashboard.verifyThatBillHoldTypePopupIsDisplayed();
		 
		 //Fill out and close the Bill Hold popup box
		 objActionsOnShipments.clearQuantityHeldField();
		 objActionsOnShipments.fillQuantityHeldField("100");
		 objActionsOnShipments.clickOnHoldTypDropDownMenu();
		 objActionsOnShipments.selectHoldTypeFromHoldTypeMenu();
		 objActionsOnShipments.clickOnHoldReasonDropDownMenu();
		 objActionsOnShipments.selectHoldReasonType("ACE Truck Processing");
		 objActionsOnShipments.clickOnHoldReasonDropDownMenu();
		 objActionsOnShipments.fillATStargetReportCommentsFieldBox("This is an Automated script to comment ATS Target");
		 objActionsOnShipments.fillCBPCommentsFieldBox("This is an Automated script to comment regarding CBP");
		 objActionsOnShipments.fillCommentsToCarrierFieldBox("This is an automated script to comment to the carrier");
		 objActionsOnShipments.clickOnCloseButton();
		 Reporter.log("A Bill is successfully placed on Bill Hold");
		 
	  }
	 
	 @Test(priority = 1, enabled = false) //Verify Bill Hold Reset 
	 public void verifyBillHoldReset() {
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 objShipmentSummaryDashboard.clickOnBillHoldTab();
		 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
		 
		 objShipmentSummaryDashboard.grabRunTimeBillNumberFromSummaryDashboard();
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 objShipmentSummaryDashboard.clickOnPostBillHoldOption();
		 
		//Page Check
		 objShipmentSummaryDashboard.verifyThatBillHoldTypePopupIsDisplayed();
		 
		 //Fill out and close the Bill Hold popup box
		 objActionsOnShipments.clearQuantityHeldField();
		 objActionsOnShipments.fillQuantityHeldField("100");
		 objActionsOnShipments.clickOnHoldTypDropDownMenu();
		 objActionsOnShipments.selectHoldTypeFromHoldTypeMenu();
		 objActionsOnShipments.clickOnHoldReasonDropDownMenu();
		 objActionsOnShipments.selectHoldReasonType("Operation-HQ", "Operation-PGA/OGA", "Operation-Port");
		 objActionsOnShipments.clickOnHoldReasonDropDownMenu();
		 
		 objActionsOnShipments.fillInOpsNameForHQ();
		 objActionsOnShipments.fillInOpsNameForPGA_OGA();
		 objActionsOnShipments.fillInOpsNameForPort();
		 
		 objActionsOnShipments.fillATStargetReportCommentsFieldBox("This is an Automated script to comment ATS Target");
		 objActionsOnShipments.fillCBPCommentsFieldBox("This is an Automated script to comment regarding CBP");
		 objActionsOnShipments.fillCommentsToCarrierFieldBox("This is an automated script to comment to the carrier");
		 objActionsOnShipments.clickOnResetButton();
		 
		 objActionsOnShipments.verifyThatOpsNameforHQisInvisible();
		 objActionsOnShipments.verifyThatOpsNameforPGA_OGAisInvisible();
		 objActionsOnShipments.verifyThatOpsNameforPortisInvisible();
		
		 objActionsOnShipments.clickOnCloseButton();
		 Reporter.log("Post Bill Form Filled out and Reset Functionality Works As Expected");
	 }
	 
	 @Test(priority = 1, enabled = false) //Place Post Intensive Entry Hold  
	 public void placePostIntensiveEntryHold(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 objShipmentSummaryDashboard.clickOnEntryHoldTab();
		 objShipmentSummaryDashboard.checkThatEntryHoldTypeMessageisDisplayed();
		 
		 objShipmentSummaryDashboard.grabRunTimeEntryNumberFromSummaryDashboard();
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 objShipmentSummaryDashboard.clickOnpostIntensiveEntryHoldOption();
		 
		//Page Check
		 objShipmentSummaryDashboard.verifyThatEntryHoldTypePopupIsDisplayed();
		 
		 //Fill out and close the Entry Hold popup box
		 objActionsOnShipments.clickOnHoldReasonDropDownMenu();
		 objActionsOnShipments.selectHoldReasonType("ACE Truck Processing");
		 objActionsOnShipments.clickOnHoldReasonDropDownMenu();
		 
		 objActionsOnShipments.clickOnDocumentsRequiredDropDownMenu();
		 objActionsOnShipments.selectHoldReasonType("Certificates (vehicle title, etc.)");
		 objActionsOnShipments.clickOnDocumentsRequiredDropDownMenu();
		 
		 objActionsOnShipments.fillCBPCommentsFieldBox("This is an Automated script to comment regarding CBP");
		 objActionsOnShipments.fillInCommentsToBrokerFieldBox("This is an Automated script to comment ATS Broker");

		 objActionsOnShipments.clickOnCloseButton();
		 Reporter.log("Post Intensive Entry Hold Form Has been submitted");
	 }
	 
	 @Test(priority = 1, enabled = false) //Place Post Document Review Entry Hold
	 public void placePostDocumentReviewEntryHold(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 objShipmentSummaryDashboard.clickOnEntryHoldTab();
		 objShipmentSummaryDashboard.checkThatEntryHoldTypeMessageisDisplayed();
		 
		 objShipmentSummaryDashboard.grabRunTimeEntryNumberFromSummaryDashboard();
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 objShipmentSummaryDashboard.clickOnPostDocumentReviewEntryHoldOption();
		 
		//Page Check
		 objShipmentSummaryDashboard.verifyThatDocumentsReviewPopupIsDisplayed();
		 
		 //Fill out and close the Post Document Review on Entry popup box
		 objActionsOnShipments.clickOnHoldReasonDropDownMenu();
		 objActionsOnShipments.selectHoldReasonType("ACE Truck Processing");
		 objActionsOnShipments.clickOnHoldReasonDropDownMenu();
		 
		 objActionsOnShipments.clickOnDocumentsRequiredDropDownMenu();
		 objActionsOnShipments.selectHoldReasonType("Certificates (vehicle title, etc.)");
		 objActionsOnShipments.clickOnDocumentsRequiredDropDownMenu();
		 
		 objActionsOnShipments.fillCBPCommentsFieldBox("This is an Automated script to comment regarding Review on Entry");
		 objActionsOnShipments.fillInCommentsToBrokerFieldBox("This is an Automated script regarding Review on Entry");

		 objActionsOnShipments.clickOnCloseButton();
		 Reporter.log("Post Documents Review On Entry Has been submitted");
	 }
	 
	 @Test(priority = 1, enabled = false) //Place Post Entry Override to General
	 public void placePostEntryOverrideToGeneral(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 objShipmentSummaryDashboard.clickOnEntryHoldTab();
		 objShipmentSummaryDashboard.checkThatEntryHoldTypeMessageisDisplayed();
		 
		 objShipmentSummaryDashboard.grabRunTimeEntryNumberFromSummaryDashboard();
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 objShipmentSummaryDashboard.clickOnPostEntryOverrideToGeneralOption();
		 
		//Page Check
		 objShipmentSummaryDashboard.verifyThatPostOverrideToGeneralOnEntryPopupIsDisplayed();
		 
		 //Fill out and close the Post Override To General on Entry popup box
		 objActionsOnShipments.clickOnOverridetoGeneralReasonsDropDownMenu();
		 objActionsOnShipments.selectHoldReasonType("Released");
		 
		 objActionsOnShipments.fillCBPCommentsFieldBox("This is an Automated script to comment regarding Override To General");
		 objActionsOnShipments.fillInCommentsToBrokerFieldBox("This is an Automated script regarding Comments To Broker");

		 objActionsOnShipments.clickOnCloseButton();
		 Reporter.log("Post Override To General On Entry Has been submitted");
	 }
	 
	 @Test(priority = 0, enabled = false) //Verify Review Shipments Functionality
	 public void verifyReviewShipmentsFunctionality(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 //Hotlist Tab
		 objShipmentSummaryDashboard.clickOnHotListTab();
		 objShipmentSummaryDashboard.checkThatHotlistTypeMessageisDisplayed();
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 objShipmentSummaryDashboard.clickOnReviewShipmentOption();
		 objActionsOnShipments.checkShipmentReviewPopup();
		 
		 //Hotlist Shipment Review Message
		 objActionsOnShipments.verifySummaryDashboardShipmentReviewMessage();
		 //Checking Okay button is enable to click
		 objActionsOnShipments.clickOnOkayButton();
		 
		 //Bill Hold Shipment Review
		 objShipmentSummaryDashboard.clickOnBillHoldTab();
		 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 objShipmentSummaryDashboard.clickOnReviewShipmentOption();
		 objActionsOnShipments.checkShipmentReviewPopup();
		//Bill Hold Shipment Review Message
		 objActionsOnShipments.verifySummaryDashboardShipmentReviewMessage();
		 //Checking Okay button is enable to click
		 objActionsOnShipments.clickOnOkayButton();
		 	 
		 //Entry Hold Shipment Review
		 objShipmentSummaryDashboard.clickOnEntryHoldTab();
		 objShipmentSummaryDashboard.checkThatEntryHoldTypeMessageisDisplayed();
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 objShipmentSummaryDashboard.clickOnReviewShipmentOption();
		 //Entry Hold Shipment Review Message
		 objActionsOnShipments.verifySummaryDashboardShipmentReviewMessage();
		 objActionsOnShipments.clickOnOkayButton();
		 
		//Review Shipment from detail page
		 objShipmentSummaryDashboard.clickOnFirstShipmentInSummaryDashboardTable();
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		 //Page check for Shipment Dashboard
		 explicitWaitFindElement("id", "shipment-number", 10);
		 String shipmentId = driver.findElement(By.id("shipment-number")).getText();
		 Assert.assertEquals(driver.getTitle(), "Shipment " + shipmentId + " Details");
		 
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Detail");
		 objShipmentSummaryDashboard.clickOnReviewShipmentOption();
		 //Checking Mark Shipment Reviewed Popup
		 objActionsOnShipments.verifySummaryDetailShipmentReviewMessage();
		// objShipmentSummaryDashboard.grabRunTimeReviewShipmentNumberFromSummaryDashboard();
		// SwitchOrCloseWindowTab(true, 2, false, 0);
		 //objActionsOnShipments.clickOnReviewButton();
		 //objActionsOnShipments.clickOnCloseButton();
		 objActionsOnShipments.clickOnReviewButton();
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 		 
		 Reporter.log("Shipment(s) from the Summary and Detail Pages reviewed Successfully");
	 }

	 /////////////////////////////////////////////////////////////DO NOT INSERT TESTS BELOW THIS LINE////////////////////////////////////////////////////////////
	 @Parameters({"Run-Type" })
	 @BeforeSuite
	  public void beforeSuite(String RunType) {
		 
		 
	  }
	 
	 @Parameters({ "Run-Type" })
	 @AfterSuite
	  public void afterSuite(String RunType) {
		 
		 if(skipCondition == false){
			 tearDownRunTimeBrowsers();
			 
			 if(RunType.contentEquals("Local")){
				 deleteLogsOlderThanNDays();
				 deleteScreenshotsOlderThanNDays();
			 }
		
			 skipCondition = true;
		 }
	  }
	 
	 
	 @BeforeTest
	  public void beforeTest() {
		 
	 }
	 
	 @AfterTest
	  public void afterTest(){
		
		 
	 }
	 
	 @Parameters({ "Browser-Type", "Run-Type", "RunTime-Environment"})
	 @BeforeClass
	  public void beforeClass(String RunTimeBrowser, String RunType, String RunTimeEnv) {
		 
			Utilities.setRunType(RunType);
			Utilities.setRunTimeEnv(RunTimeEnv);
			
			String className = GetClassName.getCallerClassName();
			className = className.substring(10);
			tmp3 = className;
			
		 
		 if(!valueForScenarioSelection.equalsIgnoreCase("On")){
				
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
			 
			 try{
				 //Set Explicit and Implicit Wait Statements, Login to Test App
				 manageDriverOptionsAndLoginToApp(RunTimeEnv);
				  
				//Login to Import Cargo UI	
			     objImportCargoLogin = new ImportCargoLogin(driver);
			     explicitWaitFindElement("id", "testUserFilter", 10);
			     
				 objImportCargoLogin.filterTestAccount();
				 explicitWaitFindElement("cssSelector", "a[data-username='HHH4001']", 10);
				 
				 objImportCargoLogin.clickOnFilteredTestAccount();
				 //objImportCargoLogin.verifyLoginResponse();
				 explicitWaitwaitForElementToBeVisible("linkText", "Import Cargo", 10);
				 
				 explicitWaitwaitForElementToBeClickable("linkText", "Import Cargo", 5);
				 objImportCargoLogin.clickOnImportCargoLink();
				 Assert.assertEquals("Import Cargo Shipment Dashboard", driver.getTitle());
				 Reporter.log("Import Cargo Dashboard Page has displayed");
			 }catch(Exception e){
				 takeScreenshot();
				 System.out.println(e);
				 
			 }
		}else{
			FolderCount = 1;
		}
		 
	 }
	 
	 @AfterClass
	  public void afterClass(){
		
	 }
	 
	 @Parameters({ "Browser-Type", "Run-Type", "RunTime-Environment"})
	 @BeforeMethod
	  public void beforeMethod(String RunTimeBrowser, String RunType, String RunTimeEnv, Method method) {
		 
		 
		 /////////////////////Getting RunTime Values for Scenario Selection Forms//////////////////////
		 if(valueForScenarioSelection.equalsIgnoreCase("On")){
			 
			 if(varRetryMechanism == true){
	    		  if (runningReTryCount == MaxRetryCount) {
	    			  FolderCount++;
	    			  Utilities.setRetryAnalyzerAsExecuted(false); //Reset back to false;
	    	        }
			 }
			 
			 try {
				Utilities.getRunTimeScenarioStatus(FolderCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			 
			 if(FolderCount > 1 && PreviousTestCaseHasFailed == false | (beforeMethodExecuted = true)) {
				 if(PreviousRunTimeEnvFromJsonFile.equalsIgnoreCase(RunTimeEnvFromJsonFile) && PreviousRunTimeBrowserFromJsonFile.equalsIgnoreCase(RunTimeBrowserFromJsonFile))  {
					 skipLogin = true;
				 }else{
					 skipLogin = false;
				 }
				 
//				 if(PreviousRunTimeBrowserFromJsonFile.equalsIgnoreCase(RunTimeBrowserFromJsonFile)){
//					 skipLogin = true;
//				 }else{
//					 skipLogin = false;
//				 }
			 
			 }
			 
		 }
		 //////////////////////////////////////////////////////////////////////////////////////////////
		 
		 String RunTimeTestCase =  method.getName();
		 Utilities.setScenarioName(RunTimeTestCase);
		 ScenarioName = Utilities.getScenarioName();
		 ScenarioName = ScenarioName.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
		 
		 
		 if(valueForScenarioSelection.equalsIgnoreCase("On")){
			
			 if(skipLogin == false) {
				 
				 if(FolderCount > 1 && closeAllWindowsBetweenRuns.equalsIgnoreCase("On")){
					 Utilities.closeOpenedRuntimeBrowsers();
				 }
				 
				 //Supports Cross Browser and GRID test setup////////////////////////////////////////////////////////////////////////
				 if(RunTimeBrowserFromJsonFile.contentEquals("Internet Explorer")){
					 
				 	try {
						internetExplorerBrowserSettings(RunTimeRemoteWebDriverSettings);
					} catch (Exception e) {
						System.out.println(e);
						e.printStackTrace();
					}
					 			 
				 }else if(RunTimeBrowserFromJsonFile.contentEquals("FireFox")){
					 
					 try {
						fireFoxDriverBrowserSettings(RunTimeRemoteWebDriverSettings);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e);
					}
					 
					 
				 }else if(RunTimeBrowserFromJsonFile.contentEquals("Google Chrome")){
					 
					 try {
						googleChromeDriverSettings(RunTimeRemoteWebDriverSettings);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e);
					}
					 
				 }else{ //If an invalid or blank RunTimeBrowser parameter is specified, by default, Internet Explorer will be used.
					 RunTimeBrowserFromJsonFile = "Internet Explorer";
					 
					 try {
							internetExplorerBrowserSettings(RunTimeRemoteWebDriverSettings);
						} catch (Exception e) {
							System.out.println(e);
							e.printStackTrace();
						}
				 }
				 
				 //Set Explicit and Implicit Wait Statements, Login to Test App
				 RunTimeEnv = RunTimeEnvFromJsonFile; 
				 RunTimeBrowser = RunTimeBrowserFromJsonFile;
				 Utilities.setRunTimeEnv(RunTimeEnv);
				 
				 try{
					 manageDriverOptionsAndLoginToApp(RunTimeEnv);
					  
					//Login to Import Cargo UI	
				     objImportCargoLogin = new ImportCargoLogin(driver);
				     explicitWaitFindElement("id", "testUserFilter", 10);
				     
					 objImportCargoLogin.filterTestAccount();
					 explicitWaitFindElement("cssSelector", "a[data-username='HHH4001']", 10);
					 
					 objImportCargoLogin.clickOnFilteredTestAccount();
					 //objImportCargoLogin.verifyLoginResponse();
					 explicitWaitwaitForElementToBeVisible("linkText", "Import Cargo", 10);
					 
					 explicitWaitwaitForElementToBeClickable("linkText", "Import Cargo", 5);
					 objImportCargoLogin.clickOnImportCargoLink();
					 Assert.assertEquals("Import Cargo Shipment Dashboard", driver.getTitle());
					 Reporter.log("Import Cargo Dashboard Page has displayed");
				 }catch(Exception e){
					 takeScreenshot();
					 System.out.println(e);
				 }
			 }
			 
			 Utilities.modifyRunTimeStatus(RunTimeScenarioName, FolderCount, "Started");
			 beforeMethodExecuted = true;
		}
		
	     createNewLogFile(RunTimeBrowser, RunType, RunTimeEnv, RunTimeTestCase);
	     log.info("Started To Run Test Case: " + RunTimeTestCase + " using the " + RunTimeBrowser + " on " + RunType);
	     
	     Utilities.getWindowHandle(1);
	  }
	 
	 @Parameters({ "Browser-Type", "Run-Type", "RunTime-Environment" })
	 @AfterMethod
	  public void afterMethod(String RunTimeBrowser, String RunType, String RunTimeEnv, Method method, ITestResult result) {
		 String RunTimeTestCase =  method.getName();
		 
		///Doesn't hurt to double check resources are freed up.///////////////
		 try{
			if(stmt!=null)
				stmt.close();

			if(myCon!=null)
				myCon.close();
		 }catch(Exception SQLClose) {
				System.out.println("There are Open Connecitons in the DB");
			}
		 /////////////////////////////////////////////////////////////////////
			
		 if (result.getStatus() == ITestResult.FAILURE) {
			  Utilities.closeAllWindowsTabsExceptForParent();
		      log.error("Test Case: " + RunTimeTestCase + " has Failed! That's Unfortunate!");
		      
		      if(valueForScenarioSelection.equalsIgnoreCase("On")){
		    	  
		    	  PreviousRunTimeEnvFromJsonFile = RunTimeEnvFromJsonFile;
				  PreviousRunTimeBrowserFromJsonFile = RunTimeBrowserFromJsonFile;
		    	  PreviousTestCaseHasFailed = true;
		    	  skipLogin = true;
		    	  Utilities.modifyRunTimeStatus(RunTimeScenarioName, FolderCount, "Done");
			  }
		      
		  }else{
			  
			  if(valueForScenarioSelection.equalsIgnoreCase("On")){
				
					 PreviousRunTimeEnvFromJsonFile = RunTimeEnvFromJsonFile;
					 PreviousRunTimeBrowserFromJsonFile = RunTimeBrowserFromJsonFile;
					 Utilities.modifyRunTimeStatus(RunTimeScenarioName, FolderCount, "Done");
					 FolderCount++;
					 PreviousTestCaseHasFailed = false;
					
					 
			  }
			  
		  }
		
		 driver.navigate().refresh();
		 log.info("Refreshed Page for Next Test Case to Run");
		 log.info("Completed Running Test Case: " + RunTimeTestCase + " using the " + RunTimeBrowser + " on " + RunType);
		 
		 
	 }
	 
}