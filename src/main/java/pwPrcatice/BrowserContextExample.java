package pwPrcatice;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class BrowserContextExample {
    public static void main(String[] args) {

        Playwright pw = Playwright.create();
        Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));

        //Browser context 1
        BrowserContext context1 = browser.newContext();
        Page page1 = context1.newPage();
        page1.navigate("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
        page1.getByLabel("Email:").fill("admin@yourstore.com");
        page1.getByLabel("Password:").click();
        page1.getByLabel("Password:").fill("admin");
        page1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in")).click();
        System.out.println(page1.title());
        context1.close();

        //Browser context 1
        BrowserContext context2 = browser.newContext();
        Page page2 = context2.newPage();
        page2.navigate("https://www.instagram.com/");
        page2.getByLabel("Phone number, username, or").fill("i__y_s_p");
        page2.getByLabel("Password").fill("pashaysp");
        page2.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in").setExact(true)).click();
        System.out.println(page2.title());
        context2.close();

        browser.close();
        pw.close();

    }
}
