package com.averydennison.qa.ApiUtils;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import groovy.json.StringEscapeUtils;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.json.Json;


import java.io.*;
import java.nio.charset.Charset;

public class JSONHandler {
    //************FileMapping************
    public JsonNode mapJSONPayloadFromFile(String chosenTCID, String chosenValueFromMainJSON) {
       
        try {
            String JSONTestDataFolderPath = "src/test/java/testData/jsonTestData/";
            String mainJSONFilePath = JSONTestDataFolderPath + "MainJson.json";
            InputStream getLocalJsonFile = new FileInputStream(mainJSONFilePath);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(getLocalJsonFile);
            JsonNode SetTCID = node.path(chosenTCID);
            JsonNode ChosenValuePayload = SetTCID.path(chosenValueFromMainJSON);
            JsonNode JSONPayload = null;
            if ((ChosenValuePayload.toString().contains("tdpl"))) {
                System.out.println(String.format("ChosenValuePayload - %s contains tdpl", ChosenValuePayload));
                InputStream getJSONPayload = new FileInputStream(JSONTestDataFolderPath + chosenTCID + "/tdpl-" + chosenTCID + "-" + chosenValueFromMainJSON + ".json");
                JSONPayload = mapper.readTree(getJSONPayload);
            } else if (ChosenValuePayload.toString().isEmpty()) {
                System.out.println("This is not a valid Path in MainJSON " + chosenValueFromMainJSON);
            } else {
                System.out.println("This is just a String Value " + ChosenValuePayload);
            }
            return JSONPayload;
        } catch (IOException e) {
           // LOGGER.error("Getting Input Exception", e);
            return null;
        }
    }

    public JsonNode mapJSONPayloadFromFile(String chosenCommonPayload) throws IOException {

        String CommonJSONTestDataPath = "src/test/java/testData/jsonTestData/commonPayloads/";
        InputStream getCommonPayload = new FileInputStream(CommonJSONTestDataPath + chosenCommonPayload + ".json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode CommonPayloadNode = mapper.readTree(getCommonPayload);
        System.out.println("Common test data was used for this TC execution. Data used: " + chosenCommonPayload);
        return CommonPayloadNode;
    }

    //************JSON Value Replacing************
    public JsonNode replaceValue(JsonNode targetNode, String key, String newValue) {
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Current JSON Value:", targetNode));
        ((ObjectNode) targetNode).put(key,newValue);
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Updated JSON Value:", targetNode));
        return targetNode;
    }

    public JsonNode replaceValue(JsonNode targetNode, String key, int newValue) {
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Current JSON Value:", targetNode));
        ((ObjectNode) targetNode).put(key,newValue);
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Updated JSON Value:", targetNode));
        return targetNode;
    }
    public JsonNode replaceValue(JsonNode targetNode, String fieldValue, String key, String newValue) {
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Current JSON Value:", targetNode));
        JsonNode modNode = targetNode.get(fieldValue);
        ((ObjectNode)modNode).put(key,newValue);
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Updated JSON Value:", modNode));
        return modNode;
    }
    public JsonNode replaceValue(JsonNode targetNode, String fieldValue, String key, int newValue) {
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Current JSON Value:", targetNode));
        JsonNode modNode = targetNode.get(fieldValue);
        ((ObjectNode)modNode).put(key,newValue);
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Updated JSON Value:", modNode));
        return modNode;
    }

    public JsonNode replaceValueArray(JsonNode targetNode, String fieldValue, String arrayKey, int arrayIndex, String keyInsideArray, String valueInsideArray) {
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Current JSON Value:", targetNode));
        JsonNode modNode = targetNode.get(fieldValue).path(arrayKey).get(arrayIndex);
        ((ObjectNode)modNode).put(keyInsideArray,valueInsideArray);
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Updated JSON Value:", modNode));
        return modNode;
    }

    public JsonNode replaceValueArray(JsonNode targetNode, String fieldValue, String arrayKey, int arrayIndex, String keyInsideArray, int valueInsideArray) {
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Current JSON Value:", targetNode));
        JsonNode modNode = targetNode.get(fieldValue).path(arrayKey).get(arrayIndex);
        ((ObjectNode)modNode).put(keyInsideArray,valueInsideArray);
        System.out.println(String.format("JSONHandler -> Replace Json Value -> Updated JSON Value:", modNode));
        return modNode;
    }

    //************JSON Parsing************
    public String parseJSON(JsonNode targetNode, String path) {
        String pathValue;
        if (targetNode == null) {
            pathValue = path + "targetNode is Null";
        } else if (targetNode.at(path).isNull()) {
            pathValue = path + "targetNode.at(path) is Null";
        } else if (targetNode.at(path).toString().isEmpty()) {
            pathValue = path + " This PATH does not exist, Please check your JSON Path for the value you are trying to retrieve";
        } else {
            pathValue = targetNode.at(path).toString();
        }
        return pathValue;
    }

    public static String parseJSON(String jsonString, String path) {
        //Write code to check if it is not null
        String[] pathArray = path.split("/");
        System.out.println("Path Array Size " + pathArray.length);
        System.out.println(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(new StringReader(jsonString));
            JsonNode newNode = null;
            for (String a : pathArray) {
                System.out.println(a);

                if (!node.isNull()) {
                    if (a.startsWith("array")) {
                        String ss = a.substring(a.indexOf("-") + 1);
                        int i = Integer.valueOf(ss);
                        newNode = node.get(i);
                        node = newNode;

                    } else {
                        newNode = node.path(a);
                        node = newNode;
                    }
                } else {
                    // Add null exception here
                }
            }
            System.out.println("********************");
            System.out.println(node.toString());
            return node.toString().replace("\"", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //************Payload Handling************
    public String obfuscatePayload(String payload) {
        String stringPayload = payload;
        byte[] encodedCreds = Base64.encodeBase64(stringPayload.getBytes());
        Charset ascii = Charset.forName("US-ASCII");
        String encodedString = new String(encodedCreds, ascii);
        StringBuilder revEncString = new StringBuilder();
        revEncString.append(encodedString);
        revEncString.reverse();
        String requestBody = '"' + String.valueOf(revEncString) + '"';
        return requestBody;

    }
    public String stringifyPayload(JsonNode payload) {
        String stringPayload = '"' + payload.toString() + '"';
        return stringPayload;
    }


    public JsonNode unescapePayload(JsonNode payload) throws JsonProcessingException {
        String payloadS = StringEscapeUtils.unescapeJava(String.valueOf(payload));
        ObjectMapper mapper = new ObjectMapper();
      //  JsonNode unescapedNode = mapper.readTree(payloadS);
        return null;
    }

    public JsonNode escapePayload(JsonNode payload) throws JsonProcessingException {
        String json = payload.toString();
        String escapedJson = StringEscapeUtils.escapeJava(json);
        ObjectMapper mapper = new ObjectMapper();
       // JsonNode escapedNode = mapper.readTree(escapedJson);
        return null;
    }
}


