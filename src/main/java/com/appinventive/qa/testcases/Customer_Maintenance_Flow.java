package com.appinventive.qa.testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.appinventive.qa.pages.DriverScript;
import com.appinventive.qa.pages.Reports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Customer_Maintenance_Flow extends DriverScript
{

		static DriverScript Ds = new DriverScript();

	         public static void CustomerMaintenanceFlow() 
			  { 
				  boolean Result; 
				  try {
					  
					  ReadProperties();
					  LaunchBrowser();
					//  ReadTestsuite();
					
	// Validate Login (OKTA) of AD Admin User
			  
					  Result = Ds.Login(AdminUser,AdminPwd,SecurityAnswer);
					  Thread.sleep(5000);
			       if (Result) 
			        {
			           verifytitle("AD Portal SS Stage","AD HomePage");
			           
			           Thread.sleep(5000);
			           
				          if(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")).isDisplayed())
				          {
				        	  click(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")), "close cookies footer in homepage");
				          }
				Thread.sleep(2000);
		
	
	// Update one or many customer attributes
    // Change/Modify Subscribed Applications	
			           
			           
			            Highlighter(driver, Customers_AppIcon);
			            click(Customers_AppIcon, "Customers link in side panel");
			            Thread.sleep(2000);
				        Highlighter(driver, Customer_button);
				        click(Customer_button, "+Customer button");
			            Thread.sleep(3000);
			           
			            if(Customers_AppIcon.isDisplayed())
			            {
			            Randomname=GenerateRandomCustomerName(6);
			            EnterValue(Create_customer_name, Randomname);
			            Highlighter(driver, Create_customer_name);
			            
			            EnterValue(Create_customer_domain, "dontmind1987@gmail.com");
			            Highlighter(driver, Create_customer_domain);
			            
			            EnterValue(Create_customer_Agreemnt_Number, "456867642334");
			            Highlighter(driver, Create_customer_Agreemnt_Number);
			            
			            EnterValue(Create_customer_contacts_phonenumber, "6754588878");
			            Highlighter(driver, Create_customer_contacts_phonenumber);
			            
			            EnterValue(Create_customer_contacts_email, "Customeruser@gmail.com");
			            Highlighter(driver, Create_customer_contacts_email);
			            
			            EnterValue(Create_customer_Address_Street, "New Street");
			            Highlighter(driver, Create_customer_Address_Street);
			            
			            EnterValue(Create_customer_Address_City, "Alaska");
			            Highlighter(driver, Create_customer_Address_City);
			            
			            EnterValue(Create_customer_Address_Zipcode, "7654892");
			            Highlighter(driver, Create_customer_Address_Zipcode);
			            //SeldropDownbyValue(Create_customer_Address_Country, "Albania", "Create Customer Address country dropdown");
			            //SeldropDownbyValue(Create_customer_Address_State, "Armavir", "Create Customer Address state dropdown");
			            click(Create_customer_Address_Country, "Create Customer Address country dropdown");
			            Thread.sleep(2000);
			            Highlighter(driver, driver.findElement(By.xpath("//span[contains(text(),'Belgium')]")));
			            click(driver.findElement(By.xpath("//span[contains(text(),'Belgium')]")), "value from the country dropdown");
			            Thread.sleep(1000);
			            
			            click(Create_customer_Address_State, "Create customer address state dropdown");
			            Thread.sleep(2000);
			            Highlighter(driver,driver.findElement(By.xpath("//span[contains(text(),'Limburg')]")));
			            click(driver.findElement(By.xpath("//span[contains(text(),'Limburg')]")), "value from the country dropdown");
			            Thread.sleep(1000);
			            
			            click(Create_customer_AddApplications, "+Add Application");
			            click(Choose_an_application_dropdown, "choose an application search dropdown");
			            Thread.sleep(2000);
			            Highlighter(driver,driver.findElement(By.xpath("((//div[@class=\"dropdown-menu applicationDB\"])[3]/div/a/div)[5]")));
			            click(driver.findElement(By.xpath("((//div[@class=\"dropdown-menu applicationDB\"])[3]/div/a/div)[5]")), "value from the +Add Application dropdown");
			            Thread.sleep(1000);
			            
			            click(Create_customer_SelApplication_ADD_Button, "Add button in select application");
	                    Thread.sleep(2000);
	                    
	                    click(Create_customer_AddApplications, "+Add Application");
			            click(Choose_an_application_dropdown, "choose an application search dropdown");
			            Thread.sleep(2000);
			            Highlighter(driver,driver.findElement(By.xpath("((//div[@class=\"dropdown-menu applicationDB\"])[3]/div/a/div)[6]")));
			            click(driver.findElement(By.xpath("((//div[@class=\"dropdown-menu applicationDB\"])[3]/div/a/div)[6]")), "value from the +Add Application dropdown");
			            Thread.sleep(1000);
			            
	                    click(Create_customer_SelApplication_ADD_Button, "Add button in select application");
	                    Thread.sleep(2000);
	                    
	                    click(Create_customer_Attachments_choosefile, "Attachments ChooseFile button");
	                    Thread.sleep(1000);
	                    Upload_File("upload1");
	                    Thread.sleep(2000);
	                    click(Create_customer_CompanyLogo_choosefile, "Company logo ChooseFile button");
	                    Thread.sleep(1000);
	                    Upload_File("upload");
	                    Thread.sleep(2000);
	                    click(Create_customer_Save_Button, "Create Customer Save button");
	                    Thread.sleep(20000);
	                    
	                    System.out.println("Successfully created the customer with all the details filled");
	                    Reports.log("PASS", "Successfully created the customer with all the details filled");
	                    
			            }else
			            {
			            	System.out.println("Failed to create the customer with all the details filled");
			            	Reports.log("FAIL", "Failed to create the customer will all the details filled");
			            	
			            }
			            
			            Highlighter(driver, Customer_Home_AllTab);
	                    Thread.sleep(2000);
	                    click(Customer_Home_AllTab, "All tab in customers page");
	                    Thread.sleep(5000);
	                    
	                   List<WebElement> AllCustomerscolumn = driver.findElements(By.xpath("//div[@id=\"customers\"]/table/thead/tr/th"));
						System.out.println("no.of column" + AllCustomerscolumn.size());
						List<WebElement> AllCustomersrows = driver.findElements(By.xpath("//div[@id=\"customers\"]/table/tbody/tr/td[2]"));
						System.out.println("no.of rows" + AllCustomersrows.size());

						boolean flag= false;
						for (int j = 1; j < AllCustomersrows.size(); j++) {
							WebElement AllCustomersList = driver.findElement(By.xpath("//*[@id=\"customers\"]/table/tbody/tr["+j+"]/td[2]//span[@class=\"CursorUnder\"]"));
							String Value = AllCustomersList.getText();
							System.out.println(Value);

							if (Value.contains(Randomname)) 
							{

								System.out.println("Value found  " + Value);
								Reports.log("PASS", "Successfully Verified the created Customer name");
								Highlighter(driver, AllCustomersList);
							WebElement edit_icon= driver.findElement(By.xpath("(//img[@id=\"adCustomerDatatable22\"])["+j+"]"));	
								click(edit_icon, "Edit icon of Newly created Customer");
								Thread.sleep(3000);
								flag=true;
								break;
	                       } 
						}
						if(flag==false)  
						{
							System.out.println("Not Found  " + Value);
							Reports.log("FAIL", "Failed to click on edit icon of New Customer");
						}

						Thread.sleep(3000);
						
						if(driver.findElement(By.xpath("//span[contains(text(),'"+Randomname+"')]")).isDisplayed())
						{
						UpdatedRandomname=GenerateRandomCustomerName(6);
			            EnterValue(Create_customer_name, UpdatedRandomname);
			            EnterValue(Create_customer_domain, "dontmind1221@gmail.com");
			            EnterValue(Create_customer_Agreemnt_Number, "78965432");
			            EnterValue(Create_customer_contacts_phonenumber, "9876543219");
			            EnterValue(Create_customer_contacts_email, "Customeruseredit@gmail.com");
			            EnterValue(Create_customer_Address_Street, "New Street1");
			            EnterValue(Create_customer_Address_City, "Florida");
			            EnterValue(Create_customer_Address_Zipcode, "56565");
								
						
						WebElement Remove_application = driver.findElement(By.xpath("(//div[@class=\"AD-Application-list-box\"]/img)[1]"));
						Highlighter(driver, Remove_application);
						click(Remove_application, "Remove application icon");
						
						click(Create_customer_AddApplications, "+Add Application");
			            click(Choose_an_application_dropdown, "choose an application search dropdown");
			            Thread.sleep(2000);
			            Highlighter(driver,driver.findElement(By.xpath("//span[contains(text(),'Device Management ')]")));
			            click(driver.findElement(By.xpath("//span[contains(text(),'Device Management ')]")), "value from the +Add Application dropdown");
			            Thread.sleep(3000);
			            
			            click(Create_customer_SelApplication_ADD_Button, "Add button in select application");
	                    Thread.sleep(2000);
	                    
	                    click(Create_customer_AddApplications, "+Add Application");
			            click(Choose_an_application_dropdown, "choose an application search dropdown");
			            Thread.sleep(2000);
			            Highlighter(driver,driver.findElement(By.xpath("//span[contains(text(),'Freshmarx Edit 2')]")));
			            click(driver.findElement(By.xpath("//span[contains(text(),'Freshmarx Edit 2')]")), "value from the +Add Application dropdown");
			            Thread.sleep(2000);
			            
	                    click(Create_customer_SelApplication_ADD_Button, "Add button in select application");
	                    Thread.sleep(2000);
			           
		               click(Create_customer_Save_Button, "Create Customer Save button");
		                Thread.sleep(3000); 
		                 
		                Highlighter(driver, Customer_Home_AllTab);
	                    Thread.sleep(2000);
	                    click(Customer_Home_AllTab, "All tab in customers page");
	                    Thread.sleep(3000);
						
						Search_bar.click();
						Thread.sleep(1000);
						EnterValue(Search_bar,UpdatedRandomname);
						Thread.sleep(3000);            
		                
		                
 if(driver.findElement(By.xpath("//*[@id=\"customers\"]/table/tbody/tr[1]/td[2]//span[@class=\"CursorUnder\"]")).getText().equalsIgnoreCase(UpdatedRandomname))
						{
							System.out.println("Successfully Updated the customer");
							Reports.log("PASS", "Successfully Updated the customer");
							Thread.sleep(5000);
						}else
						{
							System.out.println("Failed to update the customer");
							Reports.log("FAIL", "Failed to update the customer");
							Thread.sleep(5000);
						}
		                
		                
						}
						else
						{
							System.out.println("Failed to update the customer");
							Reports.log("FAIL", "Failed to update the customer");
							Thread.sleep(5000);
						}
			           
	// Change Customer User Admin		           
			           
		            
		            //Highlighter(driver, Customer_button);
			        //click(Customer_button, "+Customer button");
		            //Thread.sleep(3000);   
			           

						if(Search_bar.isDisplayed())
						{
							
						
						Search_bar.click();
						Thread.sleep(1000);
						EnterValue(Search_bar,UpdatedRandomname);
						Thread.sleep(3000);
						
	WebElement Customer_name= driver.findElement(By.xpath("//*[@id=\"customers\"]/table/tbody/tr[1]/td[2]//span[@class=\"CursorUnder\"]"));	
						click(Customer_name, "Newly created Customer");		
						System.out.println("Customer name found");
						Reports.log("PASS", "Successfully Verified and clicked on the created Customer name");
						
						}
						else
						{
							System.out.println("Customer not found");
							Reports.log("FAIL", "Failed to found the created New Customer");
						}
		           
			           
			           
   // Update/Modify Banner and Images			           
					
					Highlighter(driver, Customer_create_user);
 					click(Customer_create_user, "+user button");
 					Thread.sleep(2000);
 									
 					EnterValue(Create_user_username, "New User");
 					Highlighter(driver, Create_user_username);
 					Thread.sleep(2000);

 					RandomURL = GenerateRandomURL();
 					EnterValue(NewUser_Email, RandomURL);
 					Highlighter(driver, NewUser_Email);
 					DriverScript.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 					Highlighter(driver, NewUser_Create_button);
 					click(NewUser_Create_button, "Customer NewUser create button");
 					Thread.sleep(3000);
 					
 					List<WebElement> Usersrowsunder_selectedCustomer = driver.findElements(By.xpath("//div[@id=\"tenantUsers\"]/table/tbody/tr/td[3]"));
 		    		System.out.println("no.of rows" + Usersrowsunder_selectedCustomer.size());		
			           
 		    		boolean flag1= false;
 		    		for (int j = 1; j <= Usersrowsunder_selectedCustomer.size(); j++)
 		    		{
						WebElement AllUsersList= driver.findElement(By.xpath("//div[@id=\"tenantUsers\"]/table/tbody/tr["+j+"]/td[3]"));
						Value = AllUsersList.getText();
						System.out.println(Value);
				   String URl_tolower=RandomURL.toLowerCase();
                        System.out.println(URl_tolower);
                        
						if (Value.contains(URl_tolower)) 
						{

					System.out.println("Value found " +Value);
					Reports.log("PASS", "Successfully Verified the created user for Customer ");
					Highlighter(driver, AllUsersList);
	WebElement user_edit_icon= driver.findElement(By.xpath("//div[@id=\"tenantUsers\"]/table/tbody/tr["+j+"]/td[4]"));	
					click(user_edit_icon, "edit icon of newly created user for Customer");
					Thread.sleep(3000);
					flag1=true;
					break;
                      }
 		    		}	
 		    		if(flag1==false)
						  {
							System.out.println("Not Found  " + Value);
							Reports.log("FAIL", "Failed to verify the created user for  Customer");
						  }
      
 		    		    		
 		    		
 		    		click(Edit_user_chooseFile, "Choose file to upload user picture");
					Upload_File("upload");
					Thread.sleep(2000);
					click(Cust_EditUser_SaveButton, "Edit Customer user Save button");
	                Thread.sleep(5000);
	     			
					Search_bar.click();
					Thread.sleep(2000);
					Search_bar.sendKeys(RandomURL);
			    	Thread.sleep(3000);
					
			    
	                click(driver.findElement(By.xpath("//div[@id=\"tenantUsers\"]/table/tbody/tr[1]/td[4]")), "edit icon of the user to update the company logo");
					Thread.sleep(3000);
					
					
					click(Edit_user_chooseFile, "Choose file to update the previously  uploaded user picture");
					Upload_File("upload1");
					Thread.sleep(2000);
					click(Cust_EditUser_SaveButton, "Create Customer Save button");
	                Thread.sleep(5000);
	                
	            	WebElement Customers_breadcrumb = driver.findElement(By.xpath("//span[@class=\"AdminBreadcrumb-Nav ADcursor\"]"));
					
					Highlighter(driver, Customers_breadcrumb);
	                Thread.sleep(2000);
	                click(Customers_breadcrumb, "Customers breadcrumb to navigate back to homepage");
	                Thread.sleep(5000);
	                
	                Terminate_Customer(UpdatedRandomname);	
                    Thread.sleep(2000);

//Logout from ADPortal   		

            driver.findElement(By.xpath("//img[@id=\"AD-DropDown1\"]")).click();
            Highlighter(driver, driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")));
            driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
            Thread.sleep(5000);		       
	                
	                
		          
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

