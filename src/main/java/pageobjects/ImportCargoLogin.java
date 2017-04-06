package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.Utilities;

public class ImportCargoLogin extends Utilities {
	
	WebDriver driver;
	
	By testLoginFilterBox = By.id("testUserFilter");
	By testAccount = By.cssSelector("a[data-username='HHH4001']");
	By testLoginResponse = By.xpath("//h3[contains(text(), 'Logon response for user: HHH4001')]");
	By importCargoHyperLink = By.linkText("Import Cargo");
	
	public ImportCargoLogin(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public void filterTestAccount() {
		
		driver.findElement(testLoginFilterBox).sendKeys("HHH4001");
		
	}
	
	public void clickOnFilteredTestAccount() {
		
		driver.findElement(testAccount).click();
		
	}
	
	public boolean verifyLoginResponse() {
		
		explicitWaitFindElement("xpath", "//h3[contains(text(), 'Logon response for user: HHH4001')]", 10);
		
		boolean isResponseDisplayed = driver.findElement(testLoginResponse).isDisplayed();
		
		if (isResponseDisplayed == true){
			return true;
		}else{
			return false;
		}
	}
	
	public void clickOnImportCargoLink() {
		
		driver.findElement(importCargoHyperLink).click();
	}
	

}
