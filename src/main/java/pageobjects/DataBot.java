package pageobjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import resources.Utilities;


public class DataBot extends Utilities{
	
	WebDriver driver;


	//Tabs
	By fieldsTab = By.linkText("Fields");
	By destinationTab = By.linkText("Destinations");

	
	//Fields
	By scac = By.id("scac");
	By ladingNum = By.id("ladingNum");
	By time = By.id("time");
	By date = By.id("date");
	By processDate = By.id("processDate");
	By arrivalDate = By.id("arrivalDate");
	By port = By.id("port");
	By shName = By.id("shName");
	By shAddress = By.id("shAddress");
	By shGeo = By.id("shGeo");
	By cnName = By.id("cnName");
	By cnAddress = By.id("cnAddress");
	By cnGeo = By.id("cnGeo");
	
	//Destinations
	By name = By.id("connName");
	By destination = By.id("destName");
	By hostName = By.id("hostname");
	By hostPort = By.id("port");
	By queueManager = By.id("qManager");
	By queueChannel = By.id("qChannel");
	
	//Buttons
	By sendMessgeButton = By.cssSelector("input[value='Send Message']");
	By resetToDefaultsButton = By.cssSelector("input[value='Reset to Defaults']");
	
	//Custom messages
	By messageWebConfirmation = By.cssSelector("div[class='alert alert-dismissable alert-success'] :last-child");

	

	public DataBot(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	//Tabs Methods////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void clickOnFields(){
		
		driver.findElement(fieldsTab).click();
		Utilities.explicitWaitwaitForElementToBeVisible("id", "scac", 10);
		log.info("Clicked on Fields tab");
	}
	
	public void clickOnDestinations(){
		
		driver.findElement(destinationTab).click();
		Utilities.explicitWaitwaitForElementToBeVisible("id", "connName", 10);
		log.info("Clicked on destinations tab");

	}
	
	
	//Fields Methods//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setScac(String userScac){
		
		driver.findElement(scac).sendKeys(Keys.chord(Keys.CONTROL, "a"), userScac);
		log.info("Set Scac");
		
