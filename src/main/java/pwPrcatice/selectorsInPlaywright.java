package pwPrcatice;

import com.microsoft.playwright.*;

public class selectorsInPlaywright {
    public static void main(String[] args) {
        Playwright pw= Playwright.create();
        Browser browser= pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        BrowserContext bc= browser.newContext();
        Page page = bc.newPage();
        page.navigate("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
        System.out.println(page.title());

        //using id of the element
        Locator EmailtxtBox = page.locator("#Email");
        EmailtxtBox.fill("admin@yourstore.com");
        Locator PwdTxtbox = page.locator("#Password");
        PwdTxtbox.fill("admin");

        //using visible text of the element
        Locator LoginButton = page.locator("text=Log in");
        LoginButton.click();

        //using CSS selector
        Locator headerDashBoard = page.locator("div.content-header h1:has-text('Dashboard')");
    }
}
