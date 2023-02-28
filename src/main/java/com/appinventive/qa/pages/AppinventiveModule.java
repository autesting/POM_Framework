package com.appinventive.qa.pages;

import com.appinventive.qa.testcases.Appinventive_User;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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


    public AppinventiveModule NavigateToUserDetailsPage() {

        driver.findElement(By.xpath(Users)).click();
//        driver.findElement(By.xpath("//a[normalize-space()='USER5076a5c6']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        String uuid = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-layout[1]/div[1]/app-detail[1]/section[1]/div[1]/div[3]/div[4]/p[1]")).getText();
//        System.out.println(uuid + "uuid");
        Reports.log("Pass", "Successfully Logged in");
        driver.findElement(By.xpath(InputBox)).sendKeys("a4bd1944-a194-4ae6-97a1-c5b49c52e809");
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
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.xpath("//div[@class='mat-select-arrow']")) ).click().build().perform();
        driver.findElement(By.xpath("//div[@class='mat-select-arrow']")).click();
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
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("xyz@email.com");
        driver.findElement(By.xpath("//input[@formcontrolname='dob']")).click();
        driver.findElement(By.xpath("//div[.='15']")).click();
        driver.findElement(By.xpath("//mat-select[@formcontrolname='occupationCode']")).click();
        driver.findElement(By.xpath("//span[.='020- Blue Collar Staff ']")).click();
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
        Boolean UnRestricted = driver.findElement(By.xpath("//button[.='A/C Unrestrict']")).isEnabled();
        Assert.assertEquals(UnRestricted.booleanValue(),true);
        return this;
    }

     public AppinventiveModule VerifyAddressBook(){
         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
         driver.findElement(By.xpath("//button[normalize-space()='Address Book']")).click();
        Boolean count = driver.findElement(By.xpath("//div[@class='total_count']")).isDisplayed();
        Assert.assertEquals(count.booleanValue(),true);
        driver.navigate().back();
        return this;
    }

      public AppinventiveModule VerifyTransaction(){
        driver.findElement(By.xpath("//button[normalize-space()='Transactions']")).click();
        Boolean Savings = driver.findElement(By.xpath("//div[@class='total_count_amount']")).isDisplayed();
        Assert.assertEquals(Savings.booleanValue(),true);
        driver.navigate().back();
        return this;
    }

      public AppinventiveModule VerifyAccountStatusLog(){
          Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.xpath("//div[@class='mat-select-arrow-wrapper']"))).build().perform();
          driver.findElement(By.xpath("//div[@class='mat-select-arrow-wrapper']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Queued for KYC']")).click();
        driver.findElement(By.xpath("//button[.='Yes']")).click();
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
          a.moveToElement(driver.findElement(By.xpath("//div[@class='mat-select-arrow-wrapper']"))).build().perform();
          driver.findElement(By.xpath("//div[@class='mat-select-arrow-wrapper']")).click();
         driver.findElement(By.xpath("//span[normalize-space()='KYC Canceled']")).click();
         driver.findElement(By.xpath("//button[.='Yes']")).click();
          driver.findElement(By.xpath("//textarea")).sendKeys("Testreject");
          driver.findElement(By.xpath("//button[.='Submit']")).click();
          driver.findElement(By.xpath("//button[.='Deleted User']")).isEnabled();
        return this;

      }



}
