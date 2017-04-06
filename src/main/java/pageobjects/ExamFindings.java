package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import resources.Utilities;

public class ExamFindings extends Utilities {
	
	WebDriver driver;
	
	
	By quantityHeldFieldBox = By.id("billHold-quantityHeld");
	
	
	//Import Exam Findings Tabs
	By examInfoTab = By.linkText("Exam Info");
	By examFindingsTab = By.linkText("Exam Findings");
	By examResults = By.linkText("Exam Results");
	
	//Import Exam Findings Miscellaneous
	By cancelButton = By.linkText("Cancel");
	By ShipmentsButton = By.linkText("Shipments");
	//By yesButton = By.linkText("Yes");
	//By noButton = By.linkText("No");
	
	//Exam Info Web Elements
	By examPortDropDownMenu = By.id("selected_port");
	By firstPortWhereExamined = By.xpath("//ul[@id='ui-select-choices-0']/li");
	By firmsCodeDropDownMenu = By.xpath("//div[@name='select-firms-code']");
	By firstFirmCodeFromList = By.xpath("//div[@name='select-firms-code']//div//ul//ul/li");
	By examDateBox = By.id("startDate");
	By todaysDate = By.xpath("//ul[@ng-change='dateSelection(date)']/li[2]//button[contains(text(), 'Today')]");
	By examainerFromList = By.xpath("//div[@id='officer']//span[1]");
	By examTypeDropDownMenu = By.xpath("//div[@id='selectExamType']//span[1]");
	By firstExamTypeFromList = By.xpath("//li[@id='ui-select-choices-row-3-0']//span");
	
	//Exam Findings Web Elements
	By discrepancyButton = By.xpath("//div[@id='discrepancyIndicator']/button[2]");
	By toolContainerDropDownMenu = By.xpath("//div[@id='toolContainerNumber']//span");
	By selectFirstContainerFromList = By.xpath("//li[@id='ui-select-choices-row-5-0']//span");
	
	//Exam Results Web Elements
	
	
	
	
	
	
	public ExamFindings(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public void verifyThatExamFindigsPageHasLoaded(){
		
		explicitWaitwaitForElementToBeClickable("id", "selected_port", 20);
		log.info("Exam Findings page have completed Loading");
	}
	
	public void clickWhereExamWasDoneDropDownMenu() {
		
		driver.findElement(examPortDropDownMenu).click();
		log.info("Clicked on Exam Port Drop Down Menu");
		
	}
	
	public void selectFirstPortWhereExamined() {
		
		driver.findElement(firstPortWhereExamined).click();
		log.info("Selected First Port on Exam Port Drop Down Menu");
		
	}
	
	public void clickFirmsCodeDropDownMenu() {
		
		driver.findElement(firmsCodeDropDownMenu).click();
		log.info("Clicked on Firm Code Drop Down Menu");
	}
	
	public void selectFirstFirmCodeFromList() {
		
		driver.findElement(firstFirmCodeFromList).click();
		log.info("Selected First Firm Code From List");
	}
	
	public void clickExamDateBox() {
		
		driver.findElement(examDateBox).click();
		log.info("Clicked on Exam Date Box");
	}
	
	public void selectTodaysDate() {
		
		driver.findElement(todaysDate).click();
		log.info("Selected System Date");
	}
	
	public void clickExaminerDropDownMenu() {
		
		driver.findElement(examainerFromList).click();
		log.info("Clicked on Examiner Drop Down Menu");
	}
	
	public void selectExaminerFromList(){
		
		driver.findElement(examainerFromList).click();
		log.info("Selected first examiner from list");
	}
	
	public void clickExamTypeDropDownMenu(){
		
		explicitWaitwaitForElementToBeVisible("xpath", "//div[@id='selectExamType']//span[1]", 10);
		driver.findElement(examTypeDropDownMenu).click();
		log.info("Clicked on Exam Type Drop Down Menu");
		
	}
	
	public void selectFirstExamTypeFromList(){
		
		explicitWaitwaitForElementToBeVisible("xpath", "//li[@id='ui-select-choices-row-3-0']//span", 20);
		driver.findElement(firstExamTypeFromList).click();
		log.info("Selected first exam type from list");
		
	}
	
	public void clickDiscrepancyButton() {
		
		driver.findElement(discrepancyButton).click();
		log.info("Clicked on discepancy button");
	}
	
	public void clickContainerDropDownMenu() {
		
		explicitWaitwaitForElementToBeClickable("xpath", "//div[@id='toolContainerNumber']//span", 5);
		driver.findElement(toolContainerDropDownMenu).click();
		log.info("Click Tool Container drop down menu");
	}
	
	public void selectFirstContainerFromList() {
	
		explicitWaitwaitForElementToBeClickable("xpath", "//li[@id='ui-select-choices-row-5-0']//span", 5);
		driver.findElement(selectFirstContainerFromList).click();
		log.info("Select first container from list");
	}	

	public void clickCancelButton() {
	
		explicitWaitwaitForElementToBeClickable("linkText", "Cancel", 5);
		driver.findElement(cancelButton).click();
		log.info("Clicked on Cancel Button");
	}
	
	public void clickExamFindingsTab() {
		
		driver.findElement(examFindingsTab).click();
		log.info("Clicked on Exam Findings Tab");
	}
	
	public void verifyThatExamFindingsTabDisplays(){
		
		explicitWaitwaitForElementToBeVisible("id", "FindingsForm", 15);
		log.info("Exam Findings Form has loaded");
		
	}
	
	
	
}

