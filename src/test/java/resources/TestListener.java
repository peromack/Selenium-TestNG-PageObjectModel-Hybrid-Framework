package resources;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import resources.Utilities;
import testsuite.ActionsTestCases;
import testsuite.FeedbackTestCases;
import testsuite.MiscellaneousPagesTestCases;
import testsuite.ShipmentDashboardTestCases;
import testsuite.ShipmentDetailsTestCases;
import testsuite.SmokeTest;
import testsuite.TradeEntityTestCases;



public class TestListener implements ITestListener {
	WebDriver driver=null;
	String filePath = null;
	static String timeStamp;
	
	
    
	
	@Override
    public void onTestFailure(ITestResult result) {
    	//System.out.println("***** Error "+result.getName()+" test has failed *****");
    	String methodName=result.getName().toString().trim();
    	takeScreenShot(methodName);
    }
    
    public void takeScreenShot(String methodName) {
    	
    	String RunType = Utilities.getRunType();
    	
    	if(RunType.contentEquals("GRID")){
    		String workingDirectory = System.getProperty("user.dir");
    		filePath = workingDirectory + File.separator + "Screenshots" + File.separator;
    	}else{ 
    		//String workingDirectory = System.getProperty("user.dir");
    		//filePath = workingDirectory + "C:" + File.separator + "Selenium"+ File.separator + "Screenshots" + File.separator;
    		filePath = "C:\\Selenium\\Screenshots\\";
    	}
    	
    	//get the driver (based on runtime caller class)
    	String callerClass = Utilities.getCallerClassNameIntendedForListener();
    	
    	switch(callerClass){
    	
    		case "ActionsTestCases":
    			driver = ActionsTestCases.driver;
    			break;
    			
    		case "ShipmentDashboardTestCases":
    			driver = ShipmentDashboardTestCases.driver;
    			break;
    			
    		case "ShipmentDetailsTestCases":
    			driver = ShipmentDetailsTestCases.driver;
    			break;
    			
    		case "UATApplicationTestCases":
    			//driver = UATApplicationTestCases.driver;
    			break;	
    		
    		case "TradeEntityTestCases":
    			//driver = TradeEntityTestCases.driver;
    			break;
    			
    		case "TargetingResultsTestCases":
    			//driver = TargetingResultsTestCases.driver;
    			break;	
    			
    		case "MiscellaneousPagesTestCases":
    			driver = MiscellaneousPagesTestCases.driver;
    			break;
    			
    		case "AdvancedSearchTestCases":
    			//driver = AdvancedSearchTestCases.driver;
    			break;
    			
    		case "IconsTestCases":
    			//driver = IconsTestCases.driver;
    			break;	
    		
    		case "ForeignUserTestCases":
    			//driver = ForeignUserTestCases.driver;
    			break;
    			
    		case "FeedbackTestCases":
    			driver = FeedbackTestCases.driver;
    			break;
    			
    		case "SmokeTest":
    			driver = SmokeTest.driver;
    			break;
    			
    		default:
    			System.out.println("ERROR: Unable to grab Correct Caller Class!!!");
    			break;
    	}
    	
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with test method name 
            try {
            	timeStamp = new SimpleDateFormat("yyyyMMdd.HHmmss").format(new Date());
            	
	            	
            	
				FileUtils.copyFile(scrFile, new File(filePath + "testFailed" + "_" + methodName + "_" + timeStamp + ".png"));
				//System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	public void onFinish(ITestContext context) {
		
		Iterator<ITestResult> listOfFailedTests = context.getFailedTests().getAllResults().iterator();
        while (listOfFailedTests.hasNext()) {
            ITestResult failedTest = listOfFailedTests.next();
            ITestNGMethod method = failedTest.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                listOfFailedTests.remove();
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    listOfFailedTests.remove();
                }
            }
        }
	}
  
    public void onTestStart(ITestResult result) {  }
  
    public void onTestSuccess(ITestResult result) {  }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }
    
}  
