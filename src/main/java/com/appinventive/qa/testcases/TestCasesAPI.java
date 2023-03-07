package com.appinventive.qa.testcases;

import com.appinventive.qa.ReportFunctions;
import com.appinventive.qa.modules.AddUpdateCustomerModule;
import com.appinventive.qa.pages.DriverScript;
import com.appinventive.qa.pages.Reports;
import com.appinventive.qa.ApiUtils.APIFunctions;
import com.appinventive.qa.ApiUtils.Constants;
import com.appinventive.qa.pages.UtilityFunctions;
import com.appinventive.qa.util.UID;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.appinventive.qa.utilily.ConfigLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.appinventive.qa.ApiUtils.JSONHandler.parseJSON;

public  class TestCasesAPI extends ReportFunctions {

    public static String Baseurl = "https://reqres.in";
    APIFunctions API = new APIFunctions();
    public static  Map<String, String> headers = new HashMap<>();
    public static String uri;
    public boolean Status;
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

    public  static   void testData() throws Exception {
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
    }

    @Test
    public  static   void reportSetup() throws Exception {
        if (suitename == null) {
            suitename = "suitename";
        }
        if (Tcasename == null) {
            Tcasename = "Tcasename";
        }
        hmap.put(Tstep, "Tstep");
        hmap.put(suitename, "AppInventiveUserFlow");
        FOLDERSTRUCTURE("AppInventiveUserFlow");
    }

