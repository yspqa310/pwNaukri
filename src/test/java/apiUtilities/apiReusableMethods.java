package apiUtilities;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import utilities.genericMethods;

public class apiReusableMethods extends genericMethods {
    Playwright pw;
    APIRequest req;
    APIRequestContext reqContext;
    APIResponse response;

    public APIRequestContext createAPIRequestContext() {
        pw = Playwright.create();
        req = pw.request();
        reqContext = req.newContext();
        return reqContext;
    }
    public APIResponse getResponse() {
        return  response;
    }
}
