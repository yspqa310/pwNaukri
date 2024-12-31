package pwPrcatice;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Collections;
import java.util.List;

public class  selectingOnVisibility {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        Page page = browser.newPage();
        page.navigate("https://www.flipkart.com/");

        //with playwright filter
        List<String> mylist = page.locator("a:visible").allInnerTexts();
        System.out.println(mylist.size());
        mylist.forEach(System.out::println);

        //with xpath filter
        List<String> mylist2 = page.locator("xpath=//a>>visible=true").allInnerTexts();
        System.out.println(mylist2.size());
        Collections.reverse(mylist2);
        mylist2.forEach(System.out::println);
        pw.close();
    }
}
