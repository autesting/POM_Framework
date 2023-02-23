package com.appinventive.qa.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.appinventive.qa.pages.DriverScript.driver;

 public class  ObjectRepo
{

  public static WebElement AppUsername = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
  public static WebElement Password = driver.findElement(By.xpath("//input[@id='mat-input-1']"));
  public static WebElement Login = driver.findElement(By.xpath("//button[.='Login']"));
  public static WebElement Users = driver.findElement(By.xpath("//span[.=' Users ']"));
  public static WebElement Firstuser = driver.findElement(By.xpath("//a[normalize-space()='USER5076a5c6']"));
  public static String heading = driver.findElement(By.xpath("//h2[.='User Basic Details']")).getText();

}
