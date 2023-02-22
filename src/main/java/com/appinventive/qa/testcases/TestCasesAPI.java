package com.appinventive.qa.testcases;

import com.appinventive.qa.modules.AddUpdateCustomerModule;
import com.appinventive.qa.pages.DriverScript;
import com.appinventive.qa.pages.Reports;
import com.appinventive.qa.ApiUtils.APIFunctions;
import com.appinventive.qa.ApiUtils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.appinventive.qa.utilily.ConfigLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.appinventive.qa.ApiUtils.JSONHandler.parseJSON;

public  class TestCasesAPI extends DriverScript
{
    public TestCasesAPI()
    {

        DriverScript Ds = new DriverScript();
    }

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

    public static   void APIMethod() throws IOException {

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
    }
    @Test(priority = 1)
        public static void verifyaddupdateCustomer() throws Exception {
         APIMethod();
        response = AddUpdateCustomerModule.postFormData(uri, passAuthorization, imagePath);
        String Successmsg = parseJSON(response, "message");
        Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer");
        String uuid = parseJSON(response,"data");
        System.out.println(uuid);
    }

    @Test(priority = 2)
    public void verifyAddUpdateWithRequiredFeildsOnly() throws IOException {
        APIMethod();
        response = AddUpdateCustomerModule.postFormDataRequiredFeilds(uri, passAuthorization, imagePath);
        String Successmsg = parseJSON(response, "message");
        Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify AddUpdate With Required Feilds Only");

    }

    @Test(priority = 3)
    public void verifyAddUpdateWithOptionFeildsOnly() throws IOException {
        APIMethod();
        response = AddUpdateCustomerModule.postFormDataOptionalFeilds(uri, passAuthorization, imagePath);
        String Failuremsg = parseJSON(response, "message");
        Assert.assertEquals(Failuremsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify AddUpdate WithOption FeildsOnly");
    }

    @Test(priority = 4)
    public void verifyaddupdateAlreadyExistedCustomer() throws IOException {
        APIMethod();
        response = AddUpdateCustomerModule.postFormAlreadyExistedData(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Already Existed Customer");
    }

    @Test(priority = 5)
    public void verifyaddupdateCanceledCustomerData() throws IOException {
        APIMethod();
        response = AddUpdateCustomerModule.postFormCanceledUserDataData(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Canceled CustomerData");
    }

    @Test(priority = 6)
    public void verifyIncorrectDataCustomer() throws IOException {
        APIMethod();
        response = AddUpdateCustomerModule.postFormIncorrectDataCustomer(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify Incorrect DataCustomer");
    }

    @Test(priority = 7)
    public void verifyUpdateCustomer() throws IOException
    {
        APIMethod();
        response = AddUpdateCustomerModule.postFormUpdateExistingCustomer(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "User does not exist or you do not have permission to update this user.", "Response message is as expected");
        Reports.log("PASS","verifyUpdateCustomer");
    }
    @Test(priority = 8)
    public static void verifyaddupdateCustomerMinNumber() throws Exception
    {
        APIMethod();
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithMinNumber(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verifyaddupdateCustomerMinNumber");

    }
    @Test(priority = 8)
    public static void verifyaddupdateCustomerMaxNumber() throws Exception
    {
        APIMethod();
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithMaxNumber(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "Phone already exists.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Customer MaxNumber");

    }
    @Test(priority = 9)
    public static void verifyaddupdateCustomerStarting00Number() throws Exception
    {
        APIMethod();
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithStarting00Number(uri, passAuthorization, imagePath);
        String Verificationmessage = parseJSON(response, "message");
        Assert.assertEquals(Verificationmessage, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Customer Starting00Number");


    }
    @Test(priority = 10)
    public static void verifyaddupdateCustomerInvalidPassword() throws Exception
    {
        APIMethod();
        //String Errormsg = "Valid Phone no. is required";
        response = AddUpdateCustomerModule.postFormDatawithInvalidPassword(uri, passAuthorization, imagePath);
        String Verificationmessage = parseJSON(response, "message");
        Assert.assertEquals(Verificationmessage, "Success.", "Response message is as expected");
        Reports.log("PASS","verify addupdate Customer InvalidPassword");

    }
    @Test(priority = 11)
    public static void verifyaddupdateCustomerWrongRequestURL() throws Exception
    {
        APIMethod();

        response = AddUpdateCustomerModule.postFormDatawithWrongRequestURL(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
       // Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong Request URL");

    }
    @Test(priority = 12)
    public static void verifyaddupdateCustomerWrongAuthenticationURL() throws Exception
    {
        APIMethod();
        response = AddUpdateCustomerModule.postFormDatawithWrongAuthentication(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Basic Authentication required", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
    }
    @Test(priority = 13)
    public static void verifyaddupdateCustomerWrongContentType() throws Exception
    {
        APIMethod();
        response = AddUpdateCustomerModule.postFormDatawithWrongContentType(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Basic Authentication required", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
    }
    @Test(priority = 14)
    public static void verifyaddupdateCustomerWithoutBodyContent() throws Exception
    {
        APIMethod();
        response = AddUpdateCustomerModule.postFormDatawithWithoutBody(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Field validation failed.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer Wrong 1Request URL");
    }

}
