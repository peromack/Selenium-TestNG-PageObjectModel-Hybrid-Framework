package pageobjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import resources.Utilities;

public class ShipmentDetails extends Utilities{
	
	WebDriver driver;

	
	//Actions options
	By postBillHold = By.linkText("Post Bill Hold");
	By postIntensiveEntryHold = By.linkText("Post Intensive Entry Hold");
	By postDocumentReviewEntryHold = By.linkText("Post Document Review Entry Hold");
	By postEntryOverrideToGeneral = By.linkText("Post Entry Override to General");
	By reviewShipment = By.linkText("Review Shipment");
	By addNote = By.linkText("Add Note");
	
	By runTimeBillNumberFromSummaryTable = By.xpath("//span[@cell='billNumber']");
	By runTimeEntryNumberFromSummaryTable = By.xpath("//span[@cell='entryNumber']");
	String runTimeBillNum;
	String runTimeEntryNum;
	String xmlpath;
	
	//Shipment Detail Elements
	By actionsButton = By.xpath("(//button[@title='Actions'])");
	
	//Views
	By selectViewDropDown = By.id("selectedView");
	By manifestView = By.xpath("(//a[@data-value='manifestView'])");
	By entryView = By.xpath("(//a[@data-value='shipmentSummaryView'])");
	By preDepartureView = By.xpath("(//a[@data-value='preDepartureView'])");
	By iSFview = By.xpath("(//a[@data-value='summaryIsfView'])");
	
	//Other WebElements
	By currentlyLoggedInUser = By.xpath("//span[contains(text(), 'HHH4001 SAT TESTER SE/AES CBP SUPVR USER')]");
	By trainingPageLink = By.linkText("Training");
	By PreferencesPageLink = By.linkText("Preferences");
	By addNoteLink = By.linkText("Capture Note for Shipment");

	public ShipmentDetails(WebDriver driver) {
		
		this.driver = driver;
	}
	
	//This is added to check that the Shipment Summary Dashboard data has rendered correctly.
	public void verifyThatShipmentSummaryTableHasLoaded(){
		
		explicitWaitwaitForElementToBeVisible("xpath", "//div[@purpose='results-list']", 20);
		explicitWaitwaitForElementToBeClickable("xpath", "//input[@name='selectShipment']", 30);
		 log.info("Summary Dashboard data has rendered");
	}
	
	
	public void checkThatBillHoldTypeMessageisDisplayed(){
		
		 explicitWaitwaitForElementToBeVisible("xpath","//span[contains(text(), 'Shipments on Bill Hold')]", 10); 
		 log.info("Results for Bill Hold data has completed loading");
	}
	
	public void checkThatEntryHoldTypeMessageisDisplayed(){
		
		 explicitWaitwaitForElementToBeVisible("xpath","//span[contains(text(), 'Shipments on Entry Hold')]", 10); 
		 log.info("Results for Entry Hold data has completed loading");
	}
	
	public void checkThatHotlistTypeMessageisDisplayed(){
		
		 explicitWaitwaitForElementToBeVisible("xpath","//span[contains(text(), 'Shipments on Hotlist')]", 10); 
		 log.info("Results for HostList data has completed loading");
	}
	
	
	public void grabrunTimeBillNumberFromSummaryDashboard() {
		
		
		runTimeBillNum = driver.findElement(runTimeBillNumberFromSummaryTable).getText();
		runTimeBillNum = runTimeBillNum.substring(runTimeBillNum.indexOf("-") + 1, runTimeBillNum.indexOf(" ("));
		xmlpath = "//h4[contains(text(), 'Post Hold on Bill "+ runTimeBillNum + "')]";
		log.info("Successfully grabbed Run-Time Bill number from Shipment Summary table");
	}
	
	public void grabrunTimeEntryNumberFromSummaryDashboard() {
		
		runTimeEntryNum = driver.findElement(runTimeEntryNumberFromSummaryTable).getText();
		runTimeEntryNum = runTimeEntryNum.substring(runTimeEntryNum.indexOf("-") + 1, runTimeEntryNum.indexOf(" ("));
		xmlpath = "//h4[contains(text(), 'Post Intensive Hold on Entry " + runTimeEntryNum + "')]";
		log.info("Successfully grabbed Run-Time Entry number from Shipment Summary table");
	}
	
	public void verifyThatBillHoldTypePopupIsDisplayed() {
		
		explicitWaitwaitForElementToBeVisible("xpath", xmlpath, 10);
		log.info("Page check PASSED for Post Bill Hold Popup Form");
	}
	
	public void verifyThatEntryHoldTypePopupIsDisplayed() {
		
		explicitWaitwaitForElementToBeVisible("xpath", xmlpath, 10);
		log.info("Page check PASSED for Entry Hold Type Popup Form");
	}
	
	public void verifyThatDocumentsReviewPopupIsDisplayed(){
		
		String xmlpath = "//h4[contains(text(), 'Post Documents Review on Entry " + runTimeEntryNum + "')]";
		explicitWaitwaitForElementToBeVisible("xpath", xmlpath, 10);
	}
	
	public void verifyThatPostOverrideToGeneralOnEntryPopupIsDisplayed(){
		
		String xmlpath = "//h4[contains(text(), 'Post Override To General on Entry " + runTimeEntryNum + "')]";
		explicitWaitwaitForElementToBeVisible("xpath", xmlpath, 10);
	}
		
	
	public void clickOnActionsDropDownMenu() {
		
		driver.findElement(actionsButton).click();
		log.info("Clicked on the 'Actions' drop down menu");
	}
	
	public void clickOnpostBillHoldOption() {
		
		driver.findElement(postBillHold).click();
		log.info("Selected the Post Bill Hold Option from the drop down menu");
	}
	
	
	public void clickOnpostIntensiveEntryHoldOption() {
		
		driver.findElement(postIntensiveEntryHold).click();
		log.info("Selected the Post Intensive Entry Hold Option from the drop down menu");
	}
	
	public void clickOnpostDocumentReviewEntryHoldOption() {
		
		driver.findElement(postDocumentReviewEntryHold).click();
		log.info("Selected the Post Document Review Entry Hold from the drop down menu");
	}
	
	public void clickOnpostEntryOverrideToGeneralOption(){
		
		driver.findElement(postEntryOverrideToGeneral).click();
		log.info("Selected the Post Entry Override To General Hold from the drop down menu");
	}
	
	public void clickOnreviewShipmentOption() {
		
		driver.findElement(reviewShipment).click();
		log.info("Selected the Review Shipment Option from the drop down menu");
	}
	
	public void verifyIfActionsDropDownMenuIsDisabled(){
		
		explicitWaitwaitForElementToBecomeInvisible("xpath", actionsButton.toString(), 3);
		log.info("Actions Drop Down Menu is disabled");
	}
	
	public void clickOncurrentlyLoggedInUser(){
		
		driver.findElement(currentlyLoggedInUser).click();
		log.info("Clicked On User Options Drop Down Menu");
	}
	
	public void clickOnTrainingPageLink(){
		
		driver.findElement(trainingPageLink).click();
		log.info("Clicked On Training Page Link");
	}
	
	public void clickOnPreferencesPageLink(){
		
		driver.findElement(PreferencesPageLink).click();
		log.info("Clicked On Preferences Page Link");
	}
		
	
	public void clickOnAddNoteOption() {
		
		driver.findElement(addNoteLink).click();
		log.info("Selected the Add Note Option from the drop down menu");
	}

}