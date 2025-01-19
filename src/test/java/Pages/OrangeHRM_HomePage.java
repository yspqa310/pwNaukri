package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utilities.*;

public class OrangeHRM_HomePage extends genericMethods {
    Locator profileButton = Page().locator(".oxd-userdropdown-tab");
    Locator logOutBtn = Page().locator("'Logout'");
    public void clickLogOut() {
        click(profileButton);
        click(logOutBtn);
    }
}
