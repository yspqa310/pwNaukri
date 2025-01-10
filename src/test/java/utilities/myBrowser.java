package utilities;

import com.microsoft.playwright.*;

import java.io.IOException;

public class myBrowser extends PropertyFilesLoader {
    static Page page = null;
    static Playwright pw = null;
    static Browser browser = null;

    public static Page InitiatingBrowser() throws IOException {
        String browserName = GetProperty("browser");
        boolean headless;
        pw = Playwright.create();
        if(GetProperty("HeadLess").equalsIgnoreCase("true")){
            headless = true;
        }else{
            headless = false;
        }

        switch (browserName.toLowerCase()) {
            case "chrome":
                browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel(browserName));
                break;
            case "msedge":
                browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel(browserName));
                break;
            case "firefox":
                browser = pw.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "webkit":
                browser = pw.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
        }
        if (browser != null) {
            BrowserContext bc = browser.newContext();
            page = bc.newPage();
        }
        return page;
    }

    public static synchronized Browser getBrowser() {
        return browser;
    }

    public static synchronized Playwright getPlaywright() {
        return pw;
    }

    public static synchronized Page getPage() {
        return page;
    }
}
