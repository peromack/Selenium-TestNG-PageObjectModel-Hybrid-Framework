package resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.testng.IAnnotationTransformer2;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import resources.Utilities;

import com.esotericsoftware.yamlbeans.YamlReader;

public class GlobalTransformer implements IAnnotationTransformer2 {
	
	String TestNGscenario;
	static String RunTimeDriverScenario;
	static String valueForScenarioSelection;
	static String ScenarioStatus;
	
	int PriorityLevel;
	int FolderCount; 
	int ScenarioCount;
	int NumberOfTimesToRun;
	int NumberOfScenariosDone;
	boolean skipForm, FormWasUsed;
	boolean skipCondition, skipCondition2;
	boolean skipLoop;
	
	static JSONObject obj;
	static JSONArray JSONlist;
	
	//Json file info
	static String ScenarioName;
	static String ScenarioPath;
	static String RunTimeEnv;
	static String RunTimeBrowser;
	static String RunTimeRemoteWebDriverSettings;
	
	static int response;
	

	@Override
	public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass, @SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) {
		
		if(skipCondition == false){
			Utilities.validateFilePathsForLocalMachine();
			GetPropertyFileInfo();
			ScenarioCount = Utilities.returnScenarioCount();
			Utilities.initalizeLog4j();
			Utilities.getPropertyFileInfo();
			skipCondition = true;
		}
		
		
		if(valueForScenarioSelection.equalsIgnoreCase("On")){
		
				//////////////////////////////////////////////////////////Select Scenarios Form Manager///////////////////////////////////
				if(ScenarioCount > 0 && skipCondition2 == false && FormWasUsed == false){
					
					InfoUserOption("There are Current Scenarios in the RunTime Driver Directory. Would you Like to Continue Where You Left Off?", 
							"Select Action to Perform!");
					
					if (response == JOptionPane.YES_OPTION){
					
						FolderCount = 1;
						boolean MarkAllScenariosDone = false;
						
						for(int i = 0; i < ScenarioCount; i++){
							try {
								GetRunTimeScenarioStatus(FolderCount);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							String tmp = RunTimeDriverScenario.substring(0, RunTimeDriverScenario.length() - 5);
							
							if(ScenarioStatus.equalsIgnoreCase("Done")){
								
								if(MarkAllScenariosDone == false){
									InfoUserOption2("The Scenario: " + tmp + " Has Already Been Completed! Do you Want to ReRun it?", 
																			"Select Action to Perform!");
									
									if (response == JOptionPane.YES_OPTION){
										ModifyRunTimeStatus(RunTimeDriverScenario, FolderCount, "None");
									}else if(response == 2){
										ModifyRunTimeStatus(RunTimeDriverScenario, FolderCount, "None");
										MarkAllScenariosDone = true;
									}else if(response == 3){
										System.exit(0);
									}
								
								}else{
									ModifyRunTimeStatus(RunTimeDriverScenario, FolderCount, "None");
								}
									
								NumberOfScenariosDone++;
								
							}else if(ScenarioStatus.equalsIgnoreCase("Started")){
								ModifyRunTimeStatus(RunTimeDriverScenario, FolderCount, "None");
							}
							
							FolderCount++;
						}
						
						skipForm = true;
						System.out.println(NumberOfScenariosDone + " were marked done!");
						
					}else if(response == JOptionPane.NO_OPTION){
						skipForm = false;
					}else if(response == JOptionPane.CANCEL_OPTION){
						System.exit(0);
					}
					
					
					PriorityLevel = 0;
					skipCondition2 = true;
				}
				
				if(skipForm == false){
					try {
						Utilities.cleanDir("C:/Selenium/RunTimeScenarioData/Scenarios");
						Utilities.runScenarioSelectionApp();
						ScenarioCount = Utilities.returnScenarioCount();
						skipForm = true;
						FormWasUsed = true;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e);
					}
				}
				
				
		
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				TestNGscenario = testMethod.getName();
				NumberOfTimesToRun = 0;
				FolderCount = 1;
				
			for(int i = 0; i < ScenarioCount; i++){
				
				RunTimeDriverScenario = Utilities.getRunTimeScenarioName(FolderCount);
				RunTimeDriverScenario = RunTimeDriverScenario.substring(0, RunTimeDriverScenario.length() - 5);
				RunTimeDriverScenario = RunTimeDriverScenario.replaceAll("\\s+","");
				
				if(RunTimeDriverScenario.equalsIgnoreCase(TestNGscenario)){
					NumberOfTimesToRun++;
				}
				
				FolderCount++;
			}
			
			if(NumberOfTimesToRun < 1){
				annotation.setEnabled(false);
			}else{
				annotation.setEnabled(true);
				annotation.setInvocationCount(NumberOfTimesToRun);
				annotation.setPriority(PriorityLevel);
				PriorityLevel++;
			}
				
			
		}		

		
	}

	@Override
	public void transform(IConfigurationAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass,
			@SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) { }

	@Override
	public void transform(IDataProviderAnnotation annotation, Method method) { }

	@Override
	public void transform(IFactoryAnnotation annotation, Method method) { }
	
	@SuppressWarnings("rawtypes")
	public static void GetPropertyFileInfo() {

		try {
			YamlReader reader;
			reader = new YamlReader(new FileReader("RunTimeDriverData/config.yml"));
		
			Object object = reader.read();
			Map map = (Map) object;
			valueForScenarioSelection = (String) map.get("Scenario-Form");
			// RunTimeEnv = (String) map.get("RunTimeEnv");
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
					ScenarioName = iterator.next().toString();
					ScenarioName = (ScenarioName.substring(14));
				} else if (inc == 2) {
					ScenarioPath = iterator.next().toString();
					ScenarioPath = (ScenarioPath.substring(14));
				} else if (inc == 3) {
					RunTimeEnv = iterator.next().toString();
					RunTimeEnv = (RunTimeEnv.substring(13));
				} else if (inc == 4) {
					RunTimeBrowser = iterator.next().toString();
					RunTimeBrowser = (RunTimeBrowser.substring(17));
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
	public static void ModifyRunTimeStatus(String RunTimeDriverScenario, int FolderCount, String ScenarioStatus) {

		obj = new JSONObject();
		JSONlist = new JSONArray();

		
		if (ScenarioStatus == "None") {
			JSONlist.add("ScenarioStatus: " + "None");
		} else if (ScenarioStatus == "Started") {
			JSONlist.add("ScenarioStatus: " + "Started");
		} else if (ScenarioStatus == "Done") {
			JSONlist.add("ScenarioStatus: " + "Done");
		} else { // by default, statis is set to "None"
			JSONlist.add("ScenarioStatus: " + "None");
		}

		JSONlist.add("ScenarioName: " + ScenarioName);
		JSONlist.add("ScenarioPath: " + ScenarioPath);
		JSONlist.add("ScenarioEnv: " + RunTimeEnv);
		JSONlist.add("ScenarioBrowser: " + RunTimeBrowser);
		JSONlist.add("RemoteWebDriverSettings: " + RunTimeRemoteWebDriverSettings);

		obj.put("ScenarioRunTimeStatus", JSONlist);

		try {
			FileWriter file = new FileWriter(
					"C:\\Selenium\\RunTimeScenarioData\\Scenarios\\"
							+ FolderCount + "\\" + ScenarioName + ".json");
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
		
		System.gc();
	}
	

	public static int InfoUserOption2(String QuestionForUser, String title) {

		JDialog.setDefaultLookAndFeelDecorated(true);
		
		String[] options = new String[] {"Yes", "No", "Re-Run All Scenarios Marked Done", "Cancel" };
		
		response = JOptionPane.showOptionDialog(null, QuestionForUser, title,
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);

		return response;
	}
	
	public static int InfoUserOption(String QuestionForUser, String title) {

		JDialog.setDefaultLookAndFeelDecorated(true);
		
		String[] options = new String[] {"Yes", "No", "Cancel" };
		
		response = JOptionPane.showOptionDialog(null, QuestionForUser, title,
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);

		return response;
	}
	
	public static void InfoMsgbox(String infoMessage, String titleBar) {

		JOptionPane.showMessageDialog(null, infoMessage,
				"InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

}
