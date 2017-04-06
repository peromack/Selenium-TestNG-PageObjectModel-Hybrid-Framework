package resources;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.Parameters;
import resources.Utilities;

public class SuiteListener implements ISuiteListener {

	@Parameters({"Scenario-Selection-Form" })
	@Override
	public void onStart(ISuite suite) {
		
		//System.out.println("Suite Listener Executed");
		//Utilities.GetScenarioCount();
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

}
