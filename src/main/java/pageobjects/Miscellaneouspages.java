package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.Utilities;


public class Miscellaneouspages extends Utilities{
	
	WebDriver driver;


	//Preferences
	By userPreferencesLink = By.linkText("User Preferences");
	By importCargoLink = By.linkText("Import Cargo");
	By searchEditBoxForUserPreference = By.xpath("//li[@rel='search_input']/input[@class='form-control input-md']");
	By generalCategoryButton = By.linkText("General");
	
	

	public Miscellaneouspages(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void clickOnGeneralCategory(){
		
		driver.findElement(generalCategoryButton).click();
		log.info("Clicked on General Category Button");
	}
	
	public void clickOnUserPreferences(){
		
		driver.findElement(userPreferencesLink).click();
		log.info("Clicked on User Preferences Link");
	}
	
	public void clickOnImportCargo(){
		
		//Utilities.explicitWaitwaitForElementToBeVisible("linkText", importCargoLink.toString(), 5);
		driver.findElement(importCargoLink).click();
		log.info("Clicked on Import Cargo Link");
	}
	
	public void searchForRecentlyUsedApp(String searchKey){
		
		driver.findElement(searchEditBoxForUserPreference).sendKeys(searchKey);
	}
	

	
}

