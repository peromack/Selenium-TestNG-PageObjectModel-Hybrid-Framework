package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import resources.Utilities;

import java.util.List;

import org.openqa.selenium.WebElement;

public class ShipmentSummaryDashboard extends Utilities{
	
	WebDriver driver;
	
	//Shipment Dashboard tabs
	By billHoldTab = By.xpath("//li[@data-target='holds_bill_hold']/a");
	By entryHoldTab = By.xpath("//li[@data-target='holds_entry_hold']/a");
	By hotListTab = By.xpath("//ul[@id='hotlist_menu']/li/a");
	By hotListTabCount = By.xpath("//a[@data-target='hotlist_hotlist']/span");
	By billholdtabCount = By.xpath("//a[@data-target='holds_bill_hold']/span");
	By entryholdtabCount = By.xpath("//a[@data-target='holds_entry_hold']/span");
	
	//Actions options
	By postBillHold = By.linkText("Post Bill Hold");
	By postIntensiveEntryHold = By.linkText("Post Intensive Entry Hold");
	By postDocumentReviewEntryHold = By.linkText("Post Document Review Entry Hold");
	By postEntryOverrideToGeneral = By.linkText("Post Entry Override to General");
	By reviewShipment = By.linkText("Review Shipment");
	By enterExamFindings = By.xpath("//a[@title='Link to Enter Exam Findings for this Shipment']");
	By enterNotExaminedEvent = By.linkText("Enter Not Examined Event");
		
	
	//Dashboard Elements
	By selectShipmentCheckBox = By.xpath("//input[@name='selectShipment']");
	By shipmentSummaryActionsButton = By.xpath("(//button[@title='Actions'])");
	By shipmentDetailsActionsButton = By.xpath("(//a[@title='Actions'])");
	By selectAllCheckBox = By.id("selectAll");
	By runTimeBillNumberFromSummaryTable = By.xpath("//span[@cell='billNumber']");
	By runTimeEntryNumberFromSummaryTable = By.xpath("//span[@cell='entryNumber']");
	By shipmentId = By.xpath("//span[@cell='shipmentId']/a");
	By allColumnNames = By.xpath("//*[@class='list-group-item headers']/div//li");
	By summaryTableRecords = By.xpath("//span[@content='results-description']");
	By mot = By.xpath("//h4[@data-type='mot']");
	By airMOT = By.xpath("//div[@id='collapse_mot']//div[@class='checkbox' and @type='4']");
	By seaMOT = By.xpath("//div[@id='collapse_mot']//div[@class='checkbox' and @type='1']");
	By noMOT = By.xpath("//div[@id='collapse_mot']//div[@class='checkbox' and @type='-1']");
	By railMOT = By.xpath("//div[@id='collapse_mot']//div[@class='checkbox' and @type='2']");
	By truckMOT = By.xpath("//div[@id='collapse_mot']//div[@class='checkbox' and @type='3']");
	By airDisplay = By.xpath("//div[@content='display-filters']/button[@data-name='AIR']");
	By seaDisplay = By.xpath("//div[@content='display-filters']/button[@data-name='SEA']");
	By noMOTDisplay = By.xpath("//div[@content='display-filters']/button[@data-name='No MOT']");
	By railDisplay = By.xpath("//div[@content='display-filters']/button[@data-name='RAIL']");
	By truckDisplay = By.xpath("//div[@content='display-filters']/button[@data-name='TRUCK']");
	By clearMOTfilter = By.id("clear-filters_mot");
	By shipperlink = By.xpath("//span[@cell='shipper']/a[@rel='tooltip']");
	By seaFilterCheckbox = By.name("filter_mot_1");
	By railFilterCheckbox = By.name("filter_mot_2");
	By truckFilterCheckbox = By.name("filter_mot_3");
	By airFilterCheckbox = By.name("filter_mot_4");
	By noMotFilterCheckbox = By.name("filter_mot_-1");
	
