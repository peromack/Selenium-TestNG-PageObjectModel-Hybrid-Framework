package testsuite;

import java.lang.reflect.Method;

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
import resources.Utilities;

public class ExamFindingsTestCase extends Utilities  {
	

	 ImportCargoLogin objImportCargoLogin;
	 ShipmentSummaryDashboard objShipmentSummaryDashboard;
	 ActionsOnShipments objActionsOnShipments;
	 ExamFindings objExamFindings;
	 
	 boolean bol = true;
	 String previousRunTime, previousEnv, previousGRIDindicator;
	 boolean allpreviousParamsMatch;

	
	
	 
	 @Test //Sample test for Exam Findings
	 public void examFindingsSampleTtest(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 objExamFindings = new ExamFindings(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 objShipmentSummaryDashboard.clickOnBillHoldTab();
		 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
		 
		 objShipmentSummaryDashboard.clickOnselectShipmentCheckBox();
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Summary");
		 
		 objShipmentSummaryDashboard.clickOnEnterExamFindingsButton();
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		 //Exm findings start here
		 objExamFindings.verifyThatExamFindigsPageHasLoaded();
		 objExamFindings.clickWhereExamWasDoneDropDownMenu();
		 objExamFindings.selectFirstPortWhereExamined();
		 objExamFindings.clickFirmsCodeDropDownMenu();
		 objExamFindings.selectFirstFirmCodeFromList();
		 
		 objExamFindings.clickExamDateBox();
		 objExamFindings.selectTodaysDate();
		 objExamFindings.clickExaminerDropDownMenu();
		 objExamFindings.selectExaminerFromList();
		 //objExamFindings.clickExaminerDropDownMenu();
		 
		 objExamFindings.clickExamTypeDropDownMenu();
		 objExamFindings.selectFirstExamTypeFromList();
		 objExamFindings.clickExamTypeDropDownMenu();
		 
		 objExamFindings.clickExamFindingsTab();
		 objExamFindings.verifyThatExamFindingsTabDisplays();
		 
		 objExamFindings.clickDiscrepancyButton();
		 
		 objExamFindings.clickContainerDropDownMenu();
		 objExamFindings.selectFirstContainerFromList();
		 objExamFindings.clickContainerDropDownMenu();
		
		 objExamFindings.clickCancelButton();
		 
		 switchOrCloseWindowTab(true, 1, false, 0);
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
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
				 manageDriverOptionsAndLoginToApp(RunTimeEnv, RunType, RunTimeBrowser);
				  
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
					 manageDriverOptionsAndLoginToApp(RunTimeEnv, RunType, RunTimeBrowser);
					  
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