package pwPrcatice;

import com.microsoft.playwright.*;

import java.util.List;

public class creatingElements_Locators {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setChannel("chrome"));
        Page page = browser.newPage();
        page.navigate("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");

       /*  Locator is Similar to WebElement in selenium
        page.locator() == driver.findelement() inside locator you can use any selector you want
        Locator EmailtxtBox = page.locator("id = Email");  */

        Locator EmailtxtBox = page.locator("#Email");
        EmailtxtBox.fill("admin@yourstore.com");

        Locator PwdTxtbox = page.locator("#Password");
        PwdTxtbox.fill("admin");

        //Multiple Elements
        Locator adminElements = page.locator("text=Admin");

        //below line will show how many matching elements
        int admincount = adminElements.count();

        //1.Iteration using normal For loop
        for (int i = 0; i < admincount; i++) {
            System.out.println(adminElements.nth(i).textContent());
        }

        //2.Iteration using enhanced for loop(foreach)
        List<String> list = adminElements.allTextContents();
        for (String e : list) {
            System.out.println(e);
        }

        //3.Iteration using lambda expression
        list.forEach(e -> System.out.println(e));

        Locator LoginButton = page.locator("text=Log in");
        LoginButton.click();
        System.out.println(page.title());

        page.close();
        browser.close();
        pw.close();
    }
}
