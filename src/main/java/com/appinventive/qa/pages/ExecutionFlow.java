package com.appinventive.qa.pages;

import java.awt.AWTException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.StaleElementReferenceException;
import freemarker.core.ParseException;

@SuppressWarnings("unused")
public class ExecutionFlow {
	public static int loop = DriverScript.TSlist.size();

	@SuppressWarnings("rawtypes")
	public static ArrayList<HashMap> excellist = new ArrayList<HashMap>();
	public static ArrayList<String> TCname = new ArrayList<String>();
	public static ArrayList<String> TCtime = new ArrayList<String>();
	public static String testcasename = null;
	public static long Suitetotaltime = 0;
	public static long TCtotalTime = 0;
	public static long startTime = 0;
	public static long endTime = 0;
	public static String hms = null;

	@SuppressWarnings({ })
	
     /* Function Name: Initialisation()
	    Author : QA Automation
	    Version: 1.0
        Parameters: null
        Description: This function reads data from TSlist (ArrayList) i.e number of test cases with execution status as YES
	                 loops through the list until it executes all test cases*/
	
	
	public static void Initialisation() throws StaleElementReferenceException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, AWTException, SQLException, Exception 
	{
		// SuitestartTime captures suite start time
		Long SuitestartTime = System.currentTimeMillis();
		for (int Tc = 1; Tc <= loop; Tc++) {
			testcasename = DriverScript.TSlist.get(Tc - 1);
			switch (testcasename) {
			case "AD_AdminUser":
				startTime = System.currentTimeMillis();
<<<<<<< HEAD
		//		AD_AdminUser.ADAdminUser();
=======
//				AD_AdminUser.ADAdminUser();
>>>>>>> main
				testcaseMetrics();
				break;
			case "AD_SupportUser":
				startTime = System.currentTimeMillis();
				//AD_SupportUser.ADSupportUser();
				testcaseMetrics();
				break;
			case "Other_Admin_Process":
				startTime = System.currentTimeMillis();
		//	Other_Admin_Process.OtherAdminProcess();
				testcaseMetrics();
				break;
		/*	case "Customer_Registration":
				startTime = System.currentTimeMillis();
				Customer_Registration.CustomerRegistration();
				testcaseMetrics();
				break; */
				case  "TestCaseAPI":
					startTime = System.currentTimeMillis();
					//TestCasesAPI.verifyaddupdateCustomer();
					testcaseMetrics();
					break;



			case "Customer_Onboard_Flow":
				startTime = System.currentTimeMillis();
		//		Customer_Onboard_Flow.CustomerOnboardFlow();
				testcaseMetrics();
				break;
			case "Customer_Maintenance_Flow":
				startTime = System.currentTimeMillis();
		//		Customer_Maintenance_Flow.CustomerMaintenanceFlow();
				testcaseMetrics();
				break;
			case "Customer_Termination_Flow":
				startTime = System.currentTimeMillis();
		//		Customer_Termination_Flow.CustomerTerminationFlow();
				testcaseMetrics();
				break;
			case "Customer_User_Admin_Process_Flows":
				startTime = System.currentTimeMillis();
		//		Customer_UserAdmin_Process_Flows.CustomerUserAdminProcessFlow();
				testcaseMetrics();
				break;	    		
			case "Integration_Flows":
				startTime = System.currentTimeMillis();
	//			Integration_Flows.IntegrationFlows();
				testcaseMetrics();
				break;
			case "ResetPassword_Flow":
				startTime = System.currentTimeMillis();
		//		ResetPassword_Flow.ResetPasswordSupportUser();
		//		ResetPassword_Flow.ResetPasswordAdminUser();
				testcaseMetrics();
				break;
				
			}

		}

// SuiteendTime captures suite End time
		Long SuiteendTime = System.currentTimeMillis();
// Suitetotaltime has total suite execution time 
		Suitetotaltime = SuiteendTime - SuitestartTime;
		hms = convertMstoHH(Suitetotaltime);
		Reports.Automation_Report();
	}

//  	
	/* Function Name: testcaseMetrics()
    Author : QA Automation
    Version: 1.0
    Parameters: null
    Description: This function used for the  reporting purpose
                  captures case end time, total time taken for test case execution
                  we are taking 4 arraylists Tctime,Tcname and exccellist,Tsteps
                  in TCtime array list we  will add time take for eacch step to execute
                  in Tcname array list we will add test case name
                  in Tsteps array list we will add test  steps
                  in excel list arraylist we will add hasmap(hmapsteps) which contains test case name as key and Tsteps as value
                 */
	public static void testcaseMetrics() throws ParseException 
	{
		endTime = System.currentTimeMillis();
		TCtotalTime = endTime - startTime;
		String stepstime = apacheFormat(TCtotalTime);
		TCtime.add(stepstime);
		TCname.add(testcasename);
		Reports.hmapsteps.put(testcasename, Reports.TSteps);
		excellist.add(Reports.hmapsteps);
		Reports.TSteps = new ArrayList();
	}
	  /* Function Name: testcaseMetrics()
    Author : QA Automation
    Version: 1.0
    Parameters: time in seconds
    Description: This function converts long seconds into "HH:mm:ss format   */
	
	public static String convertMstoHH(Long Milsecs) {
		String ftime = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(Milsecs),
				TimeUnit.MILLISECONDS.toMinutes(Milsecs)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Milsecs)),
				TimeUnit.MILLISECONDS.toSeconds(Suitetotaltime)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Milsecs)));
		return ftime;

	}
	  /* Function Name: testcaseMetrics()
    Author : QA Automation
    Version: 1.0
    Parameters: time in seconds
    Description: This function converts long seconds into "HH:mm:ss format   */
	public static String apacheFormat(long millis) throws ParseException 
	{
	    return DurationFormatUtils.formatDuration(millis, "HH:mm:ss");
	}
}
