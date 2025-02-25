package apiPractice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class apiPracticeGET {
    Playwright pw;
    APIRequest req;
    APIRequestContext reqContext;

    @BeforeTest
    public void setup() {
        pw = Playwright.create();
        req = pw.request();
        reqContext = req.newContext();
    }

    @Test
    public void GetReq() throws IOException {
        // Playwright Api Testing Basic example
        APIResponse response = reqContext.get("https://gorest.co.in/public/v2/users");


        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseNode = mapper.readTree(response.body());
        String ResponsePrettyString = responseNode.toPrettyString();
        int response_code = response.status();
        String response_text = response.statusText();
        System.out.println(ResponsePrettyString);
        System.out.println(response_code);
        System.out.println(response_text);

    }

    @Test
    public void getSpecifc() throws IOException {
        APIResponse response = reqContext.get("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setQueryParam("gender", "male")
                        .setQueryParam("status", "inactive")
        );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseNode = mapper.readTree(response.body());
        String ResponseBody = responseNode.toPrettyString();

        System.out.println(ResponseBody);
        System.out.println(response.status());
        System.out.println(response.text());
        System.out.println(response.statusText());
    }
}
