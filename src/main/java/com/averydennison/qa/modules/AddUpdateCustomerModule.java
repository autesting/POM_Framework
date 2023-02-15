package com.averydennison.qa.modules;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static com.averydennison.qa.ApiUtils.APIFunctions.generateRandomNumber;

public class AddUpdateCustomerModule {

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

    public String postFormData(String baseURI, String authorization, String Imagepath) throws IOException {

        String FirstName = "Test"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print First Name : " +FirstName);
        String LastName = "QA"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print LastName : " +LastName);
        String email = "testqa"+getRandomString(1)+generateRandomNumber(1)+"@yopmail.com";
        System.out.println("Print email : " +email);
        String PassWord =  "testqa"+getRandomString(2);
        System.out.println("Print PassWord : " +PassWord);
        String  mobilenumber = "81426"+String.valueOf(generateRandomNumber(5));
        System.out.println("Print mobilenumber : " +mobilenumber);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
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
        System.out.println("Response Body : " +body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String postFormDataRequiredFeilds(String baseURI, String authorization, String Imagepath) throws IOException {

        String FirstName = "Test"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print First Name : " +FirstName);
        String LastName = "QA"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print LastName : " +LastName);
        String email = "testqa"+getRandomString(1)+generateRandomNumber(1)+"@yopmail.com";
        System.out.println("Print email : " +email);
        String PassWord =  "testqa"+getRandomString(2);
        System.out.println("Print PassWord : " +PassWord);
        String  mobilenumber = "81426"+String.valueOf(generateRandomNumber(5));
        System.out.println("Print mobilenumber : " +mobilenumber);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
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
        System.out.println("Response Body : " +body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String postFormDataOptionalFeilds(String baseURI, String authorization, String Imagepath) throws IOException {


        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)

                 .addFormDataPart("buildingName", "Bimangian")
                 .addFormDataPart("buildingNumber", "301").build();

        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String postFormAlreadyExistedData(String baseURI, String authorization, String Imagepath) throws IOException {

        String FirstName = "Test"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print First Name : " +FirstName);
        String LastName = "QA"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print LastName : " +LastName);
        String email = "test123@example.com";
        System.out.println("Print email : " +email);
        String PassWord =  "testqa"+getRandomString(2);
        System.out.println("Print PassWord : " +PassWord);
        String  mobilenumber = "9654344012346";
        System.out.println("Print mobilenumber : " +mobilenumber);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
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
        System.out.println("Response Body : " +body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String postFormCanceledUserDataData(String baseURI, String authorization, String Imagepath) throws IOException {

        String FirstName = "Test"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print First Name : " +FirstName);
        String LastName = "QA"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print LastName : " +LastName);
        String email = "testqar5@yopmail.com";
        System.out.println("Print email : " +email);
        String PassWord =  "testqa"+getRandomString(2);
        System.out.println("Print PassWord : " +PassWord);
        String  mobilenumber = "81426"+String.valueOf(generateRandomNumber(5));
        System.out.println("Print mobilenumber : " +mobilenumber);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
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
        System.out.println("Response Body : " +body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String postFormUpdateExistingCustomer(String baseURI, String authorization, String Imagepath) throws IOException {

        String FirstName = "xyz";
        System.out.println("Print First Name : " +FirstName);
        String LastName = "QA"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print LastName : " +LastName);
        String email = "testqa"+getRandomString(1)+generateRandomNumber(1)+"@yopmail.com";
        System.out.println("Print email : " +email);
        String PassWord =  "testqa"+getRandomString(2);
        System.out.println("Print PassWord : " +PassWord);
        String  mobilenumber = "81426"+String.valueOf(generateRandomNumber(5));
        System.out.println("Print mobilenumber : " +mobilenumber);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
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
                .addFormDataPart("userUuid","906a6038-50c9-4ba5-9e15-2b8995e85460")
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
        System.out.println("Response Body : " +body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String postFormIncorrectDataCustomer(String baseURI, String authorization, String Imagepath) throws IOException {

        String FirstName = "xyz";
        System.out.println("Print First Name : " +FirstName);
        String LastName = "QA"+getRandomString(1)+generateRandomNumber(1);
        System.out.println("Print LastName : " +LastName);
        String email = "xyz1";
        System.out.println("Print email : " +email);
        String PassWord =  "testqa"+getRandomString(2);
        System.out.println("Print PassWord : " +PassWord);
        String  mobilenumber = "81426"+String.valueOf(generateRandomNumber(5));
        System.out.println("Print mobilenumber : " +mobilenumber);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
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
        System.out.println("Response Body : " +body);
        Request request = new Request.Builder().url(baseURI).method("POST", body)
                .addHeader("Authorization", authorization).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}
