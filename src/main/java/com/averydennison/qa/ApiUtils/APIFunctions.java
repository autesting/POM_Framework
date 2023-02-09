package com.averydennison.qa.ApiUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.testng.Assert;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class APIFunctions {

	Utilities factPOST = new Utilities();
	JSONHandler jsonH = new JSONHandler();
	Utilities utilities = new Utilities();
	JSONHandler jsonhandler = new JSONHandler();
	String idfromresponse = null;
	public Boolean isConnected = false;
	private Connection dbConection = null;

	public APIFunctions() {
	}

	public String getSessionToken(String env, Map<String, String> headers) {
		String loginURL = env + "/Account/Logon";

		Response response = factPOST.getJFact(loginURL, headers);
		String aspSession = response.getDetailedCookie("ASP.NET_SessionId").toString();
		return aspSession;
	}

	public String userLogin(String payload, String TCID, String env, Map<String, String> headers) {

		String loginURL = env + "/Account/Logon";

		JsonNode credsRequestpayload = jsonH.mapJSONPayloadFromFile(TCID, payload);
		String credsString = credsRequestpayload.toString();
		Response loginResponse = factPOST.postJFact(loginURL, headers, credsString);
		String aspAuth = loginResponse.getDetailedCookie(".ASPXAUTH").toString();
		return aspAuth;
	}

	public String integrationLog(String payload, String TCID, String env, Map<String, String> headers)
			throws IOException {
		String getDashboard = env + "/Monitoring/IntegrationLog/Data";

		JsonNode clientSearchPayload = jsonH.mapJSONPayloadFromFile(TCID, payload);
		String clientSearchString = clientSearchPayload.toString();

		Response clientSearchResponse = factPOST.postJFact(getDashboard, headers, clientSearchString);
		String body = clientSearchResponse.getBody().asString();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(body);
		String pageNumber = node.get("page").toString();
		return pageNumber;
	}

	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
		}

		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
		}

		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[] {};
		}
	} };

	public okhttp3.Response postJFactXml(String Baseurl, String payload, String Authorization)
			throws IOException, NoSuchAlgorithmException, KeyManagementException {
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
		newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
		newBuilder.hostnameVerifier((hostname, session) -> true);
		OkHttpClient client = newBuilder.build();
		MediaType mediaType = MediaType.parse("application/xml");
		RequestBody body = RequestBody.create(mediaType, payload);
		Request request = new Request.Builder().url(Baseurl).method("POST", body)
				.addHeader("Authorization", "Basic " + Authorization).addHeader("Content-Type", "application/xml")
				.build();
		okhttp3.Response response = client.newCall(request).execute();

		return response;
	}

	public String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			return stringBuilder.toString();
		} finally {
			reader.close();
		}
	}

	public String findAndRetrieve(String response, String needle, int freq) {
		int i = response.indexOf(needle);

		i = i + needle.length();

		if (response.charAt(i) == '=') {
			int initialPosition = i + 1;
			int j = 0;
			for (j = i + 2; response.charAt(j) != '\"'; j++)
				;
			int finalPosition = j;

			if (freq > 1) {
				return findAndRetrieve(response.substring(finalPosition + 1), needle, --freq);
			}

			return response.substring(initialPosition, (finalPosition + 1));
		}

		return null;
	}

	public String getXmlValue(String response, String xmlpath, int freq)
			throws IOException, ParserConfigurationException, SAXException, TransformerException,
			KeyManagementException, NoSuchAlgorithmException {

		String resvalue = null;

		if (response != null) {
			resvalue = findAndRetrieve(response, xmlpath, freq);
		}
		return resvalue;
	}

	public String getXmlResponse(Response response) throws IOException, ParserConfigurationException,
			SAXException, TransformerException, KeyManagementException, NoSuchAlgorithmException {

		String responseString = null;
		if (response.body() != null) {
			//responseString = new String(response.body().bytes());
		}
		return responseString;
	}

	public String obfuscatePayload(String payload) {
		byte[] encodedCreds = Base64.encodeBase64(payload.getBytes());
		Charset ascii = Charset.forName("US-ASCII");
		String encodedString = new String(encodedCreds, ascii);
		StringBuilder revEncString = new StringBuilder();
		revEncString.append(encodedString);
		// revEncString.reverse();
		// String requestBody = '"' + String.valueOf(revEncString) + '"';
		String requestBody = String.valueOf(revEncString);
		return requestBody;
	}

	public JsonNode mapJSONPayloadFromFile(String chosenTCID, String chosenValueFromMainJSON) {
		////// LOGGER.debug(String.format("JSONHandler -> mapping JSONPayloadFromFile -
		////// chosenTCID - %s; chosenValueFromMainJSON - %s", chosenTCID,
		////// chosenValueFromMainJSON));

		try {
			String JSONTestDataFolderPath = "src/test/java/testData/jsonTestData/";
			String mainJSONFilePath = JSONTestDataFolderPath + "MainJson.json";
			InputStream getLocalJsonFile = new FileInputStream(mainJSONFilePath);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(getLocalJsonFile);
			JsonNode SetTCID = node.path(chosenTCID);
			JsonNode ChosenValuePayload = SetTCID.path(chosenValueFromMainJSON);
			JsonNode JSONPayload = null;
			if (ChosenValuePayload.toString().contains("tdpl")) {
				////// LOGGER.debug(String.format("ChosenValuePayload - %s contains tdpl",
				////// ChosenValuePayload));
				InputStream getJSONPayload = new FileInputStream(JSONTestDataFolderPath + chosenTCID + "/tdpl-"
						+ chosenTCID + "-" + chosenValueFromMainJSON + ".xml");
				JSONPayload = mapper.readTree(getJSONPayload);
			} else if (ChosenValuePayload.toString().isEmpty()) {
				////// LOGGER.debug("This is not a valid Path in MainJSON " +
				////// chosenValueFromMainJSON);
			} else {
				////// LOGGER.debug("This is just a String Value " + ChosenValuePayload);
			}

			return JSONPayload;
		} catch (IOException var12) {
			////// LOGGER.error("Getting Input Exception", var12);
			return null;
		}
	}

	public String readFile(String folder, String filepath) throws IOException {
		String path = "src/test/java/testData/jsonTestData/" + folder;
		BufferedReader reader = new BufferedReader(new FileReader(path + "/" + filepath + ".txt"));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			return stringBuilder.toString().trim();
		} finally {
			reader.close();
		}
	}

	public void readXMLData(String parentnode, String Attributt_Name) {
		// get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Get Document
			Document document = builder.parse(new File("src/test/java/testData/jsonTestData/VC_REG_TC_001/Test.xml"));
			// Normalize xml Structure
			document.getDocumentElement().normalize();
			// Get all the Element By tagName
			NodeList keylist = document.getElementsByTagName(parentnode);
			for (int i = 0; i < keylist.getLength(); i++) {
				Node keys = keylist.item(i);

				if (keys.getNodeType() == Node.ELEMENT_NODE) {
					Element KeysElement = (Element) keys;
					System.out.println(Attributt_Name + " :" + KeysElement.getAttribute(Attributt_Name));
					NodeList Keysdetails = keys.getChildNodes();
					for (int j = 0; j < Keysdetails.getLength(); j++) {
						Node details = Keysdetails.item(j);
						if (details.getNodeType() == Node.ELEMENT_NODE) {
							Element detailselement = (Element) details;
							// System.out.println(" " + detailselement.getTagName() + " : " +
							// detailselement.getTextContent());
						}
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public String getXMLTagname(String file, String TageName)
			throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();
		// Get Document
		Document document = builder.parse(new File("src/test/java/testData/jsonTestData/" + file));
		// Normalize xml Structure
		document.getDocumentElement().normalize();
		// Get all the Element By tagName
		NodeList keylist = document.getElementsByTagName(TageName);
		Node keys = keylist.item(0);
		Element KeysElement = null;
		if (keys.getNodeType() == Node.ELEMENT_NODE) {
			KeysElement = (Element) keys;
			// System.out.println(TageName + " :" + KeysElement.getTextContent());
		}
		return KeysElement.getTextContent();
	}

	public String getXMLTagnameResponse(String response, String TageName)
			throws SAXException, IOException, ParserConfigurationException {

		Document doc = convertStringToXMLDocument(response);
		NodeList keylist = doc.getElementsByTagName(TageName);
		Node keys = keylist.item(0);
		Element KeysElement = null;
		if (keys.getNodeType() == Node.ELEMENT_NODE) {
			KeysElement = (Element) keys;
			// System.out.println(TageName + " :" + KeysElement.getTextContent());
		}
		return KeysElement.getTextContent();
	}

	public String getCurrentUtcTimeJoda() throws ParseException {
		Date dateTime = null;
		try {
			// DateTime now = new Date(); // Gives the default time zone.
			// dateTime = now.toDateTime(DateTimeParser.UTC); // Converting default zone to
			// UTC
		} catch (Exception e) {
			////// LOGGER.error(UID + e.getMessage());
			////// report.//////reportStep("getCurrentUtcTimeJoda", "Fail", "Found issue
			////// with getCurrentUtcTimeJoda");
			Assert.fail(" Found issue with getCurrentUtcTimeJoda hence failing the test case");
		}
		return dateTime.toString();
	}

	public void replaceXMLTagname(String file, String TageName, String newvalue)
			throws SAXException, IOException, ParserConfigurationException, TransformerException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();
		// Get Document
		Document document = builder.parse(new File("src/test/java/testData/jsonTestData/" + file));
		// Normalize xml Structure
		document.getDocumentElement().normalize();
		// Get all the Element By tagName
		NodeList keylist = document.getElementsByTagName(TageName);
		Node keys = keylist.item(0);
		Element KeysElement = null;
		if (keys.getNodeType() == Node.ELEMENT_NODE) {
			KeysElement = (Element) keys;
			KeysElement.setTextContent(newvalue);
		}
		// System.out.println(TageName + " :" + KeysElement.getTextContent());
		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(document);

		StreamResult streamResult = new StreamResult(
				new File("src/test/java/testData/jsonTestData/commonPayloads/dynamicxmlpayload.xml"));
		transformer.transform(domSource, streamResult);
	}

	public void replaceXMLChildValue(String fileName, String parentTagName, String childTagName, String value)
			throws IOException, SAXException, ParserConfigurationException, TransformerException,
			XPathExpressionException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/test/java/testData/jsonTestData/" + fileName));
		document.getDocumentElement().normalize();
		NodeList keylist = document.getElementsByTagName(parentTagName);
		Node keys = keylist.item(0);
		Element KeysElement = null;
		if (keys.getNodeType() == Node.ELEMENT_NODE) {
			KeysElement = (Element) keys;
			KeysElement.getElementsByTagName(childTagName).item(0).setTextContent(value);
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(document);

		StreamResult streamResult = new StreamResult(
				new File("src/test/java/testData/jsonTestData/commonPayloads/dynamicxmlpayload.xml"));
		transformer.transform(domSource, streamResult);
	}

	// Get XML element value by passing String and Element name
	public String getXMLElementValue(String payload, String tagName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			Document doc = convertStringToXMLDocument(payload);
			NodeList nodeList = doc.getElementsByTagName(tagName);
			if (nodeList != null && nodeList.getLength() > 0) {
				NodeList subList = nodeList.item(0).getChildNodes();

				if (subList != null && subList.getLength() > 0) {
					return subList.item(0).getNodeValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, String> getXMLAttributeValueList(String payload, String tagName, String keyname,
			String valuename) {
		String attribute = "";
		Map<String, String> map = new HashMap<String, String>();
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = convertStringToXMLDocument(payload);

			NodeList nodeList = doc.getElementsByTagName(tagName);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = (Node) nodeList.item(i);

				if (node.hasAttributes()) {
					Attr key = (Attr) node.getAttributes().getNamedItem(keyname);
					Attr value = (Attr) node.getAttributes().getNamedItem(valuename);
					if (key != null && value != null) {
						map.put(key.getValue(), value.getValue());
						////// LOGGER.debug("KEY :" + key.getValue());
						////// LOGGER.debug("Value :" + value.getValue());

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//////// LOGGER.debug(UID + e.getMessage());
		}
		return map;

	}

	// Convert string to XML document
	public Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes())));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Get XML attribute value by passing String and Element name

	public String getXMLAttributeValue(String payload, String tagName, String attributeName) {
		String attribute = "";
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = convertStringToXMLDocument(payload);
			NodeList nodeList = doc.getElementsByTagName(tagName);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = (Node) nodeList.item(i);
				if (node.hasAttributes()) {
					Attr attr = (Attr) node.getAttributes().getNamedItem(attributeName);
					if (attr != null) {
						attribute = attr.getValue();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// //////LOGGER.debug(UID + e.getMessage());
		}
		return attribute;

	}

	public void replaceXMLAttributeValue(String file, String tagName, String attributeName, String Newname)
			throws TransformerException {
		String attribute = "";
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			// Get Document
			Document document = builder.parse(new File("src/test/java/testData/jsonTestData/" + file));
			// Normalize xml Structure
			document.getDocumentElement().normalize();
			NodeList nodeList = document.getElementsByTagName(tagName);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = (Node) nodeList.item(i);
				if (node.hasAttributes()) {
					Attr attr = (Attr) node.getAttributes().getNamedItem(attributeName);
					attr.setValue(Newname);
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(
					new File("src/test/java/testData/jsonTestData/commonPayloads/dynamicxmlpayload.xml"));
			transformer.transform(domSource, streamResult);
		} catch (Exception e) {
			e.printStackTrace();
			// //////LOGGER.debug(UID + e.getMessage());
		}

	}

	public void replaceXMLAttributeValueList(String file, String tagName, String keyname, String valuename,
											 String valuetochange, String Newvalue) {
		String attribute = "";
		Map<String, String> map = new HashMap<String, String>();
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			// Get Document
			Document document = builder.parse(new File("src/test/java/testData/jsonTestData/" + file));
			// Normalize xml Structure
			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName(tagName);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = (Node) nodeList.item(i);

				if (node.hasAttributes()) {
					Attr key = (Attr) node.getAttributes().getNamedItem(keyname);
					Attr value = (Attr) node.getAttributes().getNamedItem(valuename);
					if (key.getValue().contains(valuetochange)) {
						value.setValue(Newvalue);
					}
					if (key != null && value != null) {
						map.put(key.getValue(), value.getValue());
						////// LOGGER.debug("KEY :" + key.getValue());
						////// LOGGER.debug("Value :" + value.getValue());

					}
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(
					new File("src/test/java/testData/jsonTestData/commonPayloads/dynamicxmlpayload.xml"));
			transformer.transform(domSource, streamResult);

		} catch (Exception e) {
			e.printStackTrace();
			//////// LOGGER.debug(UID + e.getMessage());
		}

	}

	public String getRandomString(int length) {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = length;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		// generatedString =prefix+generatedString;
		return generatedString;
		// System.out.println(generatedString);
	}

	public static Integer generateRandomNumber(int length) {
		int min = (int) Math.pow(10, length - 1);
		int max = (int) Math.pow(10, length); // bound is exclusive
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	public String apiGetcall(String baseURI, Map<String, String> headers) {

		// add code if baseURI is null or empty
		String res = null;
		try {
			RestAssured.baseURI = baseURI;
			RestAssured.defaultParser = Parser.JSON;
			RequestSpecification request = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON)
					.urlEncodingEnabled(false);
			// RequestSpecification request =
			// RestAssured.given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/form-data",
			// ContentType.JSON)));
			if (!headers.isEmpty()) {
				headers.entrySet().stream().forEach(e -> request.header(e.getKey(), e.getValue()));
			}
			Response response = request.get(baseURI);
			if (!response.getBody().asString().isEmpty()) {
				res = response.asString();
			} else {
				return res;
			}
			return res;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	public static String toXmlString(String file)
			throws TransformerException, ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/test/java/testData/jsonTestData/" + file));
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StringWriter strWriter = new StringWriter();
		StreamResult result = new StreamResult(strWriter);

		transformer.transform(source, result);

		return strWriter.getBuffer().toString();

	}

	public String postJFactFormDataold(String baseURI, Map<String, String> headers,
									   Map<String, String> credsRequestBody) {

		// add code if baseURI is null or empty
		String res = null;
		try {
			RestAssured.baseURI = baseURI;
			RestAssured.defaultParser = Parser.JSON;
			RequestSpecification request = RestAssured.given().contentType("application/form-data");
			if (!headers.isEmpty()) {
				headers.entrySet().stream().forEach(e -> request.header(e.getKey(), e.getValue()));
			}
			request.header("Content-Type", "application/form-data");
			// JSONObject jsonObject = new JSONObject(credsRequestBody);
			// for(String key : jsonObject.keySet()) {
			request.formParams(credsRequestBody);
			// }
			// request.multiPart(credsRequestBody);
			Response response = request.post(baseURI);
			if (!response.getBody().asString().isEmpty()) {
				res = response.asString();
			} else {
				return res;
			}
			return res;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	public String formdatatoJson(String filePath) throws FileNotFoundException {
		File myObj = new File(filePath);
		Scanner myReader = new Scanner(myObj);
		JSONObject object = new JSONObject();
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] keyValuePair = data.split(":");
			if (keyValuePair.length > 1) {
				object.put(keyValuePair[0].trim(), keyValuePair[1].trim());
			}
		}
		myReader.close();
		System.out.println(object.toString());
		return object.toString();
	}

	public Map<String, String> parseAndConvertToMap(String filePath) throws FileNotFoundException {
		File myObj = new File(filePath);
		Scanner myReader = new Scanner(myObj);
		Map<String, String> formData = new HashMap<String, String>();
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] keyValuePair = data.split(":");

			if (keyValuePair.length > 1) {
				formData.put(keyValuePair[0], keyValuePair[1]);
			} else if (keyValuePair.length == 1) {
				formData.put(keyValuePair[0], "");
			}

		}
		myReader.close();

		return formData;
	}

	public Map<String, String> uniqueparseAndConvertToMap(String filePath) throws FileNotFoundException {
		File myObj = new File(filePath);
		Scanner myReader = new Scanner(myObj);
		Map<String, String> formData = new HashMap<String, String>();
		String[] keyValuePair = null;
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			if (data.contains("RoleRights")) {
				keyValuePair = data.split("&*^%$@");
			} else {
				keyValuePair = data.split(":");
			}
			if (keyValuePair.length > 1) {
				formData.put(keyValuePair[0], keyValuePair[1]);
			} else if (keyValuePair.length == 1) {
				formData.put(keyValuePair[0], "");
			}
		}
		myReader.close();
		return formData;
	}

	@SuppressWarnings("deprecation")
	public String postJFactFormData(String baseURI, Map<String, String> headers, Map<String, String> credsRequestBody,
									Map<String, String> credsRequestBody1) throws IOException {

		OkHttpClient client = new OkHttpClient().newBuilder().build();

		MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM);

		for (Map.Entry<String, String> cred : credsRequestBody1.entrySet()) {
			MultipartBody.create(MediaType.parse("application/octet-stream"),
					new File("/D:/Automation/Framework/Resources/TestData/UploadImage.png"));
			body = new MultipartBody.Builder().setType(MultipartBody.FORM);
			body.addFormDataPart(cred.getKey(), cred.getValue());

		}

		for (Map.Entry<String, String> cred : credsRequestBody.entrySet()) {
			body.addFormDataPart(cred.getKey(), cred.getValue());

		}

		RequestBody finalBody = body.build();
		Request.Builder builder = new Request.Builder();
		builder.url(baseURI).method("POST", finalBody);
		for (Map.Entry header : headers.entrySet()) {
			builder.addHeader((String) header.getKey(), (String) header.getValue());
		}
		Request request = builder.build();
		okhttp3.Response response = client.newCall(request).execute();
		return response.body().string();
	}

	public String deletedata(String filePath) throws FileNotFoundException {
		File myObj = new File(filePath);
		Scanner myReader = new Scanner(myObj);
		JSONObject object = new JSONObject();
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] keyValuePair = data.split(":");
			if (keyValuePair.length > 1) {
				object.put(keyValuePair[0].trim(), keyValuePair[1].trim());
			}
		}
		myReader.close();
		System.out.println("inputstream");
		return object.toString();
	}

	public String apiDeletecall(String baseURI, Map<String, String> headers) {

		// add code if baseURI is null or empty
		String res = null;
		try {
			RestAssured.baseURI = baseURI;
			RestAssured.defaultParser = Parser.JSON;
			RequestSpecification request = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON)
					.urlEncodingEnabled(false);
			// RequestSpecification request =
			// RestAssured.given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/form-data",
			// ContentType.JSON)));
			if (!headers.isEmpty()) {
				headers.entrySet().stream().forEach(e -> request.header(e.getKey(), e.getValue()));
			}
			Response response = request.delete(baseURI);
			if (!response.getBody().asString().isEmpty()) {
				res = response.asString();
			} else {
				return res;
			}
			return res;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

//	public String getHTMLResponseDetails(String Response, String tagname)
//			throws IOException, SAXException, ParserConfigurationException {
//		// String body = Response.getBody().asString();
//		org.jsoup.nodes.Document doc = Jsoup.parse(Response);
//		org.jsoup.nodes.Element result = doc.selectFirst(tagname);
//		String htmlres = result.toString();
//		return htmlres;
//	}

	public String getResponseDetails(String basrURI, Map<String, String> headersmap, String payload) {
		try {

			idfromresponse = postJFact(basrURI, headersmap, payload, 0);
			////// LOGGER.debug(UID + " Package ID from Response " + idfromresponse);
			if (!idfromresponse.isEmpty() || idfromresponse == null) {
				////// LOGGER.info("received the response for Get Package Details request");
			} else {
				////// LOGGER.error(UID + "Response for Get Package Details was null");
				////// report.//////reportStep("Step10", "Get Response Details", "Fail",
				////// "Response for Get Package Details was null");
				Assert.fail(
						"Response was null for Get Response Details to package request, Failing the test case createPackageHybrid_");
			}
		} catch (Exception e) {
			////// LOGGER.error(UID + e.getMessage());
			////// report.//////reportStep("Step10", "Get Response Details", "Fail",
			////// "Response for Get Package Details was null");
			Assert.fail(
					"Response was null for Get Response Details to package request, Failing the test case createPackageHybrid_");
		}
		return idfromresponse;
	}

	public String postJFactEncode(String baseURI, Map<String, String> headers, String credsRequestBody) {

		// add code if baseURI is null or empty
		String res = null;
		try {
			RestAssured.baseURI = baseURI;
			RestAssured.defaultParser = Parser.JSON;
			// RequestSpecification request =
			// RestAssured.given().contentType(ContentType.JSON);
			RequestSpecification request = RestAssured.given().config(RestAssured.config()
					.encoderConfig(encoderConfig().encodeContentTypeAs("application/form-data", ContentType.JSON)));
			if (!headers.isEmpty()) {
				headers.entrySet().stream().forEach(e -> request.header(e.getKey(), e.getValue()));
			}
			request.body(credsRequestBody);
			Response response = request.post(baseURI);
			if (!response.getBody().asString().isEmpty()) {
				res = response.asString();
			} else {
				return res;
			}
			return res;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	public String apiPostcall(String baseURI, Map<String, String> headers, String credsRequestBody) {

		// add code if baseURI is null or empty
		String res = null;
		try {
			RestAssured.baseURI = baseURI;
			RestAssured.defaultParser = Parser.JSON;
			RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

			if (!headers.isEmpty()) {
				headers.entrySet().stream().forEach(e -> request.header(e.getKey(), e.getValue()));
			}
			request.body(credsRequestBody);
			Response response = request.post(baseURI);
			if (!response.getBody().asString().isEmpty()) {
				res = response.asString();
			} else {
				return res;
			}
			return res;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	public okhttp3.Response RGpostJFact(String baseURI, String RGpayload, String Authentication)
			throws NoSuchAlgorithmException, KeyManagementException, IOException {
		// add code if baseURI is null or empty
		String res = null;
		/* try { */
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
		newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
		newBuilder.hostnameVerifier((hostname, session) -> true);
		OkHttpClient client = newBuilder.build();

		RestAssured.baseURI = baseURI;
		RestAssured.defaultParser = Parser.JSON;
		RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, RGpayload);

		Request request1 = new Request.Builder().url(baseURI).method("POST", body)
				.addHeader("Authorization", "Basic " + Authentication).addHeader("Content-Type", "application/json")
				.build();
		okhttp3.Response response = client.newCall(request1).execute();
		return response;

		/*
		 * if(!response.body().toString().isEmpty()){ res = response.toString(); } else
		 * { return res; } return res; } catch (Exception e) {
		 * System.out.println(e.getMessage()); return e.getMessage(); }
		 */
	}

	public String apiPutcall(String baseURI, Map<String, String> headers, String credsRequestBody) {

		// add code if baseURI is null or empty
		String res = null;
		try {
			RestAssured.baseURI = baseURI;
			RestAssured.defaultParser = Parser.JSON;
			RequestSpecification request = RestAssured.given().config(RestAssured.config()
					.encoderConfig(encoderConfig().encodeContentTypeAs("application/form-data", ContentType.JSON)));

			if (!headers.isEmpty()) {
				headers.entrySet().stream().forEach(e -> request.header(e.getKey(), e.getValue()));
			}
			request.body(credsRequestBody);
			Response response = request.put(baseURI);
			if (!response.getBody().asString().isEmpty()) {
				res = response.asString();
			} else {
				return res;
			}
			return res;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	public String postJFact(String baseURI, Map<String, String> headers, String credsRequestBody, int time) {

		// add code if baseURI is null or empty
		String res = null;
		try {
			RestAssured.baseURI = baseURI;
			RestAssured.defaultParser = Parser.JSON;
			RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
			if (!headers.isEmpty()) {
				headers.entrySet().stream().forEach(e -> request.header(e.getKey(), e.getValue()));
			}
			request.body(credsRequestBody);
			Thread.sleep(time);
			Response response = request.post(baseURI);
			if (!response.getBody().asString().isEmpty()) {
				res = response.asString();
			} else {
				return res;
			}
			return res;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	public JsonNode replaceValueArrayNode(JsonNode targetNode, String fieldValue, int arrayIndex, JsonNode newvalue) {
		////// LOGGER.debug(String.format("JSONHandler -> Replace Json Value -> Current
		////// JSON Value:", targetNode));
		JsonNode modNode = targetNode.path(fieldValue);
		((ArrayNode) modNode).set(arrayIndex, newvalue);
		////// LOGGER.debug(String.format("JSONHandler -> Replace Json Value -> Updated
		////// JSON Value:", modNode));
		return modNode;
	}

	public JsonNode replaceValueArrayNode(JsonNode targetNode, int arrayIndex, JsonNode newvalue) {
		////// LOGGER.debug(String.format("JSONHandler -> Replace Json Value -> Current
		////// JSON Value:", targetNode));
		JsonNode modNode = targetNode;
		((ArrayNode) modNode).set(arrayIndex, newvalue);
		////// LOGGER.debug(String.format("JSONHandler -> Replace Json Value -> Updated
		////// JSON Value:", modNode));
		return modNode;
	}

	public String getRandomString(String prefix, int length) {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		generatedString = prefix + generatedString;
		return generatedString;
		// System.out.println(generatedString);
	}

	public String getRandomMailID(String prefix, int length, String suffix) {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		generatedString = "SSS_" + generatedString + "@gmail.com";
		return generatedString;
		// System.out.println(generatedString);
	}

	public static String getDate(String value) {

		BigInteger bi1 = new BigInteger(value);

		long longval = bi1.longValue();

		long milliSec = longval;

		// Creating date format
		DateFormat simple = new SimpleDateFormat("yyyy/MM/dd h:mm aa");

		// Creating date from milliseconds
		// using Date() constructor
		Date result = new Date(milliSec);

		// Formatting Date according to the
		// given format
		return simple.format(result);
	}

	// DB Connection by Connection string
	public void DBConnect(String ConnectionString) {
		String connectionUrl = ConnectionString;
		try {
			dbConection = DriverManager.getConnection(connectionUrl);
			isConnected = true;
			////// LOGGER.debug("DB Connection successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DB Connection by passing Server , UID, PWD, DB name and port number
	/*
	 * public void DBConnect(String server, String UserName, String password, String
	 * databaseName, Integer portNumber) { isConnected = false; try {
	 * SQLServerDataSource ds = new SQLServerDataSource(); ds.setUser(server);
	 * ds.setPassword(UserName); ds.setServerName(password);
	 * ds.setDatabaseName(databaseName); ds.setTrustServerCertificate(true);
	 * ds.setPortNumber(portNumber);
	 *
	 * dbConection = ds.getConnection();
	 * //////LOGGER.debug("DB Connection successful"); System.out.println("Yes");
	 * isConnected = true;
	 *
	 * } catch (SQLException e) { e.printStackTrace();
	 * System.out.println(e.getMessage()); }
	 *
	 * }
	 */

	// Return SQL resultset by passing SQL statement as parameter
	public ResultSet getSqlRecordSet(String sqlStatement) {
		ResultSet rs = null;

		try {
			if (dbConection != null) {
				Statement stmt = dbConection.createStatement();
				rs = stmt.executeQuery(sqlStatement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
