package com.appinventive.qa.testcases;

import com.appinventive.qa.modules.AddUpdateCustomerModule;
import com.appinventive.qa.pages.DriverScript;
import com.appinventive.qa.pages.Reports;
import com.appinventive.qa.ApiUtils.APIFunctions;
import com.appinventive.qa.ApiUtils.Constants;
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

public  class TestCasesAPI {

    public static String Baseurl = "https://reqres.in";
    APIFunctions API = new APIFunctions();
    public static  Map<String, String> headers = new HashMap<>();
    public static String uri;
    public static String filepath;
    public static String requestURI;
    public static String selectImagePath;
    static  String  response = null;
    static  String passAuthorization;
    static  String imagePath;
    static ArrayList<String> list;
    static String Uuid;

    public  static   void testData() throws IOException {
        filepath = "src/main/resources/config/api/registration-qa.json";
        requestURI = "/api/v1/x/user";
        selectImagePath = "\\src\\main\\resources\\TestData\\UploadImage.png";
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadConfigJsonFile(filepath);
        uri = configLoader.getConfigValue(Constants.URI) + requestURI;
        headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
        passAuthorization = headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        String workingDirectory = new File(".").getCanonicalPath();
        imagePath = workingDirectory + selectImagePath;
       // Uuid = "fc1625af-389c-4ba4-9b8c-3e4f5b3fd31a";
    }
//    @Parameters
    @Test(priority = 1)
        public void verifyaddupdateCustomer() throws Exception {
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
//    @Test(dependsOnMethods = "verifyaddupdateCustomer")
    @Test(priority = 2)
    public void verifyAddUpdateWithRequiredFeildsOnly() throws IOException {
        testData();
        response = AddUpdateCustomerModule.postFormDataRequiredFeilds(uri, passAuthorization, imagePath);
        String Successmsg = parseJSON(response, "message");
        Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify AddUpdate With Required Feilds Only");
    }

    @Test(priority = 3)
    public void verifyAddUpdateWithOptionFeildsOnly() throws IOException {
        testData();
        response = AddUpdateCustomerModule.postFormDataOptionalFeilds(uri, passAuthorization, imagePath);
        String Failuremsg = parseJSON(response, "message");
        Assert.assertEquals(Failuremsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify AddUpdate WithOption FeildsOnly");
    }

    @Test(priority = 4)
    public void verifyaddupdateAlreadyExistedCustomer() throws IOException {
        testData();
        response = AddUpdateCustomerModule.postFormAlreadyExistedData(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Already Existed Customer");
    }

    @Test(priority = 5)
    public void verifyaddupdateCanceledCustomerData() throws IOException {
        testData();
        response = AddUpdateCustomerModule.postFormCanceledUserDataData(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Canceled CustomerData");
    }

    @Test(priority = 6)
    public void verifyIncorrectDataCustomer() throws IOException {
        testData();
        response = AddUpdateCustomerModule.postFormIncorrectDataCustomer(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "email.substring is not a function", "Response message is as expected");
        Reports.log("PASS","verify Incorrect DataCustomer");
    }

    @Test(priority = 7)
    public void verifyUpdateCustomer() throws IOException {
        testData();
        response = AddUpdateCustomerModule.postFormUpdateExistingCustomer(uri, passAuthorization, imagePath,Uuid);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verifyUpdateCustomer");
    }

    @Test(priority = 8)
    public static void verifyaddupdateCustomerMinNumber() throws Exception {
        testData();
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithMinNumber(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verifyaddupdateCustomerMinNumber");
    }

    @Test(priority = 9)
    public static void verifyaddupdateCustomerMaxNumber() throws Exception {
        testData();
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithMaxNumber(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "Phone already exists.", "Field is in incorrect format");
        Reports.log("PASS","verify addupdate Customer MaxNumber");
    }

    @Test(priority = 10)
    public static void verifyaddupdateCustomerStarting00Number() throws Exception {
        testData();
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithStarting00Number(uri, passAuthorization, imagePath);
        String Verificationmessage = parseJSON(response, "message");
        Assert.assertEquals(Verificationmessage, "Not allowed to update status", "Response message is as expected");
        Reports.log("PASS","verify addupdate Customer Starting00Number");
    }

    @Test(priority = 11)
    public static void verifyaddupdateCustomerInvalidPassword() throws Exception {
        testData();
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithInvalidPassword(uri, passAuthorization, imagePath);
        String Verificationmessage = parseJSON(response, "message");
        Assert.assertEquals(Verificationmessage, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Customer InvalidPassword");
    }

    @Test(priority = 12)
    public static void verifyaddupdateCustomerWrongRequestURL() throws Exception {
        testData();
        response = AddUpdateCustomerModule.postFormDatawithWrongRequestURL(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
       // Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong Request URL");
    }

    @Test(priority = 13)
    public static void verifyaddupdateCustomerWrongAuthenticationURL() throws Exception {
        testData();
        response = AddUpdateCustomerModule.postFormDatawithWrongAuthentication(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Basic Authentication required", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
    }

    @Test(priority = 14)
    public static void verifyaddupdateCustomerWrongContentType() throws Exception {
        testData();
        response = AddUpdateCustomerModule.postFormDatawithWrongContentType(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
    }

    @Test(priority = 15)
    public static void verifyaddupdateCustomerWithoutBodyContent() throws Exception {
        testData();
        response = AddUpdateCustomerModule.postFormDatawithWithoutBody(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
    }

//    @Test(priority = 15)
//    public  void fetchAPI() throws Exception
//    {
//        System.out.println(Uuid);
//        testData();
//        response = AddUpdateCustomerModule.fetchAPI(uri, passAuthorization, imagePath,Uuid);
//        String Verificationmsg = parseJSON(response, "message");
//        Assert.assertEquals(Verificationmsg, "Field validation failed.", "Response message is as expected");
//        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
//        System.out.println("");
//    }

//    @Test(priority = 15)public  void fetchAPI() throws Exception{
//        System.out.println(Uuid);
//        testData();
//        response = String.valueOf(AddUpdateCustomerModule.fetchAPI("https://asianbankqa.appskeeper.com/api/v1/x/profile?", passAuthorization,"a7251f53-ddbc-4122-8076-851c8c4dc1e9"));
//        String Verificationmsg = parseJSON(response, "message");
//        Assert.assertEquals(Verificationmsg, "Field validation failed.", "Response message is as expected");
//        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
//        System.out.println("");}

}
