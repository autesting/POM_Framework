package com.appinventive.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

import static com.appinventive.qa.pages.DriverScript.driver;

public  class   Object {

    //public static   WebElement AppUsername;
    public static HashMap<String,WebElement> hs;
    public static HashMap<String,WebElement> Objects()

    {
        hs = new HashMap<String,WebElement>();
        hs.put("AppUsername",driver.findElement(By.xpath("//input[@id='mat-input-0']")));
        hs.put("Password",driver.findElement(By.xpath("//input[@id='mat-input-1']")));
        hs.put("Login",driver.findElement(By.xpath("//button[.='Login']")));
        hs.put("Users",driver.findElement(By.xpath("//span[.=' Users ']")));
        hs.put("Firstuser",driver.findElement(By.xpath("//a[normalize-space()='USER5076a5c6']")));
        hs.put("searchbox",driver.findElement(By.xpath("//input[@placeholder='UUID, Account No., Name, Email, Phone No., Card No.']")));
        hs.put("User",driver.findElement(By.xpath("//a[contains(@href,'/admin/')]")));

//       public static WebElement Password = driver.findElement(By.xpath("//input[@id='mat-input-1']"));
//        public static WebElement Login = driver.findElement(By.xpath("//button[.='Login']"));
//        public static WebElement Users = driver.findElement(By.xpath("//span[.=' Users ']"));
//        public static WebElement Firstuser = driver.findElement(By.xpath("//a[normalize-space()='USER5076a5c6']"));
//
        return hs;
    }



}
