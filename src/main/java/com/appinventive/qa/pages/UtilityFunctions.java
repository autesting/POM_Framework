package com.appinventive.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.glass.events.KeyEvent;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class UtilityFunctions extends ObjectRepository
{
	 // public static WebDriver driver;
	 public static HashMap<String,String> storedDetails =  new HashMap<String,String>();
	 // Function to launch browser

	public static WebDriver LaunchBrowser()
	{
		try
		{
			if(DriverScript.URL!=null) 
			{
		if (DriverScript.browsername.equalsIgnoreCase("Chrome")) 
		       {
				   WebDriverManager.chromedriver().setup();


			  DriverScript.driver = new ChromeDriver();
			  
//		        System.setProperty("Webdriver.chrome.driver", ("user.dir") + "chromedriver.exe");    
//		        
//		        ChromeOptions options = new ChromeOptions();
//		        options.addArguments("incognito");
//		        DesiredCapabilities cap = DesiredCapabilities.chrome();
//		        cap.setCapability(ChromeOptions.CAPABILITY, options);
//		        driver = new ChromeDriver(cap);
			
		      } else if (DriverScript.browsername.equalsIgnoreCase("Firefox")) 
		{
		   System.setProperty("webdriver.gecko.driver","D:\\geckodriver.exe");
		   DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	        capabilities.setCapability("marionette", true);
     		DriverScript.driver = new FirefoxDriver(capabilities);
			
          } else if (DriverScript.browsername.equalsIgnoreCase("IE")) 
          {
        	  
			System.setProperty("webdriver.ie.driver", "D:\\IEDriverServer.exe");
			DriverScript.driver = new InternetExplorerDriver();

		}

		DriverScript.driver.manage().window().maximize();
		//DriverScript.driver.manage().deleteAllCookies();
		DriverScript.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverScript.driver.get(DriverScript.URL);
		Reports.log("PASS","Sucessfully Launched Browser");
			
			}else 
			{
				Reports.log("FAIL","Failed to Launch Browser");
			}
	
			
} catch(Exception e) 
		{
	System.out.println(e.getMessage());
}
		return DriverScript.driver;
	}
	// Login to AveryDennison portal
	
	  public   Boolean Login(String un,String pwd, String securityAnswer) throws Exception
		{
		  Boolean Status = true;
		    try
		    {
		    	
		    //This initElements method will create all WebElements
		    	
            PageFactory.initElements(DriverScript.driver, this);
            
			DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			VerifyText(verifylogintext, "Please Log in" ,"login page");
			Thread.sleep(2000);
			click(LoginBtn ,"Login Button");
			System.out.println("Username from config is "+username);
			if(username!=null) 
			{  
			EnterValue(username, un);
			Thread.sleep(2000);
			VerifyText(verifyEmailtext, "Sign In" ,"Email page");
			DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			click(UsernamePageNextBtn ,"Next Button");
			
			DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			EnterValue(password, pwd);
			Thread.sleep(2000);
			VerifyText(verifyPasswordtext, "Password" ,"Password Page");
			click(PasswordPageVerifyBtn ,"Verify Button");
			
			DriverScript.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			EnterValue(SecurityAnswer, securityAnswer);
			Thread.sleep(2000);
			VerifyText(verifySecQuestiontext, "Security Question" ,"Security Question Page");
			click(SecurityAnsPageVerifyBtn ,"Verify Button");
			Thread.sleep(10000);
			Reports.log("PASS","Successfully LoggedIn as '"+DriverScript.AdminUser+"'");
			
			}else 
			{
				
				 System.out.println("Failed to login");
				 Reports.log("FAIL","Failed  to login") ;
			}
			
		    
		    }
	  catch(Exception e) 
		{
	System.out.println(e.getMessage());
         }
			return Status;
		}
	 
	 public static  boolean EnterValue(WebElement locator, String value) throws Exception
		  { 
			  try
			  {
			  
	  // The value we want to set to input
			  
			  if (locator.isEnabled()) 
			  {
				
				  locator.clear();
				  if(locator!=null) 
				  locator.sendKeys(value);
	                System.out.println("Succesfully Entered the value in the text box");
	                Reports.log("PASS","Succesfully Entered the value ' "+value +"'  in the text box");
	                return true;
	            } else 
	            {
	                System.out.println("Unable to enter the text in the text box");
	                Reports.log("FAIL","Failed to Enter the value ' "+value +" ' in the text box");
	              
	                return false;
	            }
			  
		  
	      }catch(Exception e) 
				{
	    		System.out.println(e.getMessage());
	    		Reports.log("FAIL","Failed to Enter the value ' "+value +" ' in the text box");
	    	}
			return false;
		  }
		  
		  
	 public static Boolean click(WebElement locator , String label)
		  {
		 
		 Boolean Status = true;
		      try
		      {
		 		//locator.click();   
		    	  
		    	       if (locator.isEnabled())
		    	       {
		                locator.click();
		                System.out.println("Succesfully clicked on the element '"+ label+"'");
		                Reports.log("PASS","Succesfully clicked on the element '"+ label+"'" );
		           
		    	       }else 
		                {
		                System.out.println("Failed to click on  the element '"+ label +"'");
		                Reports.log("FAIL","Failed to click on  the element '"+ label +"'");
		             
		                Status = false;
		                }        
		    	  
		    }catch(Exception e) 
				{
		    		 System.out.println(e.getMessage());
		    		 Reports.log("FAIL","Failed to click on  the element '"+ label +"'");
		    		
		    	}	
		      return Status;
		  }
		  
    public static void WaitForElement(WebElement locator)
		  {
			  try {
				  if (locator.isEnabled())
	    	       {
					Wait<WebDriver> wait = new WebDriverWait(DriverScript.driver, 10);
					wait.until(ExpectedConditions.visibilityOf(locator));
					System.out.println("Element "+locator+" is found");
					Reports.log("PASS","Element "+locator+" is found");
					
	    	       }else
	    	       {
	    	    	   System.out.println("Element not found");
	    	    	   Reports.log("FAIL","Element "+locator+" not found");
	    	       }

				  } catch (Exception e) 
			           {
					
					System.out.println(e.getMessage());
					
				       }
			  
		  }                        
		  
   public static String getText(WebElement locator)
		  {
	         try
	         {
	        	  
			  String text = null;
				if(locator.isDisplayed())
				{
				text = locator.getText();	
				System.out.println("Successfully obtained the text '"+text+"'");
				Reports.log("PASS","Successfully obtained the text '"+text+"'");
				return text;
				
				}else
				{
					System.out.println("Failed to get the text '"+text+"'");
					Reports.log("FAIL","Failed to get the text '"+text+"'");
					return text;
				}
		    }catch(Exception e) 
	 		{
		    	System.out.println(e.getMessage());
		    }
			return null;
		  }    
		  
   public static String VerifyText(WebElement locator ,String ExpectedText ,String label)
   
	  {
      try
      {
		  String text = null;
			if(locator.isDisplayed())
			{
			text = locator.getText();	
			Assert.assertEquals(text, ExpectedText);
			System.out.println("Successfully verified the '"+ExpectedText+"' text in "+label);
			Reports.log("PASS","Successfully verified the '"+ExpectedText+"' text in "+label);
			return text;
			}else
			{
				System.out.println("Failed to verify the '"+ExpectedText+"'  text in "+label);
				Reports.log("FAIL","Failed to verify the '"+ExpectedText+"'  text in "+label);
				return text;
			}
	    }catch(Exception e) 
		{
	    	System.out.println(e.getMessage());
	    	Reports.log("FAIL","Failed to verify the '"+ExpectedText+"'  text in "+label);
	    }
		return null;
	  }
   
   
   
   public static boolean verifytitle(String title , String label) throws Exception
   {
	   
	  String Actual = DriverScript.driver.getTitle();
	  System.out.println("Page title is "+Actual );
      String Expected = title;
      Boolean status = false;

     if (Actual.equalsIgnoreCase(Expected)) 
	   
	   {
    	 System.out.println("Successfully verified the expected title " + title + " with actual tile " +Actual+"of"+label);
    	 Reports.log("PASS","Successfully verified the expected title " + title +" with actual tile " + Actual + " of  " + label);
			status=true;
		}else
		{
			System.out.println("Failed to verify the expected title "+title+"with actual tile" +Actual+"of"+label);
			Reports.log("FAIL","Failed to verify the expected title " + title + " with actual tile " + Actual + " of " + label);
		}
	return status;
	}
   
    public static void Highlighter(WebDriver driver, WebElement locator) 
		  {
			  try
			  {
			  
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].setAttribute('style', 'background : yellow;');", locator);
				try {
					Thread.sleep(2000);
				}
				catch(InterruptedException e) {
					System.out.println(e.getMessage());
				}
//				js.executeScript("arguments[0].setAttribute('style', 'background : yellow;');", driver.findElement(by1(element)));

			}catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		  }		
    
    public static void Highlighter(WebDriver driver, boolean locator) 
	  {
		  try
		  {
		  
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].setAttribute('style', 'background : yellow;');", locator);
			try {
				Thread.sleep(2000);
			}
			catch(InterruptedException e) {
				System.out.println(e.getMessage());
			}
//			js.executeScript("arguments[0].setAttribute('style', 'background : yellow;');", driver.findElement(by1(element)));

		}catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
	  }	
		  
	public static  void SelectcheckBox(WebElement locator) throws InterruptedException
		  {
		     try
		     {
			  boolean	check = locator.isSelected();
				if(check==false){
				  locator.click();
				  System.out.println("Successfully checked the checkbox");
				  Reports.log("PASS","Successfully checked the checkbox");
				  
				}else
				{
					System.out.println("Failed to check the checkbox");
					Reports.log("FAIL","Failed to check the checkbox");
				}
			}catch(Exception e) 
				{
				System.out.println(e.getMessage());
				Reports.log("FAIL","Failed to check the checkbox");
			}
		  }  
		  
	 public void pageScroll()
		   {
			  try
			  {
				JavascriptExecutor jse = (JavascriptExecutor)DriverScript.driver;
				jse.executeScript("window.scrollBy(0,550)", "");
			}
			  catch(Exception e) 
				{
			System.out.println(e.getMessage());
		         }
		   }		  

	public static void SeldropDownbyVisibleText(WebElement locator, String visibleText) 
	        {
		try
		{
			if(locator.isDisplayed())
			{
			//WebElement Element = driver.findElement(locator);
			Select sel = new Select(locator);
			sel.selectByVisibleText(visibleText);
			System.out.println("Successfully selected the value " +visibleText+ " in dropdown");
			Reports.log("PASS","Successfully selected the value " +visibleText+ " in dropdown");
			}else
			{
				System.out.println("Failed to  select the value " +visibleText+ " in dropdown");
				Reports.log("FAIL","Failed to  select the value " +visibleText+ " in dropdown");
			}
			
		}
		catch(Exception e) 
		{
	System.out.println(e.getMessage());
	Reports.log("FAIL","Failed to  select the value " +visibleText+ " in dropdown");
        }
	  }		
   public static void SeldropDownbyValue(WebElement locator, String value ,String label) throws InterruptedException 
	        {
	   Boolean flag = false;
	           try
	           {
	        	   if(locator.isDisplayed())
	   			    {
			//WebElement Element = driver.findElement(By.id(dropDown));
			Select sel = new Select(locator);
			Thread.sleep(2000);
			List<WebElement> objsel = sel.getOptions();
			int len = objsel.size();
			for(int i =0; i<=len-1;i++) {
				if(objsel.get(i).getText().trim().equalsIgnoreCase(value.trim())) {
					System.out.println("Found value entered into  if condition");
					sel.selectByValue(value);
					System.out.println("found value in drop down");
					Thread.sleep(2000);
					flag = true;
					break;
				}
			}
			if(flag==true) {
			System.out.println("Successfully selected the value " +value+ " in "+label+ "dropdown");
			Reports.log("PASS","Successfully selected the value " +value+ "in "+label+" dropdown");
	   			   
			} else {		    	
	   	   System.out.println("Failed to  select the value " +value+ " in "+label+" dropdown");
	   	   	Reports.log("FAIL","Failed to  select the value " +value+ " in "+label+" dropdown");
			}		    
	   			    }
		     }catch(Exception e) 
	   		{
		    		System.out.println(e.getMessage());
		    		Reports.log("FAIL","Failed to  select the value " +value+ " in "+label+" dropdown");
		    	}
	        }    

     public static void SeldropDownbyIndex(WebElement locator, Integer index ,String label) throws InterruptedException 
	        {
    	 try
    	 {
    		 if(locator.isDisplayed())
			    {
			//WebElement Element = driver.findElement(by1(dropDown));
			Select sel = new Select(locator);
			Thread.sleep(4000);
			sel.selectByIndex(index);
			System.out.println("Successfully selected the value " +index+ " in "+label+" dropdown");
			Reports.log("PASS","Successfully selected the value " +index+ "in "+label+" dropdown");
   			    }else
   			    {
   			    	
   	        System.out.println("Failed to  select the value " +index+ " in "+label+" dropdown");
   	        Reports.log("FAIL","Failed to  select the value " +index+ " in "+label+" dropdown");
   			    }
		
		}catch(Exception e) 
 		{
			System.out.println(e.getMessage());
			 Reports.log("FAIL","Failed to  select the value " +index+ " in "+label+" dropdown");
		}
	 }	 
		
     public static void switchToFrame(WebElement locator)
		{
			try
			{
			DriverScript.driver.switchTo().frame(locator);
			
		}catch(Exception e) 
			{
			System.out.println(e.getMessage());
		}
		}		
	public void storeValueInHashMap(String key,String value)
		{
			try
			{
			System.out.println("Value to be stored in map is "+value);
			Reports.log("PASS","Value to be stored in map is "+value);
			storedDetails.put(key,value);

		}catch(Exception e) 
			{
			System.out.println(e.getMessage());
		}
		}
	public static void Closepopup()
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(DriverScript.driver, 20);
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("close"))).click();
            System.out.println("Successfully closed the  Popup");
            Reports.log("PASS","Successfully closed the  Popup");
           
			
		}catch(Exception e) 
		{
		System.out.println(e.getMessage());
		Reports.log("FAIL","Failed to close the  Popup");
	     }
	}

	public static boolean CheckRADIOBTN(WebElement locator)
	{
		boolean checkstatus;
		try
		{
			 checkstatus = locator.isSelected();
	            if (checkstatus == true) {
	                System.out.println("Radio button is already checked");
	                Reports.log("PASS","Successfully Checked the  Radio button");
	                return false;
	            } else {
	                locator.click();
	                System.out.println("Checked the Radio button");
	                Reports.log("FAIL","Failed to Check the Radio button");
	                return true;
	            }
			
			
		}catch(Exception e) 
		{
		System.out.println(e.getMessage());
		Reports.log("FAIL","Failed to Check the Radio button");
	     }
		return false;
	}
	
	public static boolean UnCheckRADIOBTN(WebElement locator)
	{
		boolean checkstatus;
		try
		{
			 checkstatus = locator.isSelected();
	            if (checkstatus == true) {
	            	locator.click();
	                System.out.println("Radio button is already checked");
	                Reports.log("PASS","Succesfully Unchecked the Radio button");
	                return true;
	            } else {
	            	Reports.log("FAIL","Failed to Check the Radio button");
	                System.out.println("Checked the Radio button");
	                return false;
	            }
			
			
		}catch(Exception e) 
		{
		System.out.println(e.getMessage());
		Reports.log("FAIL","Failed to Check the Radio button");
	     }
		return false;
	}
	
  public static boolean IsDisplayed(WebElement locator)
	{
		try {
			
            boolean status = locator.isDisplayed();
            if (status == true) 
            {
            	Reports.log("PASS","Successfully  displayed the Object on web page");
                System.out.println("Object displayed on web page" );
                return true;
            } else 
            {
                System.out.println("Object was not displayed on web page");
                Reports.log("FAIL","Failed to  display the Object on web page");
                return false;
            }
			
		}catch(Exception e) 
		{
		System.out.println(e.getMessage());
		Reports.log("FAIL","Failed to  display the Object on web page");
		
	     }
		return false;
		
	}
  public static boolean IsEnabled(WebElement locator)
  {
	  try {
		  boolean status = locator.isEnabled();
          if (status == true) {
              System.out.println("Object is enabled" );
              Reports.log("PASS","Object is enabled");
              return true;
          } else {
              System.out.println("Object was not enabled" );
              Reports.log("FAIL","Object was not enabled");
              return false;
          }
		  
	  }catch(Exception e) 
		{
		System.out.println(e.getMessage());
		  Reports.log("FAIL","Object was not enabled");
	     }
		return false;
  }
  
  public static boolean IsDisabled(WebElement locator)
  {
	  try {
		  
          boolean status = locator.isEnabled();
          if (status == false) {
              System.out.println("Element is disabled");
              Reports.log("PASS","Element is disabled");
              return true;
          } else {
              System.out.println("Element was not disabled");
              Reports.log("FAIL","Element was not disabled");
              return false;
          }
		  
	}catch(Exception e) 
		{
		System.out.println(e.getMessage());
	    Reports.log("FAIL","Element was not disabled");
	     }
		return false;
  }
  
	/*
	 * public static String GenerateAdminGroupName(int length) { String
	 * allowedChars="1234567890"; String GroupName=""; String
	 * temp=RandomStringUtils.random(length,allowedChars);
	 * GroupName="Admin"+temp.substring(0,3); System.out.println(GroupName); return
	 * GroupName; }
	 */
  
  public static Boolean Upload_File(String UploadFileName) throws IOException 
  {
      Boolean Status = true;
      String workingDirectory = new java.io.File(".").getCanonicalPath();
      try {
    	  
          String path = workingDirectory +"/"+UploadFileName;
          Thread.sleep(3000);
          Runtime.getRuntime().exec("wscript " + path + ".vbs");
          System.out.println("wscript " + path + ".vbs");
          Reports.log("PASS","Sucessfully uploaded the file");
         } 
      catch (Exception e)
      {
    	  Reports.log("FAIL","Fail to upload the file");
          Status = false;
        
      }
      return Status;
  }
  
  public static String GenerateRandomGroupName(int length)
  {
	  String name= "Admin "+RandomStringUtils.randomAlphabetic(length);
	  System.out.println(name);
	  return name;
	 
  }
  
  public static String GenerateRandomName()
  {
	  String RandonName="User "+RandomStringUtils.randomAlphabetic(3)+RandomStringUtils.randomNumeric(2);
	    return RandonName;
  }
  
  public static String GenerateRandomSubGroupName(int length)                                 
  {
	  String name= "SubGroup "+RandomStringUtils.randomAlphabetic(length);
	  System.out.println(name);
	  return name;
	 
  }
  
  public static String GenerateRandomCustomerName(int length)                                 
  {
	  String name= "NewCustomer "+RandomStringUtils.randomAlphabetic(length);
	  System.out.println(name);
	  return name;
	 
  }
  
  
  public static String GenerateRandomCustSubGroupName(int length)                                 
  {
	  String name= "CustSubGroup "+RandomStringUtils.randomAlphabetic(length);
	  System.out.println(name);
	  return name;
	 
  }
  
  public static String GenerateRandomURL()
  {
	   String URL ="User"+RandomStringUtils.randomAlphabetic(3)+RandomStringUtils.randomNumeric(3)+"@gmail.com"  ;
	   System.out.println(URL);
		  return URL;
	   
  }
  
  public static String GenerateRandomEmail()
  {
	   String Gmail ="dontgive"+RandomStringUtils.randomNumeric(4);
	   System.out.println(Gmail);
		  return Gmail;
	   
  }
  
  public static String GenerateRandomCustGroupName(int length)
  {
	  String name= "Customer "+RandomStringUtils.randomAlphabetic(length);
	  System.out.println(name);
	  return name;
	 
  }

  
  public static boolean clickElementUsingJavascriptExecutor(String xpathValue) throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException 
  {
    
      boolean Status = true;
      try {
          WebElement element = DriverScript.driver.findElement(By.xpath(xpathValue));

        Reports.log("PASS","Succesfully clicked on the element " );
          
      } catch (Exception e) 
      {
          System.out.println("exception value : " + e.getMessage());
          Reports.log("FAIL","Failed to click on the element ");
          Status = false;
      }
      return Status;
  }
  
 
  public static void waitForElement(WebElement locator) throws Exception 
  {
		try {
			
			locator = null;
			Wait<WebDriver> wait = new WebDriverWait(DriverScript.driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated((By) locator));


		} catch (Exception e) {
			e.printStackTrace();
			throw e;

				}
	}
  
  public static  boolean isElementPresent(String locator)
  {

		try {
			
			DriverScript.driver.findElement(By.xpath(locator));
			return true;
			
		} catch(Exception e)
				{
			System.out.println("Inside catch NullPointerException");
			return false;
		}
	}
  public enum ActionTypes {

      TAB, ENTER, F12, DOWN, ESCAPE, ORGANIZATION
  }
  
  public static boolean SENDKEYS(String value) throws AWTException 
  {
      boolean status = true;
     
      try {
          Robot robot = new Robot();
          Actions action = new Actions(DriverScript.driver);
          ActionTypes actTypes = ActionTypes.valueOf(value);
          switch (actTypes) {

              case TAB:
                  robot.keyPress(KeyEvent.VK_TAB);
                  robot.keyRelease(KeyEvent.VK_TAB);
                  //ReportFunctions.LogRepoter("Pass", "send keys", "Successfully clicked on TAB");
                  status = true;
                  break;
              case ENTER:
                  robot.keyPress(KeyEvent.VK_ENTER);
                  robot.keyRelease(KeyEvent.VK_ENTER);
                  //ReportFunctions.LogRepoter("Pass", "send keys", "Successfully clicked on ENTER");
                  status = true;
                  break;
              case F12:
                  robot.keyPress(KeyEvent.VK_F12);
                  robot.keyRelease(KeyEvent.VK_F12);
                  //ReportFunctions.LogRepoter("Pass", "send keys", "Successfully clicked on F12");
                  status = true;
                  break;
              case ESCAPE:
                  robot.keyPress(KeyEvent.VK_ESCAPE);
                  robot.keyRelease(KeyEvent.VK_ESCAPE);
                  //ReportFunctions.LogRepoter("Pass", "send keys", "Successfully clicked on F12");
                  status = true;
                  break;
              case DOWN:
                  robot.keyPress(KeyEvent.VK_DOWN);
                  robot.keyRelease(KeyEvent.VK_DOWN);
                  //ReportFunctions.LogRepoter("Pass", "send keys", "Successfully clicked on F12");
                  status = true;
                  break;
          }
      } catch (NumberFormatException e) {
          //out.println("unable to find the locator" + " " + e.getMessage());
          //ReportFunctions.LogRepoter("Fail", "send keys", "Unable to find send keys");
      }
      return status;
  }
 
  public void VerifyImage(String locator,String label) throws Exception {
		
		WebElement ImageFile = DriverScript.driver.findElement(By.xpath(locator));
	        
	        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)DriverScript.driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
	        if (!ImagePresent)
	        {
	             System.out.println("Image not displayed.");
	        }
	        else
	        {
	            System.out.println("Image displayed.");
	        }
		}
  
  
 public static void Clear_Cookies() throws InterruptedException, AWTException
 {
	      
     try {
	 DriverScript.driver.get("chrome://settings/clearBrowserData");
	 Thread.sleep(1000);
	 SENDKEYS("TAB");
	 Thread.sleep(1000);
	 SENDKEYS("TAB");
	 Thread.sleep(1000);
	 SENDKEYS("TAB");
	 Thread.sleep(1000);
	 SENDKEYS("TAB");
	 Thread.sleep(1000);
	 SENDKEYS("TAB");
	 Thread.sleep(1000);
	 SENDKEYS("TAB");
	 Thread.sleep(1000);
	 SENDKEYS("TAB");
	 Thread.sleep(1000);
	 SENDKEYS("ENTER");
	 Thread.sleep(8000);
	// Reports.log("PASS","Sucessfully Cleared the cache/cookies");
     }
     catch (NumberFormatException e) 
     {
         System.out.println("unable to find the locator" + " " + e.getMessage());
        // Reports.log("FAIL","Failed to Clear the cache/cookies");
        
     }
     
	 
 }
 
 public static void Terminate_Customer(String Customername) throws InterruptedException
 {
	 
	 Thread.sleep(3000);
		
		Customers_AppIcon.click();
		Thread.sleep(3000);
		Customer_Home_AllTab.click();
		
		Thread.sleep(5000);

		List<WebElement> AllCustomersrowz = DriverScript.driver.findElements(By.xpath("//div[@id=\"customers\"]/table/tbody/tr/td[2]"));
		System.out.println("no.of rows" + AllCustomersrowz.size());

		boolean flagz=false;
		for (int j = 1; j < AllCustomersrowz.size(); j++) 
		{
			WebElement AllCustomersList = DriverScript.driver.findElement(By.xpath("//*[@id=\"customers\"]/table/tbody/tr["+ j + "]/td[2]"));
			String value = AllCustomersList.getText();
			System.out.println(value);

			if (value.contains(Customername)) 
			{

				System.out.println("Value found  " + value);
				WebElement Customer_edit_icon = DriverScript.driver.findElement(By.xpath("//div[@id=\"customers\"]/table/tbody/tr["+j+"]/td[5]"));
				Customer_edit_icon.click();
				Thread.sleep(5000);
				flagz=true;
				break;
            } 
				
		}
		if(flagz=false)
		 {
			System.out.println("Not Found  " + Customername);
		 }
		
		
	 Edit_Cust_ARCHIVEbtn.click();
     Thread.sleep(3000);
  
     Edit_Cust_ARCHIVEbtn_confirmation.click();
     Thread.sleep(6000);
    
     Reports.log("PASS", "Customer" + Customername + " is archived");
     Thread.sleep(2000);
   
     UserManagement_AppIcon.click();
     Thread.sleep(2000);
     
     Customers_AppIcon.click();
     Thread.sleep(2000);
      
     Customer_Home_AllTab.click();
     Thread.sleep(5000);  
   
		List<WebElement> AllCustomersrowz1 = DriverScript.driver.findElements(By.xpath("//div[@id=\"customers\"]/table/tbody/tr/td[2]"));
		System.out.println("no.of rows" + AllCustomersrowz1.size());

		boolean flagz1=false;
		for (int j = 1; j < AllCustomersrowz1.size(); j++) 
		{
			WebElement AllCustomersList = DriverScript.driver.findElement(By.xpath("//*[@id=\"customers\"]/table/tbody/tr["+ j + "]/td[2]"));
			String value = AllCustomersList.getText();
			System.out.println(value);
			if (value.contains(Customername)) 
			 {

				System.out.println("Value found  " + value);
				WebElement Customer_edit_icon = DriverScript.driver.findElement(By.xpath("//div[@id=\"customers\"]/table/tbody/tr["+j+"]/td[5]"));
				Customer_edit_icon.click();
				Thread.sleep(5000);
				flagz1=true;
				break;
             } 
				
	    }
		if(flagz1=false)
		 {
			System.out.println("Not Found  " + Customername);
		 }
		
 Edit_Cust_DELETEbtn.click();
 Thread.sleep(2000);

 Cust_DelConfirmation_DELETEbtn.click();
 Thread.sleep(6000);
	 
 System.out.println("Successfully deleted the customer");
 Reports.log("PASS", "Customer" + Customername + " is deleted to use this script for regression");
 Thread.sleep(2000);
	 
	 
 }
  
  
}