    @Parameters
    @Test
    public void verifyAddUpdateCustomer() throws Exception {
        testData();
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

    @Test(dependsOnMethods = "verifyAddUpdateCustomer")
    public void verifyAddUpdateWithRequiredFieldsOnly() throws IOException {
        hmap.put(Tcase, "verifyAddUpdateWithRequiredFieldsOnly");
        response = AddUpdateCustomerModule.postFormDataRequiredFeilds(uri, passAuthorization, imagePath);
        String Successmsg = parseJSON(response, "message");
        //  Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");
        Status = UtilityFunctions.verifyValue(Successmsg,"Success.");
        try {
            if(Status){
                ReportFunctions.LogRepoter("pass","verify Add Update With Required Fields Only",  "verify2 AddUpdate With Required Fields Only: " + "*" + Successmsg + "*" + " ");
            }
            else {
                ReportFunctions.LogRepoter("fail","verify Add Update With Required Fields Only",  "verify AddUpdate With Required Fields Only: *Failure*");
            }
        }
        catch (Exception e)
        {
            ReportFunctions.LogRepoter("fail","Exception Occured",  "Exception Occured "+"*"+e+"*");
        }
    }

    @Test(dependsOnMethods = "verifyAddUpdateWithRequiredFieldsOnly")
    public void verifyAddUpdateWithOptionFieldsOnly() throws IOException {
        try {
            hmap.put(Tcase, "verifyAddUpdateWithOptionFieldsOnly");
            response = AddUpdateCustomerModule.postFormDataOptionalFeilds(uri, passAuthorization, imagePath);
            String Failuremsg = parseJSON(response, "message");
            Assert.assertEquals(Failuremsg, "Field validation failed.", "Response message is as expected");
            Status = UtilityFunctions.verifyValue(Failuremsg, "Field validation failed.");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify Add Update With Option Fields Only", "verify2 AddUpdate With Option Fields Only: " + "*" + Failuremsg + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "verify Add Update With Option Fields Only", "verify AddUpdate With Option Fields Only: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }


    @Test(dependsOnMethods = "verifyAddUpdateWithOptionFieldsOnly")
    public void verifyAddUpdateAlreadyExistedCustomer() throws IOException {
        try {
            hmap.put(Tcase, "verifyAddUpdateAlreadyExistedCustomer");
            response = AddUpdateCustomerModule.postFormAlreadyExistedData(uri, passAuthorization, imagePath);
            String Verificationmsg = parseJSON(response, "message");
            Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
            Status = UtilityFunctions.verifyValue(Verificationmsg, "Success.");

            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify Add Update Already Existed Customer", "verify Add Update Already Existed Customer: " + "*" + Verificationmsg + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "verify Add Update Already Existed Customer", "verify Add Update Already Existed Customer: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }

    @Test(dependsOnMethods = "verifyAddUpdateAlreadyExistedCustomer")
    public void verifyAddUpdateCanceledCustomerData() throws IOException {
        try {
            hmap.put(Tcase, "verifyAddUpdateCanceledCustomerData");
            response = AddUpdateCustomerModule.postFormCanceledUserDataData(uri, passAuthorization, imagePath);
            String Verificationmsg = parseJSON(response, "message");
            Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
            Status = UtilityFunctions.verifyValue(Verificationmsg, "Success.");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify AddUpdate Canceled Customer Data", "verify2 AddUpdate With Required Fields Only: " + "*" + Verificationmsg + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "verify AddUpdate Canceled Customer Data", "verify AddUpdate With Required Fields Only: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }

    @Test(dependsOnMethods = "verifyAddUpdateCanceledCustomerData")
    public void verifyIncorrectDataCustomer() throws IOException {
        try {
            hmap.put(Tcase, "verifyIncorrectDataCustomer");
            response = AddUpdateCustomerModule.postFormIncorrectDataCustomer(uri, passAuthorization, imagePath);
            String Errormsg = parseJSON(response, "message");
            Assert.assertEquals(Errormsg, "email.substring is not a function", "Response message is as expected");
            Status = UtilityFunctions.verifyValue(Errormsg, "email.substring is not a function");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify Incorrect Data Customer", "verify Incorrect Data Customer: " + "*" + Errormsg + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "verify Incorrect Data Customer", "verify Incorrect Data Customer: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }

    @Test(dependsOnMethods = "verifyIncorrectDataCustomer")
    public void verifyUpdateCustomer() throws IOException {
        try {
            hmap.put(Tcase, "verifyUpdateCustomer");
            response = AddUpdateCustomerModule.postFormUpdateExistingCustomer(uri, passAuthorization, imagePath,Uuid);
            String Verificationmsg = parseJSON(response, "message");
            Status = UtilityFunctions.verifyValue(Verificationmsg, "Success.");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify Update Customer", "verify Update Customer: " + "*" + Verificationmsg + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "verify Update Customer", "verify Update Customer: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }

    @Test(dependsOnMethods = "verifyUpdateCustomer")
    public  void verifyAddUpdateCustomerMinNumber() throws Exception {
        try {
            hmap.put(Tcase, "verifyAddUpdateCustomerMinNumber");
            response = AddUpdateCustomerModule.postFormDatawithMinNumber(uri, passAuthorization, imagePath);
            String Errormsg = parseJSON(response, "message");
            Status = UtilityFunctions.verifyValue(Errormsg, "Field validation failed.");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify AddUpdate Customer Min Number", "verify2 AddUpdate With Required Fields Only: " + "*" + Errormsg + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "verify AddUpdate Customer Min Number", "verify AddUpdate With Required Fields Only: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerMinNumber")
    public  void verifyAddUpdateCustomerMaxNumber() throws Exception {
        try {
            hmap.put(Tcase, "verifyAddUpdateCustomerMaxNumber");
            response = AddUpdateCustomerModule.postFormDatawithMaxNumber(uri, passAuthorization, imagePath);
            String Errormsg = parseJSON(response, "message");
            Status = UtilityFunctions.verifyValue(Errormsg, "Field is in incorrect format");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify Add Update Customer Max Number", "verify Add Update Customer Max Number: " + "*" + Errormsg + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "verify Add Update Customer Max Number", "verify Add Update Customer Max Number: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerMaxNumber")
    public  void verifyAddUpdateCustomerStarting00Number() throws Exception {
        try {
            hmap.put(Tcase, "verifyAddUpdateCustomerStarting00Number");
            response = AddUpdateCustomerModule.postFormDatawithStarting00Number(uri, passAuthorization, imagePath);
            String Verificationmessage = parseJSON(response, "message");
            Status = UtilityFunctions.verifyValue(Verificationmessage, "Not allowed to update status");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify AddUpdate Customer Starting 00 Number", "verify AddUpdate Customer Starting 00 Number: " + "*" + Verificationmessage + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "verify AddUpdate Customer Starting 00 Number", "verify AddUpdate Customer Starting 00 Number: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerStarting00Number")
    public  void verifyAddUpdateCustomerInvalidPassword() throws Exception {
        //String Errormsg = "Valid Phone no. is required";
        try {
            response = AddUpdateCustomerModule.postFormDatawithInvalidPassword(uri, passAuthorization, imagePath);
            String Verificationmessage = parseJSON(response, "message");
            Status = UtilityFunctions.verifyValue(Verificationmessage, "Not allowed to update status");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify AddUpdate Customer Invalid Password", "verify AddUpdate Customer Invalid Password: " + "*" + Verificationmessage + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "vverify AddUpdate Customer Invalid Password", "verify AddUpdate Customer Invalid Password: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerInvalidPassword")
    public  void verifyAddUpdateCustomerWrongRequestURL() throws Exception {
        try{
            response = AddUpdateCustomerModule.postFormDatawithWrongRequestURL(uri, passAuthorization, imagePath);
            String Errormsg = parseJSON(response, "message");
            // Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
            Status = UtilityFunctions.verifyValue(Errormsg, "Field validation failed.");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify AddUpdate Customer Wrong Request URL", "verify AddUpdate Customer Wrong Request URL: " + "*" + Errormsg + "*" + " ");
            } else {
                ReportFunctions.LogRepoter("fail", "verify AddUpdate Customer Wrong Request URL", "verify AddUpdate Customer Wrong Request URL: *Failure*");
            }
        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");
        }
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerWrongRequestURL")
    public  void verifyAddUpdateCustomerWrongAuthenticationURL() throws Exception {
        try{
            response = AddUpdateCustomerModule.postFormDatawithWrongAuthentication(uri, passAuthorization, imagePath);
            String Verificationmsg = parseJSON(response, "message");
          //  Assert.assertEquals(Verificationmsg, "Basic Authentication required", "Response message is as expected");
            Status = UtilityFunctions.verifyValue(Verificationmsg, "Basic Authentication required");

            // Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify AddUpdate Customer Wrong AuthenticationURL", "verify AddUpdate Customer Wrong AuthenticationURL: " + "*" + Verificationmsg + "*" + " ");

            } else {

                ReportFunctions.LogRepoter("fail", "verify AddUpdate Customer Wrong AuthenticationURL", "verify AddUpdate Customer Wrong AuthenticationURL: *Failure*");

            }


        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");


        }

    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerWrongAuthenticationURL")
    public  void verifyAddUpdateCustomerWrongContentType() throws Exception {
        try {

            response = AddUpdateCustomerModule.postFormDatawithWrongContentType(uri, passAuthorization, imagePath);
            String Verificationmsg = parseJSON(response, "message");
       //     Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
            Status = UtilityFunctions.verifyValue(Verificationmsg, "Success.");

            // Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify AddUpdate Customer Wrong Content Type", "verify AddUpdate Customer Wrong Content Type: " + "*" + Verificationmsg + "*" + " ");

            } else {

                ReportFunctions.LogRepoter("fail", "verify AddUpdate Customer Wrong Content Type", "verify AddUpdate Customer Wrong Content Type: *Failure*");

            }


        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");


        }


    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerWrongContentType")
    public  void verifyAddUpdateCustomerWithoutBodyContent() throws Exception {
        try {

            response = AddUpdateCustomerModule.postFormDatawithWithoutBody(uri, passAuthorization, imagePath);
            String Verificationmsg = parseJSON(response, "message");
         //   Assert.assertEquals(Verificationmsg, "Field validation failed.", "Response message is as expected");

            Status = UtilityFunctions.verifyValue(Verificationmsg, "Field validation failed.");

            // Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
            if (Status) {
                ReportFunctions.LogRepoter("pass", "verify Add Update Customer Without Body Content", "verify Add Update Customer Without Body Content: " + "*" + Verificationmsg + "*" + " ");

            } else {

                ReportFunctions.LogRepoter("fail", "verify Add Update Customer Without Body Content", "verify Add Update Customer Without Body Content: *Failure*");

            }


        } catch (Exception e) {
            ReportFunctions.LogRepoter("fail", "Exception Occured", "Exception Occured " + "*" + e + "*");


        }
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerWithoutBodyContent")
    public void getFetchProfile() throws IOException {
        String response = API.apiGetcall(uriFetch+Uuid, headers);
        headers.put("userUuid", Uuid);
        String runtimeResponseBody = parseJSON(response, "message");
        String verifyResponseBody = "Success.";
        Assert.assertEquals(runtimeResponseBody, verifyResponseBody);
        assert runtimeResponseBody != null;
        if (verifyResponseBody.contentEquals(runtimeResponseBody))
        {
            System.out.println("Successfully verified response " + runtimeResponseBody);
        }
        else
        {
            System.out.println("Response verification failed " + runtimeResponseBody);
        }
    }

}
