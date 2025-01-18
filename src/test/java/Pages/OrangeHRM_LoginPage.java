package Pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

import com.microsoft.playwright.assertions.LocatorAssertions;
import org.testng.Assert;
import utilities.*;
import com.microsoft.playwright.*;

public class OrangeHRM_LoginPage extends genericMethods {
    Locator loginHeader = Page().locator("h5:has-text('Login')");
    Locator userNameTxtBox = Page().locator("input[name='username']");
    Locator pswdTxtBox = Page().locator("input[name='password']");
    Locator lgnButton = Page().locator("button:has-text('Login')");

    public void verifyLoginDisplayed() {
        assertThat(loginHeader).isEnabled();
    }

    public void enterUserName(String userName) {
        enterTextByLocator(userNameTxtBox, userName);
    }

    public void enterPassWord(String PassWord) {
        enterTextByLocator(pswdTxtBox, PassWord);
    }

    public void clickLoginButton() {
        assertThat(lgnButton).isEnabled();
        lgnButton.highlight();
        lgnButton.click();
    }
}
