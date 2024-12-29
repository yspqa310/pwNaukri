package StepDefinition;

import io.cucumber.java.en.*;
import utilities.genericMethods;

import java.io.IOException;

public class LoginStepDef extends genericMethods {
    @When("user enter the login details and click on login button")
    public void whenUserEnterLoginDetails() throws IOException {
        launchURL(getProperty("url"));
    }

    @And("click on logout")
    public void logout() {

    }
}
