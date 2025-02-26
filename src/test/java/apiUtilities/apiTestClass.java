package apiUtilities;

import com.fasterxml.jackson.databind.JsonNode;
import org.testng.annotations.Test;

import java.io.IOException;

public class apiTestClass extends apiReusableMethods {
    @Test
    public void apiget() throws IOException {
        apiGET("https://gorest.co.in/public/v2/users");
    }
    public void apipost() throws IOException {
        apiPost("https://gorest.co.in/public/v2/users","application/json","23411dafd4cd71bc33dd7f692921df4119747b53876170af99fc9f5ef2b61714","");
    }
}
