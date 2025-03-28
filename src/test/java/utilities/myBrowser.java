package utilities;

import com.microsoft.playwright.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class myBrowser extends PropertyFilesLoader {
    static Page page = null;
    static Playwright pw = null;
    static Browser browser = null;
    public static BrowserContext bc = null;

    public static Page InitiatingBrowser() throws IOException {
        String browserName = GetProperty("browser");
        boolean headless;
        pw = Playwright.create();

        if (GetProperty("HeadLess").equalsIgnoreCase("true")) {
            headless = true;
        } else {
            headless = false;
        }

        switch (browserName.toLowerCase()) {
            case "chrome":
                browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel(browserName)
                        .setArgs(List.of("--disable-incognito")));
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
            Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
            int height = (int) screensize.getHeight();
            int width = (int) screensize.getWidth();

            bc = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(width, height)
                    .setRecordVideoDir(Paths.get("ExecutionVideo/"))
                    .setRecordVideoSize(width, height));
            startTracing(bc);
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

    public static synchronized BrowserContext getContext() {
        return bc;
    }

    public static void stopTracing(BrowserContext context) {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
    }

    public static void startTracing(BrowserContext context) {
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }
}
