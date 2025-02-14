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
import java.util.HashMap;
import java.util.Map;

public class apiPracticePost {
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
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "Yakub Sajahan Pasha shaik");
        data.put("email", "pasha@gmail.com");
        data.put("gender", "male");
        data.put("status", "active");

        APIResponse response = reqContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer 51471d66d840de5fcf35d63becd6adcfabeeb3e30c0b97d5a9c971e19d304203")
                        .setData(data));

        System.out.println("Response status is :"+response.status());
        System.out.println("Response status Text is :"+response.statusText());

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
                        .setHeader("Authorization","Bearer 51471d66d840de5fcf35d63becd6adcfabeeb3e30c0b97d5a9c971e19d304203"));

        System.out.println("Response2 status is :"+response2.status());
        System.out.println("Response2 status Text is :"+response2.statusText());

        Assert.assertTrue(response2.text().contains(userid));
        System.out.println("***successful***");
        Assert.assertTrue(response2.text().contains("Yakub"));
        System.out.println("***successful***");

    }
}
