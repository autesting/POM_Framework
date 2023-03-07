package com.appinventive.qa.testcases;

import com.appinventive.qa.ApiUtils.APIFunctions;
import com.appinventive.qa.ApiUtils.Constants;
import com.appinventive.qa.Setup;
import com.appinventive.qa.modules.AddUpdateCustomerModule;
import com.appinventive.qa.pages.AppinventiveModule;
import com.appinventive.qa.pages.DriverScript;
//import com.appinventive.qa.pages.Object;
import com.appinventive.qa.pages.Reports;
import com.appinventive.qa.utilily.ConfigLoader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.appinventive.qa.ApiUtils.JSONHandler.parseJSON;
import static com.appinventive.qa.Setup.suitename;
//import static com.appinventive.qa.utilily.Setup.Tcasename;

public class Appinventive_User extends AppinventiveModule {

    static AppinventiveModule UserFlow;
    APIFunctions API = new APIFunctions();
    public static Map<String, String> headers = new HashMap<>();
    public static String uri;
    public static String filepath;
    public static String requestURI;
    public static String selectImagePath;
    static  String  response = null;
    static  String passAuthorization;
    static  String imagePath;
    static ArrayList<String> list;
    static String Uuid;
    public static String uriFetch;
    public static String requestURIFetch;
    public static Boolean Flag =false;

    public  static   void testData() throws IOException {
        filepath = "src/main/resources/config/api/registration-qa.json";
        requestURI = "/api/v1/x/user";
        requestURIFetch = "/api/v1/x/profile?";
        selectImagePath = "\\src\\main\\resources\\TestData\\UploadImage.png";
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadConfigJsonFile(filepath);
        uri = configLoader.getConfigValue(Constants.URI) + requestURI;
        uriFetch = configLoader.getConfigValue(Constants.URI) + requestURIFetch+"userUuid=";
        headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
        passAuthorization = headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        String workingDirectory = new File(".").getCanonicalPath();
        imagePath = workingDirectory + selectImagePath;
//        Uuid = "a7251f53-ddbc-4122-8076-851c8c4dc1e9";
    }
    @Parameters
    @Test
    public  static   void reportSetup() throws Exception {
        if (Setup.suitename == null) {
            Setup.suitename = "suitename";
        }
        if (Setup.Tcasename == null) {
            Setup.Tcasename = "Tcasename";
        }
        Setup.hmap.put(Setup.Tstep, "Tstep");
        Setup.hmap.put(Setup.suitename, "AppInventiveUserFlowUI");
        Setup.FOLDERSTRUCTURE("AppInventiveUserFlowUI");
    }

    @Test(dependsOnMethods = "reportSetup")
    public void verifyAddUpdateCustomer() throws Exception {
        testData();
        Flag =true;
        response = AddUpdateCustomerModule.postFormData(uri, passAuthorization, imagePath);
        String Successmsg = parseJSON(response, "message");
        Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer");
        String extractUuid = parseJSON(response,"data");
        System.out.println(extractUuid);
        String croppedUuid = extractUuid.substring(extractUuid.indexOf(":")+1);
        System.out.println(croppedUuid);
        String expectedUuid = croppedUuid.replaceAll("}", " ");
        Uuid = expectedUuid.substring(0,expectedUuid.length()-1);
        System.out.println("User UUID : " + Uuid);
        System.out.println("");
    }

    @Test (dependsOnMethods = "verifyAddUpdateCustomer")
    public void AppinventiveVerifyUserDetailsPage() throws Exception {
        ReadProperties();
        LaunchBrowser();
        UserFlow = AppinventiveLogin();
        UserFlow.NavigateToUserDetailsPage(Uuid);
    }

    @Test(dependsOnMethods = "AppinventiveVerifyUserDetailsPage")
    public void VerifyAllTheButtons(){
        UserFlow.VerifyAddressBookButton();
        UserFlow.VerifyMarkAsDelayedButton();
        UserFlow.VerifyCommunicationLogsButton();
        UserFlow.VerifyEditInfoButton();
        UserFlow.VerifyTransactionsButton();
        UserFlow.VerifyBlockUserButton();
        UserFlow.VerifyACRestrictButton();
        UserFlow.VerifyUUIDCopybutton();

    }

    @Test(dependsOnMethods = "VerifyAllTheButtons")
    public void VerifyAccStatusListAndBlockUser() {
        UserFlow.VerifyAccountStatus();
        UserFlow.BlockUserVerify();
        UserFlow.UnblockButton();
        UserFlow.VerifyLoginControl();
    }

    @Test(dependsOnMethods = "VerifyAccStatusListAndBlockUser")
    public void VerifyEdit() {

        UserFlow.VerifyEditInfo();
    }

    @Test(dependsOnMethods = "VerifyEdit")
    public void VerifyMarkAsDelayedAndAccRestricted() {
        UserFlow.VerifyMarkAsDelayed();
        UserFlow.VerifyACRestricted();
    }

    @Test(dependsOnMethods = "VerifyMarkAsDelayedAndAccRestricted")
    public void VerifyAddressBookAndTransaction(){
        UserFlow.VerifyAddressBook();
        UserFlow.VerifyTransaction();
    }

    @Test(dependsOnMethods = "VerifyAddressBookAndTransaction")
    public  void VerifyAccStatusLogAndSupportNotes()  {

        UserFlow.VerifyAccountStatusLog();
        // UserFlow.SupportNotes();


    }


    @Test(dependsOnMethods = "VerifyAccStatusLogAndSupportNotes")
    public void VerifyAccStatusDropdownAndCardAllocationPopup(){
        UserFlow.AccStatusDropdown();
        UserFlow.CardAllocationPopup();
    }

    @Test(dependsOnMethods = "VerifyAccStatusDropdownAndCardAllocationPopup")
    public void VerifyKycCancelled(){
        UserFlow.KYCCancelled();

    }










}
