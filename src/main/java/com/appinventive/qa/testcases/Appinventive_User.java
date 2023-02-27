package com.appinventive.qa.testcases;

import com.appinventive.qa.pages.AppinventiveModule;
import com.appinventive.qa.pages.DriverScript;
//import com.appinventive.qa.pages.Object;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

public class Appinventive_User extends AppinventiveModule
{

    static AppinventiveModule UserFlow;

    @Test(priority = 0)
    public  void AppinventiveVerifyUserDetailsPage() throws Exception
    {
        ReadProperties();
        LaunchBrowser();
        UserFlow = AppinventiveLogin();
        UserFlow.NavigateToUserDetailsPage();
        UserFlow.VerifyUserDetailsPage();

    }

    @Test(priority = 1)
     public  void VerifyAllTheButtons(){


      UserFlow.VerifyAddressBookButton();
      UserFlow.VerifyMarkAsDelayedButton();
      UserFlow.VerifyCommunicationLogsButton();
      UserFlow.VerifyEditInfoButton();
      UserFlow.VerifyTransactionsButton();
      UserFlow.VerifyBlockUserButton();
      UserFlow.VerifyACRestrictButton();


}

@Test(priority = 2)

public  void VerifyAccStatusListAndBlockUser()

{


    UserFlow.VerifyAccountStatus();

    UserFlow.BlockUserVerify();
    UserFlow.UnblockButton();
   // UserFlow.VerifyLoginControl();


}

@Test(priority = 3)
public  void VerifyEdit()
{

    UserFlow.VerifyEditInfo();
}

@Test(priority = 4)
public  void VerifyMarkAsDelayedAndAccRestricted(){
    UserFlow.VerifyMarkAsDelayed();
    UserFlow.VerifyACRestricted();

}

    @Test(priority = 5)
public void VerifyAddressBookAndTransaction(){
        UserFlow.VerifyAddressBook();
        UserFlow.VerifyTransaction();
}

public  void VerifyAccStatusLog()
{

        UserFlow.VerifyAccountStatusLog();

}



}
