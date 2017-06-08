package pageobjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;





import resources.Utilities;

public class ShipmentDetails extends Utilities{
	
	WebDriver driver;

	//Page Check
	By shipmentId = By.xpath("//div[@id='detail-header']/div[2]/div[4]/span");
	
	//Actions options
	By postBillHold = By.linkText("Post Bill Hold");
	By postIntensiveEntryHold = By.linkText("Post Intensive Entry Hold");
	By postDocumentReviewEntryHold = By.linkText("Post Document Review Entry Hold");
	By postEntryOverrideToGeneral = By.linkText("Post Entry Override to General");
	By reviewShipment = By.linkText("Review Shipment");
	By addNote = By.linkText("Add Note");
	
	By runTimeBillNumberFromSummaryTable = By.xpath("//span[@cell='billNumber']");
	By runTimeEntryNumberFromSummaryTable = By.xpath("//span[@cell='entryNumber']");
	
	//variables
	String runTimeBillNum;
	String runTimeEntryNum;
	String runtimeShipmentId;
	String xmlpath;
	String shipmentDetailsPageName;
	
	//Shipment Detail Elements
	By actionsButton = By.xpath("(//button[@title='Actions'])");
	
	//Views
	By selectViewDropDown = By.id("selectedView");
	By manifestView = By.xpath("(//a[@data-value='manifestView'])");
	By entryView = By.xpath("(//a[@data-value='shipmentSummaryView'])");
	By preDepartureView = By.xpath("(//a[@data-value='preDepartureView'])");
	By iSFview = By.xpath("(//a[@data-value='summaryIsfView'])");
	
	//Entry External Links
	By EBondLink = By.xpath("//a[@title='Link to Enter EBond']");
	By CargoReleaseLink = By.xpath("//a[@title='Link to Enter Cargo Release']");
	By EntrySummaryLink = By.xpath("//a[@title='Link to Enter Entry Summary']");
	By PGALink = By.xpath("//a[@title='Link to Enter PGA Data Processing']");
	By UPAXLink = By.xpath("//div[@id='header-truck']//span[@class='fa fa-external-link fa-fw']");
	
	//Other WebElements
	By currentlyLoggedInUser = By.xpath("//span[contains(text(), 'HHH4001 SAT TESTER SE/AES CBP SUPVR USER')]");
	By trainingPageLink = By.linkText("Training");
	By PreferencesPageLink = By.linkText("Preferences");
	By addNoteLink = By.linkText("Capture Note for Shipment");
	By shipperLink = By.xpath("//div[@section='bill-parties']/div/address/div//a[@rel='tooltip']");

	public ShipmentDetails(WebDriver driver) {
		
		this.driver = driver;
	}
	
	//This is added to check that the Shipment Summary Dashboard data has rendered correctly.
	public void verifyThatShipmentSummaryTableHasLoaded(){
		
		explicitWaitwaitForElementToBeVisible("xpath", "//div[@purpose='results-list']", 20);
		explicitWaitwaitForElementToBeClickable("xpath", "//input[@name='selectShipment']", 30);
		 log.info("Summary Dashboard data has rendered");
	}
	
	
	public String shipmentDetailsPageCheck(){
		
		String runtimeShipmentId = driver.findElement(shipmentId).getText().toString();
		runtimeShipmentId = runtimeShipmentId.substring(10).trim();
		shipmentDetailsPageName = "Shipment " + runtimeShipmentId + " Details";
		log.info("Verifying Page Check for Shipment Details Page");
		return shipmentDetailsPageName;
	}
	
	public void waitForShipmentDetailsPageToLoad(){
		
		explicitWaitFindElement("id", "second-navbar", 10);
		log.info("Shipment Details Page has finished loading");
	}
	
	
	public void clickOnShipperToolTip(){
		
		explicitWaitwaitForElementToBeVisible("xpath", "//div[@section='bill-parties']/div/address/div/div", 10);
		driver.findElement(shipperLink).click();
		log.info("Clicked On Shipper Link from the Details Page");
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
	
	public void navigateToSpecificShipmentFromDetailPage(String shmptId){
		
		driver.navigate().to("https://apps-" + tmp4 + ".sat.cbp.dhs.gov/ta/cargo/import/processing/shipment#detail?id=" + shmptId);	
		log.info("Navigated to Shipment Detail: " + shmptId + " Page");
	}
	
	public void clickOnEBondLink(){
		
		explicitWaitwaitForElementToBeClickable("xpath", "//a[@title='Link to Enter EBond']", 15);
		driver.findElement(EBondLink).click();
		log.info("Clicked on EBond Link");
	}
	
	public void pageCheckForEbondApp(){
		
		explicitWaitwaitForElementToBeVisible("id", "bondSummary_filter", 30);
		log.info("E-Bond Page has completed Loading");
	}
	
	public void clickOnCargoReleaseLink(){
		
		explicitWaitwaitForElementToBeClickable("xpath", "//a[@title='Link to Enter Cargo Release']", 15);
		driver.findElement(CargoReleaseLink).click();
		log.info("Clicked on Cargo Release Link");
	}
	
	public void pageCheckForCargoReleaseApp(){
		
		explicitWaitwaitForElementToBeVisible("xpath", "//div[@id='detailsActionMenu']//button[@title='Actions Menu']", 30);
		log.info("Cargo Release Page has completed Loading");
	}
	
	public void clickOnEntrySummaryLink(){
		
		explicitWaitwaitForElementToBeClickable("xpath", "//a[@title='Link to Enter Entry Summary']", 15);
		driver.findElement(EntrySummaryLink).click();
		log.info("Clicked on Entry Summary Link");
	}
	
	public void pageCheckForEntrySummaryApp(){
		
		explicitWaitwaitForElementToBeVisible("id", "es-details-esTab-lineItemsDTFilter", 30);
		log.info("Entry Summary Page has completed Loading");
	}
	
	public void clickOnPGALink(){
		
		explicitWaitwaitForElementToBeClickable("xpath", "//a[@title='Link to Enter PGA Data Processing']", 15);
		driver.findElement(PGALink).click();
		log.info("Clicked on PGA Link");
	}
	
	public void pageCheckForPGAApp(){
		
		explicitWaitwaitForElementToBeVisible("id", "aTopEntryDetails", 30);
		log.info("PGA Data Processing Page has completed Loading");
	}
	
	public void clickOnUpaxLink(){
		
		explicitWaitwaitForElementToBeClickable("xpath", "//div[@id='header-truck']//span[@class='fa fa-external-link fa-fw']", 20);
		driver.findElement(UPAXLink).click();
		log.info("Clicked on the UPAX Truck Driver Name Link");
		
	}
	
	public void pageCheckForUPAXApp(){
		
		explicitWaitwaitForElementToBeVisible("id", "passportNumber", 30);
		log.info("UPAX Page has completed Loading");
	}

}