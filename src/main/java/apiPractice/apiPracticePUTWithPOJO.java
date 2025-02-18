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

import java.io.IOException;

public class apiPracticePUTWithPOJO {
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

    //1).Post
    //2)PUT
    // 3).Get
    @Test
    public void updateUserPUT() throws IOException {
        String AccessToken = "23411dafd4cd71bc33dd7f692921df4119747b53876170af99fc9f5ef2b61714";

        //calling POJO Class Object to pass data
        userPOJO user = new userPOJO("Shaik11", "shaik11@mail", "female", "inactive");

        //1).Post
        APIResponse response = reqContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer " + AccessToken)
                        .setData(user));

        System.out.println("Response status is :" + response.status());
        System.out.println("Response status Text is :" + response.statusText());

        //printing response json in single line
        String ResponseText = response.text();
        ObjectMapper mapper = new ObjectMapper();
        userPOJO actUser = mapper.readValue(ResponseText, userPOJO.class);
        String userid = actUser.getId();
        //using response and converting into Json to print it as as pretty string
        JsonNode responseJson = mapper.readTree(response.body());
        String responseJsonText = responseJson.toPrettyString();
        System.out.println(responseJsonText);

        System.out.println("************************************ Put   request    ***********************************************");

        user.setGender("male");
        user.setEmail("ysp@34mail.com");
        APIResponse putResponse = req.newContext().put("https://gorest.co.in/public/v2/users/" + userid,
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer " + AccessToken)
                        .setData(user));

        System.out.println(putResponse.status() + "\n" + "  <--code status :put : status text--->  " + putResponse.statusText());
        userPOJO putUser = mapper.readValue(putResponse.text(), userPOJO.class);
        Assert.assertEquals(putUser.getId(), userid);
        System.out.println(putUser.getId());
        Assert.assertEquals(putUser.getGender(), user.getGender());
        System.out.println(putUser.getGender());
        Assert.assertEquals(putUser.getEmail(), user.getEmail());
        System.out.println(putUser.getEmail());
    }
}
