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
import pageobjects.TradeEntity;
import resources.GetClassName;
import resources.Utilities;

public class SmokeTest extends Utilities  {
	
	 ImportCargoLogin objImportCargoLogin;
	 ShipmentSummaryDashboard objShipmentSummaryDashboard;
	 ShipmentDetails objShipmentDetails;
	 TradeEntity objTradeEntity;
	 CreateShipments objCreateShipments = new CreateShipments();
	 ActionsOnShipments objActionsOnShipments;
	 ExamFindings objExamFindings;
	 

	
	 @Test(priority = 0)
	 public void checkDatabaseConnection(){
		 
		 try{
			 establishOracleConnection("smokeTest");
			 logOutofDb();
		 }catch(Exception test){
			 Assert.fail();
		 }
		 
	 }
	 
	 @Test(priority = 1) //Import Cargo Dashboard Page
	 public void ImportCargoDashboardPage(){
		 
		
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded(); 
		 
		 objShipmentSummaryDashboard.clickOnBillHoldTab();
		 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
		 
		 objShipmentSummaryDashboard.clickOnEntryHoldTab();
		 objShipmentSummaryDashboard.checkThatEntryHoldTypeMessageisDisplayed();
		 takeScreenshot();
	 }
	 
	 
	 @Test(priority = 2) //Verify Ocean Loader
	 public void verifyOceanLoader(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 objTradeEntity = new TradeEntity(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 try {
			objCreateShipments.createSimpleOceanBolaShipment();
			Boolean tmp = shouldTestBeFailed();
			if(tmp == true) Assert.fail();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 
	 }
	 
	 
	 @Test(priority = 3) //Verify Rail Loader
	 public void verifyRailLoader(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 objTradeEntity = new TradeEntity(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 try {
			objCreateShipments.createSimpleRailBolaShipment();
			Boolean tmp = shouldTestBeFailed();
			if(tmp == true) Assert.fail();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 
	 }
	 
	 @Test(priority = 4) //Verify M1 Air Loader
	 public void verifyM1AirLoader(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 objTradeEntity = new TradeEntity(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 try {
			objCreateShipments.createM1AirShipment();
			Boolean tmp = shouldTestBeFailed();
			if(tmp == true) Assert.fail();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 
	 }
	 
	 @Test(priority = 5) //Verify Truck Loader
	 public void verifyTruckLoader(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 objTradeEntity = new TradeEntity(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 try {
			objCreateShipments.createTruckShipment();
			Boolean tmp = shouldTestBeFailed();
			if(tmp == true) Assert.fail();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 
	 }
	 
	 @Test(priority = 6, enabled = false) //Verify ACAS Loader
	 public void verifyACASLoader(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 objTradeEntity = new TradeEntity(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 try {
			objCreateShipments.createACASShipment();
			Boolean tmp = shouldTestBeFailed();
			if(tmp == true) Assert.fail();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 
	 }
	 
	 @Test(priority = 6, groups = "EntryLoaderSet") //Verify Simplified Entry Loader
	 public void verifySimplifiedEntryLoader(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 objTradeEntity = new TradeEntity(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 try {
			objCreateShipments.createSimplifiedEntry();
			Boolean tmp = shouldTestBeFailed();
			if(tmp == true) Assert.fail();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 
	 }
	 
	 @Test(priority = 7, groups = "EntryLoaderSet") //Verify Entry Summary Loader
	 public void verifyEntrySummaryLoader(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 objTradeEntity = new TradeEntity(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 try {
			objCreateShipments.createEntrySummary();
			Boolean tmp = shouldTestBeFailed();
			if(tmp == true) Assert.fail();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		 
	 }
	 
	 
	 @Test(priority = 8) //Shipment Details Page
	 public void ShipmentDetailsPage(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 
		 objShipmentDetails = new ShipmentDetails(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 objShipmentSummaryDashboard.clickOnFirstShipmentInSummaryDashboardTable();
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		 
		//Page Check for Shipment Details Page
		 objShipmentDetails.waitForShipmentDetailsPageToLoad();
		 Assert.assertEquals(driver.getTitle(), objShipmentDetails.shipmentDetailsPageCheck());
		 takeScreenshot();
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
	 }
	 
	 @Test(priority = 9) //Old Trade Entity End Point From Dashboard Page
	 public void OldTradeEntityEndPointFromDashboardPage(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 
		 objShipmentDetails = new ShipmentDetails(driver);
		 objTradeEntity = new TradeEntity(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 objShipmentSummaryDashboard.clickOnShipperToolTip();
		 
		 switchOrCloseWindowTab(true, 2, false, 0);
		 Assert.assertEquals(driver.getTitle(), objTradeEntity.TradeEntityPageCheck());
		 objTradeEntity.waitForTradeEntitiesDashboardToFinishLoading();
		 takeScreenshot();
		 
		 switchOrCloseWindowTab(false, 0, true, 1);
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
	 }
	 
	 @Test(priority = 10) //Old Trade Entity End Point From Details Page
	 public void OldTradeEntityEndPointFromDetailsPage(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 
		 objShipmentDetails = new ShipmentDetails(driver);
		 objTradeEntity = new TradeEntity(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 objShipmentSummaryDashboard.clickOnFirstShipmentInSummaryDashboardTable();
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		 //Page Check for Shipment Details Page
		 objShipmentDetails.waitForShipmentDetailsPageToLoad();
		 Assert.assertEquals(driver.getTitle(), objShipmentDetails.shipmentDetailsPageCheck());
		 
		 objShipmentDetails.clickOnShipperToolTip();
		 
		 switchOrCloseWindowTab(true, 3, false, 0);
		 objTradeEntity.waitForTradeEntitiesDashboardToFinishLoading();
		 takeScreenshot();
	
		 closeAllWindowsTabsExceptForParent();
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
	 }
	 
	 @Test(priority = 11) //Exam Findings End Point From Dashboard Page
	 public void ExamFindingsEndPointFromDashboardPage(){
		 
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
		 
		 //Page Check for Exam Findings
		 objExamFindings.verifyThatExamFindigsPageHasLoaded();
		 
		 takeScreenshot();
		 
		 closeAllWindowsTabsExceptForParent();
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
	 }
	 
	 @Test(priority = 12) //Exam Findings End Point From Details Page
	 public void ExamFindingsEndPointFromDetailsPage(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objActionsOnShipments = new ActionsOnShipments(driver);
		 objExamFindings = new ExamFindings(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 objShipmentSummaryDashboard.clickOnBillHoldTab();
		 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
		 objShipmentSummaryDashboard.clickOnFirstShipmentInSummaryDashboardTable();
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		 //Page Check for Shipment Details Page
		 objShipmentDetails.waitForShipmentDetailsPageToLoad();
		 Assert.assertEquals(driver.getTitle(), objShipmentDetails.shipmentDetailsPageCheck());
		 
		 objShipmentSummaryDashboard.clickOnActionsDropDownMenu("Shipment Detail");		 
		 objShipmentSummaryDashboard.clickOnEnterExamFindingsButton();
		 switchOrCloseWindowTab(true, 3, false, 0);
		 
		//Page Check for Exam Findings
		 objExamFindings.verifyThatExamFindigsPageHasLoaded();
		 
		 takeScreenshot();
		 
		 closeAllWindowsTabsExceptForParent();
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
	 }
	 
	 @Test(priority = 13) //Entry End Points , dependsOnGroups = "EntryLoaderSet"
	 public void EntryEndPoints(){
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 //Create Simplified Entry and Entry Summary data
		 try {
				objCreateShipments.createSimplifiedEntry();
				Boolean tmp = shouldTestBeFailed();
				if(tmp == true) Assert.fail();
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
		 
		 try {
				objCreateShipments.createEntrySummaryFromExistingEntry();
				Boolean tmp = shouldTestBeFailed();
				if(tmp == true) Assert.fail();
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
		 ///////////////////////////////////////////////////////////////////////////////
		 
		 
		 objShipmentSummaryDashboard.clickOnBillHoldTab();
		 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
		 objShipmentSummaryDashboard.clickOnFirstShipmentInSummaryDashboardTable();
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		 //Page Check for Shipment Details Page
		 objShipmentDetails.waitForShipmentDetailsPageToLoad();
		 Assert.assertEquals(driver.getTitle(), objShipmentDetails.shipmentDetailsPageCheck());
		 
		 //Verify EBond link App////////////////////////////////////////////////
		 objShipmentDetails.navigateToSpecificShipmentFromDetailPage(shmptId);
		 objShipmentDetails.clickOnEBondLink();
		 switchOrCloseWindowTab(true, 3, false, 0);
		 
		//Page Check for EBond Findings
		 objShipmentDetails.pageCheckForEbondApp();
		 takeScreenshot();
		 switchOrCloseWindowTab(false, 0, true, 1);
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		 //Verify Cargo Release App////////////////////////////////////////////
		 objShipmentDetails.clickOnCargoReleaseLink();
		 switchOrCloseWindowTab(true, 3, false, 0);
		 objShipmentDetails.pageCheckForCargoReleaseApp();
		 takeScreenshot();
		 switchOrCloseWindowTab(false, 0, true, 1);
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		 //Verify Entry Summary App////////////////////////////////////////////
		 objShipmentDetails.clickOnEntrySummaryLink();
		 switchOrCloseWindowTab(true, 3, false, 0);
		 objShipmentDetails.pageCheckForEntrySummaryApp();
		 takeScreenshot();
		 switchOrCloseWindowTab(false, 0, true, 1);
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		//Verify PGA Data Processing App////////////////////////////////////////////
		 objShipmentDetails.clickOnPGALink();
		 switchOrCloseWindowTab(true, 3, false, 0);
		 objShipmentDetails.pageCheckForPGAApp();
		 takeScreenshot();
		 		 
		 closeAllWindowsTabsExceptForParent();
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
	 }
	 
	 @Test(priority = 14) //Truck Driver UPAX End Point From Details Page
	 public void TruckDriverUPAXEndPointFromDetailsPage() throws Exception{
		 
		 objShipmentSummaryDashboard = new ShipmentSummaryDashboard(driver);
		 objShipmentDetails = new ShipmentDetails(driver);
		 
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
		 
		 objShipmentSummaryDashboard.clickOnBillHoldTab();
		 objShipmentSummaryDashboard.checkThatBillHoldTypeMessageisDisplayed();
		 
		 //filter to truck shipments only
		 objShipmentSummaryDashboard.checkSpecifiedMOTFilterExistsAndClickOnIt("Truck");
		 objShipmentSummaryDashboard.clickOnFirstShipmentInSummaryDashboardTable();
		 switchOrCloseWindowTab(true, 2, false, 0);
		 
		 //Page Check for Shipment Details Page
		 objShipmentDetails.waitForShipmentDetailsPageToLoad();
		 Assert.assertEquals(driver.getTitle(), objShipmentDetails.shipmentDetailsPageCheck());
		 
		//click on UPAX link
		 objShipmentDetails.clickOnUpaxLink();
		 switchOrCloseWindowTab(true, 3, false, 0);
		 
		//Page Check for UPAX Page
		 objShipmentDetails.pageCheckForUPAXApp();
		 
		 takeScreenshot();
		 
		 closeAllWindowsTabsExceptForParent();
		 objShipmentSummaryDashboard.verifyThatShipmentSummaryTableHasLoaded();
	 }
	 
	 @Parameters({"Run-Type" })
	 @BeforeSuite
	  public void beforeSuite(String RunType) {
		
		 
	  }
	 
	 @Parameters({ "Run-Type" })
	 @AfterSuite
	  public void afterSuite(String RunType) {
		
		 if(skipCondition == false){
			 tearDownRunTimeBrowsers();
			 
			// if(RunType.contentEquals("Local")){
				 deleteLogsOlderThanNDays();
				 deleteScreenshotsOlderThanNDays();
			 //}
		
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
				 explicitWaitFindElement("cssSelector", "a[data-username='HHH4001']", 30);
				 
				 objImportCargoLogin.clickOnFilteredTestAccount();
				 //objImportCargoLogin.verifyLoginResponse();
				 explicitWaitwaitForElementToBeVisible("linkText", "Import Cargo", 30);
				 
				 explicitWaitwaitForElementToBeClickable("linkText", "Import Cargo", 30);
				 objImportCargoLogin.clickOnImportCargoLink();
				 Assert.assertEquals("Import Cargo Shipment Dashboard", driver.getTitle());
				 Reporter.log("Import Cargo Dashboard Page has displayed");
			 }catch(Exception e){
				// takeScreenshot();
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
					 explicitWaitFindElement("cssSelector", "a[data-username='HHH4001']", 30);
					 
					 objImportCargoLogin.clickOnFilteredTestAccount();
					 //objImportCargoLogin.verifyLoginResponse();
					 explicitWaitwaitForElementToBeVisible("linkText", "Import Cargo", 30);
					 
					 explicitWaitwaitForElementToBeClickable("linkText", "Import Cargo", 30);
					 objImportCargoLogin.clickOnImportCargoLink();
					 Assert.assertEquals("Import Cargo Shipment Dashboard", driver.getTitle());
					 Reporter.log("Import Cargo Dashboard Page has displayed");
				 }catch(Exception e){
					 //takeScreenshot();
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