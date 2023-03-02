package com.appinventive.qa.pages;

import com.appinventive.qa.testcases.Appinventive_User;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.appinventive.qa.ApiUtils.APIFunctions.generateRandomNumber;
import static com.appinventive.qa.pages.Object.*;


public class AppinventiveModule extends DriverScript {


    public HashMap<String,WebElement> hs;

    // private static Object AppUsername;
    public AppinventiveModule AppinventiveLogin() {
        driver.findElement(By.xpath(AppUsername)).sendKeys(Username);
        driver.findElement(By.xpath(AppPassword)).sendKeys(UserPassword);
        driver.findElement(By.xpath(Login)).click();
        return this;
    }


    public AppinventiveModule NavigateToUserDetailsPage(String UUID) {

        driver.findElement(By.xpath(Users)).click();
//        driver.findElement(By.xpath("//a[normalize-space()='USER5076a5c6']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        String uuid = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-layout[1]/div[1]/app-detail[1]/section[1]/div[1]/div[3]/div[4]/p[1]")).getText();
//        System.out.println(uuid + "uuid");
        Reports.log("Pass", "Successfully Logged in");
        driver.findElement(By.xpath(InputBox)).sendKeys(UUID);
        driver.findElement(By.xpath(FirstUser)).click();
        return this;
    }

    public AppinventiveModule VerifyUserDetailsPage() {
        String basicdetails = driver.findElement(By.xpath(UserBasicDetails)).getText();
        Assert.assertEquals(basicdetails, "User Basic Details");
        return this;
    }

    public static boolean RegisterationUserDate() {
        Boolean RegistrationDate = driver.findElement(By.xpath("body > app-root:nth-child(1) > app-layout:nth-child(3) > div:nth-child(3) > app-detail:nth-child(2) > section:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(17) > p:nth-child(2)")).isDisplayed();
        return RegistrationDate;
    }

    public AppinventiveModule VerifyAddressBookButton() {
        Boolean AddressBookButton = driver.findElement(By.xpath(AddressBook)).isEnabled();
        Assert.assertEquals(AddressBookButton.booleanValue(), true);
        return this;
    }

    public AppinventiveModule VerifyMarkAsDelayedButton() {
        Boolean MarkAsDelayed = driver.findElement(By.xpath(DriverScript.MarkAsDelayed)).isEnabled();
        Assert.assertEquals(MarkAsDelayed.booleanValue(), true);
        return this;
    }

    public AppinventiveModule VerifyCommunicationLogsButton() {
        Boolean CommunicationLogs = driver.findElement(By.xpath("//button[normalize-space()='Communication Logs']")).isEnabled();
        Assert.assertEquals(CommunicationLogs.booleanValue(), true);
        return this;
    }

    public AppinventiveModule VerifyEditInfoButton() {
        Boolean EditInfo = driver.findElement(By.xpath("//button[normalize-space()='Edit Info']")).isEnabled();
        Assert.assertEquals(EditInfo.booleanValue(), true);
        return this;
    }

    public AppinventiveModule VerifyTransactionsButton() {
        Boolean Transactions = driver.findElement(By.xpath("//button[normalize-space()='Transactions']")).isEnabled();
        Assert.assertEquals(Transactions.booleanValue(), true);
        return this;
    }

    public AppinventiveModule VerifyBlockUserButton() {
        Boolean BlockUser = driver.findElement(By.xpath("//p[normalize-space()='Block User']")).isEnabled();
        Assert.assertEquals(BlockUser.booleanValue(), true);
        return this;
    }

    public AppinventiveModule VerifyACRestrictButton() {
        Boolean ACRestrict = driver.findElement(By.xpath("//p[normalize-space()='A/C Restrict']")).isEnabled();
        Assert.assertEquals(ACRestrict.booleanValue(), true);
        return this;
    }

