package scenarioselection;

////////Author: Abe Endale ////////////////////////////
////////Date: November 2014////////////////////////////
///////Project: Postalone Agile-Automation Evolution///
///////Description: Interface to Select Scenarios/////

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import resources.Utilities;

public class RunSelectScenarioApp extends Utilities {

	static JFrame window = new MainWindow("Scenario Selection Form");

	public static void RunApp() throws Exception {

		AppWindowClosed = false;
		// Thread t = new Thread(){

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				// JFrame window = new
				// MainWindow("Automation Select Forms: Guess who's Back? Shady's back!!!' ");
				window.setVisible(true);
				window.setSize(900, 700);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// //////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// /////////////////////DATABASE
				// ACCESS//////////////////////////////////////////////////////////////////////////
				// try{
				// DatabaseFunction.mySetup();
				// }
				// catch (Exception DB){
				// DB.printStackTrace();
				// }

			}

		});
		// };
		// t.start();
		// t.join();

	}

	// //////////////////////Closing
	// OPERATIONS//////////////////////////////////////////////////////////////////
	public static void closeAutomationWindow() {

		// try{
		// DatabaseFunction.myTearDown();
		// }
		// catch (Exception DB){
		// DB.printStackTrace();
		// }

		window.dispose();
		AppWindowClosed = true;
	}

}
