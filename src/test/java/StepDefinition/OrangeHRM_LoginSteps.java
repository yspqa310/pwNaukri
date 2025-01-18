package StepDefinition;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.cucumber.java.en.*;
import Pages.*;

public class OrangeHRM_LoginSteps extends OrangeHRM_LoginPage {
    OrangeHRM_HomePage hm = new OrangeHRM_HomePage();
    @Given("user is on the Login page")
    public void userIsOnTheLoginPage() {
        Page().waitForLoadState();
        verifyLoginDisplayed();

    }

    @And("user login with {string} and {string}")
    public void userLoginWithAnd(String username, String password) {
        enterUserName(username);
        writeLogInfo("user entered username");
        enterPassWord(password);
        writeLogInfo("user entered password");
        clickLoginButton();
        writeLogInfo("user clicked on Login Button");
    }
    @Then("user click on logout")
    public void userLogout() {
        hm.clickLogOut();
        writeLogInfo("User clicked on Logout");
    }
}
