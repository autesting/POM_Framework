package scripts;

import com.averydennison.qa.ApiUtils.APIFunctions;
import com.averydennison.qa.ApiUtils.Constants;
import com.averydennison.qa.modules.AddUpdateCustomerModule;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilily.ConfigLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.averydennison.qa.ApiUtils.JSONHandler.parseJSON;

public class TestCasesAPI extends AddUpdateCustomerModule
{
    public String Baseurl = "https://reqres.in";
    APIFunctions API = new APIFunctions();
    public Map<String, String> headers = new HashMap<>();
    public String uri;
    public String filepath;
    public String requestURI;
    public String selectImagePath;

    @Test(priority = 1)
        public void verifyaddupdateCustomer() throws IOException {
        String response = null;
        filepath = "src/main/resources/config/api/registration-qa.json";
        requestURI = "/api/v1/x/user";
        selectImagePath = "\\src\\main\\resources\\TestData\\UploadImage.png";
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadConfigJsonFile(filepath);
        uri = configLoader.getConfigValue(Constants.URI) + requestURI;
        headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
        String passAuthorization = headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        String workingDirectory = new File(".").getCanonicalPath();
        String imagePath = workingDirectory + selectImagePath;
        response = postFormData(uri, passAuthorization, imagePath);
        String Successmsg = parseJSON(response, "message");
        Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");
    }

    @Test(priority = 2)
    public void verifyAddUpdateWithRequiredFeildsOnly() throws IOException {
        String response = null;
        filepath = "src/main/resources/config/api/registration-qa.json";
        requestURI = "/api/v1/x/user";
        selectImagePath = "\\src\\main\\resources\\TestData\\UploadImage.png";
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadConfigJsonFile(filepath);
        uri = configLoader.getConfigValue(Constants.URI) + requestURI;
        headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
        String passAuthorization = headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        String workingDirectory = new File(".").getCanonicalPath();
        String imagePath = workingDirectory + selectImagePath;
        response = postFormDataRequiredFeilds(uri, passAuthorization, imagePath);
        String Successmsg = parseJSON(response, "message");
        Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");

    }

    @Test(priority = 3)
    public void verifyAddUpdateWithOptionFeildsOnly() throws IOException {
        String response = null;
        filepath = "src/main/resources/config/api/registration-qa.json";
        requestURI = "/api/v1/x/user";
        selectImagePath = "\\src\\main\\resources\\TestData\\UploadImage.png";
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadConfigJsonFile(filepath);
        uri = configLoader.getConfigValue(Constants.URI) + requestURI;
        headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
        String passAuthorization = headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        String workingDirectory = new File(".").getCanonicalPath();
        String imagePath = workingDirectory + selectImagePath;
        response = postFormDataOptionalFeilds(uri, passAuthorization, imagePath);
        String Failuremsg = parseJSON(response, "message");
        Assert.assertEquals(Failuremsg, "Field validation failed.", "Response message is as expected");

    }
    @Test(priority = 4)
    public void verifyaddupdateAlreadyExistedCustomer() throws IOException {
        String response = null;
        filepath = "src/main/resources/config/api/registration-qa.json";
        requestURI = "/api/v1/x/user";
        selectImagePath = "\\src\\main\\resources\\TestData\\UploadImage.png";
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadConfigJsonFile(filepath);
        uri = configLoader.getConfigValue(Constants.URI) + requestURI;
        headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
        String passAuthorization = headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        String workingDirectory = new File(".").getCanonicalPath();
        String imagePath = workingDirectory + selectImagePath;
        response = postFormAlreadyExistedData(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Email already exists.", "Response message is as expected");
    }
    @Test(priority = 5)
    public void verifyaddupdateCanceledCustomerData() throws IOException {
        String response = null;
        filepath = "src/main/resources/config/api/registration-qa.json";
        requestURI = "/api/v1/x/user";
        selectImagePath = "\\src\\main\\resources\\TestData\\UploadImage.png";
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadConfigJsonFile(filepath);
        uri = configLoader.getConfigValue(Constants.URI) + requestURI;
        headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
        String passAuthorization = headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        String workingDirectory = new File(".").getCanonicalPath();
        String imagePath = workingDirectory + selectImagePath;
        response = postFormCanceledUserDataData(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
    }
    @Test(priority = 6)
    public void verifyIncorrectDataCustomer() throws IOException {
        String response = null;
        filepath = "src/main/resources/config/api/registration-qa.json";
        requestURI = "/api/v1/x/user";
        selectImagePath = "\\src\\main\\resources\\TestData\\UploadImage.png";
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadConfigJsonFile(filepath);
        uri = configLoader.getConfigValue(Constants.URI) + requestURI;
        headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
        String passAuthorization = headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        String workingDirectory = new File(".").getCanonicalPath();
        String imagePath = workingDirectory + selectImagePath;
        response = postFormIncorrectDataCustomer(uri, passAuthorization, imagePath);
        String Errormsg = parseJSON(response, "message");
        Assert.assertEquals(Errormsg, "Field validation failed.", "Response message is as expected");
    }


    @Test(priority = 7)
    public void verifyUpdateCustomer() throws IOException {
        String response = null;
        filepath = "src/main/resources/config/api/registration-qa.json";
        requestURI = "/api/v1/x/user";
        selectImagePath = "\\src\\main\\resources\\TestData\\UploadImage.png";
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadConfigJsonFile(filepath);
        uri = configLoader.getConfigValue(Constants.URI) + requestURI;
        headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
        String passAuthorization = headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
        String workingDirectory = new File(".").getCanonicalPath();
        String imagePath = workingDirectory + selectImagePath;
        response = postFormUpdateExistingCustomer(uri, passAuthorization, imagePath);
        String Verificationmsg = parseJSON(response, "message");
        Assert.assertEquals(Verificationmsg, "Success.", "Response message is as expected");
    }




}