	//variables
	String shipmentDetailsExpectedPage;
	String currentTab;
	String columnName;
	String previousCountSummary;
	String runTimeBillNum;
	String runTimeEntryNum;
	String xmlpath;
	int airSideBarRecord;
	int previousRecordCount, intPreviousAirCountSummary;
	boolean matchFound;
	String previousrecordText;
	
	
	//Views
	By selectViewDropDown = By.id("selectedView");
	By ManifestView = By.xpath("(//a[@data-value='manifestView'])");
	By entryView = By.xpath("(//a[@data-value='shipmentSummaryView'])");
	By preDepartureView = By.xpath("(//a[@data-value='preDepartureView'])");
	By iSFview = By.xpath("(//a[@data-value='summaryIsfView'])");
	
	//Other WebElements
	By currentlyLoggedInUser = By.xpath("//span[contains(text(), 'HHH4001 SAT TESTER SE/AES CBP SUPVR USER')]");
	By trainingPageLink = By.linkText("Training");
	By preferencesPageLink = By.linkText("Preferences");
	By feedbackLink = By.linkText("Feedback");
	By feedbackSubjectEditBox = By.id("feedbackSubject");
	By commentsEditBox = By.id("feedbackComments");
	By feedBackSubmitButton = By.id("feedbackDialogSubmitBtn");
	By currentShipmentDashboardTab = By.xpath("//div[@content='results']/h3[@class='section-header first-section']/span[@rel='results-heading']");

