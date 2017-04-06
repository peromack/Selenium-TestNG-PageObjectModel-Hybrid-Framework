

package resources;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esotericsoftware.yamlbeans.YamlReader;

import scenarioselection.RunSelectScenarioApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.security.Security.addProvider(new OraclePKIProvider());


public class Utilities  {
	
	
	protected static WebDriver driver;
	static WebDriverWait wait;
	
	//Member classes for Log4j////////////////////
	public static Logger log;
	static Logger rootLogger;
	static PatternLayout layout;
	static ConsoleAppender consoleAppender;
	static FileAppender fileAppender;
	static String timeStamp;
	public static String parentHandle, parentHandle2, parentHandle3;
	public static String tmp, tmp2, tmp3, tmp4;
	
	//Variables used for XML files
	public static int ChildViewInc;
	public static String tmpColumn;
	public static String CurrentViewType;
	
	// JSON file declaration(s)
	public static JSONObject obj;
	public static JSONArray JSONlist;
	public static String ScenarioName, RunTimeScenarioName;
	public static String ScenarioStatus;
	public static String ScenarioPath;
	public static String RunTimeEnv, RunTimeEnvFromJsonFile;
	public static String PreviousRunTimeEnvFromJsonFile = "";
	public static String RunTimeBrowser, RunTimeBrowserFromJsonFile; 
	public static String PreviousRunTimeBrowserFromJsonFile = "";
	public static String RunTimeRemoteWebDriverSettings;
	
	//App Window declaration(s)
	public static boolean AppWindowClosed;
	
	public static int ScenarioIndex;
	public static int ScenarioCnt;
	public static String RunTimeDriverScenario;
	
	public static String valueForScenarioSelection;
	public static String closeAllWindowsBetweenRuns;
	public static int FolderCount;
	public static boolean skipLogin;
	
	public static int MaxRetryCount, runningReTryCount;
	public static boolean varRetryMechanism;
	public static boolean PreviousTestCaseHasFailed;
	public static boolean skipCondition = false;
	public static boolean beforeMethodExecuted = false;
	
	/////////ORACLE CONNECTION/////////////////////////////////////////////////////////////////////////////////////
	public static Connection myCon = null;
	public static ResultSet result;
	public static String devServerName = "d521pr";
	public static String satServerName = "s556db";
	
	public static String devHost = "cbp-dm12.sat.cbp.dhs.gov";
	public static String satHost = "cbp-dm17.sat.cbp.dhs.gov";
	
	public static String username = "icstdaut";
	
	public static String devPassword = "3#Reston";
	public static String satPassword = "3#Reston";
	
	public static String portNumber = "1521";
	
	public static String devUrl = "jdbc:oracle:thin:@//" + devHost + ":" + portNumber + "/" + devServerName;
	public static String satUrl = "jdbc:oracle:thin:@//" + satHost + ":" + portNumber + "/" + satServerName;
	//public static String walletUrl = "jdbc:oracle:thin:/@importCargoProxySsl";

	public static Statement stmt = null;
	
	public static String query;
	public static boolean expectedResultMatchesActual;
	public static String actualQueryResult;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void initalizeLog4j() {
		
		layout = new PatternLayout();
		consoleAppender = new ConsoleAppender();
		fileAppender = new FileAppender();
	}
	
