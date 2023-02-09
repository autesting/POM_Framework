package com.averydennison.qa.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.averydennison.qa.pages.DriverScript;
import com.averydennison.qa.pages.ObjectRepository;
import com.averydennison.qa.pages.Reports;
import com.averydennison.qa.pages.UtilityFunctions;

@SuppressWarnings("unused")
public class AD_SupportUser extends DriverScript							
{
	static DriverScript Ds = new DriverScript();
	public static String Value;
	
         public static void ADSupportUser() 
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
		           verifytitle("AD Portal SS Stage"," AD HomePage title");
		                
		        
   // Validate relevant layout appears to AD Support User on Login        
		           
		           VerifyText(WebPortal, "Welcome" ,"Landing Page");   
		           Thread.sleep(5000);
		           
		           if(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")).isDisplayed())
			          {
			        	  click(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")), "close cookies footer in homepage");
			          }
			Thread.sleep(3000);  
		           
		// Create AD Support User
		 		  
			   Highlighter(driver, UserManagement_Link);
		        click(UserManagement_Link, "User Management link in side panel of homepage");
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
				Thread.sleep(10000);
				//DriverScript.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	

				List<WebElement> Groupscolumn = driver.findElements(By.xpath("//*[@id=\"tenantGroupsData\"]/table/thead/tr/th"));
				System.out.println("no.of column" + Groupscolumn.size());
				List<WebElement> Groupsrows = driver.findElements(By.xpath("//*[@id=\"tenantGroupsData\"]/table/tbody/tr/td[2]"));
				System.out.println("no.of rows" + Groupsrows.size());

				boolean flag=false;
				for (int j = 1; j < Groupsrows.size(); j++)
				{
					WebElement GroupsList = driver.findElement(By.xpath("//*[@id=\"tenantGroupsData\"]/table/tbody/tr["+ j + "]/td[2]//span[@class=\"CursorUnder\"]"));
					// String value=driver.findElement(By.xpath("(//*[@id=\"UMGrouptbody4\"]//span)["+j+"]")).getText();
					String value = GroupsList.getText();
					System.out.println(value);

					if (value.contains(Randomname)) 
					{

						System.out.println("Value found  " + value);
						Reports.log("PASS", "Successfully Verified the Group name");
						Highlighter(driver, GroupsList);
						//click(GroupsList, "Newly created Admin user");
						WebElement edit_icon = driver.findElement(By.xpath("//*[@id=\"tenantGroupsData\"]/table/tbody/tr["+ j + "]/td[5]"));
						Highlighter(driver, edit_icon);
						click(edit_icon, "Users Edit icon");
						Thread.sleep(2000);
						flag=true;
						break;
                 } 

				}
				if(flag==false) 
				{
					System.out.println("Not Found  " + Randomname);
					Reports.log("FAIL", "Failed to Verify the Group name");
				}
				Thread.sleep(3000);
				Highlighter(driver, Users_Add_Application);
				click(Users_Add_Application, "+Add Application in edit user");
				Thread.sleep(3000);
				
				click(Choose_Application_dropdown, "Choose An Application search dropdown");
				    Thread.sleep(2000);
		            click(driver.findElement(By.xpath("//span[contains(text(),'Audit ')]")), "value from the +Add Application dropdown");
		            Thread.sleep(1000);          
				Highlighter(driver, Choose_Application_AddButton);
				click(Choose_Application_AddButton, "ADD button of Choose an Application");
				Thread.sleep(3000);
				click(EditUser_Save_button, "Save button in Edit group page");
				Thread.sleep(5000);
				
				Search_bar.click();
				Thread.sleep(3000);
				EnterValue(Search_bar, Randomname);
				Thread.sleep(5000);
				
				WebElement Search_result_grpname =driver.findElement(By.xpath("//td[@id=\"UMGrouptbody4\"]/span"));
				click(Search_result_grpname, "newly created group from search results");
				Thread.sleep(5000);
				
				Highlighter(driver, SubGroup_createbutton);
				click(SubGroup_createbutton, "SubGroup +Group button");

				RandomSubGrpName = GenerateRandomSubGroupName(5);
				EnterValue(Create_SubGroup_name, RandomSubGrpName);
				Highlighter(driver, Create_SubGroup_name);
				Thread.sleep(2000);
				
				Highlighter(driver, Create_button);
				click(Create_button, "AdminSubGroup create button");
				Thread.sleep(2000);
									
				Highlighter(driver, Users_Add_Application);
				click(Users_Add_Application, "+Add Application in edit user");
				Thread.sleep(3000);
				click(Choose_Application_dropdown, "Choose An Application search dropdown in Edit subgroup page");
				 Thread.sleep(2000);
				 
		            click(driver.findElement(By.xpath("//span[contains(text(),'Freshmarx Edit 1')]")), "value from the +Add Application dropdown");
		            Thread.sleep(1000);
		            
				Highlighter(driver, Choose_Application_dropdown);
				
				Highlighter(driver, Choose_Application_AddButton);
				click(Choose_Application_AddButton, "ADD button of Choose an Application");
				Thread.sleep(3000);				
				
				click(Create_Group_SaveButton, "SubGroup Save button");
				Thread.sleep(3000);
	           
				Highlighter(driver, Create_user_button);
				click(Create_user_button, "+user button");
				Thread.sleep(2000);
								
				EnterValue(Create_user_username, "New SupportUser");
				Highlighter(driver, Create_user_username);
				Thread.sleep(2000);

				RandomURL = GenerateRandomURL();
				EnterValue(NewUser_Email, RandomURL );
				Highlighter(driver, NewUser_Email);
				DriverScript.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Highlighter(driver, NewUser_Create_button);
				click(NewUser_Create_button, "NewUser create button");
					
				click(Groups_Navigator, "Groups tab to go back to usermanagement screen");
				Thread.sleep(3000);
		 		  
	
		 		   
	// Update AD Support User attributes	 	
					
				    Highlighter(driver, Users_tab);
					click(Users_tab, "Users tab under UserManagement");
					Thread.sleep(3000);
					EnterValue(Search_bar, RandomURL);
					Thread.sleep(5000);
					WebElement edit_icon =driver.findElement(By.xpath("//td[@id=\"UMUserstbody10\"]/img"));
					Highlighter(driver, edit_icon);
					click(edit_icon, "Users Edit icon");
					Thread.sleep(3000);
					
					click(Create_customer_Attachments_choosefile, "Attachments ChooseFile button");
	   	            Thread.sleep(1000);
	   	            Upload_File("upload1");
	   	            Thread.sleep(2000);
	   	            
	   	            Highlighter(driver, User_AssignTo_Group);
	   	            Thread.sleep(1000);
	   	            click(User_AssignTo_Group, "Assign to group link in edit user page");
	   	            Thread.sleep(2000);
	   	            click(User_AssignTo_Group_Search, "Assign to group search dropdown");
	   	            Thread.sleep(3000);
	   	            Highlighter(driver, driver.findElement(By.xpath("(//span[contains(text(),'AD Tech Support')])[2]")));
	   	            click(driver.findElement(By.xpath("(//span[contains(text(),'AD Tech Support')])[2]")), " 'AD Tech Support' group from the dropdown ");
					Thread.sleep(2000);
	   	            Highlighter(driver, AssignTo_Group_SaveButton);
					click(AssignTo_Group_SaveButton, " Save button");
					Thread.sleep(6000);
					
			WebElement EditUser_Breadcrumb= driver.findElement(By.xpath("//span[contains(text(),'Edit User')]"));
			         click(EditUser_Breadcrumb, "Edit_user breadcrumb to navigate back to home page");
					
					driver.findElement(By.xpath("//button[@id=\"UMAssignedGroup24\"]")).click();
					Thread.sleep(3000);
		 		    driver.findElement(By.xpath("//img[@id=\"AD-DropDown1\"]")).click();
		 		    Highlighter(driver, driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")));
		 		    driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		 		    Thread.sleep(3000);
		 		    
		 		    Clear_Cookies();
		 		    
	// Assign Customer User role to AD Support user	 		   
		 		   
		 		   driver.navigate().to(URL); 
		 		   Ds.Login(SupportUsername,SupportUserPassword,SecurityAnswer);
		 		   
		 		   Thread.sleep(5000);
		 		   
		 		  if(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")).isDisplayed())
		          {
		        	  click(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")), "close cookies footer in homepage");
		          }
		Thread.sleep(2000);
		 // Validate relevant layout appears to AD Support User on Login 	   
		 		   
		 		  if(GridIcon.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the grid icon");
		        	   Reports.log("PASS", "Successfully Verified the grid icon");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the grid icon");
		        	   Reports.log("FAIL", "Failed to Verify the grid icon");
		           }
		           
		           if(HelpIcon.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the Help icon");
		        	   Reports.log("PASS", "Successfully Verified the Help icon");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the Help icon");
		        	   Reports.log("FAIL", "Failed to Verify the Help icon");
		           }
		           if(AccountIcon.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the Account Icon");
		        	   Reports.log("PASS", "Successfully Verified the Account Icon");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the Account Icon");
		        	   Reports.log("FAIL", "Failed to Verify the Account Icon");
		           }
		           
		           if(Dashboard.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the Dashboard text in side pane");
		        	   Reports.log("PASS", "Successfully verified the Dashboard text in side pane");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the Dashboard text in side pane");
		        	   Reports.log("FAIL", "Failed to Verify the Dashboard text in side pane");
		           }
		           
		           if(Assumerole.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the AssuneRole text in side pane");
		        	   Reports.log("PASS", "Successfully verified the AssumeRole text in side pane");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the AssumeRole text in side pane");
		        	   Reports.log("FAIL", "Failed to Verify the AssumeRole text in side pane");
		           }
		           
		           if(Systemlog.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the Systemlog text in side pane");
		        	   Reports.log("PASS", "Successfully verified the Systemlog text in side pane");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the Systemlog text in side pane");
		        	   Reports.log("FAIL", "Failed to Verify the Systemlog text in side pane");
		           }
		           
		           if(Settings.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the Settings text in side pane");
		        	   Reports.log("PASS", "Successfully verified the Settings text in side pane");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the Settings text in side pane");
		        	   Reports.log("FAIL", "Failed to Verify the Settings text in side pane");
		           }
		           
		           if(SystemLog_icon.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the SystemLog_icon text in side pane");
		        	   Reports.log("PASS", "Successfully verified the SystemLog_icon text in side pane");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the SystemLog_icon text in side pane");
		        	   Reports.log("FAIL", "Failed to Verify the SystemLog_icon text in side pane");
		           }
		           if(AssumeRole_icon.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the AssumeRole_icon text in side pane");
		        	   Reports.log("PASS", "Successfully verified the AssumeRole_icon text in side pane");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the AssumeRole_icon text in side pane");
		        	   Reports.log("FAIL", "Failed to Verify the AssumeRole_icon text in side pane");
		           }
		             
		           if(Settings_icon.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the Settings_icon text in side pane");
		        	   Reports.log("PASS", "Successfully verified the Settings_icon text in side pane");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the Settings_icon text in side pane");
		        	   Reports.log("FAIL", "Failed to Verify the Settings_icon text in side pane");
		           }
		          
		           if(RecentActivities.isDisplayed())
		           {
		        	   System.out.println("Successfully verified the RecentActivities text in side pane");
		        	   Reports.log("PASS", "Successfully verified the RecentActivities text in side pane");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the RecentActivities text in side pane");
		        	   Reports.log("FAIL", "Failed to Verify the RecentActivities text in side pane");
		           }
		           
		 		   
		 	   
		 		   Highlighter(driver, AssumeRole_sidepanel);
			       click(AssumeRole_sidepanel,"AssumeRole");
			       Thread.sleep(5000);
			       
			       
//Search for the customer to assign roles
			       
			       List<WebElement> AllCustomersrowz = DriverScript.driver.findElements(By.xpath("//div[@class=\"row col-lg-12\"]/table/tbody/tr/td[2]"));
					System.out.println("no.of rows" + AllCustomersrowz.size());

					boolean flagz=false;
					for (int j = 1; j < AllCustomersrowz.size(); j++) 
					{
						WebElement AllCustomersList = DriverScript.driver.findElement(By.xpath("//div[@class=\"row col-lg-12\"]/table/tbody/tr["+j+"]/td[2]"));
						String value = AllCustomersList.getText();
						System.out.println(value);

						if (value.contains("AACustomer new")) 
						{

							System.out.println("Value found  " + value);
							Reports.log("PASS", "Successfully found the Searched customer " +value );
							Highlighter(driver, AllCustomersList);
							WebElement Customer_name = DriverScript.driver.findElement(By.xpath("//div[@class=\"row col-lg-12\"]/table/tbody/tr["+j+"]/td[2]"));
							click(Customer_name, "Customer AA Customer new to assign roles");
							Thread.sleep(5000);
							flagz=true;
							break;
			            } 
						
					}
					if(flagz=false)
					 {
						System.out.println("Not Found  the customer ' AACustomer new '" );
						Reports.log("FAIL", "Failed to found the Searched customer  AACustomer new  ");
						
					 }	
			
					
			       WebElement AssignCustomerUsertoSupportUser= driver.findElement(By.xpath("//span[contains(text(),'Assume role')]"));
			       Highlighter(driver, AssignCustomerUsertoSupportUser);
			       click(AssignCustomerUsertoSupportUser, "Assume Role  to support user");
			       Thread.sleep(3000);
			       
	// Validate AD Support user access after assigning Customer User Role		       
			       
			      // if(driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]")).isDisplayed());
			       
			    	   if(driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]")).isDisplayed())
			           {
			    		   Highlighter(driver, driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]")));
			        	   System.out.println("Successfully verified the 'Dashboard' link in sidepanel");
			        	   Reports.log("PASS", "Successfully verified the 'Dashboard' link in sidepanel");
			           }
			           else
			           {
			        	   System.out.println("Failed to verify the 'Dashboard' link in sidepanel");
			        	   Reports.log("FAIL", "Failed to Verify the 'Dashboard' link in sidepanel");
			           }
			     
			    	   if(driver.findElement(By.xpath("//span[contains(text(),'Storage')]")).isDisplayed())
			           {
			    		   Highlighter(driver, driver.findElement(By.xpath("//span[contains(text(),'Storage')]")));
			        	   System.out.println("Successfully verified the 'Storage' link in sidepanel ");
			        	   Reports.log("PASS", "Successfully Verified the 'Storage' link in sidepanel");
			           }
			           else
			           {
			        	   System.out.println("Failed to verify the 'Storage' link in sidepanel");
			        	   Reports.log("FAIL", "Failed to Verify the 'Storage' link in sidepanel");
			           }
			    	   
			      
			    	   if(driver.findElement(By.xpath("//span[contains(text(),'Applications')]")).isDisplayed())
			           {
			    		   Highlighter(driver, driver.findElement(By.xpath("//span[contains(text(),'Applications')]")));
			        	   System.out.println("Successfully verified the 'Applications' link in sidepanel ");
			        	   Reports.log("PASS", "Successfully Verified the 'Applications' link in sidepanel");
			           }
			           else
			           {
			        	   System.out.println("Failed to verify the 'Applications' link in sidepanel");
			        	   Reports.log("FAIL", "Failed to Verify the 'Applications' link in sidepanel");
			           }
			    	   

			    	   if(driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).isDisplayed())
			           {
			    		   Highlighter(driver, driver.findElement(By.xpath("//span[contains(text(),'Settings')]")));
			        	   System.out.println("Successfully verified the 'Settings' link in sidepanel ");
			        	   Reports.log("PASS", "Successfully Verified the 'Settings' link in sidepanel");
			           }
			           else
			           {
			        	   System.out.println("Failed to verify the 'Settings' link in sidepanel");
			        	   Reports.log("FAIL", "Failed to Verify the 'Settings' link in sidepanel");
			           }
			    	   
			    	   System.out.println("Successfully validated the AD Support user access after assigning Customer User Role");
				       Reports.log("PASS", "Successfully validated the AD Support user access after assigning Customer User Role");
			     
			     		     
			       
    // Terminate Customer User role to AD Support User	
			       
			       Highlighter(driver, AssumeRolelogout);
			       click(AssumeRolelogout, "logout button");
			       Thread.sleep(3000);
			       Clear_Cookies();
			       
   // Assign Customer Admin Role to AD Support User		
			       
			       driver.navigate().to(URL); 
		 		   Ds.Login(SupportUsername,SupportUserPassword,SecurityAnswer);
			       
		 		   Thread.sleep(5000);
		 		   
		 		  if(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")).isDisplayed())
		          {
		        	  click(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")), "close cookies footer in homepage");
		          }
		Thread.sleep(2000);

		 		  Highlighter(driver, AssumeRole_sidepanel);
			       click(AssumeRole_sidepanel,"AssumeRole");
			       Thread.sleep(5000);
			       
			       
//Search for the customer to assign roles
			       
			       List<WebElement> AllCustomersroww = DriverScript.driver.findElements(By.xpath("//div[@class=\"row col-lg-12\"]/table/tbody/tr/td[2]"));
					System.out.println("no.of rows" + AllCustomersroww.size());

					boolean flagg=false;
					for (int j = 1; j < AllCustomersroww.size(); j++) 
					{
						WebElement AllCustomersList = DriverScript.driver.findElement(By.xpath("//div[@class=\"row col-lg-12\"]/table/tbody/tr["+j+"]/td[2]"));
						String value = AllCustomersList.getText();
						System.out.println(value);

						if (value.contains("AACustomer new")) 
						{

							System.out.println("Value found  " + value);
							Reports.log("PASS", "Successfully found the Searched customer " +value );
							Highlighter(driver, AllCustomersList);
							WebElement Customer_name = DriverScript.driver.findElement(By.xpath("//div[@class=\"row col-lg-12\"]/table/tbody/tr["+j+"]/td[2]"));
							click(Customer_name, "Customer AA Customer new to assign roles");
							Thread.sleep(5000);
							flagg=true;
							break;
			            } 
						
					}
					if(flagg=false)
					 {
						System.out.println("Not Found  the customer ' AACustomer new '" );
						Reports.log("FAIL", "Failed to found the Searched customer  AACustomer new  ");
						
					 }	 		   
		 		   
			       WebElement AssignCustomerAdmintoSupportUser= driver.findElement(By.xpath("//span[contains(text(),'Assume admin role')]"));
			       Highlighter(driver, AssignCustomerAdmintoSupportUser);
			       click(AssignCustomerAdmintoSupportUser, "Assume admin role  to support user");
			       Thread.sleep(8000);
		 		   
		 		   
//  Validate AD Support user access after assigning Customer Admin User Role
			       
			       
			       if(driver.findElement(By.xpath("//span[@class=\"AD-HeaderRight AD-Welcome-Box\"]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//span[@class=\"AD-HeaderRight AD-Welcome-Box\"]")));
		        	   System.out.println("Successfully verified the 'Welcome_Text' link in HomePage");
		        	   Reports.log("PASS", "Successfully verified the 'Welcome_Text' link in HomePage");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Welcome_Text' link in HomePage");
		        	   Reports.log("FAIL", "Failed to Verify the 'Welcome_Text' link in HomePage");
		           }
			       Thread.sleep(2000);
			       
			       if(GridIcon.isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//img[@id=\"AD-GridContainer1\"]")));
		        	   System.out.println("Successfully verified the 'GridIcon' in HomePage");
		        	   Reports.log("PASS", "Successfully verified the 'GridIcon'  in HomePage");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'GridIcon'  in HomePage");
		        	   Reports.log("FAIL", "Failed to Verify the 'GridIcon'  in HomePage");
		           }
			       Thread.sleep(2000);
			       if(HelpIcon.isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//img[@id=\"AD-HelpIcon2\"]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'HelpIcon' in HomePage");
		        	   Reports.log("PASS", "Successfully verified the 'HelpIcon'  in HomePage");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'HelpIcon'  in HomePage");
		        	   Reports.log("FAIL", "Failed to Verify the 'HelpIcon'  in HomePage");
		           }
			       Thread.sleep(2000);
			       if(AccountIcon.isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//img[@id=\"AD-DropDown1\"]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'AccountIcon' in HomePage");
		        	   Reports.log("PASS", "Successfully verified the 'AccountIcon'  in HomePage");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'AccountIcon'  in HomePage");
		        	   Reports.log("FAIL", "Failed to Verify the 'AccountIcon'  in HomePage");
		           }
			       Thread.sleep(2000);
			       if(driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Dashboard' in HomePage");
		        	   Reports.log("PASS", "Successfully verified the 'Dashboard' link in HomePage");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Dashboard'  in HomePage");
		        	   Reports.log("FAIL", "Failed to Verify the 'Dashboard' link  in HomePage");
		           } 
			       Thread.sleep(2000);
			       if(driver.findElement(By.xpath("//span[contains(text(),'Storage')]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//span[contains(text(),'Storage')]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Storage' in HomePage");
		        	   Reports.log("PASS", "Successfully verified the 'Storage' link  in HomePage");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Dashboard'  in HomePage");
		        	   Reports.log("FAIL", "Failed to Verify the 'Dashboard' link  in HomePage");
		           }  
					
			       Thread.sleep(2000); 
			       if(driver.findElement(By.xpath("//span[contains(text(),'User Management')]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//span[contains(text(),'User Management')]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Usermanagement' in HomePage");
		        	   Reports.log("PASS", "Successfully verified the 'Usermanagement' link  in HomePage");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Usermanagement'  in HomePage");
		        	   Reports.log("FAIL", "Failed to Verify the 'Usermanagement' link  in HomePage");
		           }  
			       Thread.sleep(2000);
			       if(driver.findElement(By.xpath("//span[contains(text(),'Applications')]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//span[contains(text(),'Applications')]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Applications' in HomePage");
		        	   Reports.log("PASS", "Successfully verified the 'Applications' link  in HomePage");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Applications'  in HomePage");
		        	   Reports.log("FAIL", "Failed to Verify the 'Applications' link  in HomePage");
		           }   
			       Thread.sleep(2000);
			       if(driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Settings' in HomePage");
		        	   Reports.log("PASS", "Successfully verified the 'Settings' link  in HomePage");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Settings'  in HomePage");
		        	   Reports.log("FAIL", "Failed to Verify the 'Settings' link  in HomePage");
		           } 
			       Thread.sleep(2000); 
			       if(driver.findElement(By.xpath("(//img[@class=\"img img-responsive\"])[5]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("(//img[@class=\"img img-responsive\"])[5]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Usermanagement Icon' in Dashboard");
		        	   Reports.log("PASS", "Successfully verified the 'Usermanagement Icon'   in Dashboard");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Usermanagement Icon'  in Dashboard");
		        	   Reports.log("FAIL", "Failed to Verify the 'Usermanagement Icon'  in Dashboard");
		           } 
			       Thread.sleep(2000); 
			       if(driver.findElement(By.xpath("(//img[@class=\"img img-responsive AD-SettingIcon\"])[1]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("(//img[@class=\"img img-responsive AD-SettingIcon\"])[1]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Storage Icon' in Dashboard");
		        	   Reports.log("PASS", "Successfully verified the 'Storage Icon'   in Dashboard");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Storage Icon'  in Dashboard");
		        	   Reports.log("FAIL", "Failed to Verify the 'Storage Icon'  in Dashboard");
		           } 
				
			       Thread.sleep(2000);
			       if(driver.findElement(By.xpath("(//img[@class=\"img img-responsive\"])[6]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("(//img[@class=\"img img-responsive\"])[6]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Applications Icon' in Dashboard");
		        	   Reports.log("PASS", "Successfully verified the 'Applications Icon'   in Dashboard");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Applications Icon'  in Dashboard");
		        	   Reports.log("FAIL", "Failed to Verify the 'Applications Icon'  in Dashboard");
		           } 
			       Thread.sleep(2000);
			       if(driver.findElement(By.xpath("(//img[@class=\"img img-responsive AD-SettingIcon\"])[2]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("(//img[@class=\"img img-responsive AD-SettingIcon\"])[2]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Settings Icon' in Dashboard");
		        	   Reports.log("PASS", "Successfully verified the 'Settings Icon'   in Dashboard");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Settings Icon'  in Dashboard");
		        	   Reports.log("FAIL", "Failed to Verify the 'Settings Icon'  in Dashboard");
		           } 
			       Thread.sleep(2000);
			       if(driver.findElement(By.xpath("//p[contains(text(),'Recent Activities')]")).isDisplayed())
		           {
		    		   Highlighter(driver,driver.findElement(By.xpath("//p[contains(text(),'Recent Activities')]")).isDisplayed());
		        	   System.out.println("Successfully verified the 'Recent Activities' text in Dashboard");
		        	   Reports.log("PASS", "Successfully verified the 'Recent Activities' text  in Dashboard");
		           }
		           else
		           {
		        	   System.out.println("Failed to verify the 'Recent Activities' text in Dashboard");
		        	   Reports.log("FAIL", "Failed to Verify the 'Recent Activities' text in Dashboard");
		           }
			       Thread.sleep(2000);				 
					 System.out.println("Successfully Validated AD Support user access after assigning Customer Admin User Role");
					 Reports.log("PASS", "Successfully Validated AD Support user access after assigning Customer Admin User Role");
					 Thread.sleep(3000);
					 
				
	// Terminate Customer Admin User role to AD Support User
					 
					   Highlighter(driver, AssumeRolelogout);
				       click(AssumeRolelogout, "logout button");
				       Thread.sleep(8000);
				       
	// Terminate AD Support User			  
				       
				       driver.findElement(By.xpath("//img[@id=\"AD-DropDown1\"]")).click();
			 		   Highlighter(driver, driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")));
			 		   driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
			 		   Thread.sleep(3000);      
		       
		 	} else 
		           { 
			           System.out.println("failed to verify Login"); 
	                   Reports.log("FAIL","Failed to Login "); 
		           } 
					
		      } catch (Exception e)
		         {
			         System.out.println(e.getMessage()); 
		         }
		
		         DriverScript.driver.manage().deleteAllCookies(); 
		         DriverScript.driver.quit();
		  }
		 
}
