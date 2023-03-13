package com.appinventive.qa.pages;

import com.appinventive.qa.ReportFunctions;
import com.appinventive.qa.Setup;
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
//import static com.appinventive.qa.pages.Object.*;


public class AppinventiveModule extends DriverScript {


    public HashMap<String,WebElement> hs;
    public boolean Status;


    // private static Object AppUsername;
    public AppinventiveModule AppinventiveLogin() {
        driver.findElement(By.xpath(AppUsername)).sendKeys(Username);
        driver.findElement(By.xpath(AppPassword)).sendKeys(UserPassword);
        driver.findElement(By.xpath(Login)).click();
        return this;
    }


    public AppinventiveModule NavigateToUserDetailsPage(String UUID) {
        Setup.hmap.put(Setup.Tcase, "NavigateToUserDetailsPage");
        try {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.findElement(By.xpath(Users)).click();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.findElement(By.xpath(InputBox)).sendKeys(UUID);
            driver.findElement(By.xpath(FirstUser)).click();
            String basicdetails = driver.findElement(By.xpath(UserBasicDetails)).getText();
            Status = UtilityFunctions.verifyValue(basicdetails,"User Basic Details");
            if(Status){
                ReportFunctions.LogRepoter("pass","Navigate To User Details Page",  "Navigate To User Details Page: " + "*" + basicdetails + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Navigate To User Details Page",  "Navigate To User Details Page: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occured",  "Exception Occured "+"*"+e+"*");
        }
        return this;
    }

    public static boolean RegisterationUserDate() {
        Boolean RegistrationDate = driver.findElement(By.xpath("body > app-root:nth-child(1) > app-layout:nth-child(3) > div:nth-child(3) > app-detail:nth-child(2) > section:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(17) > p:nth-child(2)")).isDisplayed();
        return RegistrationDate;
    }

    public AppinventiveModule VerifyAddressBookButton() {
        Setup.hmap.put(Setup.Tcase, "VerifyAddressBookButton");
        try {
            Boolean AddressBookButton = driver.findElement(By.xpath(AddressBook)).isEnabled();
//        Assert.assertEquals(AddressBookButton.booleanValue(), true);
            Status = UtilityFunctions.verifyValueBoolean(AddressBookButton,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify Address Book Button",  "Verify Address Book Button: " + "*" + AddressBookButton + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify Address Book Button",  "Verify Address Book Button: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        return this;
    }

    public AppinventiveModule VerifyMarkAsDelayedButton() {
        Setup.hmap.put(Setup.Tcase, "VerifyMarkAsDelayedButton");
        try {
            Boolean MarkAsDelayed = driver.findElement(By.xpath(DriverScript.MarkAsDelayed)).isEnabled();
//        Assert.assertEquals(MarkAsDelayed.booleanValue(), true);
            Status = UtilityFunctions.verifyValueBoolean(MarkAsDelayed,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify Mark As Delayed Button",  "Verify Mark As Delayed Button: " + "*" + MarkAsDelayed + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify Mark As Delayed Button",  "Verify Mark As Delayed Button: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        return this;
    }

    public AppinventiveModule VerifyCommunicationLogsButton() {
        Setup.hmap.put(Setup.Tcase, "VerifyCommunicationLogsButton");
        try {
            Boolean CommunicationLogs = driver.findElement(By.xpath("//button[normalize-space()='Communication Logs']")).isEnabled();
//        Assert.assertEquals(CommunicationLogs.booleanValue(), true);
            Status = UtilityFunctions.verifyValueBoolean(CommunicationLogs,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify Communication Logs Button",  "Verify Communication Logs Button: " + "*" + CommunicationLogs + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify Communication Logs Button",  "Verify Communication Logs Button: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        return this;
    }

    public AppinventiveModule VerifyEditInfoButton() {
        Setup.hmap.put(Setup.Tcase, "VerifyEditInfoButton");
        try {
            Boolean EditInfo = driver.findElement(By.xpath("//button[normalize-space()='Edit Info']")).isEnabled();
            Status = UtilityFunctions.verifyValueBoolean(EditInfo,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify Edit Info Button",  "Verify Edit Info Button: " + "*" + EditInfo + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify Edit Info Button",  "Verify Edit Info Button: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        return this;
    }

    public AppinventiveModule VerifyTransactionsButton() {
        Setup.hmap.put(Setup.Tcase, "VerifyTransactionsButton");
        try {
            Boolean Transactions = driver.findElement(By.xpath("//button[normalize-space()='Transactions']")).isEnabled();
            Status = UtilityFunctions.verifyValueBoolean(Transactions,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify Transactions Button",  "Verify Transactions Button: " + "*" + Transactions + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify Transactions Button",  "Verify Transactions Button: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        return this;
    }

    public AppinventiveModule VerifyBlockUserButton() {
        Setup.hmap.put(Setup.Tcase, "VerifyBlockUserButton");
        try {
            Boolean BlockUser = driver.findElement(By.xpath("//p[normalize-space()='Block User']")).isEnabled();
            Status = UtilityFunctions.verifyValueBoolean(BlockUser,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify Block User Button",  "Verify Block User Button: " + "*" + BlockUser + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify Block User Button",  "Verify Block User Button: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        return this;
    }

    public AppinventiveModule VerifyACRestrictButton() {
        Setup.hmap.put(Setup.Tcase, "VerifyACRestrictButton");
        try {
            Boolean ACRestrict = driver.findElement(By.xpath("//p[normalize-space()='A/C Restrict']")).isEnabled();
            Status = UtilityFunctions.verifyValueBoolean(ACRestrict,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify AC Restrict Button",  "Verify AC Restrict Button: " + "*" + ACRestrict + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify AC Restrict Button",  "Verify AC Restrict Button: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        return this;
    }

    public AppinventiveModule VerifyUUIDCopybutton(){
        Setup.hmap.put(Setup.Tcase, "VerifyUUIDCopybutton");
        driver.findElement(By.xpath("//i[.=' file_copy ']")).click();
        String uuid = Keys.CONTROL + "v";
        System.out.println("UUID is " +uuid);
        return this;
    }

    public AppinventiveModule VerifyAccountStatus() {
        Setup.hmap.put(Setup.Tcase, "VerifyAccountStatus");
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
        String verifyResponseBody = "Queued for KYC";
        assert AccStatuslist.get(0) != null;
        Status = UtilityFunctions.verifyValue(AccStatuslist.get(0),"Queued for KYC");
        if(Status){
            ReportFunctions.LogRepoter("pass","Verify Account Status",  "Verify Account Status: " + "*" + AccStatuslist.get(0) + "*" + " ");
        }
        else {
            ReportFunctions.LogRepoter("fail","Verify Account Status",  "Verify Account Status: *Failure*");
        }
//        Assert.assertEquals(AccStatuslist.get(1),"KYC Rejected");
        String verifyResponseBody1 = "KYC Rejected";
        Status = UtilityFunctions.verifyValue(AccStatuslist.get(1),"KYC Rejected");
        if(Status){
            ReportFunctions.LogRepoter("pass","Verify KYC Rejected",  "KYC Rejected: " + "*" + AccStatuslist.get(1) + "*" + " ");
        }
        else {
            ReportFunctions.LogRepoter("fail","Verify KYC Rejected",  "KYC Rejected: *Failure*");
        }
//        Assert.assertEquals(AccStatuslist.get(2),"KYC Canceled");
        String verifyResponseBody2 = "KYC Rejected";
        assert AccStatuslist.get(2) != null;
        if (verifyResponseBody2.contentEquals(AccStatuslist.get(2))) {
            System.out.println("Successfully verified KYC Rejected KYC response " + AccStatuslist.get(2));
        } else {
            System.out.println("Response verification KYC Rejected failed " + AccStatuslist.get(2));
        }
        return this;
    }

    public AppinventiveModule BlockUserVerify() {
        Setup.hmap.put(Setup.Tcase, "BlockUserVerify");
        driver.findElement(By.xpath("//span[normalize-space()='KYC Canceled']")).click();
        driver.findElement(By.xpath("//button[.='No']")).click();
        driver.findElement(By.xpath("//button[.='Block User']")).click();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[.='Yes']")).click();
        return this;
    }

    public AppinventiveModule UnblockButton() {
        Setup.hmap.put(Setup.Tcase, "VerifyUnblockButton");
        try {
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            Boolean Unblock = driver.findElement(By.xpath("//button[.='Unblock User']")).isDisplayed();
//        Assert.assertEquals(Unblock.booleanValue(), true);
            Status = UtilityFunctions.verifyValueBoolean(Unblock,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify Unblock Button",  "Verify Unblock Button: " + "*" + Unblock + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify Unblock Button",  "Verify Unblock Button: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
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
        a.moveToElement(driver.findElement(By.xpath("//button[normalize-space()='Edit Info']"))).build().perform();
        WebElement ele = driver.findElement(By.xpath("//button[normalize-space()='Edit Info']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);        driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys("ABCDE");
        driver.findElement(By.xpath("//input[@formcontrolname='middleName']")).sendKeys("xyz");
        driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys("xyz");
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(Keys.CONTROL+"a");
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).clear();
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("xyz@email.com");
        driver.findElement(By.xpath("//input[@formcontrolname='dob']")).click();
        driver.findElement(By.xpath("//div[.='1']")).click();
        driver.findElement(By.xpath("//mat-select[@formcontrolname='occupationCode']")).click();
        a.moveToElement(driver.findElement(By.xpath("//span[.='017- Security Guard ']"))).click().build().perform();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        a.moveToElement(driver.findElement(By.xpath("//button[.='Save']"))).build().perform();
        driver.findElement(By.xpath("//button[.='Save']")).click();
        return  this;
    }

    public AppinventiveModule VerifyMarkAsDelayed(){
        Setup.hmap.put(Setup.Tcase, "VerifyUnblockButton");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement ele = driver.findElement(By.xpath("//button[normalize-space()='Mark as Delayed']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);
        driver.findElement(By.xpath("//textarea[@placeholder='reason']")).sendKeys("delayed");
        WebElement ele2 = driver.findElement(By.xpath("//button[normalize-space()='Submit']"));
        jse.executeScript("arguments[0].click()", ele2);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Boolean MarkOnTrack = driver.findElement(By.xpath("//button[normalize-space()='Mark on Track']")).isEnabled();
        Assert.assertEquals(MarkOnTrack.booleanValue(),true);
        Status = UtilityFunctions.verifyValueBoolean(MarkOnTrack,true);
        return this;
    }

    public AppinventiveModule VerifyACRestricted(){
        Setup.hmap.put(Setup.Tcase, "VerifyACRestricted");
        driver.findElement(By.xpath("//button[.='A/C Restrict']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
        try {
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            Boolean UnRestricted = driver.findElement(By.xpath("//button[.='A/C Unrestrict']")).isEnabled();
//        Assert.assertEquals(UnRestricted.booleanValue(),true);
            Status = UtilityFunctions.verifyValueBoolean(UnRestricted,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify UnRestricted",  "Verify UnRestricted: " + "*" + UnRestricted + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify UnRestricted",  "Verify UnRestricted: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        return this;
    }

    public AppinventiveModule VerifyAddressBook(){
        Setup.hmap.put(Setup.Tcase, "VerifyAddressBook");
        Actions a = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        a.moveToElement(driver.findElement(By.xpath("//button[normalize-space()='Address Book']"))).build().perform();
        WebElement ele = driver.findElement(By.xpath("//button[normalize-space()='Address Book']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);
        try {
            Boolean count = driver.findElement(By.xpath("//div[@class='total_count']")).isDisplayed();
//        Assert.assertEquals(count.booleanValue(),true);
            Status = UtilityFunctions.verifyValueBoolean(count,true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify count",  "Verify count: " + "*" + count + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify count",  "Verify count: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        driver.navigate().back();
        return this;
    }

    public AppinventiveModule VerifyTransaction(){
        Setup.hmap.put(Setup.Tcase, "VerifyTransaction");
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transactions']")));
        driver.findElement(By.xpath("//button[normalize-space()='Transactions']")).click();
        try {
            Boolean Savings = driver.findElement(By.xpath("//div[@class='total_count_amount']")).isDisplayed();
//        Assert.assertEquals(Savings.booleanValue(),true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify Transaction",  "Verify Transaction: " + "*" + Savings + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify Transaction",  "Verify Transaction: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        driver.navigate().back();
        return this;
    }

    public AppinventiveModule VerifyAccountStatusLog()  {
        Setup.hmap.put(Setup.Tcase, "VerifyAccountStatusLog");
        Actions a = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement ele = driver.findElement(By.xpath("//mat-label[.='Account Status']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);
        driver.findElement(By.xpath("//span[normalize-space()='Queued for KYC']")).click();
        driver.findElement(By.xpath("//button[.='Yes']")).click();
        jse.executeScript("arguments[0].click()", ele);
        try {
            Boolean KYCApplied = driver.findElement(By.xpath("//span[.=' KYC Applied ']")).isDisplayed();
//        Assert.assertEquals(KYCApplied.booleanValue(),true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify KYC Applied",  "Verify KYC Applied: " + "*" + KYCApplied + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify KYC Applied",  "Verify KYC Applied: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        try {
            Boolean KYCRejected = driver.findElement(By.xpath("//span[.=' KYC Rejected ']")).isDisplayed();
//        Assert.assertEquals(KYCRejected.booleanValue(),true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify KYC Rejected",  "Verify KYC Rejected: " + "*" + KYCRejected + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify KYC Rejected",  "Verify KYC Rejected: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        try {
            Boolean KYCCanceled = driver.findElement(By.xpath("//span[.=' KYC Canceled ']")).isDisplayed();
//        Assert.assertEquals(KYCCanceled.booleanValue(),true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify KYC Canceled",  "Verify KYC Canceled: " + "*" + KYCCanceled + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify KYC Canceled",  "Verify KYC Canceled: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }
        a.moveToElement(driver.findElement(By.xpath("//div[28]//p[1]"))).build().perform();
        driver.findElement(By.xpath("//div[28]//p[1]")).isDisplayed();
        return this;
    }

    public AppinventiveModule SupportNotes(){
        Actions a = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        a.moveToElement(driver.findElement(By.xpath("//i[.=' create '][1]"))).build().perform();
        WebElement ele = driver.findElement(By.xpath("//i[.=' create '][1]"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);
        driver.findElement(By.xpath("//textarea[@formcontrolname='notes']")).sendKeys("Support Notes");
        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
        a.moveToElement(driver.findElement(By.xpath(UserBasicDetails))).build().perform();
        return this;
    }

    public AppinventiveModule KYCCancelled(){
        Setup.hmap.put(Setup.Tcase, "KYCCancelled");
        Actions a = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        a.moveToElement(driver.findElement(By.xpath("//mat-label[.='Account Status']"))).build().perform();
        WebElement ele = driver.findElement(By.xpath("//mat-label[.='Account Status']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);
        //driver.findElement(By.xpath("//mat-label[.='Account Status']")).click();
        WebElement ele2 = driver.findElement(By.xpath("//span[normalize-space()='Cancel Account']"));
        jse.executeScript("arguments[0].click()", ele2);
        try {
            Boolean Popup = driver.findElement(By.xpath("//h1[.='Confirmation']")).isDisplayed();
//        Assert.assertEquals(Popup.booleanValue(),true);
            if(Status){
                ReportFunctions.LogRepoter("pass","Verify Popup",  "Verify Popup: " + "*" + Popup + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","Verify Popup",  "Verify Popup: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occurred",  "Exception Occurred "+"*"+e+"*");
        }

        WebElement ele3 = driver.findElement(By.xpath("//button[.='Yes']"));
        jse.executeScript("arguments[0].click()", ele3);
        WebDriverWait wait = new WebDriverWait(driver,6);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Deleted User']"))));
        driver.findElement(By.xpath("//button[.='Deleted User']")).isDisplayed();
        Boolean Closebtn = false ;
        try {
            Closebtn = driver.findElement(By.xpath("//button[.='Close']")).isDisplayed();
        } catch (Exception e) {
            if (Closebtn) {
                driver.findElement(By.xpath("//button[.='Close']")).click();
            } else {
                System.out.println("Cannot read property 'toJSON' of null not shown");
            }
        }
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
        double Accno = random.nextInt(40)+100730+Math.random();
        String Acc = String.valueOf(Accno);
        String CardNo = Math.random()+"00000086998999"+Math.random()+Math.random()+Math.random();
//        int CardNo = generateRandomNumber(16);
//        String cardnum = String.valueOf(CardNo);
        driver.findElement(By.xpath("//input[@formcontrolname='jdbAccountNo']")).sendKeys(Acc);
        driver.findElement(By.xpath("//button[.='Submit']")).click();
        driver.findElement(By.xpath("//mat-error[.='Card Number is required']")).isDisplayed();
        driver.findElement(By.xpath("//mat-error[.='Card Issued Date is required']")).isDisplayed();
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
    public  AppinventiveModule Sorting(){
        Actions a = new Actions(driver);
        driver.findElement(By.xpath(Users)).click();
        WebElement ele =  driver.findElement(By.xpath("//i[.=' filter_list ']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);
        WebElement ele2 = driver.findElement(By.xpath("//input[contains(@placeholder,'Choose a date from')]"));
        jse.executeScript("arguments[0].click()", ele2);
        WebElement ele3 = driver.findElement(By.xpath("//div[.='3']"));
        jse.executeScript("arguments[0].click()", ele3);
        WebElement ele4 = driver.findElement(By.xpath("//mat-select[@formcontrolname='unifiedAccountStatus']"));
        jse.executeScript("arguments[0].click()", ele4);
        WebElement ele5  = driver.findElement(By.xpath("//span[.=' Queued for KYC ']"));
        jse.executeScript("arguments[0].click()", ele5);
        a.moveToElement(driver.findElement(By.xpath("//button[.='Apply']"))).click().build().perform();
        return this;

    }
    public  AppinventiveModule ResetSorting(){
        Actions a = new Actions(driver);
        WebElement ele =  driver.findElement(By.xpath("//i[.=' filter_list ']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);
        WebElement ele2 = driver.findElement(By.xpath("//input[contains(@placeholder,'Choose a date from')]"));
        jse.executeScript("arguments[0].click()", ele2);
        WebElement ele3 = driver.findElement(By.xpath("//div[.='3']"));
        jse.executeScript("arguments[0].click()", ele3);
        WebElement ele4 = driver.findElement(By.xpath("//mat-select[@formcontrolname='unifiedAccountStatus']"));
        jse.executeScript("arguments[0].click()", ele4);
        WebElement ele5  = driver.findElement(By.xpath("//span[.=' Queued for KYC ']"));
        jse.executeScript("arguments[0].click()", ele5);
        try {
            a.moveToElement(driver.findElement(By.xpath("//button[.='Reset']"))).click().build().perform();
        }
        catch (ElementNotInteractableException e){
            System.out.println("Completed");

        }
        return this;

    }


    public AppinventiveModule CardColor(){
        Actions a = new Actions(driver);
        WebElement ele = driver.findElement(By.xpath("//button[normalize-space()='Edit Info']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", ele);
        a.moveToElement(driver.findElement(By.xpath("//mat-select[@formcontrolname='cardColor' and @aria-disabled='true' ]"))).build().perform();
        driver.navigate().back();

        return this;
    }


}