    public AppinventiveModule VerifyAccountStatus() {

        Actions a = new Actions(driver);
        WebElement ele = driver.findElement(By.xpath("//mat-label[.='Account Status']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);
        String Text1 = driver.findElement(By.xpath("//span[normalize-space()='Queued for KYC']")).getText();
        String Text2 = driver.findElement(By.xpath("//span[normalize-space()='KYC Rejected']")).getText();
        String Text3 = driver.findElement(By.xpath("//span[normalize-space()='KYC Canceled']")).getText();
        ArrayList<String> AccStatuslist = new ArrayList<String>();
        AccStatuslist.add(Text1);
        AccStatuslist.add(Text2);
        AccStatuslist.add(Text3);
        Assert.assertEquals(AccStatuslist.get(0),"Queued for KYC");
        Assert.assertEquals(AccStatuslist.get(1),"KYC Rejected");
        Assert.assertEquals(AccStatuslist.get(2),"KYC Canceled");
        return this;
    }

    public AppinventiveModule BlockUserVerify() {
        driver.findElement(By.xpath("//span[normalize-space()='KYC Canceled']")).click();
        driver.findElement(By.xpath("//button[.='No']")).click();
        driver.findElement(By.xpath("//button[.='Block User']")).click();

        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[.='Yes']")).click();
        return this;
    }

    public AppinventiveModule UnblockButton() {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Boolean Unblock = driver.findElement(By.xpath("//button[.='Unblock User']")).isDisplayed();
        Assert.assertEquals(Unblock.booleanValue(), true);
        return this;
    }

    public AppinventiveModule VerifyLoginControl() {
        Actions a = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        a.moveToElement(driver.findElement(By.xpath("//p[normalize-space()='Blocked']"))).build().perform();
        String LoginControl = driver.findElement(By.xpath("//p[normalize-space()='Blocked']")).getText();
        return this;
    }

    public AppinventiveModule VerifyEditInfo() {
        Actions a = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        a.moveToElement(driver.findElement(By.xpath("//button[normalize-space()='Edit Info']"))).click().build().perform();
        driver.findElement(By.xpath("//button[normalize-space()='Edit Info']")).click();
        driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys("ABCDE");
        driver.findElement(By.xpath("//input[@formcontrolname='middleName']")).sendKeys("xyz");
        driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys("xyz");
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(Keys.CONTROL+"a");
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("xyz@email.com");
        driver.findElement(By.xpath("//input[@formcontrolname='dob']")).click();
        driver.findElement(By.xpath("//div[.='1']")).click();
        driver.findElement(By.xpath("//mat-select[@formcontrolname='occupationCode']")).click();
        a.moveToElement(driver.findElement(By.xpath("//span[.='017- Security Guard ']"))).click().build().perform();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        a.moveToElement(driver.findElement(By.xpath("//button[.='Save']"))).click().build().perform();
        return  this;
    }

    public  AppinventiveModule VerifyMarkAsDelayed(){
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[normalize-space()='Mark as Delayed']")).click();
        driver.findElement(By.xpath("//textarea[@placeholder='reason']")).sendKeys("delayed");
        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
        Boolean MarkOnTrack = driver.findElement(By.xpath("//button[normalize-space()='Mark on Track']")).isEnabled();
        Assert.assertEquals(MarkOnTrack.booleanValue(),true);
        return this;
    }

    public AppinventiveModule VerifyACRestricted(){
        driver.findElement(By.xpath("//button[.='A/C Restrict']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Boolean UnRestricted = driver.findElement(By.xpath("//button[.='A/C Unrestrict']")).isEnabled();
        Assert.assertEquals(UnRestricted.booleanValue(),true);
        return this;
    }

     public AppinventiveModule VerifyAddressBook(){
        Actions a = new Actions(driver);
         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
         a.moveToElement(driver.findElement(By.xpath("//button[normalize-space()='Address Book']"))).build().perform();

         WebElement ele = driver.findElement(By.xpath("//button[normalize-space()='Address Book']"));
         JavascriptExecutor jse = (JavascriptExecutor)driver;
         jse.executeScript("arguments[0].click()", ele);
         Boolean count = driver.findElement(By.xpath("//div[@class='total_count']")).isDisplayed();
         Assert.assertEquals(count.booleanValue(),true);
         driver.navigate().back();
        return this;
    }

      public AppinventiveModule VerifyTransaction(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transactions']")));
        driver.findElement(By.xpath("//button[normalize-space()='Transactions']")).click();
        Boolean Savings = driver.findElement(By.xpath("//div[@class='total_count_amount']")).isDisplayed();
        Assert.assertEquals(Savings.booleanValue(),true);
        driver.navigate().back();
        return this;
    }

      public AppinventiveModule VerifyAccountStatusLog()  {
          Actions a = new Actions(driver);
          WebDriverWait wait = new WebDriverWait(driver,30);
          WebElement ele = driver.findElement(By.xpath("//mat-label[.='Account Status']"));
          JavascriptExecutor jse = (JavascriptExecutor)driver;
          jse.executeScript("arguments[0].click()", ele);
          driver.findElement(By.xpath("//span[normalize-space()='Queued for KYC']")).click();
          driver.findElement(By.xpath("//button[.='Yes']")).click();
          jse.executeScript("arguments[0].click()", ele);
        Boolean KYCApplied = driver.findElement(By.xpath("//span[.=' KYC Applied ']")).isDisplayed();
        Boolean KYCRejected = driver.findElement(By.xpath("//span[.=' KYC Rejected ']")).isDisplayed();
        Boolean KYCCanceled = driver.findElement(By.xpath("//span[.=' KYC Canceled ']")).isDisplayed();
        Assert.assertEquals(KYCApplied.booleanValue(),true);
        Assert.assertEquals(KYCRejected.booleanValue(),true);
        Assert.assertEquals(KYCCanceled.booleanValue(),true);
        a.moveToElement(driver.findElement(By.xpath("//div[28]//p[1]"))).build().perform();
        driver.findElement(By.xpath("//div[28]//p[1]")).isDisplayed();

      return this;


      }

      public  AppinventiveModule SupportNotes(){
          Actions a = new Actions(driver);
          driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
          a.moveToElement(driver.findElement(By.xpath("//i[@class='material-icons edit-icon ng-star-inserted'][normalize-space()='create'][2]"))).build().perform();
          driver.findElement(By.xpath("//i[@class='material-icons edit-icon ng-star-inserted'][normalize-space()='create'][2]")).click();
          driver.findElement(By.xpath("//textarea[@formcontrolname='notes']")).sendKeys("Support Notes");
          driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
          a.moveToElement(driver.findElement(By.xpath(UserBasicDetails))).build().perform();
          return this;
      }

      public AppinventiveModule KYCCancelled(){
          Actions a = new Actions(driver);
          driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
          WebElement ele = driver.findElement(By.xpath("//mat-label[.='Account Status']"));
          JavascriptExecutor jse = (JavascriptExecutor)driver;
          jse.executeScript("arguments[0].click()", ele);
          //driver.findElement(By.xpath("//mat-label[.='Account Status']")).click();
         WebElement ele2 = driver.findElement(By.xpath("//span[normalize-space()='Cancel Account']"));
          jse.executeScript("arguments[0].click()", ele2);
         Boolean Popup = driver.findElement(By.xpath("//h1[.='Confirmation']")).isDisplayed();
         Assert.assertEquals(Popup.booleanValue(),true);
         WebElement ele3 = driver.findElement(By.xpath("//button[.='Yes']"));
          jse.executeScript("arguments[0].click()", ele3);
          driver.findElement(By.xpath("//button[.='Deleted User']")).isDisplayed();
        return this;

      }

      public AppinventiveModule AccStatusDropdown(){
          Actions a = new Actions(driver);
          WebElement ele = driver.findElement(By.xpath("//mat-label[.='Account Status']"));
          JavascriptExecutor jse = (JavascriptExecutor)driver;
          jse.executeScript("arguments[0].click()", ele);
          Boolean KYCApplied = driver.findElement(By.xpath("//span[.=' KYC Applied ']")).isDisplayed();
          Boolean KYCRejected = driver.findElement(By.xpath("//span[.=' KYC Rejected ']")).isDisplayed();
          Boolean KYCCanceled = driver.findElement(By.xpath("//span[.=' KYC Canceled ']")).isDisplayed();
          Assert.assertEquals(KYCApplied.booleanValue(),true);
          Assert.assertEquals(KYCRejected.booleanValue(),true);
          Assert.assertEquals(KYCCanceled.booleanValue(),true);
          driver.findElement(By.xpath("//span[.=' KYC Applied ']")).click();
          driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
          jse.executeScript("arguments[0].click()", ele);
          Boolean KYCApproved = driver.findElement(By.xpath("//span[.=' KYC Approved ']")).isDisplayed();
          Boolean KYCRejected2 = driver.findElement(By.xpath("//span[.=' KYC Rejected ']")).isDisplayed();
          Boolean KYCCanceled2 = driver.findElement(By.xpath("//span[.=' KYC Canceled ']")).isDisplayed();
          Assert.assertEquals(KYCApplied.booleanValue(),true);
          Assert.assertEquals(KYCRejected2.booleanValue(),true);
          Assert.assertEquals(KYCCanceled2.booleanValue(),true);
          driver.findElement(By.xpath("//span[.=' KYC Approved ']")).click();
          driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
          Boolean CardAllocationPopup = driver.findElement(By.xpath("//h1[@class='modal-title']")).isDisplayed();
          Assert.assertEquals(CardAllocationPopup.booleanValue(),true);
          return this;

      }
      public AppinventiveModule CardAllocationPopup(){
        Random random = new Random();
       Actions a = new Actions(driver);
        Integer Accno = random.nextInt(40)+1000000000;
        String Acc = String.valueOf(Accno);
        String CardNo = Math.random()+"00000000000000055"+Math.random();
        driver.findElement(By.xpath("//input[@formcontrolname='jdbAccountNo']")).sendKeys(Acc);
        driver.findElement(By.xpath("//input[@formcontrolname='cardNumber']")).sendKeys(CardNo);
        driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M19 3h-1V1')]")).click();
        driver.findElement(By.xpath("//div[normalize-space()='16']")).click();
        driver.findElement(By.xpath("//mat-select[@formcontrolname ='expiryMM']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='03']")).click();
        driver.findElement(By.xpath("//mat-select[@formcontrolname ='expiryYY']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='2026']")).click();
        driver.findElement(By.xpath("//button[.='Submit']")).click();
        Boolean BlockCard = driver.findElement(By.xpath("//button[normalize-space()='Block Card']")).isEnabled();
        Assert.assertEquals(BlockCard.booleanValue(),true);
        a.moveToElement(driver.findElement(By.xpath("//h2[normalize-space()='Card Details']"))).build().perform();
        driver.findElement(By.xpath("//p[.='"+Accno+"']")).isDisplayed();
        return this;

      }



}
