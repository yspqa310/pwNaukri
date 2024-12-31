package pwPrcatice;

import com.microsoft.playwright.*;

import java.util.List;

public class CSSRelativeLocators_basedLayout {
    static Page page;
    static Playwright pw;
    static Browser browser;

    public static void main(String[] args) {
        getPage();

        //      :left-of
        selectUser_LeftOf_CheckBox("Jasmine.Morgan");
        selectUser_LeftOf_CheckBox("Garry.White");

        //      :right-of
        getText_rightOf_Username("Jasmine.Morgan");
        getText_rightOf_Username("Garry.White");

        //      :above
        getText_above_Username("Jasmine.Morgan");
        getText_above_Username("Garry.White");

        //      :below
        getText_below_Username("Jasmine.Morgan");
        getText_below_Username("Garry.White");

       //     :near
       getText_near_Username("Garry.White");


        closebrowser();
    }

    public static Page getPage() {
        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        page = browser.newPage();
        page.navigate("https://selectorshub.com/xpath-practice-page/");
        return page;
    }

    public static void selectUser_LeftOf_CheckBox(String UserName) {
        Locator checkbox = page.locator("input[type='Checkbox']:left-of(:text('" + UserName + "'))").first();
        checkbox.click();
    }

    public static void getText_rightOf_Username(String UserName) {
        Locator textvalue = page.locator("a:right-of(:text('" + UserName + "'))").first();
        System.out.println(textvalue.textContent());
    }

    public static void getText_above_Username(String UserName) {
        Locator textvalue = page.locator("a:above(:text('" + UserName + "'))").first();
        System.out.println(textvalue.textContent());
    }

    public static void getText_below_Username(String UserName) {
        Locator textvalue = page.locator("a:below(:text('" + UserName + "'))").first();
        System.out.println(textvalue.textContent());
    }

    public static void getText_near_Username(String UserName) {
        //it will fetches all links(a) which are near to the given Username with in the 200 pixels
        List<String> textvalue = page.locator("a:near(:text('" + UserName + "'),200)").allInnerTexts();
        textvalue.forEach(e-> System.out.println(e));

    }
    public static void closebrowser() {
        page.close();
        browser.close();
        pw.close();
    }
}
