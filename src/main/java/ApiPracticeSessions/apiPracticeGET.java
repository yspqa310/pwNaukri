package ApiPracticeSessions;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class apiPracticeGET {
    @Test
    public void getUser() {
        Playwright pw = Playwright.create();
        APIRequest request = pw.request();
        APIRequestContext apiContext = request.newContext();
        APIResponse response = apiContext.get("https://gorest.co.in/public/v2/users");
        String Response = response.body().toString();

        int statusCode = response.status();

        System.err.println("Below is the Response bidy as string");
        System.out.println(Response);

        System.err.println("below is the status code for response");
        System.out.println(statusCode);
    }
}
