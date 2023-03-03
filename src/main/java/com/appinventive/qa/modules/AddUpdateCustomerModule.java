package com.appinventive.qa.modules;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.appinventive.qa.ApiUtils.APIFunctions.generateRandomNumber;

public class AddUpdateCustomerModule {

    static String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);

    static String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);

    static String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";

    static String PassWord = "testqa" + getRandomString(2);

    static String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));

    static OkHttpClient client = new OkHttpClient().newBuilder().build();
    static MediaType mediaType = MediaType.parse("text/plain");

    public static void print() {
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);
    }


    public static String getRandomString(int length) {

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

    public static String postFormData(String baseURI, String authorization, String Imagepath) throws IOException {

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(8) + generateRandomNumber(5) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                .addFormDataPart("buildingName", "Bimangian")
                .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", PassWord)
                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();


        return response.body().string();


    }

    public static String postFormDataRequiredFeilds(String baseURI, String authorization, String Imagepath) throws IOException {


        print();

        String FirstName = "Test" + getRandomString(8) + generateRandomNumber(5);
        String LastName = "QA" + getRandomString(8) + generateRandomNumber(5);
        String email = "testqa" + getRandomString(8) + generateRandomNumber(5) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(8);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                // .addFormDataPart("buildingName", "Bimangian")
                //   .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", PassWord)
                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postFormDataOptionalFeilds(String baseURI, String authorization, String Imagepath) throws IOException {


        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)

                .addFormDataPart("buildingName", "Bimangian")
                .addFormDataPart("buildingNumber", "301").build();

        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postFormAlreadyExistedData(String baseURI, String authorization, String Imagepath) throws IOException {


        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                // .addFormDataPart("buildingName", "Bimangian")
                //   .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", PassWord)
                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();


    }

    public static String postFormCanceledUserDataData(String baseURI, String authorization, String Imagepath) throws IOException {


        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                // .addFormDataPart("buildingName", "Bimangian")
                //   .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", PassWord)
                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        String uuid = response.body().toString();
        return response.body().string();


    }

    public static String postFormUpdateExistingCustomer(String baseURI, String authorization, String Imagepath, String UUID) throws IOException {


        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                .addFormDataPart("userUuid", UUID)
                // .addFormDataPart("buildingName", "Bimangian")
                //   .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", PassWord)
                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postFormIncorrectDataCustomer(String baseURI, String authorization, String Imagepath) throws IOException {


        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                // .addFormDataPart("userUuid","906a6038-50c9-4ba5-9e15-2b8995e85460")
                // .addFormDataPart("buildingName", "Bimangian")
                //   .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")

                .addFormDataPart("email", email)
                .addFormDataPart("password", PassWord)

                .addFormDataPart("email", "wrongemail")
                // .addFormDataPart("password", PassWord)

                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postFormDatawithMinNumber(String baseURI, String authorization, String Imagepath) throws IOException {

        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                .addFormDataPart("buildingName", "Bimangian")
                .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", PassWord)
                .addFormDataPart("mobileNo", "567234")
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();


        return response.body().string();


    }

    public static String postFormDatawithMaxNumber(String baseURI, String authorization, String Imagepath) throws IOException {

        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "183838384848484884";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                .addFormDataPart("buildingName", "Bimangian")
                .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", PassWord)
                .addFormDataPart("mobileNo", "56723456474833")
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();


        return response.body().string();


    }

    public static String postFormDatawithStarting00Number(String baseURI, String authorization, String Imagepath) throws IOException {

        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                .addFormDataPart("buildingName", "Bimangian")
                .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", PassWord)

                .addFormDataPart("mobileNo", "0012345")

                .addFormDataPart("mobileNo", "00123456")

                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();


        return response.body().string();


    }

    public static String postFormDatawithInvalidPassword(String baseURI, String authorization, String Imagepath) throws IOException {

        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                .addFormDataPart("buildingName", "Bimangian")
                .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", "india@123")
                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();


        return response.body().string();


    }

    public static String postFormDatawithWrongRequestURL(String baseURI, String authorization, String Imagepath) throws IOException {

        print();

        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                .addFormDataPart("buildingName", "Bimangian")
                .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", "india@123")
                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);

        Request request = new Request.Builder().url(baseURI).method("POST", body)

//        Request request = new Request.Builder().url("https:").method("POST", body)

                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();


        return response.body().string();


    }

    public static String postFormDatawithWrongAuthentication(String baseURI, String authorization, String Imagepath) throws IOException {
        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                .addFormDataPart("buildingName", "Bimangian")
                .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", "india@123")
                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postFormDatawithWrongContentType(String baseURI, String authorization, String Imagepath) throws IOException {
        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("firstName", FirstName)
                .addFormDataPart("lastName", LastName)
                .addFormDataPart("firstNameNative", "裕規")
                .addFormDataPart("lastNameNative", "伊藤")
                .addFormDataPart("dob", "1977-07-29")
                .addFormDataPart("nationality", "Japanese")
                .addFormDataPart("stateProvinceCode", "Tokyo")
                .addFormDataPart("city", "Toshima-ku")
                .addFormDataPart("streetName", "Sentifa")
                .addFormDataPart("streetNumber", "21-12-12")
                .addFormDataPart("buildingName", "Bimangian")
                .addFormDataPart("buildingNumber", "301")
                .addFormDataPart("postalCode", "1700121")
                .addFormDataPart("occupationCode", "019")
                .addFormDataPart("country", "JP")
                .addFormDataPart("gender", "female")
                .addFormDataPart("email", email)
                .addFormDataPart("password", "india@123")
                .addFormDataPart("mobileNo", mobilenumber)
                .addFormDataPart("phoneAreaCode", "+81")
                .addFormDataPart("docPidNumber", "TS74123325")
                .addFormDataPart("docPidIssuedAt", "2013-09-21")
                .addFormDataPart("docPidExpiredAt", "2023-09-20")
                .addFormDataPart("passport", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("selfPhoto", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("signature", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("frontSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("backSideIdentityDoc", Imagepath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(Imagepath)))
                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).addHeader("Content-Type", "hbfrfnfj").build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postFormDatawithWithoutBody(String baseURI, String authorization, String Imagepath) throws IOException {
        String FirstName = "Test" + getRandomString(1) + generateRandomNumber(1);
        String LastName = "QA" + getRandomString(1) + generateRandomNumber(1);
        String email = "testqa" + getRandomString(1) + generateRandomNumber(1) + "@yopmail.com";
        String PassWord = "testqa" + getRandomString(2);
        String mobilenumber = "81426" + String.valueOf(generateRandomNumber(5));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        System.out.println("Print First Name : " + FirstName);
        System.out.println("Print LastName : " + LastName);
        System.out.println("Print email : " + email);
        System.out.println("Print PassWord : " + PassWord);
        System.out.println("Print mobilenumber : " + mobilenumber);
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)

                .addFormDataPart("cardColor", "MATTE_BLACK").addFormDataPart("customerType", "AFFILIATE").build();
        System.out.println("Response Body : " + body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).addHeader("Content-Type", "hbfrfnfj").build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}