package apiPractice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class apiPracticePostWithPOJO {
    Playwright pw;
    APIRequest req;
    APIRequestContext reqContext;

    @BeforeTest
    public void setup() {
        pw = Playwright.create();
        req = pw.request();
        reqContext = req.newContext();
    }

    @AfterTest
    public void tearDown() {
        pw.close();
    }

    @Test
    public void createUserPost() throws IOException {
        String AccessToken = "23411dafd4cd71bc33dd7f692921df4119747b53876170af99fc9f5ef2b61714";

        //calling POJO Class Object to pass data
        userPOJO user = new userPOJO("Lathif", "lathif@4mail", "male", "active");

        APIResponse response = reqContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer " + AccessToken)
                        .setData(user));

        System.out.println("Response status is :" + response.status());
        System.out.println("Response status Text is :" + response.statusText());

        //printing response json in single line
        String ResponseText = response.text();
        System.out.println(ResponseText);

        ObjectMapper mapper = new ObjectMapper();
        //storing response into the Created POJO class (Deserialization)
        userPOJO actUser = mapper.readValue(ResponseText, userPOJO.class);

        //using response and converting into Json to print it as as pretty string
        JsonNode responseJson = mapper.readTree(response.body());
        String responseJsonText = responseJson.toPrettyString();
        System.out.println(responseJsonText);

        //asserting with the actual(Json-->deserialized pojo) with the expected(created POJO)
        Assert.assertEquals(actUser.getGender(), user.getGender());

    }
}
