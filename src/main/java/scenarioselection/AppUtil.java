package scenarioselection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


//import util.Utilities;
//import driver.ScriptDriver;
import resources.Utilities;

public class AppUtil extends Utilities {

//	public static void SetScenarioCount(int index) {
//		ScenarioCnt = index;
//	}

	@SuppressWarnings("unchecked")
	public static void CreateRunTimeJSONFile(String fileName, String filepath,
			int index, int tmp2, String AppEnviornment,
			String AppRunTimeBrowser, String RemoteWebDriverSettings,
			boolean MultipleSelection) {

		obj = new JSONObject();
		JSONlist = new JSONArray();

		// /////////////////////////////////////////////////////////
		ScenarioName = fileName.substring(0, fileName.length() - 4);
		ScenarioPath = filepath;

		RunTimeEnv = AppEnviornment;
		RunTimeBrowser = AppRunTimeBrowser;
		// //////////////////////////////////////////////////////////

		// //CREATE INDEX
		// FOLDER///////////////////////////////////////////////////////////////
		File indexFile;
		if (MultipleSelection == false) {
			indexFile = new File(
					"C:\\Selenium\\RunTimeScenarioData\\Scenarios\\" + index);
					//"C:\\Selenium\\RunTimeScenarioData\\Scenarios\\" + ScenarioName);
		} else {
			indexFile = new File(
					"C:\\Selenium\\RunTimeScenarioData\\Scenarios\\" + tmp2);
					//"C:\\Selenium\\RunTimeScenarioData\\Scenarios\\" + ScenarioName);
		}

		if (!indexFile.exists()) {
			if (indexFile.mkdir()) {
				// System.out.println("index folder created!");
			} else {
				System.out.println("Failed to create index folder!");
			}
		}

		JSONlist.add("ScenarioStatus: " + "None");
		JSONlist.add("ScenarioName: " + ScenarioName);
		JSONlist.add("ScenarioPath: " + ScenarioPath);
		JSONlist.add("ScenarioEnv: " + RunTimeEnv);
		JSONlist.add("ScenarioBrowser: " + RunTimeBrowser);
		JSONlist.add("RemoteWebDriverSettings: " + RemoteWebDriverSettings);

		obj.put("ScenarioRunTimeStatus", JSONlist);

		
		
		try {
			FileWriter file;
			if (MultipleSelection == false) {
				file = new FileWriter(
						"C:\\Selenium\\RunTimeScenarioData\\Scenarios\\"
								+ index + "\\" + ScenarioName + ".json");
							//	+ ScenarioName + "\\" + ScenarioName + ".json");
			} else {
				file = new FileWriter(
						"C:\\Selenium\\RunTimeScenarioData\\Scenarios\\" 
							+ tmp2
							//+ ScenarioName
							+ "\\" + ScenarioName + ".json");
							
			}
			
			file.write(obj.toJSONString());

			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}

}
