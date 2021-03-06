package testsuite;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import resources.Utilities;

public class ShipmentDashboardTestCases extends Utilities  {
	

	 ImportCargoLogin objImportCargoLogin;
	 ShipmentSummaryDashboard objShipmentSummaryDashboard;
	 ActionsOnShipments objActionsOnShipments;
	 ShipmentDetails objShipmentDetails;
	 
	 boolean bol = true;
	 String previousRunTime, previousEnv, previousGRIDindicator;
	 boolean AllpreviousParamsMatch;

		 
	 @Test(priority = 1, enabled = false) //Verify Actions Drop Down Menu
	 public void VerifyActionsDropDownMenu(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 objShipmentSummaryDashboard.clickOnBillHoldTab();
		 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
		 
		 
		 explicitWaitwaitForElementToBecomeInvisible("xpath", "//button[@title='Actions']", 3);
		 Reporter.log("Actions Drop Down Menu Functionality Has been verified");
	 }
	 
	 
	 @Test(priority = 1, enabled = false) //Verify Shipment Summary Views And Columns
	 public void verifyShipmentSummaryViewsAndColumns(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 
		 for(int parentLoop = 0; parentLoop < 3; parentLoop++) { //Will Iterate through the HotList, Bill Hold and Entry Hold tabs
			 
			 
			 objShipmentSummaryDashboard.grabCurrentlyDisplayedTabFromDashboard();
			 objShipmentSummaryDashboard.clickOnTabThatIsNotCurrent();
			 
			 
			 for(int childLoop = 0; childLoop < 4; childLoop++) { //Loop through 4 different views
				 
				 objShipmentSummaryDashboard.grabCurrentViewType();
				 objShipmentSummaryDashboard.clickOnViewTypeDropDownMenu();
				 objShipmentSummaryDashboard.selectViewTypeThatIsNotCurrent();
				 objShipmentSummaryDashboard.grabCurrentViewType();
			 
				 List<WebElement> ColumnNames = objShipmentSummaryDashboard.grabAllColumnNameFromDashboardTable();
				 for (WebElement column : ColumnNames) {
					 ChildViewInc++;
					 Utilities.getXMLAppDataColumnInformation();
					 
					 int  y = column.getText().compareToIgnoreCase(tmpColumn);
					 boolean failTestCase = false;
					 
					 if (y != 0) {
							if (tmpColumn.endsWith("Sort Ascending")) {
								//ignore those that have sorting
							} else {
								failTestCase = true;
								log.error("The column: " + tmpColumn + 
										" does not match what is currently displayed in the application which is " + 
										 column.getText());
								Assert.assertEquals(failTestCase, false);
							}
						}
				 }
				 
				 ChildViewInc = 0;
				 log.info("Child View: " + childLoop + " Executed!!");
			 }
			  
			 log.info("Tab: " + parentLoop + " Executed!!");
		 }
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 Reporter.log("Successfully Verified all Shipment Columns in all view types");
	 }
	 
	 @Test(priority = 0, enabled = true) // Verify Tooltip Functionality
	 public void verifyTooltipFunctionality(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
/*		 //Hotlist Tab
		 objShipmentSummaryDashboard.clickOnHotListTab();
		 objShipmentSummaryDashboard.checkThatHotlistTypeMessageisDisplayed();
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 objShipmentSummaryDashboard.clickOnReviewShipmentOption();
		 objActionsOnShipments.checkShipmentReviewPopup();*/
		 

		 
		 //Page check for Shipment Dashboard
		 explicitWaitFindElement("id", "shipment-number", 10);
		 String shipmentId = driver.findElement(By.id("shipment-number")).getText();
		 Assert.assertEquals(driver.getTitle(), "Shipment " + shipmentId + " Details");
		 
	 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 		 
		 Reporter.log("Tooltips Successfully Verified");
	 } // End of verifyToolTipsFunctionality
	 
	 
	 @Test(priority = 1, enabled = false) //Verify Filter Functionality
	 public void verifyFilterFunctionality() throws Exception{
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 
		 for(int parentLoop = 0; parentLoop < 3; parentLoop++) { //Will Iterate through the HotList, Bill Hold and Entry Hold tabs
			 
			 if (parentLoop == 0) {
				 objShipmentSummaryDashboard.clickOnHotListTab();
				 objShipmentSummaryDashboard.checkThatHotlistTypeMessageisDisplayed();
				 objShipmentSummaryDashboard.validateHotlistRecordCount();
			 
			 } else if (parentLoop == 1) {
				 objShipmentSummaryDashboard.clickOnBillHoldTab();
				 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
				 objShipmentSummaryDashboard.validateBillHoldRecordCount();
			
			 } else {
				 objShipmentSummaryDashboard.clickOnEntryHoldTab();
				 objShipmentSummaryDashboard.checkThatEntryHoldTypeMessageisDisplayed();
				 objShipmentSummaryDashboard.validateEntryHoldRecordCount();
			 }
			    
			     objShipmentSummaryDashboard.MOTValidation();
			 
		 }
	 }
	 
	 @Test(priority = 0, enabled = false) // Verify Review Shipments Functionality
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
			 
			 //Set Explicit and Implicit Wait Statements, Login to Test App
			 manageDriverOptionsAndLoginToApp(RunTimeEnv, RunType, RunTimeBrowser);
			  
			//Login to Import Cargo UI	
		     objImportCargoLogin = new ImportCargoLogin(driver);
		     explicitWaitFindElement("id", "testUserFilter", 10);
		     
			 objImportCargoLogin.filterTestAccount();
			 explicitWaitFindElement("cssSelector", "a[data-username='HHH4001']", 10);
			 
			 objImportCargoLogin.clickOnFilteredTestAccount();
			 objImportCargoLogin.verifyLoginResponse();
			 
			 explicitWaitwaitForElementToBeClickable("linkText", "Import Cargo", 5);
			 objImportCargoLogin.clickOnImportCargoLink();
			 Assert.assertEquals("Import Cargo Shipment Dashboard", driver.getTitle());
			 Reporter.log("Import Cargo Dashboard Page has displayed");
			 
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
				 
				 manageDriverOptionsAndLoginToApp(RunTimeEnv, RunType, RunTimeBrowser);
				  
				//Login to Import Cargo UI	
			     objImportCargoLogin = new ImportCargoLogin(driver);
			     explicitWaitFindElement("id", "testUserFilter", 10);
			     
				 objImportCargoLogin.filterTestAccount();
				 explicitWaitFindElement("cssSelector", "a[data-username='HHH4001']", 10);
				 
				 objImportCargoLogin.clickOnFilteredTestAccount();
				 objImportCargoLogin.verifyLoginResponse();
				 
				 explicitWaitwaitForElementToBeClickable("linkText", "Import Cargo", 5);
				 objImportCargoLogin.clickOnImportCargoLink();
				 Assert.assertEquals("Import Cargo Shipment Dashboard", driver.getTitle());
				 Reporter.log("Import Cargo Dashboard Page has displayed");
			
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
		 
		 if (result.getStatus() == ITestResult.FAILURE) {
			  Utilities.closeAllWindowsTabsExceptForParent();
		      log.error("Test Case: " + RunTimeTestCase + " has Failed! That's Unfortunate!");
		      
		      if(valueForScenarioSelection.equalsIgnoreCase("On")){
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