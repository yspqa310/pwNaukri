package apiPractice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.HttpHeader;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class apiHeaders {
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
    public void GetReq() throws IOException {
        // Playwright Api Testing Basic example
        APIResponse response = reqContext.get("https://gorest.co.in/public/v2/users");

        Map<String, String> headerMap = response.headers();

        System.err.println("-------------HTTP Headers as a Map-------------------");
        headerMap.forEach((k,v)-> System.out.println(k+"   --:--   "+v));


        List<HttpHeader> list=response.headersArray();
        System.err.println("-------------HTTP Headers as a List-------------------");
        list.forEach((k)-> System.out.println(k.name+"***:***"+k.value));

//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode responseNode = mapper.readTree(response.body());
//        String ResponsePrettyString = responseNode.toPrettyString();
//        int response_code = response.status();
//        String response_text = response.statusText();
//
//        System.out.println("--------------------Response body as pretty string----------------------");
//        System.out.println(ResponsePrettyString);
//
//
//        System.out.println("-----------  Response code : -------------------");
//        System.out.println(response_code);
//
//
//        System.out.println("---------------Response text :-------------");
//        System.out.println(response_text);

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

