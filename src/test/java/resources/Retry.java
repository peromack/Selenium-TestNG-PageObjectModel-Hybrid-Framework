package resources;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import resources.Utilities;

public class Retry implements IRetryAnalyzer {
	
	private int retryCount = 0;
    private int maxRetryCount = 1;
    boolean RetryAnalyzerExecuted = false;

  
    public boolean retry(ITestResult result) {

    	Utilities.setMaxRetryValue(maxRetryCount);
    	Utilities.setRunningRetryCount(retryCount);
    	Utilities.setRetryAnalyzerAsExecuted(true);
    	
    	if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
	

}