	public ShipmentSummaryDashboard(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void clickOnFirstShipmentInSummaryDashboardTable(){
		
		String tmp = driver.findElement(shipmentId).getAttribute("title").substring(26);
		shipmentDetailsExpectedPage = "Shipment " + shipmentId + " Details";
		driver.findElement(shipmentId).click();
		log.info("Clicked on Shipment: " + tmp);
	}
	
	public void clickOnFeedBackButton(){
		
		driver.findElement(feedbackLink).click();
		log.info("Clicked on FeedBack button");
		
	}
	
	public void clickOnEnterExamFindingsButton(){
		
		
		driver.findElement(enterExamFindings).click();
		log.info("Clicked on Enter Exam Findings Button");
		
	}
	
	public void clickOnEnterNotExaminedEventButton(){
		
		driver.findElement(enterNotExaminedEvent).click();
		log.info("Clicked on Not Examined Event button");
		
	}

	public void clickOnFeedbackSubmitButton(){
		
		driver.findElement(feedBackSubmitButton).click();
		log.info("Clicked on FeedBack Submit Button");
	}

	
	public List<WebElement> grabAllColumnNameFromDashboardTable(){
		
		List<WebElement> ColumnNames = driver.findElements(allColumnNames);
		
		return ColumnNames;
	}
	
	public void clickOnViewTypeDropDownMenu(){
		
		driver.findElement(selectViewDropDown).click();
		log.info("Clicked On View Type Drop Down Menu");
	}
	
	public void grabCurrentViewType(){
		
		CurrentViewType = driver.findElement(selectViewDropDown).getText();
		log.info("Current View Type is: " + CurrentViewType);
	}
	
	public void clickOnEntryForViewType(){
		
		driver.findElement(entryView).click();
		log.info("Selected Entry For View Type");
	}
	
	public void clickOnISFforViewType(){
		
		driver.findElement(iSFview).click();
		log.info("Selected ISF For View Type");
	}
	
	public void clickOnPreDepartureforViewType(){
		
		driver.findElement(preDepartureView).click();
		log.info("Selected Pre-Departure For View Type");
	}
	
	public void clickOnManifestforViewType(){
		
		
		driver.findElement(ManifestView).click();
		log.info("Selected Manifest For View Type");
	}
	
	public void selectViewTypeThatIsNotCurrent(){
		
		switch(CurrentViewType){
		
			case "ISF":
				clickOnPreDepartureforViewType();
				break;
				
			case "Pre-departure":
				clickOnEntryForViewType();
				break;
				
			case "Manifest":
				clickOnISFforViewType();
				break;
				
			case "Entry":
				clickOnManifestforViewType();
				break;
		}
		
		log.info("Executed method to select view type not already present");
	}
	
	public void grabCurrentlyDisplayedTabFromDashboard(){
		
		currentTab = driver.findElement(currentShipmentDashboardTab).getText();
		log.info("Current Tab displayed is " + currentTab);
	}
	
	public void clickOnTabThatIsNotCurrent(){
		
		switch(currentTab){
		
			case "Shipments on Hotlist":
				clickOnBillHoldTab();
				checkThatBillHoldTypeMessageisDisplayed();
				break;
				
			case "Shipments on Bill Hold":
				clickOnEntryHoldTab();
				checkThatEntryHoldTypeMessageisDisplayed();
				break;
				
			case "Shipments on Entry Hold":
				clickOnHotListTab();
				checkThatHotlistTypeMessageisDisplayed();
				break;
		}
		
		log.info("Executed method to click on a Tab not already present");
		
	}
	

	//This is added to check that the Shipment Summary Dashboard data has rendered correctly.
	public void verifyThatShipmentSummaryTableHasLoaded(){
		
		explicitWaitwaitForElementToBeVisible("xpath", "//div[@purpose='results-list']", 20);
		explicitWaitwaitForElementToBeClickable("xpath", "//input[@name='selectShipment']", 30);
		log.info("Summary Dashboard data has rendered");
	}
	
	
	public void clickOnBillHoldTab() {
		
		explicitWaitwaitForElementToBeVisible("xpath", "//li[@data-target='holds_bill_hold']/a", 5);
		driver.findElement(billHoldTab).click();
		log.info("Bill Hold Tab has been Clicked on");
		
	}
	
	public void checkThatBillHoldTypeMessageisDisplayed(){
		
		 explicitWaitwaitForElementToBeVisible("xpath","//span[contains(text(), 'Shipments on Bill Hold')]", 10); 
		 log.info("Results for Bill Hold data has completed loading");
	}
	
	public void checkThatFeedBackPopupisDisplayed(){
		
		 explicitWaitFindElement("id", "feedbackLabel", 5);
		 log.info("FeedBack Popup has completed loading");
	}
	
	public void checkThatEntryHoldTypeMessageisDisplayed(){
		
		 explicitWaitwaitForElementToBeVisible("xpath","//span[contains(text(), 'Shipments on Entry Hold')]", 10); 
		 log.info("Results for Entry Hold data has completed loading");
	}
	
	public void checkThatHotlistTypeMessageisDisplayed(){
		
		 explicitWaitwaitForElementToBeVisible("xpath","//span[contains(text(), 'Shipments on Hotlist')]", 10); 
		 log.info("Results for HostList data has completed loading");
	}
	
	public void clickOnEntryHoldTab() {
		
		driver.findElement(entryHoldTab).click();
		
	}
	
	public void clickOnHotListTab() {
		
		driver.findElement(hotListTab).click();
	}
	
	public void grabRunTimeBillNumberFromSummaryDashboard() {
		
		
		runTimeBillNum = driver.findElement(runTimeBillNumberFromSummaryTable).getText();
		runTimeBillNum = runTimeBillNum.substring(runTimeBillNum.indexOf("-") + 1, runTimeBillNum.indexOf(" ("));
		xmlpath = "//h3[contains(text(), 'Post Hold on Bill "+ runTimeBillNum + "')]";
		log.info("Successfully grabbed Run-Time Bill number from Shipment Summary table");
		
	}
	
	public void grabRunTimeEntryNumberFromSummaryDashboard() {
		
		
		runTimeEntryNum = driver.findElement(runTimeEntryNumberFromSummaryTable).getText();
		runTimeEntryNum = runTimeEntryNum.substring(runTimeEntryNum.indexOf("-") + 1, runTimeEntryNum.indexOf(" ("));
		xmlpath = "//h3[contains(text(), 'Post Intensive Hold on Entry " + runTimeEntryNum + "')]";
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
		
		String xmlpath = "//h3[contains(text(), 'Post Documents Review on Entry " + runTimeEntryNum + "')]";
		explicitWaitwaitForElementToBeVisible("xpath", xmlpath, 10);
		
	}
	
	public void verifyThatPostOverrideToGeneralOnEntryPopupIsDisplayed(){
		
		String xmlpath = "//h3[contains(text(), 'Post Override To General on Entry " + runTimeEntryNum + "')]";
		explicitWaitwaitForElementToBeVisible("xpath", xmlpath, 10);
		
	}
	
	public void clickOnselectShipmentCheckBox() {
		
		driver.findElement(selectShipmentCheckBox).click();
		log.info("Clicked on the 'Select Shipment' checkbox");
		
	}
	
	public void clickOnActionsDropDownMenu(String WhichPageAreYouOn) {
		
		if(WhichPageAreYouOn.equalsIgnoreCase("Shipment Summary")){
			explicitWaitwaitForElementToBeClickable("xpath", "//button[@title='Actions']", 10);
			driver.findElement(shipmentSummaryActionsButton).click();
			log.info("Clicked on the 'Actions' drop down menu from Shipment Summary Page");
		}else if (WhichPageAreYouOn.equalsIgnoreCase("Shipment Detail")){
			explicitWaitwaitForElementToBeVisible("xpath", "//a[@title='Actions']", 10);
			driver.findElement(shipmentDetailsActionsButton).click();
			log.info("Clicked on the 'Actions' drop down menu from Shipment Detail Page");
		}else{
			log.error("Incorrect argument specified for Page Location");
		}
		
		
	}
	
	public void clickOnPostBillHoldOption() {
		
		driver.findElement(postBillHold).click();
		log.info("Selected the Post Bill Hold Option from the drop down menu");
		
	}
	
	
	public void clickOnpostIntensiveEntryHoldOption() {
		
		driver.findElement(postIntensiveEntryHold).click();
		log.info("Selected the Post Intensive Entry Hold Option from the drop down menu");
		
	}
	
	public void clickOnPostDocumentReviewEntryHoldOption() {
		
		driver.findElement(postDocumentReviewEntryHold).click();
		log.info("Selected the Post Document Review Entry Hold from the drop down menu");
		
	}
	
	
	public void clickOnPostEntryOverrideToGeneralOption(){
		
		driver.findElement(postEntryOverrideToGeneral).click();
		log.info("Selected the Post Entry Override To General Hold from the drop down menu");
	}
	
	public void clickOnReviewShipmentOption() {
		
		driver.findElement(reviewShipment).click();
		log.info("Selected the Review Shipment Option from the drop down menu");
		
	}
	
	public void verifyIfActionsDropDownMenuIsDisabled(){
		
		explicitWaitwaitForElementToBecomeInvisible("xpath", shipmentSummaryActionsButton.toString(), 3);
		log.info("Actions Drop Down Menu is disabled");
	}
	
	public void clickOnCurrentlyLoggedInUser(){
		
		driver.findElement(currentlyLoggedInUser).click();
		log.info("Clicked On User Options Drop Down Menu");
	}
	
	public void clickOnTrainingPageLink(){
		
		
		driver.findElement(trainingPageLink).click();
		log.info("Clicked On Training Page Link");
	}
	
	public void clickOnPreferencesPageLink(){
		
		driver.findElement(preferencesPageLink).click();
		log.info("Clicked On Preferences Page Link");
	}
	
	
	public void enteringSubjectForFeedback(){
		
		explicitWaitwaitForElementToBeVisible("id", "feedbackSubject", 5);
		driver.findElement(feedbackSubjectEditBox).sendKeys("Test to Input Feedback Subject");
		log.info("Entered Feedback Subject");
	}

	public void enteringCommentsForFeedback(){
		
		driver.findElement(commentsEditBox).sendKeys("Test to Input Comments for Feedback");
		log.info("Entered Feedback Comments");
	}
	
	public void validateHotlistRecordCount() throws Exception{
		String hotListRecordCount = driver.findElement(hotListTabCount).getText();
		hotListRecordCount = hotListRecordCount.substring(hotListRecordCount.indexOf("(") + 1, hotListRecordCount.indexOf(")"));
		log.info("The number of hotlist records displayed on the tab is: " + hotListRecordCount);
		
		String hotlistsummaryDisplay = driver.findElement(summaryTableRecords).getText();
		hotlistsummaryDisplay = hotlistsummaryDisplay.substring(hotlistsummaryDisplay.indexOf("of") + 3, hotlistsummaryDisplay.indexOf("records"));
		hotlistsummaryDisplay  = hotlistsummaryDisplay.trim(); 
        log.info("The number of hotlist records showing in the Dashboard is: " + hotlistsummaryDisplay);
        
        int intHotListTabRecord = Integer.parseInt(hotListRecordCount);
        int intHotListSummaryRecord = Integer.parseInt(hotlistsummaryDisplay);
       
        //Custom check to verify Hotlist record count on the tab and dashboard level match.
        if(intHotListTabRecord == intHotListSummaryRecord){
        	log.info("Hot List Tab Record equals Hot List Summary Record");
        }else{
        	throw new Exception("Record count and summary count for the hotlist do not match");
        }

        
		if (intHotListSummaryRecord > 0) {
			//boolean displayMOT = Utilities.isElementPresent(mot);
			explicitWaitwaitForElementToBeVisible("xpath", "//h4[@data-type='mot']", 10);
			log.info("Mode of Transport options are available for Shipment on Hotlist");
		} else {
			log.warn("No data available in the Hotlist Tab");
		}
	}
	
	public void validateBillHoldRecordCount () throws Exception {
		String billHoldTabRecordCount = driver.findElement(billholdtabCount).getText();
		billHoldTabRecordCount = billHoldTabRecordCount.substring(billHoldTabRecordCount.indexOf("(") + 1, billHoldTabRecordCount.indexOf(")"));
        log.info("The number of records on the bill hold tab are: " + billHoldTabRecordCount);
        
        String billholdsummaryDisplay = driver.findElement(summaryTableRecords).getText();
        billholdsummaryDisplay = billholdsummaryDisplay.substring(billholdsummaryDisplay.indexOf("of") + 3,billholdsummaryDisplay.indexOf("records"));
        billholdsummaryDisplay = billholdsummaryDisplay.trim();
        log.info("The number of billhold showing in the Display summary table is: " + billholdsummaryDisplay);
        
        int intBillHoldTabRecord = Integer.parseInt(billHoldTabRecordCount);
        int intBillHoldSummaryRecord = Integer.parseInt(billholdsummaryDisplay);
        
         //Custom check to verify Bill Hold record count on the tab and dashboard level match.
        if(intBillHoldTabRecord == intBillHoldSummaryRecord){
        	log.info("Bill Hold Tab Record equals Bill Hold Summary Record");
        }else{
        	throw new Exception("Record count and summary count for the Bill Hold do not match");
        }
        
		
		if (intBillHoldTabRecord > 0) {
			explicitWaitwaitForElementToBeVisible("xpath", "//h4[@data-type='mot']", 10);
			log.info("Mode of Transport options are available for Shipment on Bill Hold");
		} else {
			log.warn("No data available in the Billhold Tab");
			
		}
	}
	
	public void validateEntryHoldRecordCount () throws Exception {
		String entryHoldTab  = driver.findElement(entryholdtabCount).getText();
		entryHoldTab = entryHoldTab.substring(entryHoldTab.indexOf("(") + 1, entryHoldTab.indexOf(")"));
        log.info("The number of entryholdtab for Entryholdtab tab is: " + entryHoldTab);
       
        String entryHoldSummaryDisplay  = driver.findElement(summaryTableRecords).getText();
        entryHoldSummaryDisplay = entryHoldSummaryDisplay.substring(entryHoldSummaryDisplay.indexOf("of") + 3, entryHoldSummaryDisplay.indexOf("records"));
        entryHoldSummaryDisplay = entryHoldSummaryDisplay.trim();
        log.info("The number of EntryholdDisplay showing in the Display summary table is: " + entryHoldSummaryDisplay);
        
        int intEntryHoldTabRecord  = Integer.parseInt(entryHoldTab);
        int intEntryHoldSummaryRecord  = Integer.parseInt(entryHoldSummaryDisplay);
        
         //Custom check to verify Entry Hold record count on the tab and dashboard level match.
        if(intEntryHoldTabRecord == intEntryHoldSummaryRecord){
        	log.info("Entry Hold Tab Record equals Entry Hold Summary Record");
        }else{
        	throw new Exception("Record count and summary count for the hotlist do not match");
        }
        
		
		if (intEntryHoldTabRecord > 0) {
			explicitWaitwaitForElementToBeVisible("xpath", "//h4[@data-type='mot']", 10);
			log.info("Mode of Transport options are available for Shipment on Entry Hold");
		} else {
			log.error("No data available in the Entryhold Tab");
			
		}
	}

	public void MOTValidation () throws Exception {
	    
	    boolean noMOTExist  = Utilities.isElementPresent(noMOT);
	    boolean seaMOTExist  = Utilities.isElementPresent(seaMOT);
	    boolean railMOTExistt  = Utilities.isElementPresent(railMOT);
	    boolean truckMOTExist = Utilities.isElementPresent(truckMOT);
	    boolean airMOTExist = Utilities.isElementPresent(airMOT);
	    
	    String whichMot;
	   
	    
	    if (airMOTExist == true) {
	    	
	    	whichMot = "Air";
	    	
	    	Utilities.explicitWaitwaitForElementToBeVisible("name", "filter_mot_4", 10);
	    	driver.findElement(By.name("filter_mot_4")).click();
	    	waitUntilRecordCountHasChanged(whichMot, false);
	    	
	    	
	    	Utilities.explicitWaitwaitForElementToBeVisible("xpath", "//div[@content='display-filters']/button[@data-name='AIR']", 10);
	    	Utilities.explicitWaitwaitForElementToBeVisible("xpath", "//span[@content='results-description']", 15);
	    	

	    	driver.findElement(clearMOTfilter).click();
	    	waitUntilRecordCountHasChanged(whichMot, true);

		} else {
			log.info("The Air filter is not available  in the MOT section");
	    
	    }
	    
	    if (seaMOTExist == true) {
	    	
	    	whichMot = "Sea";
	    	
			Utilities.explicitWaitwaitForElementToBeVisible("name", "filter_mot_1", 10);
			
			driver.findElement(By.name("filter_mot_1")).click();
			waitUntilRecordCountHasChanged(whichMot, false);
			
			Utilities.explicitWaitwaitForElementToBeVisible("xpath", "//div[@content='display-filters']/button[@data-name='SEA']", 10);
			Utilities.explicitWaitwaitForElementToBeVisible("xpath", "//span[@content='results-description']", 35);
			
			driver.findElement(clearMOTfilter).click();
			waitUntilRecordCountHasChanged(whichMot, true);
			
			
		} else {
			log.info("The Sea filter is not available in the MOT section");
		}
	    
	    
	    if (noMOTExist == true) {
	    	
	    	whichMot = "None";
	    	
			Utilities.explicitWaitwaitForElementToBeVisible("name", "filter_mot_-1", 10);
			
			driver.findElement(By.name("filter_mot_-1")).click();
			waitUntilRecordCountHasChanged(whichMot, false);
			
			Utilities.explicitWaitwaitForElementToBeVisible("xpath", "//div[@content='display-filters']/button[@data-name='No MOT']", 10);
			Utilities.explicitWaitwaitForElementToBeVisible("xpath", "//span[@content='results-description']", 25);
			
			driver.findElement(clearMOTfilter).click();
			waitUntilRecordCountHasChanged(whichMot, true);
		
			
		} else {
			log.info("The NoMot filter is not available in the MOT section");
		}
	   
	    if (railMOTExistt == true) {
	    	
	    	whichMot = "Rail";
	    	
			Utilities.explicitWaitwaitForElementToBeVisible("name", "filter_mot_2", 10);
			
			driver.findElement(By.name("filter_mot_2")).click();
			waitUntilRecordCountHasChanged(whichMot, false);
			
			Utilities.explicitWaitwaitForElementToBeVisible("xpath", "//div[@content='display-filters']/button[@data-name='RAIL']", 10);
			Utilities.explicitWaitwaitForElementToBeVisible("xpath", "//span[@content='results-description']", 25);
			
			driver.findElement(clearMOTfilter).click();
			waitUntilRecordCountHasChanged(whichMot, true);
		
		} else {
			log.info("The Rail filter is not available");
		}
	    
	    if (truckMOTExist == true) {
	    	
	    	whichMot = "Truck";
	    	
			explicitWaitwaitForElementToBeVisible("name", "filter_mot_3", 10);
			
			driver.findElement(By.name("filter_mot_3")).click();
			waitUntilRecordCountHasChanged(whichMot, false);
			
			explicitWaitwaitForElementToBeVisible("xpath", "//div[@content='display-filters']/button[@data-name='TRUCK']", 10);
			explicitWaitwaitForElementToBeVisible("xpath", "//span[@content='results-description']", 25);
			
			driver.findElement(clearMOTfilter).click();
			waitUntilRecordCountHasChanged(whichMot, true);
			
		} else {
			log.info("The Truck filter is not available");
		}
	} 
	
	public void checkSpecifiedMOTFilterExistsAndClickOnIt (String modeOfTransrpot) throws Exception {
		
		
		switch(modeOfTransrpot){
		
		case "Sea":
			
			explicitWaitwaitForElementToBeVisible("name", "filter_mot_1", 10);
			log.info(modeOfTransrpot + "  Element has became visible");

			waitForElementToLongerBeStale(seaFilterCheckbox, 10);
			
			driver.findElement(By.name("filter_mot_1")).click();
			waitUntilRecordCountHasChanged(modeOfTransrpot, false);
			break;
		
		case "Rail":
			
			explicitWaitwaitForElementToBeVisible("name", "filter_mot_2", 10);
			log.info(modeOfTransrpot + "  Element has became visible");
			
			waitForElementToLongerBeStale(railFilterCheckbox, 10);
			
			driver.findElement(By.name("filter_mot_2")).click();
			waitUntilRecordCountHasChanged(modeOfTransrpot, false);
			break;
			
		case "Truck":
			
			explicitWaitwaitForElementToBeVisible("name", "filter_mot_3", 10);
			log.info(modeOfTransrpot + "  Element has became visible");
			
			waitForElementToLongerBeStale(truckFilterCheckbox, 10);
			
			driver.findElement(By.name("filter_mot_3")).click();
			waitUntilRecordCountHasChanged(modeOfTransrpot, false);
			break;
			
		case "Air":
			
			explicitWaitwaitForElementToBeVisible("name", "filter_mot_4", 10);
			log.info(modeOfTransrpot + "  Element has became visible");
			
			waitForElementToLongerBeStale(airFilterCheckbox, 10);
			
	    	driver.findElement(By.name("filter_mot_4")).click();
	    	waitUntilRecordCountHasChanged(modeOfTransrpot, false);
			break;
			
		case "No MOT":
			
			explicitWaitwaitForElementToBeVisible("name", "filter_mot_-1", 10);
			log.info(modeOfTransrpot + "  Element has became visible");
			
			waitForElementToLongerBeStale(noMotFilterCheckbox, 10);
			
			driver.findElement(By.name("filter_mot_-1")).click();
			waitUntilRecordCountHasChanged("None", false);
			break;
			
		}
		
	}
	
	public void waitUntilRecordCountHasChanged(String whichMot, boolean verifyLoadingStatus) throws Exception {
	   
	    
		boolean recordCountHasChanged = false;
		boolean recordCountMatches = false;
		int loopIterator = 0;
		String anyMotCountSideBar = null;
		String newRecordStringText;
		String anyMotCountSummary = null;
		
		
		if(verifyLoadingStatus == true){
			
			do{
				
		    	anyMotCountSummary  = driver.findElement(summaryTableRecords).getText();
		    	newRecordStringText = anyMotCountSummary;
			
		    	if(previousrecordText.equals(newRecordStringText)){
		    		
		    		if(loopIterator == 60) recordCountHasChanged = true; //to handle rare instance of the previous and current text record count being the same.
		    		recordCountHasChanged = false;
		    	}else{
		    		
		    		boolean isElementStillLoading =  Utilities.validateRegularExpression(anyMotCountSummary, "\\bLoading\\b"); //checks if element is stil loading
		    		
		    		if(isElementStillLoading == true){
		    			recordCountHasChanged = false;
		    		}else{
		    			recordCountHasChanged = true;
		    		}
		    	}
		    	
		    	//to avoid the infinite loop////////////////////////////////////////////////////////////////
		    	if(loopIterator == 120) throw new Exception("Record count taking longer than 1 minute! Aborted Test");
		    	////////////////////////////////////////////////////////////////////////////////////////////
		    	
		    	try{
		    		Thread.sleep(500);
		    	}catch(Exception e){
		    		log.error(e);
		    	}
		    	
		    	loopIterator++;
			
			}while(recordCountHasChanged == false);
		
			
		}else{
			
		
			do{
				
				
					////Account summary side bar/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					anyMotCountSummary  = driver.findElement(summaryTableRecords).getText();
					previousrecordText = anyMotCountSummary;
					
					recordCountMatches =  Utilities.validateRegularExpression(anyMotCountSummary, "\\bof\\b");
					//int lengthOfString = anyMotCountSummary.length();
					
					if (recordCountMatches == true) {
						anyMotCountSummary = anyMotCountSummary.substring(anyMotCountSummary.indexOf("of") + 3, anyMotCountSummary.indexOf("records"));
					} else {
						anyMotCountSummary = anyMotCountSummary.substring(anyMotCountSummary.indexOf("Showing") + 8, anyMotCountSummary.indexOf("records"));
					}
			    	
					anyMotCountSummary = anyMotCountSummary.trim();
			    	
			    	int anyMotsummaryrecord = Integer.parseInt(anyMotCountSummary);
			    	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			    	
			    	////////side bar count////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			    	if(whichMot.equalsIgnoreCase("Sea")){
			    		anyMotCountSideBar  = driver.findElement(seaMOT).getText();
			    		
			    	}else if(whichMot.equalsIgnoreCase("Rail")){
			    		anyMotCountSideBar  = driver.findElement(railMOT).getText();
			    		
			    	}else if(whichMot.equalsIgnoreCase("None")){
			    		anyMotCountSideBar  = driver.findElement(noMOT).getText();
			    		
			    	}else if(whichMot.equalsIgnoreCase("Truck")){
			    		anyMotCountSideBar  = driver.findElement(truckMOT).getText();
			    		
			    	}else if(whichMot.equalsIgnoreCase("Air")){
			    		anyMotCountSideBar  = driver.findElement(airMOT).getText();
			    		
			    	}
			    	
			    	
			    	//log.info("The Air MOT is displayed " + anyMotCountSideBar);
			    	
			    	anyMotCountSideBar  = anyMotCountSideBar.substring(anyMotCountSideBar .indexOf("(") + 1, anyMotCountSideBar .indexOf(")"));
			    	//log.info(" The number of Air MOT records are: " + anyMotCountSideBar );
			    	
			    	int anyMotSideBarRecord  = Integer.parseInt(anyMotCountSideBar);
			    	//Utilities.ExplicitWaitwaitForElementToBeVisible("name", "filter_mot_4", 10);
			    	
			    	if(anyMotsummaryrecord == anyMotSideBarRecord){ //check if record count matches side bar count up to a minute
			    		recordCountHasChanged = true;
					}
			    	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				//to avoid the infinite loop////////////////////////////////////////////////////////////////
		    	if(loopIterator == 120) throw new Exception("Record count taking longer than 1 minute Aborted Test");
		    	////////////////////////////////////////////////////////////////////////////////////////////
	
		    	try{
		    		Thread.sleep(500);
		    	}catch(Exception e){
		    		log.error(e);
		    	}
		    	
		    	loopIterator++;
		    	
				
			}while(recordCountHasChanged == false); 
		
		}
   }
	
	public void clickOnShipperToolTip(){
		
		explicitWaitwaitForElementToBeVisible("xpath", "//span[@cell='shipper']/a[@rel='tooltip']", 5);
		driver.findElement(shipperlink).click();
		log.info("Clicked On Shipper Link from the Dashboard Page");
	}
}

