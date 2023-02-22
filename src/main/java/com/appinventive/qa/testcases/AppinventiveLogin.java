package com.appinventive.qa.testcases;

import com.appinventive.qa.pages.DriverScript;
import com.appinventive.qa.pages.Reports;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AppinventiveLogin extends DriverScript
{
    static DriverScript ds = new DriverScript();



    @Test
    public static void AppinventiveLogin() throws Exception

    {
        ReadProperties();
        LaunchBrowser();
        driver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys(Username);
        driver.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys(UserPassword);
        driver.findElement(By.xpath("//button[.='Login']")).click();
        driver.findElement(By.xpath("//span[.=' Users ']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='USER5076a5c6']")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
       String uuid = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-layout[1]/div[1]/app-detail[1]/section[1]/div[1]/div[3]/div[4]/p[1]")).getText();
       System.out.println(uuid + "uuid");
        Reports.log("Pass","Successfully Logged in");

    }
}
