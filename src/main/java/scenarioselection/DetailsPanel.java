package scenarioselection;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;



//The following class manages all components of the Detail Panel. This includes buttons, Dropdown menus, etc. 

public class DetailsPanel extends JPanel {

	static final String[] env = { "SAT", "DEV" };

	// static final String[] story = {"", "UC1", "UC2"};
	static final String[] BrowserDriver = { "Internet Explorer", "FireFox",
			"Google Chrome" };

	// static JComboBox useCaseDropMenu = new JComboBox(story);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static JComboBox envDropMenu = new JComboBox(env);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static JComboBox BrowserDriverDropMenu = new JComboBox(BrowserDriver);

	static JCheckBox checkbox = new JCheckBox("Remote Web Driver?");

	private static final long serialVersionUID = 6915622549267792262L;

	private EventListenerList listenerList = new EventListenerList();

	static String LastOption = "";

	public static int index = 0;

	public DetailsPanel() {

		// Detail Panel Settings
		Dimension size = getPreferredSize();
		size.width = 830; // used to be 420 without file label
		setPreferredSize(size);
		setLayout(new GridBagLayout());

		setBorder(BorderFactory.createTitledBorder("Scenario Parameters"));

		// Creating the Detail Panel Components
		JLabel useCaseLabel = new JLabel("Select Data File(s): ");
		JLabel envLabel = new JLabel("Select Enviornment: ");

		JLabel BrowserDriverLabel = new JLabel("Selenium Browser Driver: ");
		JLabel FileSubmissionLabel = new JLabel("Queue Mode Settings: ");
		// JLabel NotesOnHowToInitiateRun = new
		// JLabel("Note: You must press ENTER on the Console to Initiate Run");
		// checkbox.setEnabled(false);

		Preferences pref = Preferences.userRoot();

		String path = pref.get("DEFAULT_PATH", "");
		// String path = pref.get("C:/", "");

		final JFileChooser fc = new JFileChooser();

		// final JTextField testField = new JTextField(10);
		// final JTextField testField2 = new JTextField(10);

		// JComboBox useCaseDropMenu = new JComboBox(story);
		// JComboBox envDropMenu = new JComboBox(env);
		// JButton setUseBtn = new JButton("Set User Story");
		// JButton setEnvBtn = new JButton("Set Enviornment");
		JButton open = new JButton("Scenario(s)");
		JButton SubmitFiles = new JButton("Under Construction");
		SubmitFiles.setEnabled(false);
		JButton run = new JButton("Execute Scenario(s)");
		// /////////Drop Down
		// Menus/////////////////////////////////////////////////////////////////////////////////////////
		// useCaseDropMenu.addActionListener(new ActionListener(){
		// public void actionPerformed(ActionEvent e){

		// String use = useCaseDropMenu.getSelectedItem().toString();
		// System.out.println(use);

		// }

		// });

		envDropMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				@SuppressWarnings("unused")
				String env = envDropMenu.getSelectedItem().toString();
				// System.out.println(env);

			}
		});

		BrowserDriverDropMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				@SuppressWarnings("unused")
				String inteface = BrowserDriverDropMenu.getSelectedItem()
						.toString();
				// System.out.println(inteface);

				// if(inteface.equals("CommandLine")) {
				// envDropMenu.setEnabled(false);
				// }else{
				// envDropMenu.setEnabled(true);
				// }
			}
		});

		// ////////////////////////////XML DATA FILE CHOOSER
		// SETTINGS////////////////////////////////////////////

		// File f = new File("Q:/DATA/Selenium_Projects/WebDriver_Projects");
		// File f = new File();

		fc.setCurrentDirectory(new File(path));

		fc.setMultiSelectionEnabled(true);

		FileFilter filter = new FileNameExtensionFilter("xml files (*.xml)",
				"xml");
		fc.setFileFilter(filter);

		// File[] files = fc.getSelectedFiles();
		// String choice = fc.getSelectedFiles().toString();

		// /////////////////////////DROP DOWN
		// MENUS//////////////////////////////////////////////////////////////////
		// //////////////BUTTONS/////////////////////////////////////////////////////////////////////////////////////
		// setUseBtn.addActionListener(new ActionListener(){
		// public void actionPerformed(ActionEvent e){
		//
		//
		// String name2 = useCaseDropMenu.getSelectedItem().toString();
		// String msg = name2 + ": " + "Database Table is Updated" + "\n";
		//
		// fireDetailEvent(new DetailEvent(this, msg));
		// }
		// });

		// setEnvBtn.addActionListener(new ActionListener(){
		// public void actionPerformed(ActionEvent e){
		//
		//
		//
		//
		//
		// String name2 = envDropMenu.getSelectedItem().toString();
		// String text = name2 + ": " + "Database Table is Updated" + "\n";
		//
		// fireDetailEvent(new DetailEvent(this, text));
		// }
		//
		// });

		// ///////////////////////////DIRECTORY STRUCTURE FOR DATA
		// FILES////////////////////////

		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int intmpindex = 0;

				// ///Grab ENV, Browser settings, and Remote WebDriver
				// Settings//////
				String AppEnviornment = envDropMenu.getSelectedItem()
						.toString();
				String AppRunTimeBrowser = BrowserDriverDropMenu
						.getSelectedItem().toString();
				String RemoteWebDriverSettings;

				if (checkbox.isSelected()) {
					RemoteWebDriverSettings = "GRID";
				} else {
					RemoteWebDriverSettings = "Local";
				}

				// LastOption = fc.getCurrentDirectory().toString();

				int result = fc.showOpenDialog(null);

				switch (result) {

				case JFileChooser.APPROVE_OPTION:

					// Delete old directory files.
					
					File f = fc.getSelectedFile();
					boolean MultipleSelection = false;
					@SuppressWarnings("unused")
					boolean ModifyFile = false;
					@SuppressWarnings("unused")
					String ScenarioStatus = "None";

					// Setting path in last preference.
					fc.setCurrentDirectory(f);

					// Save the File
					pref.put("DEFAULT_PATH", f.getAbsolutePath());

					// System.out.println("Most recent directory: " +
					// LastOption);

					@SuppressWarnings("unused")
					String filenames = "";
					String fileName = "";
					String filepath = "";

					File file1 = fc.getSelectedFile();
					filepath = fc.getSelectedFile().toString();
					fileName = file1.getName();

					final File[] files = fc.getSelectedFiles();

					if (files != null && files.length > 1 || files.length == 1) {

						int tmp2;

						if (files.length == 1) {
							MultipleSelection = false;
							index++;
							AppUtil.CreateRunTimeJSONFile(fileName, filepath, index, 0, AppEnviornment, AppRunTimeBrowser, RemoteWebDriverSettings, MultipleSelection);
							System.out.println("Scenario " + index + ": " + fileName);
						} else {

							MultipleSelection = true;
							for (int x = 0; x < files.length; x++) {

								intmpindex = index;
								// intmpindex = index + x + 1;
								int tmp = x + 1;

								if (tmp >= 2) {
									tmp2 = intmpindex + tmp / tmp;
								} else {
									tmp2 = intmpindex + tmp;
								}

								index++;

								// filenames = filenames + "\n" +
								// files[x].getName(); This spits out all the
								// filenames in one command.
								fileName = files[x].getName();
								AppUtil.CreateRunTimeJSONFile(fileName,
										filepath, 0, tmp2, AppEnviornment,
										AppRunTimeBrowser,
										RemoteWebDriverSettings,
										MultipleSelection);
								System.out.println("Scenario " + tmp2 + ": "
										+ files[x].getName()); // this spits out
																// the files
																// chosen, one
																// by one.

							}
							// Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER,
							// "Software\\Selenium and Java Program Settings\\StmtScenarios\\RunTimeDriverData\\Scenarios",
							// "1");

						} // System.out.println("You have selected these files:\n"
							// + filenames);

						// Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER,
						// "Software\\Selenium and Java Program Settings\\StmtScenarios\\CurrentScenario",
						// "ScenarioName", filenames);
					}

					// if (files.length > 1){

					// for (int i = 0; i < files.length; i++) {
					// var [i] = myArrayList.indexOf(i);
					// }

					// Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER,
					// "Software\\Selenium and Java Program Settings\\StmtScenarios\\CurrentScenario",
					// "ScenarioName", filenames);
					// System.out.println("You have selected these files:\n" +
					// filenames);

					// if (files.length == 1) {
					//
					// index = index + 1;
					// //System.out.println("This File was choosen: " +
					// fileName);
					// //System.out.println(filepath);
					// System.out.println("Scenario " + index + ": " +
					// fileName); //this spits out the files chosen, one by one.
					// singeFileChosen = true;
					//
					//
					// //Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER,
					// "Software\\Selenium and Java Program Settings\\StmtScenarios\\CurrentScenario",
					// "ScenarioName", fileName);
					// //Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER,
					// "Software\\Selenium and Java Program Settings\\StmtScenarios\\RunTimeScenarioData",
					// "ScanarioPath", filepath);
					//
					// }

					break;

				case JFileChooser.CANCEL_OPTION:
					// System.out.println("Cancel button choosen");
					break;
				}

				// System.out.println("Another controller");

				// if (fc.getSelectedFile().toString().equals(null)){
				// System.out.println("No files Selected!");
				// }else {
				//
				// String sel = fc.getSelectedFile().toString();
				// System.out.println("The following Files were Selected: ");
				// System.out.println(sel.substring(69));
				// System.out.println("The following files were Selected: " +
				// "\n" + sel );
				// }

				// System.out.println("Number of Scenarios to Tagged to Run: " +
				// index);

			}
		});

		// //////////////////////////////////////////////eVS/PRS/SBP Submission
		// File Mini
		// Window////////////////////////////////////////////////////////////
		SubmitFiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				//FileSubmitWindow.JFrameFileWindow();

			}
		});

		// ////////////////////////////////////////////////////////////////////////////////////////////
		// ////////////////////////RUN BUTTON BEGIN UPDATEING DATABASE
		// ENTRIES////////////////////////////////////////////////////////////
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ////////////////GLOBAL PARAMETER
				// UPDATE!!!!!///////////////////////////////////////