	public static void createNewLogFile(String RunTimeBrowser, String RunType, String RunTimeEnv, String RunTimeTestCase){
		
		String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
		layout.setConversionPattern(conversionPattern);

		// creates console appender

		consoleAppender.setLayout(layout);
		consoleAppender.activateOptions();

		// Get timestamp and return to a simple date format.
		timeStamp = new SimpleDateFormat("yyyyMMdd.HHmmss").format(new Date());

		// creates file appender
		fileAppender = new FileAppender();
		//fileAppender.setFile("Logs/" + timeStamp);

		if(RunType.contentEquals("Local")){
			fileAppender.setFile("C:/Selenium/logs/" + RunTimeTestCase + "_" 
					+ RunTimeEnv + "_" + RunTimeBrowser 
					+ "_" + timeStamp + ".log4j");
		}
		
		fileAppender.setLayout(layout);
		fileAppender.activateOptions();

		try { 
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// configures the root logger
		rootLogger = Logger.getRootLogger();
		
		if(RunType.contentEquals("Local")){
			rootLogger.setLevel(Level.INFO);
		}else{
			rootLogger.setLevel(Level.OFF);
		}
		
		
		
		rootLogger.addAppender(consoleAppender);
		rootLogger.addAppender(fileAppender);

		// creates a custom logger and log messages
		log = Logger.getLogger(Utilities.class);
	}

	public static void validateFilePathsForLocalMachine() {


		/*
		 * This method is used to validate that all the appropriate directories
		 * exist in order to properly run the automated tests locally. Refer to
		 * README file for more details.
		 */

		File SeleniumFolder = new File("C:\\Selenium");
		if (!SeleniumFolder.exists()) {
			if (SeleniumFolder.mkdir()) {
				System.out.println("C://Selenium directory Created!!");
			} else {
				System.out.println("Failed to create C://Selenium directory");
				System.exit(0);
			}
		}
		
		File LogFolder = new File("C:\\Selenium\\logs");
		if (!LogFolder.exists()) {
			if (LogFolder.mkdirs()) {
				System.out.println("C://Selenium//logs have been created!");
			} else {
				System.out.println("Failed to create C://Selenium//logs directory");
				System.exit(0);
			}
		}
		
		File ScreenShotFolder = new File("C:\\Selenium\\Screenshots");
		if (!ScreenShotFolder.exists()) {
			if (ScreenShotFolder.mkdirs()) {
				System.out.println("C://Selenium//logs have been created!");
			} else {
				System.out.println("Failed to create C://Selenium//Screenshots directory");
				System.exit(0);
			}
		}
		
		File RunTimeScenarioData = new File("C:\\Selenium\\RunTimeScenarioData\\Scenarios");
		if (!RunTimeScenarioData.exists()) {
			if (RunTimeScenarioData.mkdirs()) {
				System.out.println("C://Selenium//RunTimeScenarioData//Scenarios have been created!");
			} else {
				System.out.println("Failed to create C://Selenium//RunTimeScenarioData//Scenarios directory");
				System.exit(0);
			}
		}
		
		File CurrentScenario = new File("C:\\Selenium\\RunTimeScenarioData\\CurrentScenario");
		if (!CurrentScenario.exists()) {
			if (CurrentScenario.mkdirs()) {
				System.out.println("C://Selenium//RunTimeScenarioData//CurrentScenario have been created!");
			} else {
				System.out.println("Failed to create C://Selenium//RunTimeScenarioData//CurrentScenario directory");
				System.exit(0);
			}
		}

	}

	public static void deleteLogsOlderThanNDays() {

		int daysBack = 7; // Logs older than 7 days will be deleted at the end of each run

		File directory = new File("C:\\Selenium\\logs\\");

		if (directory.exists()) {

			File[] listFiles = directory.listFiles();
			long purgeTime = System.currentTimeMillis()
					- (daysBack * 24 * 60 * 60 * 1000);
			for (File listFile : listFiles) {
				if (listFile.lastModified() < purgeTime) {
					if (!listFile.delete()) {
						System.err.println("Unable to delete file: " + listFile);
					}
				}
			}
		}
	}
	
	public static void deleteScreenshotsOlderThanNDays() {

		int daysBack = 7; // Screenshots older than 7 days will be deleted at the end of each run

		File directory = new File("C:\\Selenium\\Screenshots\\");

		if (directory.exists()) {

			File[] listFiles = directory.listFiles();
			long purgeTime = System.currentTimeMillis()
					- (daysBack * 24 * 60 * 60 * 1000);
			for (File listFile : listFiles) {
				if (listFile.lastModified() < purgeTime) {
					if (!listFile.delete()) {
						System.err.println("Unable to delete file: " + listFile);
					}
				}
			}
		}
	}

	public static void internetExplorerBrowserSettings(String RunType) throws Exception {

		
		if (RunType.contentEquals("Local")) {


			final String InitDriver = "webdriver.ie.driver"; // The location of the IE driver binary
			final String startDriver = "C:/Selenium/BrowserDrivers/InternetExplorerDriver/IEDriverServer_x64_2.53.0/IEDriverServer.exe";

			System.setProperty(InitDriver, startDriver);

			// Used to mitigate certain IE issues such as NoSuchElementFound
			DesiredCapabilities cp = DesiredCapabilities.internetExplorer();
			cp.setCapability("enablePersistanceHover", false);
			cp.setCapability("requiredWindowFocus", true);
			cp.setCapability("ignoreZoomSetting", true);
			cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			
			driver = new InternetExplorerDriver(cp);
			System.out.println("Internet Explorer Driver Has Started");

		} else if (RunType.contentEquals("GRID")) { //USE GRID IE

			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.DISMISS); 
			driver = new RemoteWebDriver(new URL("http://10.153.176.186:4444/wd/hub"), capability);
			System.out.println("Internet Explorer Remote WebDriver Has Started");
		
		}else{ //By Default, Local will be used.
			
			final String InitDriver = "webdriver.ie.driver"; // The location of the IE driver binary
			final String startDriver = "C:/Selenium/BrowserDrivers/InternetExplorerDriver/IEDriverServer_x64_2.53.0/IEDriverServer.exe";

			System.setProperty(InitDriver, startDriver);

			// Used to mitigate certain IE issues such as NoSuchElementFound
			DesiredCapabilities cp = DesiredCapabilities.internetExplorer();
			cp.setCapability("enablePersistanceHover", false);
			cp.setCapability("requiredWindowFocus", true);
			cp.setCapability("ignoreZoomSetting", true);
			cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

			driver = new InternetExplorerDriver(cp);
			System.out.println("Default parameter used: Internet Explorer Driver Has Started");

		}

	}
	
	public static void fireFoxDriverBrowserSettings(String RunType) throws Exception {

		if (RunType.contentEquals("Local")) {
			driver = new FirefoxDriver();
			System.out.println("FireFox Driver Has Started");

		}else if (RunType.contentEquals("GRID")) { //USE GRID FireFox
			
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			driver = new RemoteWebDriver(new URL("http://10.153.176.186:4444/wd/hub"), capability);
			System.out.println("FireFox Remote WebDriver Has Started");
		
		}else{ //By Default, Local will be used.
			
			driver = new FirefoxDriver();
			System.out.println("FireFox Driver Has Started");
		}
	}
	
