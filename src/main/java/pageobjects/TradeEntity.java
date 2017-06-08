package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import resources.Utilities;


import org.openqa.selenium.WebElement;

public class TradeEntity extends Utilities{
	
	WebDriver driver;
	
	//Trade Entity Search
	//By billHoldTab = By.xpath("//li[@data-target='holds_bill_hold']/a");
	
	
	//Trade Entities
	//By tradeEntities = By.linkText("Post Bill Hold");
	
		
	
	//Trade Entity details for Entity Key
	//By selectShipmentCheckBox = By.xpath("//input[@name='selectShipment']");
	
	
	//variables
	String tradeEntityPageName = "Trade Entity Dashboard";
	

	public TradeEntity(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	
	public String TradeEntityPageCheck(){
		
		log.info("Verified Page Check for Trade Entity Dashboard");
		return tradeEntityPageName;
		
	}
	
	public void waitForTradeEntitiesDashboardToFinishLoading(){
		
		explicitWaitwaitForElementToBeVisible("id", "entityDetails", 30);
		log.info("Trade Entities Dashboard has completed loading");
	}
}

