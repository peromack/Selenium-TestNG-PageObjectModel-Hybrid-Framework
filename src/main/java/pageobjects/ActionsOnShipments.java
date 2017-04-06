package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import resources.Utilities;

public class ActionsOnShipments extends Utilities {
	
	WebDriver driver;
	
	
	By quantityHeldFieldBox = By.id("billHold-quantityHeld");
	
	//Drop Down Menus
	By holdTypeDropDownMenu = By.xpath("(//button[@data-id='billHold-holdTypes'])");
	By documentsRequiredDropDownMenu = By.cssSelector("[data-id$=docsRequired]");
	By holdTypeReasonSelection = By.xpath("//span[contains(text(), 'BTA-CBP Exam Only Hold [12-5, 12-X] (BTA-EXM)')]");
	By holdReasonDropDownMenu = By.cssSelector("[data-id$=holdReasons]");
	
	//Reset Selections for Bill Hold
	By opsNameHQ = By.id("textfor1600");
	By opsNamePGA_OGA = By.id("textfor1800");
	By opsNamePort = By.id("textfor1700");
	By overrideToGeneralReasonsDropDownMenu = By.xpath("//button[@data-id='generalHold-overrideReasons']");
	
	
	//Comments for all Actions
	By newATSTargetReportCommentsFieldBox = By.id("billHold-targetReportComments");
	By cBPorInternalCommentsFieldBox = By.cssSelector("[id$=cbpComments]");
	By commentsToBrokerFieldBox = By.cssSelector("[id$=brokerComments]");
	By commentsToCarrierFieldBox = By.id("billHold-carrierComments");
	