		/*
		 OCEA = Ocean Loader
		 RAIL = Rail Loader
		 MAIL = Mail Loader
		 RFOR = R4 Truck Loader
		 */
	}
	
	public void setLadingNumber(String fourDigitIdentifier){
		
		//Generate unique lading number
		Random rand = new Random();
		int  randomNumber = rand.nextInt(99999) + 00000;

		
		driver.findElement(ladingNum).sendKeys(Keys.chord(Keys.CONTROL, "a"), fourDigitIdentifier + randomNumber);
		log.info("Set a random lading number");
		
		/*
		 999 = Ocean 
		 888 = Rail
		 777 = M1 Air
		 666 = R4 Truck
		 555 = Mail Loader
		 444 = future loader
		 */
	}
	
	public void setCurrentTime(){
		
		 Date curDate = new Date();
		 SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		 String DateToStr = format.format(curDate);
		
		driver.findElement(time).sendKeys(Keys.chord(Keys.CONTROL, "a"), DateToStr + 0 + 0);
		log.info("Set Current Time in HH:mm:ss:DD");
	}
	
	public void setDate(){
		
		 Date curDate = new Date();
		 SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		 String DateToStr = format.format(curDate);
		
		
		driver.findElement(date).sendKeys(Keys.chord(Keys.CONTROL, "a"), DateToStr);
		log.info("Set Current date in HH:mm:ss");
	}
	
	public void setProcessDate(){
		
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String DateToStr = format.format(curDate);
		
		driver.findElement(processDate).sendKeys(Keys.chord(Keys.CONTROL, "a"), DateToStr);
		log.info("Set Process date");

	}
	
	public void setArrivalDate(){
		
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String DateToStr = format.format(curDate);
		
		driver.findElement(arrivalDate).sendKeys(Keys.chord(Keys.CONTROL, "a"), DateToStr);
		log.info("Set Arrival date");

	}
	
	public void setPort(String portOfLading){
		
		driver.findElement(port).sendKeys(Keys.chord(Keys.CONTROL, "a"), portOfLading);
		log.info("Set Port of Lading");

	}
	
	public void setShipperName(String shipperName){ //CBP will accept up to 35 alpha/numeric characters in this element
		
		driver.findElement(shName).sendKeys(Keys.chord(Keys.CONTROL, "a"), shipperName);
		log.info("Set Shipper Name");

	}
	
	public void setShipperAddress(String shipperAddress){
		
		driver.findElement(shAddress).sendKeys(Keys.chord(Keys.CONTROL, "a"), shipperAddress); 
		log.info("Set Shipper Address");

	}
	
	public void setShipperGeo(String shipperGeo){
		
		driver.findElement(shGeo).sendKeys(Keys.chord(Keys.CONTROL, "a"), shipperGeo);
		log.info("Set Shipper Geography");

	}
	
	public void setConveyanceName(String conveyanceName){
				
		driver.findElement(cnName).sendKeys(Keys.chord(Keys.CONTROL, "a"), conveyanceName);
		log.info("Set Conveyance Name");

	}
	
	public void setConveyanceAddress(String conveyanceAddress){
		
		driver.findElement(cnAddress).sendKeys(Keys.chord(Keys.CONTROL, "a"), conveyanceAddress);
		log.info("Set Conveyance Address");

	}
	
	public void setConveyanceGeo(String conveyanceGeo){
		
		driver.findElement(cnGeo).sendKeys(Keys.chord(Keys.CONTROL, "a"), conveyanceGeo);
		log.info("Set Conveyance Geography");

	}
	
	///////////////////////////////////////////Destination Method//////////////////////////////////////////////////////////////////////	
	public void setDestinationName(String destName){
		
		driver.findElement(name).sendKeys(Keys.chord(Keys.CONTROL, "a"), destName);
		log.info("Set Destination Name");

	}
	
	public void setDestinationAddress(String destinationAddress){
		
		driver.findElement(destination).sendKeys(Keys.chord(Keys.CONTROL, "a"), destinationAddress);
		log.info("Set Destination Name");

	}
	
	public void setHostName(String host){

		driver.findElement(hostName).sendKeys(Keys.chord(Keys.CONTROL, "a"), host);
		log.info("Set Host Name");

	}
	
	public void setHostPort(String Port){
		
		driver.findElement(hostPort).sendKeys(Keys.chord(Keys.CONTROL, "a"), Port);
		log.info("Set Host Port");

	}
	
	public void setQueueManager(String strqueueManager){
		
		driver.findElement(queueManager).sendKeys(Keys.chord(Keys.CONTROL, "a"), strqueueManager);
		log.info("Set queueManager Name");

	}
	
	public void setQueueChannel(String strQueueChannel){
		
		driver.findElement(queueChannel).sendKeys(Keys.chord(Keys.CONTROL, "a"), strQueueChannel);
		log.info("Set queueChannel");

	}
	
	public void clickOnSendMessageButton(){
		
		driver.findElement(sendMessgeButton).click();
		log.info("Clicked on Send Message Button");
		
		explicitWaitwaitForElementToBeVisible("cssSelector", "div[class='alert alert-dismissable alert-success'] :last-child", 5);
		
	}
	
	public void clickOnResetToDefaults(){
		
		driver.findElement(resetToDefaultsButton).click();
		log.info("Clicked on Reset to Defaults Button");
	
	}
	
	public void verifyMessageWebConfirmation() throws Exception{
		
		String expectedText = "Message sent successfully!";
		String actualText = driver.findElement(messageWebConfirmation).getText();
		
		if(expectedText.equalsIgnoreCase(actualText)){
			log.info("Message Successfully placed on MQ");
		}else{
			throw new Exception("Unable to send message using databot");
		}
		
	}

	
}

