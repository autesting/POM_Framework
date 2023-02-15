package scripts;

import utilily.ConfigLoader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.averydennison.qa.ApiUtils.APIFunctions;
import com.averydennison.qa.ApiUtils.Constants;
import com.averydennison.qa.modules.AddUpdateCustomerModule;
//import com.cms.utilily.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.averydennison.qa.ApiUtils.JSONHandler.parseJSON;

public class APIScripts extends AddUpdateCustomerModule {
	 public String Baseurl = "https://reqres.in";
	APIFunctions API = new APIFunctions();
	public Map<String, String> headers = new HashMap<>();
	public String uri;
	public String filepath;
	public String requestURI;
	public String selectImagePath;

	 @Test(priority = 0)
	public void verifyGet() {

		String firstname = "Lindsay";
		String uri = Baseurl + "/api/users?page=2";
		headers.put("Content-Type", "application/json");
		String response = API.apiGetcall(uri, headers);
		String runtimefirstname = parseJSON(response, "data/array-1/first_name");
		Assert.assertEquals(runtimefirstname, firstname);
		if (firstname.contentEquals(runtimefirstname)) {
			System.out.println("Sucessfully verified the first name " + runtimefirstname);
		} else {
			System.out.println("first name verification failed " + runtimefirstname);
		}
	}

	@Test(priority = 0)
	@SuppressWarnings({"unused" })
	public void verifys3signedURL() throws IOException {
		filepath = "src/main/resources/config/api/registration-qa.json";
		requestURI = "/api/v1/x/signed-s3?fileName=file20041648&fileType=Image";
		ConfigLoader configLoader = ConfigLoader.getInstance();
		configLoader.loadConfigJsonFile(filepath);
		uri = configLoader.getConfigValue(Constants.URI) + requestURI;
		headers.put("Authorization", configLoader.getConfigValue(Constants.AUTHORIZATION));
		headers.put("Content-Type", configLoader.getConfigValue(Constants.CONTENT_TYPE));
		String response = API.apiGetcall(uri, headers);
		String Successmsg = parseJSON(response, "message");
		Assert.assertEquals(Successmsg, "Success.", "Response message is as expected");
	}

	//f72dc358-c5b5-4865-adc8-da3daad09965

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

}
