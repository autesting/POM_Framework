package com.averydennison.qa.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
public class DriverScript extends UtilityFunctions 
{
	private static final String Tcase = null;
	public static WebDriver driver;
	public static Properties prop;
	public static String URL;
	public static String Gmail;
	public static String browsername;
	public static String AdminUser;
	public static String AdminPwd;
	public static String SauceUsername;
	public static String SaucePassword;

	public static String SupportUsername;
	public static String SupportUserPassword;
	public static String CustomerAdminUser;
	public static String CustomerAdminPwd;
	public static String Customeruser;
	public static String CustomerPassword;
	public static String SecurityAnswer;
	public static String NewAdminUser;
	public static String NewSupportUser;
	public static String NewCustomerAdmin;
	public static String NewCustomerUser;
	public static String NewAdmin;
	public static String Randomname;
	public static String UpdatedRandomname;
	public static String RandomSubGrpName;
	public static String RandomURL;
	public static String RandomnameUser;
	public static String NewPassword;
	public static String ResetPassword;
	public static String CustAdminProcessUser;
	public static String CustAdminProcessAdminUser;
	public static String AdminForRequestPolicy;
	public static String UserOnboardFlow;
	private static Properties hmap;
	private static Workbook TDwb;
	private static XSSFWorkbook wb;
	private static Workbook wb2;
	public static ArrayList<String> TSlist = new ArrayList<String>();
	public static HashMap<String,String> envmap = new HashMap();
	public static String Value;
	public static String Text;
	
	  /* Function Name: ReadProperties()
         Author :  QA Automation
         Version: 1.0
         Parameters: null
         Description: Reading the details from config.properties files*/
 	  

	public static void ReadProperties() throws IOException, InterruptedException 
	{
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
			prop.load(ip);
			System.out.println(ip);
			browsername = prop.getProperty("Browser");
			URL = prop.getProperty("URL");
			Gmail=prop.getProperty("GMAILURL");

			
			AdminUser = prop.getProperty("AveryAdminUsername");
			AdminPwd = prop.getProperty("AveryAdminPassword");
			SecurityAnswer = prop.getProperty("SecurityAnswer");
			
			SupportUsername = prop.getProperty("SupportuserUsername");
			SupportUserPassword = prop.getProperty("SupportuserPassword");
			
			CustomerAdminUser = prop.getProperty("CustomerAdminUsername");
			CustomerAdminPwd = prop.getProperty("CustomerAdminPassword");
			
			Customeruser = prop.getProperty("CustomeruserUsername");
			CustomerPassword = prop.getProperty("CustomeruserPassword");
			
			NewAdminUser=prop.getProperty("AdminUserForResetPWDFlow");
			NewSupportUser=prop.getProperty("SupportUserForResetPWDFlow");
			
			NewCustomerUser=prop.getProperty("CustomerUserForIntegrationFlow");
			NewAdmin=prop.getProperty("CustomerAdminForIntegrationFLow");
			
			CustAdminProcessUser=prop.getProperty("UserForCustomerAdminProcessFlow");
			CustAdminProcessAdminUser=prop.getProperty("AdminUserForCustomerAdminProcessFlow");
			
			AdminForRequestPolicy=prop.getProperty("AdminUserForRequestPolicy");
			UserOnboardFlow =prop.getProperty("UserForOnBoardFlow");
			
			NewPassword=prop.getProperty("GenericPassword");
			ResetPassword=prop.getProperty("NewResetPassword");
			

			System.out.println(browsername + ", " + URL + " ," + AdminUser + " ," + AdminPwd);

		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/* Function Name: ReadTestsuite()
    Author :  QA Automation
    Version: 1.0
    Parameters: null
    Description: Reading the Testcase names which are set YES for execution from Test Suite file and storing in the TSlist arraylist */

	public static void ReadTestsuite() {
		try {
			String path = "";
			Sheet suitesheet;
			String CellData = null;
			int finval = 0;
			//String workingDirectory = new java.io.File(".").getCanonicalPath();
			//path = workingDirectory + "\\AD_TestSuite.xlsx";
			// System.out.println(path);
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "\\AD_TestSuite.xlsx");
			System.out.println(fs);
			// Create Workbook instance holding reference to .xlsx file
			wb = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet Suite = wb.getSheetAt(0);
			// System.out.println(Suite.getRow(0).getCell(0).getStringCellValue());
			// System.out.println(Suite.getRow(0).getCell(1).getStringCellValue());
			// System.out.println(Suite.getRow(1).getCell(0).getStringCellValue());

			int rowcount = Suite.getLastRowNum();
			// System.out.println(rowcount);

			int colcount = Suite.getRow(rowcount).getLastCellNum();
			int Rowval = 0;
			int colval = 1;

			for (Rowval = 0; Rowval <= rowcount; Rowval++) {
				Row r1 = Suite.getRow(Rowval);
				if (r1 != null) {
					Cell cell = Suite.getRow(Rowval).getCell(1);
					String CellVal = cell.getRichStringCellValue().getString();
					if (CellVal.trim().toUpperCase().equalsIgnoreCase("TESTCASE NAME")) 
					{
						finval = Rowval + 1;
						System.out.println(finval);
						break;
					}

				}

			}
			for (int j = finval; j <= rowcount; j++) {
				Row r2 = Suite.getRow(j);
				if (r2 != null) {
					Cell cell = Suite.getRow(j).getCell(1);
					Cell cell1 = Suite.getRow(j).getCell(0);
					String CellVal = cell.getRichStringCellValue().getString();
					String TCName = cell1.getRichStringCellValue().getString();

					if (CellVal.trim().toUpperCase().equalsIgnoreCase("YES")) {
						// TSlist.add(Suite.getRow(j).getCell(0));
						TSlist.add(TCName);
						// System.out.println(TSlist.size());
						 //System.out.println(TCName);

					}

				}

			}

			 System.out.println(TSlist);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/* Function Name: ReadTestsuite()
    Author :  QA Automation
    Version: 1.0
    Parameters: null
    Description: Main method from where the execution starts */
	

	public static void main(String args[]) throws IOException, InterruptedException, Exception 
	   {

		ReadTestsuite();
		ExecutionFlow.Initialisation();
	
				
		}

}
