package pageobjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	By ladingNum = By.id("BillNumber");
	By acasHouseLad = By.id("houseladingNum");
	By entryNum = By.id("entryNumber");
	By altLading = By.id("ladingNum");
	By time = By.id("time");
	By date = By.id("date");
	By departureDate = By.id("dateOfDeparture");
	By processDate = By.id("processDate");
	By arrivalDate = By.id("arrivalDate");
	By port = By.id("portOfArrival");
	By shName = By.id("shName");
	By shAddress = By.id("shAddress");
	By shGeo = By.id("shGeo");
	By cnName = By.id("cnName");
	By cnAddress = By.id("cnAddress");
	By cnGeo = By.id("cnGeo");
	By PreLimStateDate = By.id("PrelimStateDate");
	
	//Destinations
	By availableEndPoint = By.id("endpointSelect");
	By devEndPoint = By.xpath("//div[@id='endpointSelect']/option");
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
		 ABE =  M1 Air
		 MAIL = Mail Loader
		 RFOR = R4 Truck Loader
		 ACAS = SFA
		 */
	}
	
	public String setLadingNumber(String optionalPrefixId){
		
		//Generate unique lading number
		Random rand = new Random();
		int  randomNumber = 10000000 + rand.nextInt(90000000);
		//int  randomNumber = rand.nextInt(99999999) + 00000000;
		log.info("Random Number Generated is: " + randomNumber);
		
		driver.findElement(ladingNum).sendKeys(Keys.chord(Keys.CONTROL, "a"), optionalPrefixId + randomNumber);
		log.info("Set a random lading number");
		String tmp = optionalPrefixId + randomNumber;
		
		return tmp;
		
		/*
		 999 = Ocean 
		 888 = Rail
		 777 = M1 Air
		 666 = R4 Truck
		 555 = Mail Loader
		 444 = ACAS
		 */
	}
	
	public void setLadingNumberDoNotGenerate(String existingBillNum){
		
		driver.findElement(ladingNum).sendKeys(Keys.chord(Keys.CONTROL, "a"), existingBillNum);
		log.info("Set an existing lading number");
		
	}
	
	public void setEntryNumberDoNotGenerate(String existingEntryNum){
		
		driver.findElement(entryNum).sendKeys(Keys.chord(Keys.CONTROL, "a"), existingEntryNum);
		log.info("Set an existing Entry number");
		
	}
	
	public String setAltLadingNum(String fourDigitIdentifier){
		
		//Generate unique lading number
				Random rand = new Random();
				int  randomNumber = 10000 + rand.nextInt(90000);
				//int  randomNumber = rand.nextInt(99999) + 10000;
				log.info("Random Number Generated is: " + randomNumber);
				
				driver.findElement(altLading).sendKeys(Keys.chord(Keys.CONTROL, "a"), fourDigitIdentifier + randomNumber);
				log.info("Set a random lading number: " + fourDigitIdentifier + randomNumber);
				String tmp = fourDigitIdentifier + randomNumber;
				
				return tmp;
	}
	
	public String setEntryNum(String optionalPrefixDigit){
		
				//Generate unique entry number
				Random rand = new Random();
				int  randomNumber = 10000000 + rand.nextInt(90000000);
				//int  randomNumber = rand.nextInt(99999999) + 10000000;
				log.info("Random Number Generated is: " + randomNumber);
				
				driver.findElement(entryNum).sendKeys(Keys.chord(Keys.CONTROL, "a"), optionalPrefixDigit + randomNumber);
				log.info("Set a random entry number: " + optionalPrefixDigit + randomNumber);
				String tmp = optionalPrefixDigit + randomNumber;
				
				return tmp;
	}
	
	public String setAcasHouseLadingNumber(String fourDigitIdentifier){
		
		//Generate unique lading number
		Random rand = new Random();
		int  randomNumber = 10000 + rand.nextInt(90000);
		//int  randomNumber = rand.nextInt(99999) + 10000;
		log.info("Random Number Generated is: " + randomNumber);

		
		driver.findElement(acasHouseLad).sendKeys(Keys.chord(Keys.CONTROL, "a"), fourDigitIdentifier + randomNumber);
		log.info("Set a random lading number");
		String tmp = fourDigitIdentifier + randomNumber;
		
		return tmp;
		
		/*
		 999 = Ocean 
		 888 = Rail
		 777 = M1 Air
		 666 = R4 Truck
		 555 = Mail Loader
		 444 = ACAS
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
		 SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		 String DateToStr = format.format(curDate);
		
		
		driver.findElement(date).sendKeys(Keys.chord(Keys.CONTROL, "a"), DateToStr);
		log.info("Set Current date in HH:mm:ss");
	}
	
	public void setEntrSumDate(){
		
		 Date curDate = new Date();
		 SimpleDateFormat format = new SimpleDateFormat("MMddyy");
		 String DateToStr = format.format(curDate);
		
		
		driver.findElement(date).sendKeys(Keys.chord(Keys.CONTROL, "a"), DateToStr);
		log.info("Set Entry Summary date in MMddyy");
	}
	
	public void setPrelimEntrySummaryDate()throws Exception{
		
		 Date curDate = new Date();
		 SimpleDateFormat format = new SimpleDateFormat("MMddyy");
		 String DateToStr = format.format(curDate);
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(format.parse(DateToStr));
		 
		 cal.add(Calendar.DATE, 1);
		 
		 if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			 cal.add(Calendar.DATE, 3);
		 }
		 
		 DateToStr = format.format(cal.getTime());
		
		driver.findElement(PreLimStateDate).sendKeys(Keys.chord(Keys.CONTROL, "a"), DateToStr);
		log.info("Set Current date in MM:dd:yy");
	}
	
//	public void setPreLimEntrySummaryDate(){
//		
//		Date curDate = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyy");
//		Calendar cal = Calendar.getInstance();
//		cal.setTime( dateFormat.parse(curDate));
//		cal.add(Calendar.DATE, 1);
//		
//		
//		driver.findElement().sendKeys(Keys.chord(Keys.CONTROL, "a"), DateToStr);
//		log.info("Set Current date in MM:dd:yy");
//	}
	
	public void setDepartureDate(){
		
		 Date curDate = new Date();
		 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		 String DateToStr = format.format(curDate);
		
		
		driver.findElement(departureDate).sendKeys(Keys.chord(Keys.CONTROL, "a"), DateToStr);
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
	
	public void setEndPointToDev(){
		
		driver.findElement(availableEndPoint).click();
		log.info("Clicked on DEV Endpoint");
		
		Utilities.explicitWaitwaitForElementToBeVisible("xpath", "//div[@id='endpointSelect']/option", 5);
		
		driver.findElement(devEndPoint);
		log.info("Set DEV Endpoint to DEV Loader");
		
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

