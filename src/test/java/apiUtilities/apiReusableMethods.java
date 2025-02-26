package apiUtilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import utilities.genericMethods;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class apiReusableMethods extends genericMethods {
    Playwright pw;
    APIRequest req;
    APIRequestContext reqContext;
    APIResponse response;

    public APIRequestContext apiRequestContext() {
        pw = Playwright.create();
        req = pw.request();
        reqContext = req.newContext();
        return reqContext;
    }

    public APIResponse apiResponse() {
        return response;
    }

    public APIResponse apiGET(String baseUrl) throws IOException {
        response = apiRequestContext().get(baseUrl);
        assertEquals(getStatusCode(), 200);
        assertEqualsIgnoreCase(getStatusText(), "ok");
        assertEquals(getUri(), baseUrl);
        return response;
    }

    public APIResponse apiGET(String baseUrl, String contentType, String accessToken) throws IOException {
        response = apiRequestContext().get(baseUrl,
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer " + accessToken)
                        .setHeader("content-type", contentType)
        );
        assertEquals(getStatusCode(), 200);
        assertEqualsIgnoreCase(getStatusText(), "ok");
        assertEquals(getUri(), baseUrl);
        return response;
    }

    public APIResponse apiPost(String url, String contentType, String accessToken, String jsonFilePath) throws IOException {

        response = apiRequestContext().post(url,
                RequestOptions.create()
                        .setHeader("Content-Type", contentType)
                        .setHeader("Authorization", "Bearer " + accessToken)
                        .setData(reqPayloadAsJsonFile(jsonFilePath)));

        assertEquals(getStatusCode(), 201);
        assertEqualsIgnoreCase(getStatusText(), "created");
        getResponseAsPrettyString();
        return response;
    }

    public byte[] reqPayloadAsJsonFile(String jsonFilePath) throws IOException {
        File file = new File(jsonFilePath);
        return Files.readAllBytes(file.toPath());
    }

    public int getStatusCode() {
        System.out.println("Response Status Code is : " + response.status());
        return response.status();
    }

    public String getStatusText() {
        System.out.println("Response Status Text is :" + response.statusText());
        return response.statusText();
    }

    public String getUri() {
        System.out.println("URL for the respective Request Response is :" + response.url());
        return response.url();
    }

    public String getResponseAsPrettyString() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseNode = mapper.readTree(apiResponse().body());
        writeLogInfo("Response payload as JSON Pretty String");
        System.out.println(responseNode.toPrettyString());
        return responseNode.toPrettyString();
    }

    public JsonNode getJsonResponse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(apiResponse().body());
    }

    public String getResponseText() throws IOException {
        return response.text();
    }

}
