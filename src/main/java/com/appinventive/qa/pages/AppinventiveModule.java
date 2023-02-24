package com.appinventive.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AppinventiveModule extends DriverScript {


    public static void AppinventiveLogin() {
        driver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys(Username);
        driver.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys(UserPassword);
        driver.findElement(By.xpath("//button[.='Login']")).click();
    }


    public static void NavigateToUserDetailsPage(){

        driver.findElement(By.xpath("//span[.=' Users ']")).click();
//        driver.findElement(By.xpath("//a[normalize-space()='USER5076a5c6']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        String uuid = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-layout[1]/div[1]/app-detail[1]/section[1]/div[1]/div[3]/div[4]/p[1]")).getText();
//        System.out.println(uuid + "uuid");
        Reports.log("Pass","Successfully Logged in");
        driver.findElement(By.xpath("//input[@placeholder='UUID, Account No., Name, Email, Phone No., Card No.']")).sendKeys("7f7bbdbb-1b49-47b3-a4c3-2460a766526b");
        driver.findElement(By.xpath("//a[contains(@href,'/admin/')]")).click();

    }


    public static boolean RegisterationUserDate(){

     Boolean RegistrationDate =   driver.findElement(By.xpath("body > app-root:nth-child(1) > app-layout:nth-child(3) > div:nth-child(3) > app-detail:nth-child(2) > section:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(17) > p:nth-child(2)")).isDisplayed();
      return  RegistrationDate;

    }
    public static boolean AddressBookButton(){


        Boolean AddressBookButton = driver.findElement(By.xpath("//button[normalize-space()='Address Book']")).isEnabled();
        return  AddressBookButton;

    }
    public static boolean MarkAsDelayedButton(){


        Boolean MarkAsDelayed = driver.findElement(By.xpath("//button[normalize-space()='Mark as Delayed']")).isEnabled();
        return  MarkAsDelayed;
    }
    public static boolean CommunicationLogsButton(){


        Boolean CommunicationLogs = driver.findElement(By.xpath("//button[normalize-space()='Communication Logs']")).isEnabled();
        return  CommunicationLogs;
    }
    public static boolean EditInfoButton(){


        Boolean EditInfo = driver.findElement(By.xpath("//button[normalize-space()='Edit Info']")).isEnabled();
        return  EditInfo;
    }
    public static boolean TransactionsButton(){


        Boolean Transactions = driver.findElement(By.xpath("//button[normalize-space()='Transactions']")).isEnabled();
        return  Transactions;
    }
    public static boolean BlockUserButton(){


        Boolean BlockUser = driver.findElement(By.xpath("//p[normalize-space()='Block User']")).isEnabled();
        return  BlockUser;
    }
    public static boolean ACRestrictButton(){


        Boolean ACRestrict = driver.findElement(By.xpath("//p[normalize-space()='A/C Restrict']")).isEnabled();
        return  ACRestrict;
    }

    public static ArrayList<String> AccountStatus(){
    driver.findElement(By.xpath("//mat-select[@role='listbox']")).click();
    String Text1 = driver.findElement(By.xpath("//span[normalize-space()='Queued for KYC']")).getText();
    String Text2 = driver.findElement(By.xpath("//span[normalize-space()='KYC Rejected']")).getText();
    String Text3 = driver.findElement(By.xpath("//span[normalize-space()='KYC Canceled']")).getText();
    ArrayList<String> AccStatuslist = new ArrayList<String>();
    AccStatuslist.add(Text1);
    AccStatuslist.add(Text2);
    AccStatuslist.add(Text3);
    return AccStatuslist;
    }

    public static void  BlockUserVerify(){

        driver.findElement(By.xpath("//span[normalize-space()='KYC Canceled']")).click();
        driver.findElement(By.xpath("//button[.='No']")).click();

        driver.findElement(By.xpath("//button[.='Block User']")).click();

        driver.findElement(By.xpath("//button[.='Yes']")).click();


    }

    public static boolean UnblockButton(){
        Boolean Unblock =  driver.findElement(By.xpath("//button[.='Unblock User']")).isDisplayed();
        return Unblock;
    }




}
