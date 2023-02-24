package com.appinventive.qa.testcases;

import com.appinventive.qa.pages.AppinventiveModule;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Appinventive_User extends AppinventiveModule {


    @Test(priority = 0)
    public static void AppinventiveVerifyUserDetailsPage() throws Exception {
        ReadProperties();
        LaunchBrowser();
        AppinventiveLogin();
        NavigateToUserDetailsPage();
        String basicdetails = driver.findElement(By.xpath("//h2[normalize-space()='User Basic Details']")).getText();
        Assert.assertEquals(basicdetails,"User Basic Details");
    }

    @Test(priority = 1)
     public static void VerifyAllTheButtons(){

       Assert.assertEquals(AddressBookButton(),true);
       Assert.assertEquals(MarkAsDelayedButton(),true);
       Assert.assertEquals(CommunicationLogsButton(),true);
       Assert.assertEquals(EditInfoButton(),true);
       Assert.assertEquals(TransactionsButton(),true);
       Assert.assertEquals(BlockUserButton(),true);
       Assert.assertEquals(ACRestrictButton(),true);


}

@Test(priority = 2)
public  static void VerifyAccStatusListAndBlockUser()
{

    ArrayList<String> AccStatusList = AccountStatus();
    Assert.assertEquals(AccStatusList.get(0),"Queued for KYC");
    Assert.assertEquals(AccStatusList.get(1),"KYC Rejected");
    Assert.assertEquals(AccStatusList.get(2),"KYC Canceled");
    BlockUserVerify();
    Assert.assertEquals(UnblockButton(),true);

}



}
