package com.appinventive.qa.testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.appinventive.qa.pages.DriverScript;
import com.appinventive.qa.pages.Reports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Customer_Onboard_Flow extends DriverScript
{

		static DriverScript Ds = new DriverScript();

	         public static void CustomerOnboardFlow() 
			  { 
				  boolean Result; 
			      try {
				   
				      ReadProperties();
					  LaunchBrowser();
				//	  ReadTestsuite();
					
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
				
// Validate relevant notifications triggered (based on values) to Customer Admin/User  	           
// Create new customer with all relevant attributes     
// Ability to assign list of  SaaS Applications  
			           
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
			            
			            EnterValue(Create_customer_domain, "userdomain453@gmail.com");
			            Highlighter(driver, Create_customer_domain);
			            
			            EnterValue(Create_customer_Agreemnt_Number, "6782312312");
			            Highlighter(driver, Create_customer_Agreemnt_Number);
			            
			            EnterValue(Create_customer_contacts_phonenumber, "9876098760");
			            Highlighter(driver, Create_customer_contacts_phonenumber);
			            
			            EnterValue(Create_customer_contacts_email, "customeruser@gmail.com");
			            Highlighter(driver, Create_customer_contacts_email);
			            
			            EnterValue(Create_customer_Address_Street, "New streest");
			            Highlighter(driver, Create_customer_Address_Street);
			            
			            EnterValue(Create_customer_Address_City, "Boston");
			            Highlighter(driver, Create_customer_Address_City);
			            
			            EnterValue(Create_customer_Address_Zipcode, "54367");
			            Highlighter(driver, Create_customer_Address_Zipcode);
			            
			            
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
			            
		 WebElement Sel_Application =driver.findElement(By.xpath("((//div[@class=\"dropdown-menu applicationDB\"])[3]/div/a/div)[10]"));
			          
//			            Application_Selected = Sel_Application.getText();
//			            System.out.println(Application_Selected);
			            
			           Highlighter(driver,Sel_Application);				
			           click(Sel_Application, "value from the +Add Application dropdown");
			           Thread.sleep(1000);  
			           click(Create_customer_SelApplication_ADD_Button, "Add button in select application");
		               Thread.sleep(3000);
	                   
	                   
		                click(Create_customer_AddApplications, "+Add Application");
			            click(Choose_an_application_dropdown, "choose an application search dropdown");
			            Thread.sleep(2000);
			            
			            WebElement Sel_Application1 =driver.findElement(By.xpath("((//div[@class=\"dropdown-menu applicationDB\"])[3]/div/a/div)[3]"));
			            String Application_Selected1 = Sel_Application1.getText();
			            System.out.println(Application_Selected1);
			            
			            Highlighter(driver,driver.findElement(By.xpath("((//div[@class=\"dropdown-menu applicationDB\"])[3]/div/a/div)[3]")));
			            click(driver.findElement(By.xpath("((//div[@class=\"dropdown-menu applicationDB\"])[3]/div/a/div)[3]")), "value from the +Add Application dropdown");
			            Thread.sleep(1000);  
			            click(Create_customer_SelApplication_ADD_Button, "Add button in select application");
		                Thread.sleep(3000);
	                                     
		               click(Create_customer_Attachments_choosefile, "Attachments ChooseFile button");
		               Thread.sleep(1000);
		               Upload_File("upload1");
		               Thread.sleep(2000);
		               click(Create_customer_CompanyLogo_choosefile, "Company logo ChooseFile button");
		               Thread.sleep(1000);
		               Upload_File("upload");
		               Thread.sleep(2000);
		               click(Create_customer_Save_Button, "Create Customer Save button");
		               Thread.sleep(15000);
		               
		               System.out.println("Successfully created the customer "+  Randomname  +"with all the details filled");
		               Reports.log("PASS", "Successfully created the customer "+  Randomname  +"with all the details filled");
		               
			            }else
			            {
			            	System.out.println("Failed to create the customer with all the details filled");
			            	Reports.log("FAIL", "Failed to create the customer will all the details filled");
			            	
			            }
	                   
			            
			            Highlighter(driver, Customer_Home_AllTab);
		                Thread.sleep(2000);
		                click(Customer_Home_AllTab, "All tab in customers page");
		                Thread.sleep(5000);                  
	                  
						//DriverScript.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	

						List<WebElement> AllCustomerscolumn = driver.findElements(By.xpath("//div[@id=\"customers\"]/table/thead/tr/th"));
						System.out.println("no.of column" + AllCustomerscolumn.size());
						List<WebElement> AllCustomersrows = driver.findElements(By.xpath("//div[@id=\"customers\"]/table/tbody/tr/td[2]"));
						System.out.println("no.of rows" + AllCustomersrows.size());

						boolean flag=false;
						for (int j = 1; j <= AllCustomersrows.size(); j++) 
						{
							WebElement AllCustomersList = driver.findElement(By.xpath("//*[@id=\"customers\"]/table/tbody/tr["+ j + "]/td[2]//span[@class=\"CursorUnder\"]"));
							String value = AllCustomersList.getText();
							System.out.println(value);

							if (value.contains(Randomname)) 
							{

								System.out.println("Value found  " + value);
								Reports.log("PASS", "Successfully Verified the created Customer name");
								Highlighter(driver, AllCustomersList);
								click(AllCustomersList, "Newly created Customer");
								Thread.sleep(3000);
								flag=true;
								break;
	                        } 
						}
						if(flag==false) 
						{
							System.out.println("Not Found  " + Randomname);
							Reports.log("FAIL", "Failed to Verify the Customer name");
						}
						
						 Highlighter(driver, Customer_create_user);
							click(Customer_create_user, "+user button");
							Thread.sleep(2000);
											
							Highlighter(driver, Create_user_Slider);
							click(Create_user_Slider, "Create Admin/User slider ");
							
							EnterValue(Create_user_username,"New Adminuser");
							Highlighter(driver, Create_user_username);
							Thread.sleep(2000);

							
							Highlighter(driver, NewUser_Email);
							//RandomURL = GenerateRandomURL();
							EnterValue(NewUser_Email, UserOnboardFlow );
							Thread.sleep(1000);
							Highlighter(driver, NewUser_Create_button);
							click(NewUser_Create_button, "Customer NewUser create button");
							Thread.sleep(5000);
							
							WebElement Customers_breadcrumb = driver.findElement(By.xpath("//span[@class=\"AdminBreadcrumb-Nav ADcursor\"]"));
							
							Highlighter(driver, Customers_breadcrumb);
			                Thread.sleep(2000);
			                click(Customers_breadcrumb, "Customers breadcrumb to navigate back to homepage");
			                Thread.sleep(5000);

	//  Login to the Admin user email and password setting flow			
			                
	driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?redirect_uri=https%3A%2F%2Fdevelopers.google.com%2Foauthplayground&prompt=consent&response_type=code&client_id=407408718192.apps.googleusercontent.com&scope=email&access_type=offline&flowName=GeneralOAuthFlow");
			                   
		                    Thread.sleep(3000);
						    WebElement GmailEmailTxtbox1=driver.findElement(By.xpath("//input[@type=\"email\"]"));
						    Highlighter(driver, GmailEmailTxtbox1);
						    EnterValue(GmailEmailTxtbox1,UserOnboardFlow);
						    driver.findElement(By.xpath("//div[@class=\"VfPpkd-RLmnJb\"]")).click();
						    Thread.sleep(5000);
						   			    
						    
						    WebElement Gmailpasword1=driver.findElement(By.xpath("//input[@type=\"password\"]"));
						    Highlighter(driver, Gmailpasword1);
						    EnterValue(Gmailpasword1,"Sorrybunny6@");
					    
						    driver.findElement(By.xpath("(//div[@class=\"VfPpkd-RLmnJb\"])[1]")).click();
						    Thread.sleep(5000);
						  		   
						    driver.navigate().to("https://mail.google.com/mail/u/0/#inbox");
						    Thread.sleep(5000);
						
						    WebElement Inbox1=driver.findElement(By.xpath("(//div[@class=\"aio UKr6le\"])[1]"));
						    Highlighter(driver, Inbox1); 
						    Thread.sleep(3000);
		       			    Inbox1.click();
						    Thread.sleep(3000);
						    
						    List<WebElement> InboxMessagescolumn1 = driver.findElements(By.xpath("(//div[@class=\"Cp\"]/div/table)[2]/tbody/tr/td"));
						    System.out.println("no.of column" + InboxMessagescolumn1.size());

						    List<WebElement> INboxMessagesrows1 = driver.findElements(By.xpath("(//div[@class=\"Cp\"]/div/table)[2]/tbody/tr"));
						    System.out.println("no.of rows" + INboxMessagesrows1.size());
			              
						boolean flagg=false;    
						for (int j = 1; j <=INboxMessagesrows1.size(); j++) 
						{
							WebElement MessagesList1 = driver.findElement(By.xpath("(//div[@class=\"Cp\"]/div/table)[2]/tbody/tr[" + j + "]/td[4]"));
							String Text = MessagesList1.getText();
							System.out.println(Text);
							if (Text.contains("No-reply"))
							{
			                     try {
								System.out.println("Value found" + Text);
								Reports.log("PASS", "Successfully Verified the inbox message");
								Thread.sleep(3000);
								Highlighter(driver, MessagesList1);
								Thread.sleep(2000);
								clickElementUsingJavascriptExecutor("(//div[@class=\"Cp\"]/div/table)[2]/tbody/tr[" + j + "]/td[4]");
								//click(MessagesList, "User account created Email");
			                     }catch(Exception e) 
			                     {
			                                System.out.println(e.getMessage());
			                     }
								Thread.sleep(5000);
								flagg=true;
								break;
							 } 
						  }	
						if(flagg==false) 
						 {
								System.out.println("Not Found  " + Text);
								Reports.log("FAIL", "Failed to Verify the Sub Group name");
							}
						
						   
					       Thread.sleep(5000);
					       
					       WebElement TempPassword1=driver.findElement(By.xpath("//b[contains(text(),'Temporary password:')]"));
					       Highlighter(driver, TempPassword1);
					       String TemporaryPWDs=TempPassword1.getText();
					       System.out.println("Temporary password from email is " +TemporaryPWDs);
					       String[] splitTempPWDs=TemporaryPWDs.split(":");
					       String TEMPPasswords = splitTempPWDs[1].trim();
				           System.out.println(splitTempPWDs[0]);
					       System.out.println(splitTempPWDs[1]);
					       System.out.println(TEMPPasswords);
				       
					       
						   WebElement LinkInEmail1 =driver.findElement(By.xpath("//a[contains(text(),'here')]"));
						   Highlighter(driver, LinkInEmail1);
						   Thread.sleep(5000);
						   click(LinkInEmail1, " 'Click here' link in the email");
						 
						   Thread.sleep(5000);
						   String parentWindow1= driver.getWindowHandle();
						   for(String winHandle1 : driver.getWindowHandles())
						    {
							    driver.switchTo().window(winHandle1);
						    }				   
						   System.out.println(driver.getCurrentUrl());
						   
						   DriverScript.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						   WebElement EnterOldPassword1 =driver.findElement(By.xpath("//input[@id=\"oldPasswordEmail\"]"));
					       Highlighter(driver, EnterOldPassword1);
						   driver.findElement(By.xpath("//*[@id=\"oldPasswordEmail\"]")).click();
						   EnterValue(EnterOldPassword1,TEMPPasswords);
						   Thread.sleep(3000);
						   
						   WebElement EnterNewPassword1 =driver.findElement(By.xpath("//*[@id=\"inputResetPasswordEmail\"]"));
						   Highlighter(driver, EnterNewPassword1);
						   driver.findElement(By.xpath("//*[@id=\"inputResetPasswordEmail\"]")).click();
						   EnterValue(EnterNewPassword1,ResetPassword);
						   Thread.sleep(3000);
						   
						   WebElement EnterConfirmPassword1 =driver.findElement(By.xpath("//input[@id=\"inputConfirmPasswordEmail\"]"));
						   Highlighter(driver, EnterConfirmPassword1);
						   driver.findElement(By.xpath("//input[@id=\"inputConfirmPasswordEmail\"]")).click();
						   EnterValue(EnterConfirmPassword1,ResetPassword);
						   Thread.sleep(3000);
						   
						  	   
						   WebElement SecurityQuestion1 =driver.findElement(By.xpath("//select[@id=\"inputSecurityQuestion\"]"));
						   Highlighter(driver, SecurityQuestion1);
						   SeldropDownbyValue(SecurityQuestion1, "What is the food you least liked as a child?", "SecurityQuestion dropdown");
						   Thread.sleep(3000);
						   
						   WebElement SecurityAnswer2 =driver.findElement(By.xpath("//input[@id=\"securityAnswer\"]"));
						   Highlighter(driver, SecurityAnswer2);
						   driver.findElement(By.xpath("//input[@id=\"securityAnswer\"]")).click();
						   EnterValue(SecurityAnswer2,"AveryDennison");
						   Thread.sleep(3000);
						   
						   
						   WebElement SaveButton1 =driver.findElement(By.xpath("//div[@class=\"form-group\"]/button"));
						   click(SaveButton1, "Save Button");
						   Thread.sleep(8000);
						   
						   System.out.println(driver.getCurrentUrl());
						   
		         		   WebElement SuccessMesage1 =driver.findElement(By.xpath("//h3[@id=\"success_title\"]"));
						   Highlighter(driver, SuccessMesage1);
		      			   VerifyText(SuccessMesage1, "User update successful" ,"User Account creation");
						   Thread.sleep(3000);
						   WebElement LoginInsuccessPage1 =driver.findElement(By.xpath("//a[contains(text(),'Login')]"));
						   Highlighter(driver, LoginInsuccessPage1);
						   click(LoginInsuccessPage1, "Login link in success message pop up ");
						   DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							VerifyText(verifylogintext, "Please Log in" ,"login page");
							Thread.sleep(3000);            
			              
			                
	// login to AD Portal with created Customer Admin user and new password		
							
							Clear_Cookies();
							Thread.sleep(2000);		 		
							 				
				            Ds.driver.get(URL);
					        Thread.sleep(5000);
						    click(LoginBtn ,"Login Button");
						 	Thread.sleep(3000);
						 	
							if(username!=null) 
						 	{
						 	EnterValue(username,UserOnboardFlow);
							Thread.sleep(2000);
							
							DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							click(UsernamePageNextBtn ,"Next Button");
							
							DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							EnterValue(password, ResetPassword);
							Thread.sleep(2000);
							
							click(PasswordPageVerifyBtn ,"Verify Button");
							Thread.sleep(8000);
							
										
							click(driver.findElement(By.xpath("(//div[@class=\"enroll-factor-button\"]/a)[6]")), "Security question setup button");
							Thread.sleep(2000);
							EnterValue(driver.findElement(By.xpath("//input[@name=\"answer\"]")), "Avery");
							Thread.sleep(2000);
							click(driver.findElement(By.xpath("//input[@value=\"Save\"]")), "Save button in security question authentication page");
							Thread.sleep(2000);
							click(driver.findElement(By.xpath("//input[@value=\"Finish\"]")), "Finish button in Set up multifactor authentication page");	
							Thread.sleep(15000);
						 	}
							else
							{
								Clear_Cookies();
							}
							click(driver.findElement(By.xpath("//input[@name=\"ADCookie\"]")), "privacy check boxes");
							click(driver.findElement(By.xpath("//input[@name=\"ADCookieEmail\"]")), "privacy check boxes");
							click(driver.findElement(By.xpath("//input[@name=\"ADCookieSMS\"]")), "privacy check boxes");
							Thread.sleep(3000);
							Highlighter(driver, driver.findElement(By.xpath("//button[@id=\"AdOkButton\"]")));
							click(driver.findElement(By.xpath("//button[@id=\"AdOkButton\"]")), " OK Button to save the selected privacy options");
							Thread.sleep(2000);        
						 	

	// logout
							
							click(ADProfileUser_Icon, "User account icon in homepage");
							Thread.sleep(2000);	
					 	    Highlighter(driver, logout);
					 	    click(logout, "logout option in User account icon dropdown in homepage");
					 		Thread.sleep(5000);	
					 		Clear_Cookies();
					 		Thread.sleep(2000);					
							
							
							
	//Login to AD portal with AD Admin credentials						
			                               
							            driver.get(URL);	
						                Thread.sleep(5000);
									    click(LoginBtn ,"Login Button");
									 	Thread.sleep(3000);
									 	EnterValue(username,AdminUser);
										Thread.sleep(2000);
										
										DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
										click(UsernamePageNextBtn ,"Next Button");
										
										DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
										EnterValue(password, AdminPwd);
						        		Thread.sleep(2000);
										
										click(PasswordPageVerifyBtn ,"Verify Button");
										DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
										
										driver.findElement(By.xpath("//input[@name=\"answer\"]")).sendKeys(SecurityAnswer);
										Thread.sleep(2000);
										VerifyText(verifySecQuestiontext, "Security Question" ,"Security Question Page");
										click(SecurityAnsPageVerifyBtn ,"Verify Button");
										Thread.sleep(10000);	
							
										 Highlighter(driver, Customers_AppIcon);
								         click(Customers_AppIcon, "Customers link in side panel");
								         Thread.sleep(2000);               
			           
	//  Send notifications to Customer Admin User					
						
			                if(Customer_Emergency_Notification.isDisplayed())
							{
								
							Highlighter(driver, Customer_Emergency_Notification);
							click(Customer_Emergency_Notification, "Emergency notification icon in customer page");
							Thread.sleep(2000);
							
							Highlighter(driver, Emergency_Notification_SearchTenantName);
							EnterValue(Emergency_Notification_SearchTenantName, Randomname);
							Thread.sleep(1000);
							System.out.println(driver.findElement(By.xpath("//div[@id=\"myDropdown\"]/a[contains(text(),'"+Randomname+"')]")));
							click(driver.findElement(By.xpath("//div[@id=\"myDropdown\"]/a[contains(text(),'"+Randomname+"')]")), "Customername in Search Tenant Name dropdown");
							Thread.sleep(2000);
							
							Highlighter(driver, Emergency_Notification_Subject);
							EnterValue(Emergency_Notification_Subject, "Test");
							Thread.sleep(2000);
							
							Highlighter(driver, Emergency_Notification_EmailBody);
							EnterValue(Emergency_Notification_EmailBody, "Please verify the email");
							Thread.sleep(2000);
							
							Highlighter(driver, Emergency_Notification_SEND_Button);
							click(Emergency_Notification_SEND_Button, "SEND button in emergency notification panel");
							Thread.sleep(2000);
							
							System.out.println("Successfully sent the email notification");
							Reports.log("PASS", "Successfully sent the email notification");
								Thread.sleep(5000);
							
							}
							else
							{
								System.out.println("Failed to sent the email notification ");
								Reports.log("FAIL","Failed to sent the email notification ");
								
							}
					    
			                Clear_Cookies();
	// Verify the Email sent from 	Emergency notification			    
				              
					    
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?redirect_uri=https%3A%2F%2Fdevelopers.google.com%2Foauthplayground&prompt=consent&response_type=code&client_id=407408718192.apps.googleusercontent.com&scope=email&access_type=offline&flowName=GeneralOAuthFlow");
		Thread.sleep(5000);
		WebElement GmailEmailTxtbox=driver.findElement(By.xpath("//input[@type=\"email\"]"));
		Highlighter(driver, GmailEmailTxtbox);
		EnterValue(GmailEmailTxtbox,UserOnboardFlow);
	   driver.findElement(By.xpath("//div[@class=\"VfPpkd-RLmnJb\"]")).click();
		Thread.sleep(5000);
					   
		WebElement Gmailpasword=driver.findElement(By.xpath("//input[@type=\"password\"]"));
		Highlighter(driver, Gmailpasword);
	   EnterValue(Gmailpasword,"Sorrybunny6@");
					    
	   driver.findElement(By.xpath("(//div[@class=\"VfPpkd-RLmnJb\"])[1]")).click();
		Thread.sleep(5000);
					  		   
		 driver.navigate().to("https://mail.google.com/mail/u/0/#inbox");
	    Thread.sleep(5000);
					
					    WebElement Inbox=driver.findElement(By.xpath("(//div[@class=\"aio UKr6le\"])[1]"));
					    Highlighter(driver, Inbox); 
					    Thread.sleep(3000);
					    Inbox.click();
					    Thread.sleep(5000);
					    
					    List<WebElement> InboxMessagescolumn = driver.findElements(By.xpath("(//div[@class=\"Cp\"]/div/table)[2]/tbody/tr/td"));
					    System.out.println("no.of column" + InboxMessagescolumn.size());

					    List<WebElement> INboxMessagesrows = driver.findElements(By.xpath("(//div[@class=\"Cp\"]/div/table)[2]/tbody/tr"));
					    System.out.println("no.of rows" + INboxMessagesrows.size());
	                boolean flaa=false;
					for (int j = 1; j <=INboxMessagesrows.size(); j++) 
					{
						WebElement MessagesList = driver.findElement(By.xpath("(//div[@class=\"Cp\"]/div/table)[2]/tbody/tr[" + j + "]/td[5]"));
						String Text = MessagesList.getText();
						System.out.println(Text);
						if (Text.contains("Test"))
						{
							System.out.println("Notification Email found  " + Text);
							Reports.log("PASS", "Successfully Verified the email received or not");
							Thread.sleep(3000);
							Highlighter(driver, driver.findElement(By.xpath("(//div[@class=\"Cp\"]/div/table)[2]/tbody/tr[" + j + "]/td[5]")));
							click(driver.findElement(By.xpath("(//div[@class=\"Cp\"]/div/table)[2]/tbody/tr[" + j + "]/td[5]")), "User account created Email");
							Thread.sleep(10000);
							flaa=true;
							break;
						} 
					}
					if(flaa==false )
					{
						System.out.println("Notification email Not Found  " + Text);
						Reports.log("FAIL", "Failed to Verify the email received or not");
					}
//					   
//				       Thread.sleep(5000);
//				       
//	String Email_Notification_verification_bodyText= driver.findElement(By.xpath("//*[@id=\":6h\"]/div[1]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td/div/table/tbody/tr/td/p/text()[2]")).getText();			       
//				  System.out.println(Email_Notification_verification_bodyText);     
//	              Thread.sleep(3000);
//				  
//				  if(Email_Notification_verification_bodyText.contains("Please verify the email"))
//				  {
//					  Highlighter(driver, driver.findElement(By.xpath("//*[@id=\":6h\"]/div[1]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td/div/table/tbody/tr/td/p/text()[2]")));
//					 System.out.println("Successfully verified the 'Please Verify the email' text in the body of the email which was given in the Email notification Body");
//					 
//						Reports.log("PASS", "Successfully verified the 'Please Verify the email' text in the body of the email which was given in the Email notification Body");
//				  }else
//				  {
//					  System.out.print("Failed to verify the 'Please Verify the email' text in the body of the email which was given in the Email notification Body");
//					  Reports.log("FAIL","Failed to verify the 'Please Verify the email' text in the body of the email which was given in the Email notification Body"); 
//					  
//				  }
				      
						driver.findElement(By.xpath("//img[@class=\"gb_Da gbii\"]")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//a[contains(text(),'Sign out')]")).click();
						Thread.sleep(5000);
					   
 
// Login to AD portal to delete the Customer to use this same script for regression  

	                     Ds.driver.get(URL);
	                     Thread.sleep(5000);
	                     Ds.Login(AdminUser,AdminPwd,SecurityAnswer);
	                     Thread.sleep(5000);
	  
	                     if(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")).isDisplayed())
	                            {
		                              click(driver.findElement(By.xpath("//img[@data-bind=\"click: $data.cookieClose\"]")), "close cookies footer in homepage");
	                                  }
	                              Thread.sleep(2000);  	
	                                Terminate_Customer(Randomname);	
	                               Thread.sleep(2000);
		
// Logout from ADPortal   		

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