//				AppUtil.SetScenarioCount(index);
//				System.out.println("Number of Scenarios to Tagged to Run: "
//						+ index);

				// Experiment//

				// for (int i = 0; i < list.size(); i++) {
				// System.out.println(list.get(i));
				// }

				// String AppEnviornment =
				// envDropMenu.getSelectedItem().toString();
				// String AppRunTimeBrowser =
				// BrowserDriverDropMenu.getSelectedItem().toString();
				//
				// AppUtil.SetEnvironmentAndBrowserDriverParameters(AppEnviornment,
				// AppRunTimeBrowser);
				//
				// System.out.println(index);

				// switch(enviornment) {

				// case "DEV":
				//
				// // try{
				// // DatabaseFunction.SIT1AUpdate();
				// // }
				// // catch (Exception DB){
				// // DB.printStackTrace();
				// // }
				//
				// break;
				//
				// case "SAT":
				//
				// // try{
				// // DatabaseFunction.SIT2AUpdate();
				// // }
				// // catch (Exception DB){
				// // DB.printStackTrace();
				// // }
				//
				// break;
				//
				// // case "SIT3A":
				// //
				// //// try{
				// //// DatabaseFunction.SIT3AUpdate();
				// //// }
				// //// catch (Exception DB){
				// //// DB.printStackTrace();
				// //// }
				// //
				// // break;
				// //
				// // case "DEV1A":
				// //
				// //// try{
				// //// DatabaseFunction.DEV1AUpdate();
				// //// }
				// //// catch (Exception DB){
				// //// DB.printStackTrace();
				// //// }
				// //
				// // break;
				// //
				// // case "DEV2A":
				// //
				// //// try{
				// //// DatabaseFunction.DEV2AUpdate();
				// //// }
				// //// catch (Exception DB){
				// //// DB.printStackTrace();
				// //// }
				// //
				// // break;
				// //
				// // case "DEV3A":
				// //
				// //// try{
				// //// DatabaseFunction.DEV3AUpdate();
				// //// }
				// //// catch (Exception DB){
				// //// DB.printStackTrace();
				// //// }
				// //
				// // break;
				// //
				// // case "CAT":
				// //
				// //// try{
				// //// DatabaseFunction.CATUpdate();
				// //// }
				// //// catch (Exception DB){
				// //// DB.printStackTrace();
				// //// }
				// //
				// // break;
				// //
				// // case "TEM":
				// //
				// //// try{
				// //// DatabaseFunction.TEMUpdate();
				// //// }
				// //// catch (Exception DB){
				// //// DB.printStackTrace();
				// //// }
				// //
				// // break;
				// //
				// // case "PROD":
				// //
				// //// try{
				// //// DatabaseFunction.PRODUpdate();
				// //// }
				// //// catch (Exception DB){
				// //// DB.printStackTrace();
				// //// }
				// //
				// // break;
				//
				// default:
				// System.out.println("Default ENV is SAT");
				// break;
				// }
				// //////Workaround Listener///////////////////////////
				// if(checkbox.isSelected()) {
				// System.out.println("Checkbox is selected");
				// // try{
				// // DatabaseFunction.workaroundLinksUpdate();
				// // }catch(Exception DB) {
				// // DB.printStackTrace();
				// // }
				//
				// }
				// ///////////////////////////////////////////////////////////

				// ///////////////BrowserDriver SETTING
				// UPDATE/////////////////////////////////////////////////////////

				// String ScenarioName =
				// Advapi32Util.registryGetStringValue(WinReg.HKEY_CURRENT_USER,
				// "Software\\Selenium and Java Program Settings\\StmtScenarios\\CurrentScenario",
				// "ScenarioName");

				// if(BrowserDriverDropMenu.getSelectedItem().equals("Mailer")){
				//
				// System.out.println("Mailer selected.");
				// //try{
				// // DatabaseFunction.MailerUpdate();
				// //
				// //// if(ScenarioName.matches("(.*)Report(.*)")) {
				// //// DatabaseFunction.ExternalScenarioTypeUpdate();
				// //// }else{
				// //// DatabaseFunction.InternalScenarioTypeUpdate();
				// //// DatabaseFunction.GenerateAndSubmitEVSUpdateQuery();
				// //// }
				// //
				// //
				// // }
				// // catch(Exception DB){
				// // DB.printStackTrace();
				// // }
				//
				// }else if
				// (BrowserDriverDropMenu.getSelectedItem().equals("BMEU")){
				//
				// System.out.println("BMEU dropdown selected");
				//
				// // try{
				// // DatabaseFunction.BMEUUpdate();
				// // DatabaseFunction.ExternalScenarioTypeUpdate();
				// // }
				// // catch(Exception DB){
				// // DB.printStackTrace();
				// // }
				// }else if
				// (BrowserDriverDropMenu.getSelectedItem().equals("CommandLine"))
				// {
				//
				//
				// System.out.println("Commandline dropdown selected");
				// // try{
				// // DatabaseFunction.CommandLineUpdate();
				// // DatabaseFunction.InternalScenarioTypeUpdate();
				// // }
				// // catch(Exception DB){
				// // DB.printStackTrace();
				// // }
				//
				// }else{
				// System.out.println("using default values"); //Add a pop-up
				// window to prevent run here if no option is chosen.
				// }

				RunSelectScenarioApp.closeAutomationWindow();

			}
		});

		// /////////////////////////////////BUTTONS////////////////////////////////////////////////////////////////////

		// The following section manages the pixel location of the components
		// added in the Detail Panel.
		GridBagConstraints gc = new GridBagConstraints();

		// //////FIRST
		// ROW/////////////////////////////////////////////////////////////////////////////////////
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 15, 5, 5); // Top, Left, Bottom, Right

		gc.gridx = 0;
		gc.gridy = 0;
		add(useCaseLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		add(open, gc);

		gc.gridx = 3;
		gc.gridy = 0;
		add(FileSubmissionLabel, gc);

		gc.gridx = 4;
		gc.gridy = 0;
		add(SubmitFiles, gc);

		// //////SECOND
		// ROW//////////////////////////////////////////////////////////////////////////////////////
		// gc.anchor = GridBagConstraints.LINE_START;

		// gc.weightx = 0.8;
		gc.weighty = 0.5;

		gc.gridx = 0;
		gc.gridy = 1;
		add(envLabel, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		add(envDropMenu, gc);

		// gc.gridx = 2;
		// gc.gridy = 1;
		// add(BrowserDriverSetting, gc);

		gc.gridx = 3;
		gc.gridy = 1;
		add(BrowserDriverLabel, gc);

		gc.gridx = 4;
		gc.gridy = 1;
		add(BrowserDriverDropMenu, gc);

		// gc.anchor = GridBagConstraints.LAST_LINE_START;
		// gc.gridx = 0;
		// gc.gridy = 4;
		// add(testField, gc);

		// gc.anchor = GridBagConstraints.LAST_LINE_START;
		// gc.gridx = 0;
		// gc.gridy = 5;
		// add(testField2, gc);

		// gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 5;
		gc.gridy = 1;
		add(checkbox, gc);

		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 2;
		add(run, gc);

		// gc.anchor = GridBagConstraints.CENTER;
		// gc.gridx = 1;
		// gc.gridy = 3;
		// add(NotesOnHowToInitiateRun, gc);

	}

	// This section manages the addition, removal and firing of responses for
	// events.
	public void fireDetailEvent(DetailEvent event) {
		Object[] listeners = listenerList.getListenerList();

		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == DetailListener.class) {
				((DetailListener) listeners[i + 1]).detailEventOccured(event);

			}
		}
	}

	public void addDetailListener(DetailListener listener) {
		listenerList.add(DetailListener.class, listener);
	}

	public void removeDetailListener(DetailListener listener) {
		listenerList.remove(DetailListener.class, listener);
	}

	@SuppressWarnings("unused")
	private static List<String> readFileAsList(File file) throws IOException {

		final List<String> ret = new ArrayList<String>();
		final BufferedReader br = new BufferedReader(new FileReader(file));

		try {
			String strLine;
			while ((strLine = br.readLine()) != null) {
				ret.add(strLine);
			}
			return ret;
		} finally {
			br.close();
		}

	}

}
