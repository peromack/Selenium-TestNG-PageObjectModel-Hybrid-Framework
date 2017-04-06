//package scenarioSelection;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//import com.sun.jna.platform.win32.Advapi32Util;
//import com.sun.jna.platform.win32.WinReg;
//
//public class DatabaseFunction {
//
//	// Data Driver Parameters
//	static String mySQL, myDB, mySQLDB, myDriver, myT1, myUName, myPswd;
//	static String myQuery1, myQuery2;
//	static Connection myCon = null;
//	static Statement mySt;
//	static PreparedStatement myPst;
//	static ResultSet rs1, rs2;
//	static String p1, p2;
//
//	public static void testDB() throws Exception {
//
//		myQuery1 = "SELECT Case_Scenario FROM DataTable WHERE ID = 1;";
//		rs1 = mySt.executeQuery(myQuery1);
//		rs1.next();
//		String ScenarioType = (rs1.getString("Case_Scenario"));
//		System.out.println(ScenarioType);
//	}
//
//	public static void mySetup() throws Exception {
//
//		// Declare SQL variables
//		mySQL = "jdbc:mysql://localhost:3306/";
//		myDB = "uexample";
//		mySQLDB = mySQL + myDB;
//		myDriver = "com.mysql.jdbc.Driver";
//		myUName = "root";
//		myPswd = "Usps$1319";
//
//		// Connect to the Database
//		Object myDC = Class.forName(myDriver).newInstance();
//		myCon = DriverManager.getConnection(mySQLDB, myUName, myPswd);
//		mySt = myCon.createStatement();
//		System.out.println("ACCESS!");
//	}
//
//	public static void myTearDown() throws Exception {
//
//		// Close the mySQL Database connection
//		myCon.close();
//		System.out.println("6 figures is coming soon.");
//	}
//
//	public static void InternalScenarioTypeUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Internal' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void ExternalScenarioTypeUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'External' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void SIT1AUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'SIT1A' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void SIT2AUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'SIT2A' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void SIT3AUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'SIT3A' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void DEV1AUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'DEV1A' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void DEV2AUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'DEV2A' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void DEV3AUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'DEV3A' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void CATUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'CAT' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void TEMUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'TEM' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void PRODUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'PROD' WHERE ID = 1;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void workaroundLinksUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'Yes' WHERE ID = 4;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void workaroundLinksUpdateToNull() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'NULL' WHERE ID = 4;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void MailerUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'Mailer' WHERE ID = 2;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void BMEUUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'BMEU' WHERE ID = 2;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void CommandLineUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'CommandLine' WHERE ID = 2;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void GenerateAndSubmitEVSUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Generate AND Submit EVS/PRS Files' WHERE ID = 2;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void GenerateOnlyEVSUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Generate ONLY EVS/PRS Files' WHERE ID = 2;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void SubmitEVSUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Submit EVS/PRS Files' WHERE ID = 2;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void AllServiceComboUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'All Rate and Extra Service Combinations' WHERE ID = 6;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void RatesOnlyUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Rates Only' WHERE ID = 6;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void BoundPrintMailClassUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'BPM -- 3605-BPR/DPR' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void LibraryMailClassUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Library Mail -- 3605' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void MediaMailClassUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Media Mail -- 3605' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void CriticalMailClassUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'CM: Critical Mail' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void PrioirityMailExpressClassUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Priority Mail Express -- 3560' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void FirstClassPackageServiceUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'First Class -- 3600' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void LightParcelSelectUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Parcel Select Lightweight -- 3605-R' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void PrioirtyMailUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Priority Mail -- 3600-R' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void ParcelSelectUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Parcel Select -- 3605-PR/SR' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void StandardMailNonprofitUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Standard Mail -- 3602-N' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void StandardMailMarketingUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Standard Mail -- 3602-R' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void PriorityMailInternationalUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Priority Mail International -- 3700-E' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void PriorityMailExpressInternationalUpdateQuery()
//			throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Priority Mail Express International -- 3700-G' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void FirstClassMailInternationalUpdateQuery()
//			throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'First-Class Package International Service -- 3700-B' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void GlobalExpressGuaranteeUpdateQuery() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Global Express Guaranteed -- 3700-H' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void SIT1AUnixUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'SIT1A' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void SIT2AUnixUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'SIT2A' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void SIT3AUnixUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'SIT3A' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void DEV1AUnixUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'DEV1A' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void DEV2AUnixUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'DEV2A' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void DEV3AUnixUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'DEV3A' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void CATUnixUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Environment = 'CAT' WHERE ID = 3;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void NoneSamplesUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'None' WHERE ID = 5;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void AllSamplesUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'ALL' WHERE ID = 5;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void IMDSampleUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'I IMD' WHERE ID = 5;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void STATSSampleUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'S STATS' WHERE ID = 5;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void PASSSampleUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'P PASS' WHERE ID = 5;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void POSSampleUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'O POS' WHERE ID = 5;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void NoneExtractUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'None' WHERE ID = 4;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void UnmanifestedExtractUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Un-manifested' WHERE ID = 4;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void DuplicatePackageExtractUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Duplicate Package' WHERE ID = 4;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void MisShippedExtractUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'Mis-Shipped' WHERE ID = 4;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void IMpbExtractUpdate() throws Exception {
//
//		myQuery1 = "UPDATE DataTable set Case_Scenario = 'IMpb' WHERE ID = 4;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//	public static void CurrentScenarioUpdate() throws Exception {
//
//		String CurrentScenarioName = Advapi32Util
//				.registryGetStringValue(
//						WinReg.HKEY_CURRENT_USER,
//						"Software\\Selenium and Java Program Settings\\StmtScenarios\\CurrentScenario",
//						"ScenarioName");
//
//		myQuery1 = "UPDATE DataTable set CurrentScenario = "
//				+ CurrentScenarioName + " WHERE ID = 2;";
//		mySt.executeUpdate(myQuery1);
//		System.out.println("UPDATED TABLE");
//
//	}
//
//}