	public static void googleChromeDriverSettings(String RunType) throws Exception {

		if (RunType.contentEquals("Local")) {

			ChromeOptions options = new ChromeOptions();
			final String InitDriver = "webdriver.chrome.driver";
			final String startDriver = "C:/Selenium/BrowserDrivers/chromedriver_win32/chromedriver2.25.exe";
			options.addArguments("chrome.switches", "--disable-extensions");
			System.setProperty(InitDriver, startDriver);

			driver = new ChromeDriver(options);
			//System.out.println("Google Chrome Driver Has Started");

		} else if (RunType.contentEquals("GRID")) { //USE GRID Google Chrome
			
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
			driver = new RemoteWebDriver(new URL("http://10.153.176.186:4444/wd/hub"), capability);
			System.out.println("Google Chrome WebDriver Has Started");
			
		}else{ //By Default, Local will be used.
			
			ChromeOptions options = new ChromeOptions();
			final String InitDriver = "webdriver.chrome.driver";
			final String startDriver = "C:/Selenium/BrowserDrivers/chromedriver_win32/chromedriver2.25.exe";
			options.addArguments("chrome.switches", "--disable-extensions");
			System.setProperty(InitDriver, startDriver);
			
			driver = new ChromeDriver(options);
			//System.out.println("Google Chrome Driver Has Started");
		}
	}
	
