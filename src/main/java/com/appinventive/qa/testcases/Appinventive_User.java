package com.appinventive.qa.testcases;

import com.appinventive.qa.pages.AppinventiveLogin;
import org.testng.annotations.Test;

public class Appinventive_User extends AppinventiveLogin {


    @Test
    public static void AppinventiveVerifyUserDetailsPage() throws Exception {
        ReadProperties();
        LaunchBrowser();
        AppinventiveLogin();
    }
}
