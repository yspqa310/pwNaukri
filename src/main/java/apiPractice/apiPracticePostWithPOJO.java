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

        APIResponse response = reqContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer " + AccessToken));
//                        .setData(filebytes));

        System.out.println("Response status is :" + response.status());
        System.out.println("Response status Text is :" + response.statusText());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonRes = mapper.readTree(response.body());
        String jsnPretty = jsonRes.toPrettyString();
        System.err.println("**********Response in json format as a pretty string****************");
        System.out.println(jsnPretty);

//storing id value of created user
        String userid = jsonRes.get("id").asText();


        //GET call to verify the userd id created or not
        APIResponse response2 = reqContext.get("https://gorest.co.in/public/v2/users/" + userid,
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer " + AccessToken));

        System.out.println("Response2 status is :" + response2.status());
        System.out.println("Response2 status Text is :" + response2.statusText());

        Assert.assertTrue(response2.text().contains(userid));
        System.out.println("***successful***");
        Assert.assertTrue(response2.text().contains("pasha356"));
        System.out.println("***successful***");

    }
}
