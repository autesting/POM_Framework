package com.appinventive.qa.testcases;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.appinventive.qa.pages.DriverScript;
import com.appinventive.qa.pages.Reports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@SuppressWarnings("unused")
public class AD_AdminUser extends DriverScript

{
	static DriverScript Ds = new DriverScript();
	
    public static WebElement UsersList;	
    
	public static void ADAdminUser() throws IOException, InterruptedException 
	{
		boolean Result;
		try {
			
			ReadProperties();
			LaunchBrowser();
			//ReadTestsuite();

			// Validate Login (OKTA) of AD Admin User
			
			Result = Ds.Login(AdminUser,AdminPwd,SecurityAnswer);
			  Thread.sleep(3000);
			
			  if (Result) 
		        {
		           verifytitle("AD Portal SS Stage","AD HomePage");
		           
		           Thread.sleep(5000);
		           
			          if(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")).isDisplayed())
			          {
			        	  click(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")), "close cookies footer in homepage");
			          }
			Thread.sleep(2000);
				
			//  Validate relevant layout appears to AD Admin User on Login
				
			if(Search_Inpt.isDisplayed())
			{
				 boolean WelcomeProtal = driver.findElement(By.xpath("//span[@class=\"AD-HeaderRight AD-Welcome-Box\"]/span")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the Welcome Text in Homepage");
				 
				 boolean GridIcon= driver.findElement(By.xpath("//img[@id=\"AD-GridContainer1\"]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the GridIcon in Homepage");
				 
				 boolean HelpIcon = driver.findElement(By.xpath("//img[@id=\"AD-HelpIcon2\"]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the HelpIcon in Homepage");
				 
				 boolean AccountIcon = driver.findElement(By.xpath("//img[@id=\"AD-DropDown1\"]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the AccountIcon in Homepage");
				 
				 boolean Dashboard =driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the 'Dashboard' link in side panel of Homepage ");
				 
				 boolean Usermanagement = driver.findElement(By.xpath("//span[contains(text(),'User Management')]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the 'Usermanagement' link in side panel of Homepage ");
				 
				 boolean Applications =driver.findElement(By.xpath("//span[contains(text(),'Applications')]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the 'Applications' link in side panel of Homepage ");
				 
				 boolean Customers = driver.findElement(By.xpath("//span[contains(text(),'Customers')]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the 'Customers' link in side panel of Homepage ");
				 
				 boolean Settings = driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the 'Settings' link in side panel of Homepage ");
				 
				 boolean UsermanagementIcon = driver.findElement(By.xpath("(//img[@class=\"img img-responsive\"])[5]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the 'Usermanagement Icon' link in Dashboard page ");
				 
				 boolean CustomermanagementIcon = driver.findElement(By.xpath("(//img[@class=\"img img-responsive\"])[6]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the 'Customermanagement Icon' link in Dashboard page ");
				 
				 boolean SettingsIcon = driver.findElement(By.xpath("(//img[@class=\"img img-responsive AD-SettingIcon\"])")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the 'Settings Icon' link in Dashboard page ");
				 
				 boolean RecentActivities = driver.findElement(By.xpath("//p[contains(text(),'Recent Activities')]")).isDisplayed();
				 Reports.log("Pass", "Successfully Verified the 'RecentActivities' text in Dashboard page ");
				 
				 System.out.println("Successfully Validated the layout");
				 Reports.log("PASS", "Successfully Validated the layout of Admin User after login");
			}else
			{
				Reports.log("FAIL", "Failed to Validate the layout of Admin User after login");
			}
		 
// Create AD Admin User

				Highlighter(driver, UserManagement_Link);
				click(UserManagement_Link, "User Management link");
				Highlighter(driver, Group_button);
				click(Group_button, "+Group button");
				Randomname = GenerateRandomGroupName(5);
				EnterValue(Create_Group_name, Randomname);
				Highlighter(driver, Create_Group_name);
				DriverScript.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Highlighter(driver, Create_button);
				click(Create_button, "AdminGroup create button");
				
				DriverScript.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Highlighter(driver, Create_Group_SaveButton);
				click(Create_Group_SaveButton, "Group Save button");
				Thread.sleep(3000);

				List<WebElement> Groupscolumn = driver.findElements(By.xpath("//*[@id=\"tenantGroupsData\"]/table/thead/tr/th"));
				System.out.println("no.of column" + Groupscolumn.size());
				List<WebElement> Groupsrows = driver.findElements(By.xpath("//*[@id=\"tenantGroupsData\"]/table/tbody/tr/td[1]"));
				System.out.println("no.of rows" + Groupsrows.size());
                Thread.sleep(2000);
				boolean flag= false;
				for (int j = 1; j < Groupsrows.size(); j++) 
				{
					WebElement GroupsList = driver.findElement(By.xpath("//*[@id=\"tenantGroupsData\"]/table/tbody/tr["+ j + "]/td[2]//span[@class=\"CursorUnder\"]"));
					Value = GroupsList.getText();
					System.out.println(Value);
					System.out.println(Randomname);

					if (Value.contains(Randomname)) 
					{

						System.out.println("Value found  " + Value);
						Reports.log("PASS", "Successfully Verified the Group name");
						Highlighter(driver, GroupsList);
						click(GroupsList, "Newly created Admin user");
						Thread.sleep(2000);
						flag=true;
						break;
						
					} 
					Thread.sleep(1000);
				}
				if(flag==false)
					
				{
					System.out.println("Not Found  " + Value);
					Thread.sleep(1000);
					Reports.log("FAIL", "Failed to Verify the Sub Group name");
				}
				Thread.sleep(3000);
				Highlighter(driver, SubGroup_createbutton);
				click(SubGroup_createbutton, "SubGroup +Group button");

				RandomSubGrpName = GenerateRandomSubGroupName(5);
				EnterValue(Create_SubGroup_name, RandomSubGrpName);
				Highlighter(driver, Create_SubGroup_name);
				// DriverScript.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(2000);
				Highlighter(driver, Create_button);
				click(Create_button, "AdminSubGroup create button");
				// DriverScript.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(2000);
				Highlighter(driver, Create_Group_SaveButton);
				click(Create_Group_SaveButton, "SubGroup Save button");
				Thread.sleep(5000);
				Highlighter(driver, Create_user_button);
				click(Create_user_button, "+user button");
				Thread.sleep(5000);

				RandomnameUser =GenerateRandomName();
				
				EnterValue(Create_user_username,RandomnameUser);
				Highlighter(driver, Create_user_username);
				Thread.sleep(2000);
				
				RandomURL = GenerateRandomURL();
				Create_user_Slider.click();
				EnterValue(NewUser_Email, RandomURL);
				Highlighter(driver, NewUser_Email);
				DriverScript.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Highlighter(driver, NewUser_Create_button);
				click(NewUser_Create_button, "NewUser create button");
				Thread.sleep(2000);
//				Highlighter(driver, NewUser_SAVE_button);
//				click(NewUser_SAVE_button, "NewUser Save button");
//				Thread.sleep(3000);
				click(Groups_Navigator,"Groups tab to go back to usermanagement screen");
				Thread.sleep(3000);

// Update AD Admin User attributes

				Highlighter(driver, Users_tab);
				click(Users_tab, "Users tab under UserManagement");

				List<WebElement> Userscolumn = driver.findElements(By.xpath("//*[@id=\"Users\"]/table/thead/tr/th"));
				System.out.println("no.of column" + Userscolumn.size());

				List<WebElement> Usersrows = driver.findElements(By.xpath("//*[@id=\"Users\"]/table/tbody/tr/td[1]"));
				System.out.println("no.of rows" + Usersrows.size());

				boolean flag1= false;
				for (int j = 1; j < Usersrows.size(); j++) 
				{ 
					 try {
						 
						 UsersList = driver.findElement(By.xpath("(//*[@id=\"UMUserstbody4\"])[" + j + "]")); 
						 Text = UsersList.getText();
					    
					 } catch(Exception e) 
					 {
						 Thread.sleep(2000);
						 UsersList = driver.findElement(By.xpath("(//*[@id=\"UMUserstbody4\"])[" + j + "]"));
						 Text = UsersList.getText();
				     }
					
					System.out.println(Text);
					System.out.println(RandomnameUser);
					if (Text.contains(RandomnameUser)) 
					{
						System.out.println("Value found  " + RandomnameUser);
						Reports.log("PASS", "Successfully Verified the SubGroup name");
						WebElement edit_icon = driver.findElement(By.xpath("(//*[@id=\"UMUserstbody10\"])[" + j + "]"));
						Highlighter(driver, edit_icon);
						click(edit_icon, "Users Edit icon");
						Thread.sleep(2000);
						flag1=true;
						break;
					} 
					//Thread.sleep(1000);
				}
				if(flag1==false)
				{
					System.out.println("Not Found  " + Text);
					Thread.sleep(1000);
					Reports.log("FAIL", "Failed to Verify the Sub Group name");
				}
				Thread.sleep(3000);
				click(Choose_File, "Choose file to upload user picture");
				Upload_File("upload");
				Thread.sleep(3000);
   	            
   	            Highlighter(driver, User_AssignTo_Group);
   	            Thread.sleep(1000);
   	            click(User_AssignTo_Group, "Assign to group link in edit user page");
   	            Thread.sleep(2000);
   	            click(User_AssignTo_Group_Search, "Assign to group search dropdown");
   	            Thread.sleep(3000);
   	            Highlighter(driver, driver.findElement(By.xpath("(//span[contains(text(),'PSVBSB Support')])[2]")));
   	            click(driver.findElement(By.xpath("(//span[contains(text(),'PSVBSB Support')])[2]")), " 'AD Tech Support' group from the dropdown ");
				Thread.sleep(2000);
   	            Highlighter(driver, AssignTo_Group_SaveButton);
				click(AssignTo_Group_SaveButton, " Save button");
				Thread.sleep(6000);

// Terminate AD Admin User

				Thread.sleep(5000);
				Highlighter(driver, Edit_User_Disable);
				click(Edit_User_Disable, "Disable button in edit user page");
				Highlighter(driver, Disable_confirmation);
				click(Disable_confirmation, "Disable confirmation popup");
				Thread.sleep(5000);
				click(Users_tab, "Users tab under UserManagement");
				Thread.sleep(3000);
				EnterValue(Search_bar, RandomURL);
				Thread.sleep(3000);
				WebElement Users_Edit_icon = driver.findElement(By.xpath("//*[@id=\"UMUserstbody10\"]"));
				Highlighter(driver, Users_Edit_icon);		
				Thread.sleep(5000);
	            click(Users_Edit_icon, "User Edit Icon");
	            Thread.sleep(5000);
	            String ButtonEnable=Edit_User_Enable.getText();
	            System.out.println(ButtonEnable);
	            
				if (ButtonEnable.equalsIgnoreCase("ENABLE")) 
				{
					
					Highlighter(driver,Edit_User_Enable);
					Edit_User_Enable.click();
					Highlighter(driver, Enable_confirmation);
					click(Enable_confirmation, "Enable confirmation popup");
					
					System.out.println("Successfully disabled the user");
					Reports.log("PASS", "Successfully disabled the user");
				
				} else 
				{
					Reports.log("FAIL", "Failed to disable the user");
				}

//				driver.findElement(By.xpath("//a[@id=\"tab_user1\"]/span")).click();
//				WebElement User1 = driver.findElement(By.xpath("(//*[@id=\"UMUserstbody4\"])[4]"));
//				String UserName1 = User1.getText();
//				System.out.println("Name is " + UserName1);
//				Thread.sleep(3000);
//				WebElement Users_Edit_icon1 = driver.findElement(By.xpath("(//*[@id=\"UMUserstbody10\"]/img)[4]"));
//				Highlighter(driver, Users_Edit_icon1);
//				click(Users_Edit_icon1, "User Edit Icon");
//				Thread.sleep(5000);
//				Highlighter(driver, Edit_User_Disable);
				
				Thread.sleep(3000);
				click(Users_tab, "Users tab under UserManagement");
				Thread.sleep(5000);
				Search_bar.click();
				Thread.sleep(1000);
				Search_bar.sendKeys(RandomURL);
				Search_bar.clear();
				Thread.sleep(2000);
				EnterValue(Search_bar, RandomURL);
				Thread.sleep(5000);
				WebElement Users_Edit_icon1= driver.findElement(By.xpath("//*[@id=\"UMUserstbody10\"]"));
				Highlighter(driver, Users_Edit_icon1);
				Thread.sleep(2000);
				click(Users_Edit_icon1, "User Edit Icon");
	            Thread.sleep(5000);
						
	            Highlighter(driver, Edit_User_Delete);
				click(Edit_User_Delete, "Delete button in edit user page");
				Thread.sleep(2000);
				
				Highlighter(driver, driver.findElement(By.xpath("//button[contains(text(),'DELETE')]")));		
				click(driver.findElement(By.xpath("//button[contains(text(),'DELETE')]")), "Delete confirmation popup");
				Thread.sleep(8000);
				click(Users_tab, "Users tab under UserManagement");
				Thread.sleep(5000);
			   
				Search_bar.click();
				Thread.sleep(1000);
				EnterValue(Search_bar, RandomURL);
				Search_bar.clear();
				Thread.sleep(2000);
				Search_bar.sendKeys(RandomURL);
				Thread.sleep(5000);
				
	if(driver.findElement(By.xpath("//div[@class=\"AD-noMatches-profiles AD-noMatches\"]/h1")).getText().equalsIgnoreCase("No records found"))
				{
					System.out.println("User is  deleted");
					Highlighter(driver, driver.findElement(By.xpath("//div[@class=\"AD-noMatches-profiles AD-noMatches\"]/h1")));
					Reports.log("PASS","Successfully Verified the deleted the user " + RandomURL ); 
				}
				else
				{
					System.out.println("User is not deleted");
					  Reports.log("FAIL","Failed to delete the user still user exists " + RandomURL); 
				}
				
			  } 
			else {
				System.out.println("failed to verify Login");
				Reports.log("FAIL", "Failed to Login ");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
		
		DriverScript.driver.manage().deleteAllCookies();
		DriverScript.driver.quit();
	}

}
