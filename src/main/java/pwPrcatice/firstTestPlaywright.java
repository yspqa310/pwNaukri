package pwPrcatice;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class firstTestPlaywright {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
            System.out.println(page.title());
        }

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
            page.getByLabel("Email:").fill("admin@yourstore.com");
            page.getByLabel("Password:").click();
            page.getByLabel("Password:").fill("admin");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in")).click();
            System.out.println("clicked on Login Button");
        }


        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://www.instagram.com/");
            page.getByLabel("Phone number, username, or").fill("i__y_s_p");
            page.getByLabel("Password").fill("pashaysp");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in").setExact(true)).click();
            System.out.println(page.title());

        }
    }
}