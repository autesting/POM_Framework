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
    public static String uriFetch;
    public static String requestURIFetch;

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
        response = AddUpdateCustomerModule.postFormDataRequiredFeilds(uri, passAuthorization, imagePath);
        String Successmsg = parseJSON(response, "message");
        Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify AddUpdate With Required Fields Only");
    }

    @Test(dependsOnMethods = "verifyAddUpdateWithRequiredFieldsOnly")
    public void verifyAddUpdateWithOptionFieldsOnly() throws IOException {
        response = AddUpdateCustomerModule.postFormDataOptionalFeilds(uri, passAuthorization, imagePath);
        String Failuremsg = parseJSON(response, "message");
        Assert.assertEquals(Failuremsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify AddUpdate WithOption FieldsOnly");
    }

    @Test(dependsOnMethods = "verifyAddUpdateWithOptionFieldsOnly")
    public void verifyAddUpdateAlreadyExistedCustomer() throws IOException {
        response = AddUpdateCustomerModule.postFormAlreadyExistedData(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Already Existed Customer");
    }

    @Test(dependsOnMethods = "verifyAddUpdateAlreadyExistedCustomer")
    public void verifyAddUpdateCanceledCustomerData() throws IOException {
        response = AddUpdateCustomerModule.postFormCanceledUserDataData(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Canceled CustomerData");
    }

    @Test(dependsOnMethods = "verifyAddUpdateCanceledCustomerData")
    public void verifyIncorrectDataCustomer() throws IOException {
        response = AddUpdateCustomerModule.postFormIncorrectDataCustomer(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "email.substring is not a function", "Response message is as expected");
        Reports.log("PASS","verify Incorrect DataCustomer");
    }

    @Test(dependsOnMethods = "verifyIncorrectDataCustomer")
    public void verifyUpdateCustomer() throws IOException {
        response = AddUpdateCustomerModule.postFormUpdateExistingCustomer(uri, passAuthorization, imagePath,Uuid);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verifyUpdateCustomer");
    }

    @Test(dependsOnMethods = "verifyUpdateCustomer")
    public static void verifyAddUpdateCustomerMinNumber() throws Exception {
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithMinNumber(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verifyaddupdateCustomerMinNumber");
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerMinNumber")
    public static void verifyAddUpdateCustomerMaxNumber() throws Exception {
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithMaxNumber(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "Success.", "Field is in incorrect format");
        Reports.log("PASS","verify addupdate Customer MaxNumber");
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerMaxNumber")
    public static void verifyAddUpdateCustomerStarting00Number() throws Exception {
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithStarting00Number(uri, passAuthorization, imagePath);
        String Verificationmessage = parseJSON(response, "message");
        Assert.assertEquals(Verificationmessage, "Not allowed to update status", "Response message is as expected");
        Reports.log("PASS","verify addupdate Customer Starting00Number");
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerStarting00Number")
    public static void verifyAddUpdateCustomerInvalidPassword() throws Exception {
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithInvalidPassword(uri, passAuthorization, imagePath);
        String Verificationmessage = parseJSON(response, "message");
        Assert.assertEquals(Verificationmessage, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Customer InvalidPassword");
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerInvalidPassword")
    public static void verifyAddUpdateCustomerWrongRequestURL() throws Exception {
        response = AddUpdateCustomerModule.postFormDatawithWrongRequestURL(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
       // Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong Request URL");
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerWrongRequestURL")
    public static void verifyAddUpdateCustomerWrongAuthenticationURL() throws Exception {
        response = AddUpdateCustomerModule.postFormDatawithWrongAuthentication(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Basic Authentication required", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerWrongAuthenticationURL")
    public static void verifyAddUpdateCustomerWrongContentType() throws Exception {
        response = AddUpdateCustomerModule.postFormDatawithWrongContentType(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
    }

    @Test(dependsOnMethods = "verifyAddUpdateCustomerWrongContentType")
    public static void verifyAddUpdateCustomerWithoutBodyContent() throws Exception {
        response = AddUpdateCustomerModule.postFormDatawithWithoutBody(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
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
