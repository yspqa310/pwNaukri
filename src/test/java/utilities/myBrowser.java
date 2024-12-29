package utilities;

import com.microsoft.playwright.*;

import java.io.IOException;

public class myBrowser extends PropertyFilesLoader {
    static Page page = null;
    static Playwright pw = null;
    static Browser browser = null;

    public static Page InitiatingBrowser() throws IOException {
        String browserName = GetProperty("browser");
        pw = Playwright.create();
        switch (browserName.toLowerCase()) {
            case "chrome":
                browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel(browserName));
                break;
            case "msedge":
                browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel(browserName));
                break;
            case "firefox":
                browser = pw.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = pw.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
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
