package com.appinventive.qa.testcases;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class rough {
	//public  static  WebDriver driver;
	public static void main (String args[]) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?redirect_uri=https%3A%2F%2Fdevelopers.google.com%2Foauthplayground&prompt=consent&response_type=code&client_id=407408718192.apps.googleusercontent.com&scope=email&access_type=offline&flowName=GeneralOAuthFlow");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("sahitya.chadaram@gmail.com");
		driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("sahityamurali@1");
		driver.findElement(By.xpath("(//div[@class=\"VfPpkd-RLmnJb\"])[1]")).click();
		Thread.sleep(5000);
	}
	 
}	 
	 