	//Buttons
	By submitButton = By.xpath("(//button[@data-bb-handler='submit'])");
	By resetButton = By.xpath("(//input[@data-bb-handler='reset'])");
	By closeButton = By.xpath("(//button[@data-bb-handler='close'])");
	By okayButton = By.xpath("(//button[@data-bb-handler='success'])");
	By reviewButton = By.xpath("//button[@data-bb-handler='success']");
	
	
	
	
	public ActionsOnShipments(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public void clearQuantityHeldField(){
		
		driver.findElement(quantityHeldFieldBox).clear();
		log.info("Cleared existing quantity");
	}
	
	public void fillQuantityHeldField(String quantity){
		
		driver.findElement(quantityHeldFieldBox).sendKeys(quantity);
		log.info("Filled in user-defined quantity of: " + quantity);
		
	}
	
	public void clickOnHoldReasonDropDownMenu(){
		
		driver.findElement(holdReasonDropDownMenu).click();
		log.info("Clicked on Hold Reason Drop down Menu.");
	}
	
	
	public void selectHoldReasonType(String... Holdreasons){
		
		for (String reason : Holdreasons) {
			String xpathExpression = "//span[contains(text(), '" + reason + "')]";
			driver.findElement(By.xpath(xpathExpression)).click();
		}
		log.info("Selected Hold Reason(s)");
	}
	
	public void clickOnHoldTypDropDownMenu(){
		
		driver.findElement(holdTypeDropDownMenu).click();
		log.info("Clicked on Hold Type Drop down Menu.");

	}
	
	
	public void selectHoldTypeFromHoldTypeMenu(){
		
		driver.findElement(holdTypeReasonSelection).click();
		log.info("Hold Type Reason Selected");
		
	}
	
	public void fillATStargetReportCommentsFieldBox(String comment){
		
		driver.findElement(newATSTargetReportCommentsFieldBox).sendKeys(comment);
		log.info("Commented on the ATS Target field box. Commented includes: " + comment);
	}
	
	public void fillCBPCommentsFieldBox(String comment){
		
		driver.findElement(cBPorInternalCommentsFieldBox).sendKeys(comment);
		log.info("Commented on the CBP field box. Commented includes: " + comment);
	}
	
	
	public void fillCommentsToCarrierFieldBox(String comment){
		
		driver.findElement(commentsToCarrierFieldBox).sendKeys(comment);
		log.info("Commented on the Carrier field box. Commented includes: " + comment);
	}
	
	public void clickOnDocumentsRequiredDropDownMenu(){
		
		driver.findElement(documentsRequiredDropDownMenu).click();
		log.info("Clicked on Document Required Drop Down Menu");
	}
	
	public void clickOnOverridetoGeneralReasonsDropDownMenu(){
		
		driver.findElement(overrideToGeneralReasonsDropDownMenu).click();
		log.info("Clicked on Override To General Reasons Drop Down Menu");
	}

	public void fillInCommentsToBrokerFieldBox(String comment){
	
		driver.findElement(commentsToBrokerFieldBox).sendKeys(comment);
		log.info("Commented on Broker Field Box. Comment includes: " + comment);
	}
	
	public void clickOnsubmitButton(){
		
		driver.findElement(submitButton).click();
		log.info("Clicked on the 'Submit' button");
	}
	
	public void clickOnResetButton(){
		
		driver.findElement(resetButton).click();
		log.info("Clicked on the 'Reset' button");
	}
	
	public void clickOnCloseButton(){
		
		driver.findElement(closeButton).click();
		log.info("Clicked on the 'Close' button");
	}
		
	
	public void fillInOpsNameForHQ(){
		
		driver.findElement(opsNameHQ).sendKeys("Test Message for Operation-HQ");
		log.info("Filled in Generic message for OPS Name HQ");
		
	}
	
	public void fillInOpsNameForPGA_OGA(){
		
		driver.findElement(opsNamePGA_OGA).sendKeys("Test Message for Operation-PGA/OGA");
		log.info("Filled in Generic message for OPS Name PGA_OGA");
	}

	public void fillInOpsNameForPort(){
	
		driver.findElement(opsNamePort).sendKeys("Test message for Operation-Port");
		log.info("Filled in Generic message for OPS Port");
	}
	
	public void verifyThatOpsNameforHQisInvisible(){
		
		explicitWaitwaitForElementToBecomeInvisible("id", opsNameHQ.toString(), 3);
		log.info("OPs name for HQ Web element is now invisible");
	}
	
	public void verifyThatOpsNameforPGA_OGAisInvisible(){
		
		explicitWaitwaitForElementToBecomeInvisible("id", opsNamePGA_OGA.toString(), 3);
		log.info("OPs name for PGA/OGA Web element is now invisible");
	}
	
	public void verifyThatOpsNameforPortisInvisible(){
		
		explicitWaitwaitForElementToBecomeInvisible("id", opsNamePort.toString(), 3);
		log.info("OPs name Web element is now invisible");
	}

	public void clickOnOkayButton(){
		driver.findElement(okayButton).click();
		log.info("Clicked on the 'Okay' button");
		
		String xmlpath = "//div[@class='modal-backdrop fade in']";
		explicitWaitwaitForElementToBecomeInvisible("xpath", xmlpath, 10);
		log.info("Review Submission window has completely closed.");
	}
	
	public void verifySummaryDashboardShipmentReviewMessage() {
		
		String xmlpath = "//strong[contains(text(), '1 Shipment(s) reviewed successfully out of 1')]";
		explicitWaitwaitForElementToBeVisible("xpath", xmlpath, 10);
		log.info("Page check PASSED for Shipment Review(s) Results Popup Form from Summary Dashboard");
		
	}
	
	public void verifySummaryDetailShipmentReviewMessage() {
		
		String shipmentId = driver.findElement(By.id("shipment-number")).getText();
		String xmlpath = "//h4[contains(text(), 'Mark Shipment " + shipmentId + " Reviewed')]";
		explicitWaitwaitForElementToBeVisible("xpath", xmlpath, 10);
		log.info("Page check PASSED for Mark Shipment Reviewed Results Popup Form");
		
	}
	
	public void checkShipmentReviewPopup(){
		
		String xmlpath = "//h4[contains(text(), 'Shipment Review(s)')]";
		explicitWaitwaitForElementToBeVisible("xpath", xmlpath, 10);
		log.info("Page check PASSED for Shipment Review(s) Popup Form");
	}
	
	public void clickOnReviewButton(){
		
		driver.findElement(reviewButton).click();
		log.info("Clicked On Review Button From Shipment Detail page");
	}
	
}