	public static void manageDriverOptionsAndLoginToApp(String RunTimeEnv){
		
		 //wait = new FluentWait(driver); //What was this used for again???
		
		 driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // Implicit Wait timeout settings.
		 driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); // Page Load timeout settings.
	     driver.navigate().to("https://apps-" + RunTimeEnv + ".sat.cbp.dhs.gov/ta/sso/test/");
		 driver.manage().window().maximize();
	
		
	}
	
	public static boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}
	
	public static boolean explicitWaitVerifyIfElementIsDisabled(String LocatorMethod, String CustomLocator) {

			WebElement Element;

						if(LocatorMethod.equalsIgnoreCase("cssSelector")){
							Element = driver.findElement(By.cssSelector(CustomLocator));
							
						}else if(LocatorMethod.equalsIgnoreCase("id")){
							Element = driver.findElement(By.id(CustomLocator));
							
						}else if(LocatorMethod.equalsIgnoreCase("linkText")){
							Element = driver.findElement(By.linkText(CustomLocator));
							
						}else if(LocatorMethod.equalsIgnoreCase("name")){
							Element = driver.findElement(By.name(CustomLocator));
							
						}else if(LocatorMethod.equalsIgnoreCase("partialLinkText")){
							Element = driver.findElement(By.partialLinkText(CustomLocator));
							
						}else if(LocatorMethod.equalsIgnoreCase("tagName")){
							Element = driver.findElement(By.tagName(CustomLocator));
							
						}else if(LocatorMethod.equalsIgnoreCase("xpath")){
							Element = driver.findElement(By.xpath(CustomLocator));
							
						}else{
							Element = driver.findElement(By.xpath(CustomLocator));
						}
						
				if(!Element.isEnabled()){
					return true;
				}else{
					return false;
				}	
	}
	
	
	public static void getWindowHandle(int WhichWindowHandle){
		
		switch(WhichWindowHandle){
			case 1:
				parentHandle = driver.getWindowHandle();
				log.info("Current Window Handle stored inside variable: " + parentHandle);
				break;
				
			case 2:
				parentHandle2 = driver.getWindowHandle();
				log.info("Current Window Handle stored inside variable: " + parentHandle2);
				break;
				
			case 3:
				parentHandle3 = driver.getWindowHandle();
				log.info("Current Window Handle stored inside variable: " + parentHandle3);
				break;
		}
		
	}
	
	public static void closeAllWindowsTabsExceptForParent(){
		String originalHandle = parentHandle;
		boolean tmp = true;
		
		for(String handle : driver.getWindowHandles()) {
	        if (!handle.equals(originalHandle)) {
	            driver.switchTo().window(handle);
	            driver.close();
	        }
	    }
		
		
		try{
			tmp = driver.findElement(By.xpath("//button[@data-bb-handler='close']")).isDisplayed();
		}catch(Exception e){
			tmp = false;
		}finally{
			if(tmp == true){
				driver.findElement(By.xpath("//button[@data-bb-handler='close']")).click();
			}
		}

		driver.switchTo().window(parentHandle);
		
	}
	
	
	public static void closeBrowserWindow(){
		driver.close();
		log.info("Closed Tab or Window browser");
		
		driver.switchTo().window(parentHandle);
		log.info("Switched Back To Parent Window browser");
	}
	
	
	public static void switchOrCloseWindowTab(boolean switchWindowOrTab, int ExpectedNumberOfWindows, boolean closeWindowOrTab, int WhichWindowToSwitchBackTo) {

//		ArrayList<String> OpenTabList = new ArrayList<String>(
//				driver.getWindowHandles());
		
		if(switchWindowOrTab == true){
			
			int ActualNumberOfWindowsOrTabsOpened;
			int NumberoFTimesToExecuteLoop = 0;
			
				try{ //The purpose of this try-catch is to ensure the expected number of tabs/windows open match the actual
						
					do{
						ActualNumberOfWindowsOrTabsOpened = driver.getWindowHandles().size();
						Thread.sleep(500);
						NumberoFTimesToExecuteLoop++;
						if(NumberoFTimesToExecuteLoop == 120) ActualNumberOfWindowsOrTabsOpened = ExpectedNumberOfWindows;
					}while(ActualNumberOfWindowsOrTabsOpened != ExpectedNumberOfWindows);
					
						if(NumberoFTimesToExecuteLoop == 120) log.error("Window/Tab took longer than expected to appear");
						
				}catch(Exception e){
					log.error("e");
				}finally{
					log.info("Exited out of Loop to check that expected windows/tabs equal actual windows/tabs");
				}
				
				for (String winHandle : driver.getWindowHandles()) {
				    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				    log.info("Found window: " + winHandle);
				}
			 
		}
		
		if (closeWindowOrTab == true) {
			driver.close();
			log.info("Closed Tab or Window browser");
			
			if(WhichWindowToSwitchBackTo == 0 || WhichWindowToSwitchBackTo == 1){
				driver.switchTo().window(parentHandle);
				log.info("Switched Back To Parent Window browser: " + parentHandle);
			}else if(WhichWindowToSwitchBackTo == 2){
				driver.switchTo().window(parentHandle2);
				log.info("Switched To Window: " + parentHandle2);
			}else if(WhichWindowToSwitchBackTo == 3){
				driver.switchTo().window(parentHandle3);
				log.info("Switched To Window: " + parentHandle3);
			}else{
				log.error("More than three windows are being used! Please modify Function!");
			}
			
			
		}
		
	}
	
	
	public static void explicitWaitFindElement(String LocatorMethod, String CustomLocator, int CustomWait) {

		try{
			@SuppressWarnings("unused")
				WebElement AnyElementName = new WebDriverWait(driver, CustomWait)
					.until(new ExpectedCondition<WebElement>() {
						
						@Override
						public WebElement apply(WebDriver d) {
			
							if(LocatorMethod.equalsIgnoreCase("cssSelector")){
								return d.findElement(By.cssSelector(CustomLocator));
								
							}else if(LocatorMethod.equalsIgnoreCase("id")){
								return d.findElement(By.id(CustomLocator));
								
							}else if(LocatorMethod.equalsIgnoreCase("linkText")){
								return d.findElement(By.linkText(CustomLocator));
								
							}else if(LocatorMethod.equalsIgnoreCase("name")){
								return d.findElement(By.name(CustomLocator));
								
							}else if(LocatorMethod.equalsIgnoreCase("partialLinkText")){
								return d.findElement(By.partialLinkText(CustomLocator));
								
							}else if(LocatorMethod.equalsIgnoreCase("tagName")){
								return d.findElement(By.tagName(CustomLocator));
								
							}else if(LocatorMethod.equalsIgnoreCase("xpath")){
								return d.findElement(By.xpath(CustomLocator));
								
							}else{
								return d.findElement(By.xpath(CustomLocator));
							}
						}
					});
		}catch(Exception e){
			System.out.println("Unable to find element. Exception thrown");
		}
		
	}
	
	public static void explicitWaitwaitForElementToBeClickable(String LocatorMethod, String CustomLocator, int CustomWait){
		
		wait = new WebDriverWait(driver, CustomWait);
			switch(LocatorMethod){
				case "cssSelector": 
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(CustomLocator)));
				break;
				
				case "id": 
					wait.until(ExpectedConditions.elementToBeClickable(By.id(CustomLocator)));
				break;
				
				case "linkText": 
					wait.until(ExpectedConditions.elementToBeClickable(By.linkText(CustomLocator)));
				break;
				
				case "name": 
					wait.until(ExpectedConditions.elementToBeClickable(By.name(CustomLocator)));
				break;
				
				case "partialLinkText": 
					wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(CustomLocator)));
				break;
				
				case "tagName": 
					wait.until(ExpectedConditions.elementToBeClickable(By.tagName(CustomLocator)));
				break;
				
				case "xpath": 
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CustomLocator)));
				break;
			}
	}
	
	public static void explicitWaitwaitForElementToBecomeInvisible(String LocatorMethod, String CustomLocator, int CustomWait){
		
		//boolean notPresent;
		
		wait = new WebDriverWait(driver, CustomWait);
			switch(LocatorMethod){
				case "cssSelector": 
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(CustomLocator)));
				break;
				
				case "id": 
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(CustomLocator)));
				break;
				
				case "linkText": 
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(CustomLocator)));
				break;
				
				case "name": 
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(CustomLocator)));
				break;
				
				case "partialLinkText": 
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText(CustomLocator)));
				break;
				
				case "tagName": 
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(CustomLocator)));
				break;
				
				case "xpath": 
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CustomLocator)));
				break;
			}
	}
	
	public static void explicitWaitwaitForElementToBeVisible(String LocatorMethod, String CustomLocator, int CustomWait){
		
		wait = new WebDriverWait(driver, CustomWait);
			switch(LocatorMethod){
				case "cssSelector": 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CustomLocator)));
				break;
				
				case "id": 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CustomLocator)));
				break;
				
				case "linkText": 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(CustomLocator)));
				break;
				
				case "name": 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(CustomLocator)));
				break;
				
				case "partialLinkText": 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(CustomLocator)));
				break;
				
				case "tagName": 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(CustomLocator)));
				break;
				
				case "xpath": 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CustomLocator)));
				break;
			}
	}
	
	public static void getXMLAppDataColumnInformation() {


		File xmlFile;
		try {

			// XML read file
			SAXBuilder saxBuilder = new SAXBuilder();
			
			
			xmlFile = new File("XMLScenarioDataFiles/" + ScenarioName + ".xml");

			Document document = saxBuilder.build(xmlFile);
			Element rootNode = document.getRootElement();
			tmpColumn = rootNode.getChild("AppData")
					.getChild(CurrentViewType)
					.getChild("ChildView" + ChildViewInc).getText();

		} catch (Exception e) {

			log.error("Failed to get XMl Info. Exception thrown is: " + e);

		}

	}
	
	@SuppressWarnings("rawtypes")
	public static void GetXMLTestDataInformation() {

		try {

			// XML read file
			SAXBuilder saxBuilder = new SAXBuilder();

			File xmlFile = new File("XMLScenarioDataFiles/" + ScenarioName + ".xml");

			Document document = saxBuilder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("TestData");

			for (int i = 0; i < list.size(); i++) {

				//Element node = (Element) list.get(i);

				//ShipmentNumber = node.getChildText("ShipmentID");
				//BillNumber = node.getChildText("BillNumber");
				//Scac = node.getChildText("SCAC");
				//HBill = node.getChildText("HBILL");

			}

		} catch (Exception e) {

			System.out.println("This needs to be caught and handled as accordingly");

		}

	}
	
	public static void SetRunType(String RunType){
		
		tmp = RunType;
	}
	
	public static String GetRunType(){
		
		return tmp;
	}
	
	public static void SetScenarioName(String RunTimeTestCase){
		
		tmp2 = RunTimeTestCase;
	}
	
	public static String GetScenarioName(){
		
		return tmp2;
	}
	
	
	public static void RunScenarioSelectionApp() throws Exception{
		
		RunSelectScenarioApp.RunApp();

		/*
		 * This loop is used to infinitely pause the main thread until the App
		 * thread window is closed out.
		 */
		do {
			Thread.sleep(1000);
		} while (AppWindowClosed == false);
	}
	
	public static boolean validateRegularExpression(String line, String pattern){
		
		boolean matchFound;
		
		// Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      
	   // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	    	 matchFound = true;
	      }else {
	    	  matchFound = false;
	      }
		return matchFound;
	}
	
	public static void CleanDir(String file) {

		// /Cleanup Scenario Directory ////////////////////////////////////
		File folder = new File(file);

		try {
			System.gc();
			Thread.sleep(1000);
			FileUtils.cleanDirectory(folder);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static String GetRunTimeScenarioName(int FolderCount){
		
		File folder = new File("C:\\Selenium\\RunTimeScenarioData\\Scenarios\\"
				+ FolderCount);
		File[] listOfFiles = folder.listFiles();

		RunTimeDriverScenario = listOfFiles[0].getName();
		return RunTimeDriverScenario;
		
	}
	
	
//	public static void GetScenarioCount() {
//
//		File file = new File("C:\\Selenium\\RunTimeScenarioData\\Scenarios\\");
//		File[] files = file.listFiles(new FileFilter() {
//			@Override
//			public boolean accept(File f) {
//				return f.isDirectory();
//			}
//		});
//
//		ScenarioCnt = files.length;
////		System.out.println(files.length
////				+ " Scenario(s) listed in Local Directory \n");
//
//	}
	
//	public static int ReturnScenarioCount(){
//
//		GetScenarioCount();
//		return ScenarioCnt;
//	}
	
	@SuppressWarnings("rawtypes")
	public static void GetPropertyFileInfo() {

		try {
			YamlReader reader;
			reader = new YamlReader(new FileReader("RunTimeDriverData/config.yml"));
		
			Object object = reader.read();
			Map map = (Map) object;
			valueForScenarioSelection = (String) map.get("Scenario-Form");
			closeAllWindowsBetweenRuns = (String) map.get("Close-All-Windows-Between-Runs");
			//String tmp1 = (String) map.get("ScenarioRetryCount");
			// String tmp2 = (String) map.get("ScenarioCnt");
			//String tmp3 = (String) map.get("Session-ID");
			// RunTimeStatus = (String) map.get("RunTimeStatus");
	
			//ScenarioRetryCnt = Integer.parseInt(tmp1);
			// ScenarioCnt = Integer.parseInt(tmp2);
			//SessionId = Integer.parseInt(tmp3);
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void GetRunTimeScenarioStatus(int FolderCount) throws Exception {

		
		try {
			File folder = new File("C:\\Selenium\\RunTimeScenarioData\\Scenarios\\"
					+ FolderCount);
			File[] listOfFiles = folder.listFiles();
	
			RunTimeDriverScenario = listOfFiles[0].getName();
	
			JSONParser parser = new JSONParser();

		
			Object obj = parser.parse(new FileReader(
					"C:/Selenium/RunTimeScenarioData/Scenarios/"
							+ FolderCount + "/" + RunTimeDriverScenario));
			JSONObject jsonObject = (JSONObject) obj;

			// String name = (String) jsonObject.get("index");
			// System.out.println(name);

			// long index = (Long) jsonObject.get("index");
			// System.out.println(index);

			// loop array
			JSONArray msg = (JSONArray) jsonObject.get("ScenarioRunTimeStatus");
			// jsonObject.get(objj);

			Iterator<String> iterator = msg.iterator();

			int inc = 0;
			while (iterator.hasNext()) {

				if (inc == 0) {
					ScenarioStatus = iterator.next().toString();
					ScenarioStatus = (ScenarioStatus.substring(16));
					// System.out.println(msg);
				} else if (inc == 1) {
					RunTimeScenarioName = iterator.next().toString();
					RunTimeScenarioName = (RunTimeScenarioName.substring(14));
				} else if (inc == 2) {
					ScenarioPath = iterator.next().toString();
					ScenarioPath = (ScenarioPath.substring(14));
				} else if (inc == 3) {
					RunTimeEnvFromJsonFile = iterator.next().toString();
					RunTimeEnvFromJsonFile = (RunTimeEnvFromJsonFile.substring(13));
				} else if (inc == 4) {
					RunTimeBrowserFromJsonFile = iterator.next().toString();
					RunTimeBrowserFromJsonFile = (RunTimeBrowserFromJsonFile.substring(17));
				} else if (inc == 5) {
					RunTimeRemoteWebDriverSettings = iterator.next().toString();
					RunTimeRemoteWebDriverSettings = (RunTimeRemoteWebDriverSettings
							.substring(25));
				}

				inc++;
			}

			inc = 0; // Reset

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void ModifyRunTimeStatus(String RunTimeScenarioName, int FolderCount, String ScenarioStatus) {

		obj = new JSONObject();
		JSONlist = new JSONArray();

		
		if (ScenarioStatus == "None") {
			JSONlist.add("ScenarioStatus: " + "None");
		} else if (ScenarioStatus == "Started") {
			JSONlist.add("ScenarioStatus: " + "Started");
		} else if (ScenarioStatus == "Done") {
			JSONlist.add("ScenarioStatus: " + "Done");
		} else { // by default, status is set to "None"
			JSONlist.add("ScenarioStatus: " + "None");
		}

		JSONlist.add("ScenarioName: " + RunTimeScenarioName);
		JSONlist.add("ScenarioPath: " + ScenarioPath);
		JSONlist.add("ScenarioEnv: " + RunTimeEnvFromJsonFile);
		JSONlist.add("ScenarioBrowser: " + RunTimeBrowserFromJsonFile);
		JSONlist.add("RemoteWebDriverSettings: " + RunTimeRemoteWebDriverSettings);

		obj.put("ScenarioRunTimeStatus", JSONlist);

		try {
			FileWriter file = new FileWriter(
					"C:\\Selenium\\RunTimeScenarioData\\Scenarios\\"
							+ FolderCount + "\\" + RunTimeScenarioName + ".json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Utilities.GetRunTimeScenarioStatus(FolderCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ScenarioStatus.equalsIgnoreCase("Started")){
			
			File srcFile = new File("C:\\Selenium\\RunTimeScenarioData\\Scenarios\\" + FolderCount + "\\" + RunTimeScenarioName + ".json");
			File destDir = new File("C:\\Selenium\\RunTimeScenarioData\\CurrentScenario\\");
			
			try {
				Utilities.CleanDir("C:\\Selenium\\RunTimeScenarioData\\CurrentScenario");
				FileUtils.copyFileToDirectory(srcFile, destDir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void SetMaxRetryValue(int maxRetryCount){
		
		MaxRetryCount = maxRetryCount;
	}
	
	public static void SetRunningRetryCount(int retryCount){
		
		runningReTryCount = retryCount;
	}
	
	public static void SetRetryAnalyzerAsExecuted(boolean RetryMechanism){
		
		varRetryMechanism = RetryMechanism;
	}
	
	public static void CloseOpenedRuntimeBrowsers() {

		String line;
		@SuppressWarnings("unused")
		String pidInfo = "";

		Process p = null;

		try {
			p = Runtime.getRuntime().exec(
					System.getenv("windir") + "\\system32\\" + "tasklist.exe");
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		BufferedReader input = new BufferedReader(new InputStreamReader(
				p.getInputStream()));

		try {
			while ((line = input.readLine()) != null) {
				pidInfo += line;
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {
			input.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {

			switch (PreviousRunTimeBrowserFromJsonFile) {

			case "Internet Explorer":
				Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
				break;

			case "FireFox":
				Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
				break;

			case "Google Chrome":
				Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
				break;

			default:
				System.out.println("No Browsers to Destroy!!");
				break;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public static void getXMLTestDataInformation() {

		try {

			// XML read file
			SAXBuilder saxBuilder = new SAXBuilder();

			File xmlFile = new File("XMLScenarioDataFiles/" + ScenarioName + ".xml");

			Document document = saxBuilder.build(xmlFile);
			Element rootNode = document.getRootElement();
			@SuppressWarnings("rawtypes")
			List list = rootNode.getChildren("TestData");

			for (int i = 0; i < list.size(); i++) {

				//Element node = (Element) list.get(i);

				//ShipmentNumber = node.getChildText("ShipmentID");
				//BillNumber = node.getChildText("BillNumber");
				//Scac = node.getChildText("SCAC");
				//HBill = node.getChildText("HBILL");

			}

		} catch (Exception e) {

			System.out.println("This needs to be caught and handled as accordingly");

		}

	}
	
	public static void setRunType(String RunType){
		
		tmp = RunType;
	}
	
	public static String getRunType(){
		
		return tmp;
	}
	
	public static void setRunTimeEnv(String RunTimeEnv){
		
		tmp4 = RunTimeEnv;
	}
	
	public static String getRunTimeEnv(){
		
		return tmp4;
	}
	
//	public static void getAndSetCallerClassName(){
//		
//		String className = GetClassName.getCallerClassName();
//		className = className.substring(10);
//		tmp3 = className;
//	}
	
	public static String getCallerClassNameIntendedForListener(){
		
		return tmp3;
	}
	
	public static void setScenarioName(String RunTimeTestCase){
		
		tmp2 = RunTimeTestCase;
	}
	
	public static String getScenarioName(){
		
		return tmp2;
	}
	
	
	public static void runScenarioSelectionApp() throws Exception{
		
		RunSelectScenarioApp.RunApp();

		/*
		 * This loop is used to infinitely pause the main thread until the App
		 * thread window is closed out.
		 */
		do {
			Thread.sleep(1000);
		} while (AppWindowClosed == false);
	}
	
	public static void cleanDir(String file) {

		// /Cleanup Scenario Directory ////////////////////////////////////
		File folder = new File(file);

		try {
			System.gc();
			Thread.sleep(1000);
			FileUtils.cleanDirectory(folder);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String getRunTimeScenarioName(int FolderCount){
		
		File folder = new File("C:\\Selenium\\RunTimeScenarioData\\Scenarios\\"
				+ FolderCount);
		File[] listOfFiles = folder.listFiles();

		RunTimeDriverScenario = listOfFiles[0].getName();
		return RunTimeDriverScenario;
		
	}
	
	
	public static void getScenarioCount() {

		File file = new File("C:\\Selenium\\RunTimeScenarioData\\Scenarios\\");
		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory();
			}
		});

		ScenarioCnt = files.length;
//		System.out.println(files.length
//				+ " Scenario(s) listed in Local Directory \n");

	}
	
	public static int returnScenarioCount(){

		getScenarioCount();
		return ScenarioCnt;
	}
	
	@SuppressWarnings("rawtypes")
	public static void getPropertyFileInfo() {

		try {
			YamlReader reader;
			reader = new YamlReader(new FileReader("RunTimeDriverData/config.yml"));
		
			Object object = reader.read();
			Map map = (Map) object;
			valueForScenarioSelection = (String) map.get("Scenario-Form");
			closeAllWindowsBetweenRuns = (String) map.get("Close-All-Windows-Between-Runs");
			//String tmp1 = (String) map.get("ScenarioRetryCount");
			// String tmp2 = (String) map.get("ScenarioCnt");
			//String tmp3 = (String) map.get("Session-ID");
			// RunTimeStatus = (String) map.get("RunTimeStatus");
	
			//ScenarioRetryCnt = Integer.parseInt(tmp1);
			// ScenarioCnt = Integer.parseInt(tmp2);
			//SessionId = Integer.parseInt(tmp3);
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getRunTimeScenarioStatus(int FolderCount) throws Exception {

		
		try {
			File folder = new File("C:\\Selenium\\RunTimeScenarioData\\Scenarios\\"
					+ FolderCount);
			File[] listOfFiles = folder.listFiles();
	
			RunTimeDriverScenario = listOfFiles[0].getName();
	
			JSONParser parser = new JSONParser();

		
			Object obj = parser.parse(new FileReader(
					"C:/Selenium/RunTimeScenarioData/Scenarios/"
							+ FolderCount + "/" + RunTimeDriverScenario));
			JSONObject jsonObject = (JSONObject) obj;

			// String name = (String) jsonObject.get("index");
			// System.out.println(name);

			// long index = (Long) jsonObject.get("index");
			// System.out.println(index);

			// loop array
			JSONArray msg = (JSONArray) jsonObject.get("ScenarioRunTimeStatus");
			// jsonObject.get(objj);

			Iterator<String> iterator = msg.iterator();

			int inc = 0;
			while (iterator.hasNext()) {

				if (inc == 0) {
					ScenarioStatus = iterator.next().toString();
					ScenarioStatus = (ScenarioStatus.substring(16));
					// System.out.println(msg);
				} else if (inc == 1) {
					RunTimeScenarioName = iterator.next().toString();
					RunTimeScenarioName = (RunTimeScenarioName.substring(14));
				} else if (inc == 2) {
					ScenarioPath = iterator.next().toString();
					ScenarioPath = (ScenarioPath.substring(14));
				} else if (inc == 3) {
					RunTimeEnvFromJsonFile = iterator.next().toString();
					RunTimeEnvFromJsonFile = (RunTimeEnvFromJsonFile.substring(13));
				} else if (inc == 4) {
					RunTimeBrowserFromJsonFile = iterator.next().toString();
					RunTimeBrowserFromJsonFile = (RunTimeBrowserFromJsonFile.substring(17));
				} else if (inc == 5) {
					RunTimeRemoteWebDriverSettings = iterator.next().toString();
					RunTimeRemoteWebDriverSettings = (RunTimeRemoteWebDriverSettings
							.substring(25));
				}

				inc++;
			}

			inc = 0; // Reset

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void modifyRunTimeStatus(String RunTimeScenarioName, int FolderCount, String ScenarioStatus) {

		obj = new JSONObject();
		JSONlist = new JSONArray();

		
		if (ScenarioStatus == "None") {
			JSONlist.add("ScenarioStatus: " + "None");
		} else if (ScenarioStatus == "Started") {
			JSONlist.add("ScenarioStatus: " + "Started");
		} else if (ScenarioStatus == "Done") {
			JSONlist.add("ScenarioStatus: " + "Done");
		} else { // by default, status is set to "None"
			JSONlist.add("ScenarioStatus: " + "None");
		}

		JSONlist.add("ScenarioName: " + RunTimeScenarioName);
		JSONlist.add("ScenarioPath: " + ScenarioPath);
		JSONlist.add("ScenarioEnv: " + RunTimeEnvFromJsonFile);
		JSONlist.add("ScenarioBrowser: " + RunTimeBrowserFromJsonFile);
		JSONlist.add("RemoteWebDriverSettings: " + RunTimeRemoteWebDriverSettings);

		obj.put("ScenarioRunTimeStatus", JSONlist);

		try {
			FileWriter file = new FileWriter(
					"C:\\Selenium\\RunTimeScenarioData\\Scenarios\\"
							+ FolderCount + "\\" + RunTimeScenarioName + ".json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Utilities.getRunTimeScenarioStatus(FolderCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ScenarioStatus.equalsIgnoreCase("Started")){
			
			File srcFile = new File("C:\\Selenium\\RunTimeScenarioData\\Scenarios\\" + FolderCount + "\\" + RunTimeScenarioName + ".json");
			File destDir = new File("C:\\Selenium\\RunTimeScenarioData\\CurrentScenario\\");
			
			try {
				Utilities.cleanDir("C:\\Selenium\\RunTimeScenarioData\\CurrentScenario");
				FileUtils.copyFileToDirectory(srcFile, destDir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setMaxRetryValue(int maxRetryCount){
		
		MaxRetryCount = maxRetryCount;
	}
	
	public static void setRunningRetryCount(int retryCount){
		
		runningReTryCount = retryCount;
	}
	
	public static void setRetryAnalyzerAsExecuted(boolean RetryMechanism){
		
		varRetryMechanism = RetryMechanism;
	}
	
	public static void closeOpenedRuntimeBrowsers() {

		String line;
		@SuppressWarnings("unused")
		String pidInfo = "";

		Process p = null;

		try {
			p = Runtime.getRuntime().exec(
					System.getenv("windir") + "\\system32\\" + "tasklist.exe");
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		BufferedReader input = new BufferedReader(new InputStreamReader(
				p.getInputStream()));

		try {
			while ((line = input.readLine()) != null) {
				pidInfo += line;
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {
			input.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {

			switch (PreviousRunTimeBrowserFromJsonFile) {

			case "Internet Explorer":
				Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
				break;

			case "FireFox":
				Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
				break;

			case "Google Chrome":
				Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
				break;

			default:
				System.out.println("No Browsers to Destroy!!");
				break;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void establishOracleConnection() {
		
		String dbEnv = null;
		try{
		
			dbEnv = Utilities.getRunTimeEnv();
			
			//Uses Test Accounts
			if(dbEnv.equalsIgnoreCase("DEV")){
				myCon = java.sql.DriverManager.getConnection(devUrl, username, devPassword);
				log.info("Succesfully logged into DEV DB");
			}else if(dbEnv.equalsIgnoreCase("SAT")){
				myCon = java.sql.DriverManager.getConnection(satUrl, username, satPassword);
				log.info("Succesfully logged into SAT DB");
			}else{
				throw new Exception("Incorrect dbEnv is set!!");
			}
					
		}catch(Exception sql){
			log.error("SQL Exception Thrown! Unable to Establish a connection with the " + dbEnv + " Database! Error thrown is " + sql);
		}
		
	}
	
	public static void closeConnections() {
		
		try{
			result.close();
			stmt.close();
			myCon.close();

		}catch(Exception SQLClose) {
			System.out.println("There are Open Connecitons in the DB");
		}
	}
	
	public static void runAndVerifyCustomSQLQuery(String customQuery, String columnName, String expectedQueryResult, String actualQueryResult) throws Exception{
		
		query = customQuery; 
		
		try {
			stmt = myCon.createStatement();
			result = stmt.executeQuery(query);
			
			result.next();
			actualQueryResult = result.getString(columnName);
			
			if(expectedQueryResult.equals(actualQueryResult)){
				log.info("Expected Query Result Matches Actual Query Result");
			}else{
				log.error("Expected Query Does NOT match Actual Query");
				throw new Exception("Expected Query Does NOT match Actual Query");
			}
		
		} catch (SQLException e) {
			log.error("Could not retrieve info! Error thrown is " + e);
			
		}
	}
	
	public static void takeScreenshot(){
		
		String filePath = "Screenshots\\";
		
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 
         try {
         	timeStamp = new SimpleDateFormat("yyyyMMdd.HHmmss").format(new Date());
			FileUtils.copyFile(scrFile, new File(filePath + "beforeClass" +"_"+ timeStamp +".png"));
		 }catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	public static void tearDownRunTimeBrowsers(){
		
		 driver.close();
		 driver.quit();
	}

}

