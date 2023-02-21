package scripts;

import com.appinventive.qa.ApiUtils.APIFunctions;
import com.appinventive.qa.ApiUtils.Constants;
import com.appinventive.qa.modules.AddUpdateCustomerModule;
import com.appinventive.qa.pages.DriverScript;
import com.appinventive.qa.pages.Reports;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilily.ConfigLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.appinventive.qa.ApiUtils.JSONHandler.parseJSON;

public  class TestCasesAPI extends DriverScript
{
    public TestCasesAPI(){
     DriverScript Ds = new DriverScript();
    }

    public String Baseurl = "https://reqres.in";
    APIFunctions API = new APIFunctions();
    public Map<String, String> headers = new HashMap<>();
    public String uri;
    public String filepath;
    public String requestURI;
    public String selectImagePath;
    String response = null;
    String passAuthorization;
    String imagePath;

    public  void APIMethod() throws IOException {

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
        public  void verifyaddupdateCustomer() throws IOException {
         APIMethod();
        response = AddUpdateCustomerModule.postFormData(uri, passAuthorization, imagePath);
        String Successmsg = parseJSON(response, "message");
        Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verify add update Customer");
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
        Assert.assertEquals(Verificationmsg, "Email already exists.", "Response message is as expected");
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
    public void verifyUpdateCustomer() throws IOException {
        APIMethod();
        response = AddUpdateCustomerModule.postFormUpdateExistingCustomer(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
        Reports.log("PASS","verifyUpdateCustomer");
    }





}
