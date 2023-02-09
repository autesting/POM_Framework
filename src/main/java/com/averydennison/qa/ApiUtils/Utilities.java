package com.averydennison.qa.ApiUtils;

import java.io.File;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Utilities {
    RequestSpecification httpRequest;
	

    public JsonPath getValueByJsonPath(String baseURI, Map<String, String> headers, String credsRequestBody) {

        try {
            RestAssured.baseURI = baseURI;
            RestAssured.defaultParser = Parser.JSON;
            RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
            if (!headers.isEmpty()) {
                headers.entrySet().stream().forEach((e) -> {
                    request.header((String) e.getKey(), e.getValue(), new Object[0]);
                });
            }
            request.body(credsRequestBody);
            Response response = (Response) request.post(baseURI, new Object[0]);
            ResponseBody body = response.getBody();
            return response.jsonPath();
        } catch (Exception var7) {
            System.out.println(var7.getMessage());
            return null;
        }
    }


    public Response postJFact(String baseURI, Map<String, String> headers, String credsRequestBody) {
        Response res = null;
        try {
            RestAssured.baseURI = baseURI;
            RestAssured.defaultParser = Parser.JSON;
            RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
            if (!headers.isEmpty()) {
                headers.entrySet().stream().forEach(e ->
                        request.header(e.getKey(), e.getValue())
                );
            }
            request.body(credsRequestBody);
            Response response = request.post(baseURI);
            if (!response.getBody().asString().isEmpty()) {
                //res = response.asString();
                res = response;
            } else {
                return res;
            }
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Response getJFact(String baseURI, Map<String, String> headers) {
        Response res = null;
        try {
            RestAssured.baseURI = baseURI;
            RestAssured.defaultParser = Parser.JSON;
            RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
            if (!headers.isEmpty()) {
                headers.entrySet().stream().forEach(e ->
                        request.header(e.getKey(), e.getValue())
                );
            }

            Response response = request.get(baseURI);
            if (!response.getBody().asString().isEmpty()) {
                //res = response.asString();
                res = response;
            } else {
                return res;
            }
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

//    public void setup(){
//        RestAssured.baseURI = "http://localhost:5002";
//        RestAssured.basePath = "api/upload";
//        Header acceptHeader = new Header("Accept" , "application/json");
//        httpRequest = RestAssured.given().auth().basic("admin", "admin").header(acceptHeader);
//    }
//
//    public void uploadFile() {
//        File file = new File(System.getProperty("user.dir") + "src/test/resources/uploads/image.jpg");
//        Response response = httpRequest
//                .multiPart("passport", file)
//                .when()
//                .post()
//                .andReturn();
//        System.out.println(response.asPrettyString());
//        return;
//    }

}
