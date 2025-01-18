package pwPrcatice;

import com.microsoft.playwright.*;


public class HandlingFrames_PW {
    static Playwright pw = Playwright.create();
    static Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));

    //Browser contexts :
    static BrowserContext bc1 = browser.newContext();
    static Page page1 = bc1.newPage();

    public static void main(String[] args) {
        page1.navigate("https://demo.automationtesting.in/Frames.html");
        Locator textbox = page1.locator("//input[@type='text']");
        enterTextInsideFrame("SingleFrame","//input[@type='text']","Yakub");
//        Playwright pw = Playwright.create();
//        Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
//
//        //Browser contexts :
//        BrowserContext bc1 = browser.newContext();
//        Page page1 = bc1.newPage();
//
//        BrowserContext bc2 = browser.newContext();
//        Page page2 = bc2.newPage();
//
//        //Handling Frames
//        page1.navigate("http://londonfreelance.org/courses/frames/index.html");
//
//        //1.Using FrameLocator()
//        Locator headerInsideFrame = page1.frameLocator("frame[name='main']").locator("h2");
//        String Headername = headerInsideFrame.textContent();
//        System.out.println(Headername);
//
//        //2.using frame name()
//        Locator frame = page1.frame("main").locator("h2");
//        String frameNmae = headerInsideFrame.textContent();
//        System.out.println(frameNmae);
//
//        //Handling iframes
//        page2.navigate("https://www.formsite.com/templates/registration-form-templates/vehicle-registration-form/");
//        page2.locator("img[title='Vehicle-Registration-Forms-and-Examples']").click();
//        Locator textBox = page2.frameLocator("//iframe[contains(@id,'frame-one')]").locator("#RESULT_TextField-8");
//        textBox.fill("Yakub Pasha Shaik");
//        System.out.println(page2.title());
//
//        browser.close();
//        pw.close();
    }

    public static Frame switchToFrameByName(String frameName) {
        return page1.frame(frameName);
    }

    public static void enterTextInsideFrame(String framename, String locator, String value) {
        page1.waitForLoadState();
        switchToFrameByName(framename);
        enterTextBySelector(locator,value);
    }
    public static void enterTextBySelector(String selector, String value) {
        page1.locator(selector).fill(value);
    }
}
