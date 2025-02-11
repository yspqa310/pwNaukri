package utilities;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.MouseButton;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import  static com.microsoft.playwright.assertions.PlaywrightAssertions.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class genericMethods extends myBrowser {
    static Page page;
    public static Logger LOGGER = Logger.getLogger(genericMethods.class);
    static Page focusedPage;
    static String parentWindowTitle;
    static Page NEWPAGE;

    //    below methods are for the Logging purpose

    /**
     * Method will print the information you logged
     *
     * @param loginfo
     */
    public static void writeLogInfo(String loginfo) {
        LOGGER.info("*************  " + loginfo + "  *************");
    }

    /**
     * Method will print the information you logged
     *
     * @param loginfo
     */
    public void writeLoginfo(String loginfo) {
        LOGGER.info("*************  " + loginfo + "  *************");
    }

    /**
     * Method will print the warning you logged
     *
     * @param logWarn
     */
    public static void writeLogWarn(String logWarn) {
        LOGGER.warn("*************  " + logWarn + "  *************");
    }

    /**
     * Method will print the warning you logged
     *
     * @param logWarn
     */
    public void writeLogwarn(String logWarn) {
        LOGGER.warn("*************  " + logWarn + "  *************");
    }

    /**
     * Method will print the debug you logged
     *
     * @param logWarn
     */
    public static void writeLogDebug(String logWarn) {
        LOGGER.debug("*************  " + logWarn + "  *************");
    }

    /**
     * Method will print the debug you logged
     *
     * @param logWarn
     */
    public void writeLogdebug(String logWarn) {
        LOGGER.debug("*************  " + logWarn + "  *************");
    }

    /**
     * Method will print the error you logged
     *
     * @param logError
     */
    public static void writeLogError(String logError) {
        LOGGER.error("*************  " + logError + "  *************");
    }

    /**
     * Method will print the error you logged
     *
     * @param logError
     */
    public void writeLogerror(String logError) {
        LOGGER.error("*************  " + logError + "  *************");
    }


    public static Page Page() {
        page = getPage();
        return page;
    }

    public void highlight(Locator locator) {
        JavascriptExecutor JavascriptExecutor = null;
        final Object o = JavascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", locator);
    }

    /**
     * Method will click on the element of your locator
     *
     * @param locator
     */
    public void click(Locator locator) throws InterruptedException {
        waitForPagefullyLoaded();
        Thread.sleep(5000);
        locator.click();
    }
    /**
     * Method will click on the element of your selector in the specified page
     * @param page
     * @param selector
     */
    public void click(Page page, String selector) {
        page.waitForLoadState();
        page.click(selector);
    }
    /**
     * Method will click on the element of your locator in the specified page
     * @param page
     * @param locator
     */
    public void click(Page page, Locator locator) {
        page.waitForLoadState();
        locator.click();
    }
    /**
     * Method will get the text or  text content of the element
     * @return text value of the Locator(Element)
     * @param locator
     */
    public static String getText(Locator locator) {
        return locator.textContent();
    }
    /**
     * Method will get the all the text values or  all text  content of the Locator matching elements
     * @return all text values of the Locator mateched Elements
     * @param locatorr
     */
    public static void getAllTextvalues(Locator locatorr) {
        List<String> list = locatorr.allTextContents();
        writeLogInfo("Below are the dropdown options");
        list.forEach(list1 -> System.out.println(list1));
    }

    /**
     * Method will on the specified selector pointed element
     *
     * @param selector
     * @implNote selector may be : 1).name, id, xpath, Css Selector which is surrounded by string
     * 2).Do not use the Locator object for String Selector
     */
    public void clickBySelector(String selector) {
        waitForPagefullyLoaded();
        Page().locator(selector).click();
    }

    /**
     * Method will send the text into the texbox using locator and value
     *
     * @param locator
     * @param value
     */
    public static void enterTextByLocator(Locator locator, String value) {
        waitForPagefullyLoaded();
        locator.highlight();
        locator.fill(value);
    }

    /**
     * Method will send the text into the texbox using locator and value
     *
     * @param selector
     * @param value
     * @implNote selector may be : 1).name, id, xpath, Css Selector which is surrounded by string
     * 2).Do not use the Locator object for String Selector
     */
    public void enterTextBySelector(String selector, String value) {
        waitForPagefullyLoaded();
        Page().locator(selector).fill(value);
    }

    /**
     * Method will send the text into the texbox using LabelName and value
     *
     * @param LabelName
     * @param value
     * @implNote Make Sure Lable name is unique over the current Page
     */
    public void enterTextByLabel(String LabelName, String value) {
        Page().getByLabel(LabelName).fill(value);
    }

    /**
     * Method will send the text into the texbox using LabelName and value
     *
     * @param LabelName
     */
    public void checkByLabel(String LabelName) {
        Page().getByLabel(LabelName).check();
    }

    /**
     * Method will send the text into the texbox using LabelName and value
     *
     * @param locator
     */
    public void check(Locator locator) {
        locator.check();
    }

    //Handling Frames

    /**
     * Method will identifies the frame by its @name attribute value
     *
     * @param frameName as String
     * @return Frame
     */
    public Frame switchToFrameByName(String frameName) {
        return Page().frame(frameName);
    }

    /**
     * Method will identifies the frame by its Locator
     *
     * @param frameSelector as String
     * @return FrameLocator
     * @implNote frameSelector may be Id,name,CSS,Xpath etc.,
     */
    public FrameLocator switchToFrameByLocator(String frameSelector) {
        return Page().frameLocator(frameSelector);
    }

    /**
     * Method will enter the text in a element which is inside the frame
     *
     * @param framename
     * @param selector
     * @param value
     * @implNote selector may be Id,name,CSS,Xpath etc.,
     */
    public void enterTextInsideFrame(String framename, String selector, String value) {
        waitForPagefullyLoaded();
        switchToFrameByName(framename).locator(selector).fill(value);
    }

    /**
     * Method will click on a element which is inside the frame
     *
     * @param framename
     * @param selector
     * @implNote selector may be Id,name,CSS,Xpath etc.,
     */
    public void clickInsideFrame(String framename, String selector) {
        waitForPagefullyLoaded();
        switchToFrameByName(framename).locator(selector).click();
    }

    /**
     * Method will  double click on a element which is inside the frame
     *
     * @param framename
     * @param selector
     * @implNote selector may be Id,name,CSS,Xpath etc.,
     */
    public void doubleClickInsideFrame(String framename, String selector) {
        waitForPagefullyLoaded();
        switchToFrameByName(framename).locator(selector).dblclick();
    }


    /**
     * Method will enter the text in a element which is inside the frame found by Locator
     *
     * @param frameLocator
     * @param Locator
     * @param value
     * @implNote "frameLocator" may be Id,name,CSS,Xpath etc.,
     */
    public void enterTextInsideByFrameLocator(String frameLocator, Locator Locator, String value) {
        waitForPagefullyLoaded();
        switchToFrameByLocator(frameLocator).locator(Locator).fill(value);
    }

    /**
     * Method will click on a element which is inside the frame found by Locator
     *
     * @param frameLocator
     * @param locator
     * @implNote "frameLocator" may be Id,name,CSS,Xpath etc.,
     */
    public void clickInsideByFrameLocator(String frameLocator, Locator locator) {
        waitForPagefullyLoaded();
        switchToFrameByLocator(frameLocator).locator(locator).click();
    }

    /**
     * Method will  double click on a element which is inside the frame found By Locator
     *
     * @param frameLocator
     * @param locator
     * @implNote "frameLocator" may be Id,name,CSS,Xpath etc.,
     */
    public void doubleClickByFrameLocator(String frameLocator, Locator locator) {
        waitForPagefullyLoaded();
        switchToFrameByLocator(frameLocator).locator(locator).dblclick();
    }

    /**
     * Method will  double click on a element which is inside the frame found By Locator
     *
     * @param ParentframeName
     * @param childFrameIndex
     */
    public Frame switchToChildFrame(String ParentframeName, int childFrameIndex) {
        List<Frame> frames = switchToFrameByName(ParentframeName).childFrames();
        return frames.get(childFrameIndex);
    }

    /**
     * Method will  double click on a element which is inside the frame found By Locator
     *
     * @param ParentFrame
     * @param childFrameIndex
     * @param selector
     * @param Value
     */
    public void enterTextInsideChildFrame(String ParentFrame, int childFrameIndex, String selector, String Value) {
        switchToChildFrame(ParentFrame, childFrameIndex).locator(selector).fill(Value);
    }


    //Handling Alerts

    /**
     * This method will accept the alert which is populated on the page
     */
    public static void acceptAlert() {
        Page().onDialog(dialog -> {
            System.out.println(dialog.message());
            dialog.accept();
        });
    }

    /**
     * This method will accept the alert which is populated on the page and accept the prompt text
     *
     * @param promptText
     * @implNote promptText is the text which is urging the alert to pass
     */
    public static void acceptAlert(String promptText) {
        Page().onDialog(dialog -> {
            System.out.println(dialog.message());
            dialog.accept(promptText);
            writeLogInfo("you have entered alert prompt text as : " + promptText);
        });
    }

    /**
     * This method will dismiss the alert which is populated on the page
     */
    public static void dismissAlert() {
        Page().onDialog(dialog -> {
            System.out.println(dialog.message());
            dialog.dismiss();
        });
    }


    // file upload

    /**
     * This method will upload the file from the Local Directory
     *
     * @param selector
     * @param fullFilePath
     * @implNote Selector may be Id,name,CSS,Xpath etc..
     */
    public static void uploadFileFromLocalDirectory(String selector, String fullFilePath) {
        Page().setInputFiles(selector, Paths.get(fullFilePath));
    }

    /**
     * This method will upload the file from the current Directory
     *
     * @param selector
     * @param filePath give relative file path
     * @implNote Selector may be Id,name,CSS,Xpath etc.,
     */
    public static void uploadFileFromProjectDirectory(String selector, String filePath) {
        Page().setInputFiles(selector, Paths.get(filePath));
    }

    /**
     * This method will upload the multiple files from the specified paths or may be from a specified path
     *
     * @param selector
     * @param filePaths give the file paths as array
     * @implNote Selector may be Id,name,CSS,Xpath etc.,
     * @implNote example for filepaths ["src/test/resource","src/main/java"]
     */
    public static void uploadMultipleFiles(String selector, String[] filePaths) {
        String path = null;
        for (int i = 0; i <= filePaths.length; i++) {
            List<String> list = Arrays.asList(filePaths);
            path = list.get(i);

            Page().setInputFiles(selector, new Path[]
                    {Paths.get(path),
                    });
        }
    }

    /**
     * This method will delete or dettach  the uploaded files from where you have uploaded
     *
     * @param selector
     * @implNote Selector may be Id,name,CSS,Xpath etc.,
     * @implNote provide the selector from where you want to delete or dettach the files
     */
    public static void deleteUploadedFile(String selector) {
        Page().setInputFiles(selector, new Path[0]);
    }


    //DropDown Handling
    public static List<String> printAllValues(Locator locator) {
        List<String> list = locator.allTextContents();
        writeLogInfo("Below are the dropdown options");
        list.forEach(list1 -> writeLogInfo(list1));
        return list;
    }

    public static void printAllvalues(Locator locatorr) {
        List<String> list = locatorr.allTextContents();
        writeLogInfo("Below are the dropdown options");
        list.forEach(list1 -> writeLogInfo(list1));
    }

    public static void selectDropDownByText(Locator locator, String optionValue) {
        List<String> list = locator.allTextContents();
        for (String e : list) {
            if (e.equalsIgnoreCase(optionValue)) {
                locator.selectOption(optionValue);
                break;
            }
        }
    }

    public static void selectDropDownByValue(Locator locator, String optionValue) {
        for (int i = 0; i < locator.count(); i++) {
            String dropDownValue = locator.nth(i).getAttribute("value");
            if (dropDownValue.equalsIgnoreCase(optionValue)) {
                locator.selectOption(optionValue);
                break;
            }
        }
    }


    //Mouse Actions

    /**
     * This method will perform double click action  on the specified element
     *
     * @implNote This will work at the specified locator
     */
    public static void doubleClickOnElement(Locator locator) {
        waitForPagefullyLoaded();
        locator.dblclick();
    }

    /**
     * This method will perform Mouse Right click action  on the specified element
     *
     * @implNote This will work at the specified locator
     */
    public static void rightClickOnElement(Locator locator) {
        waitForPagefullyLoaded();
        locator.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
    }

    /**
     * This method will perform hover action on the specified element
     *
     * @implNote This will work at the specified locator
     */
    public static void mouseHoverOnElement(Locator locator) {
        waitForPagefullyLoaded();
        locator.hover();
    }

    public static void scrollToElement(Locator locator) {
        locator.scrollIntoViewIfNeeded();
    }

    //KeyBoard Actions

    /**
     * This method will perform keyboard actions based on the Key combiination you provided
     *
     * @param locator
     * @param keyCombination : example "Shift+A"
     * @implNote This will work at the specified locator
     */
    public static void pressKey(Locator locator, String keyCombination) {
        locator.press(keyCombination);
    }

    /**
     * This method will perform keyboard actions based on the Key combiination you provided
     *
     * @param keyCombination : example "Shift+A"
     * @implNote This will work at entire page level
     */
    public static void pressKey(String keyCombination) {
        Page().keyboard().press(keyCombination);
    }


    //Page Navigations
    public static void waitForPagefullyLoaded() {
        Page().waitForLoadState();
    }

    public static void waitForcefully(int timeInMillieSecs) throws InterruptedException {
        Thread.sleep(timeInMillieSecs);
    }

    public static void forward() {
        Page().goForward();
        waitForPagefullyLoaded();
    }

    public static void backward() {
        Page().goBack();
        waitForPagefullyLoaded();

    }

    public static void refreshPage() {
        Page().reload();
        waitForPagefullyLoaded();
    }


    //Playwright Assertions
    public static void assertEquals(String Actual, String Expected) {
        if (Actual.trim().equals(Expected.trim())) {
            writeLogError(Actual + "is  matching with the " + Expected + "  value");
        } else {
            writeLogInfo(Actual + "is not matching with the " + Expected + "  value");
            throw new AssertionError(Actual + "is not matching with the " + Expected + "  value");

        }
    }
public static void assertThatContainsText(Locator locator,String expectedValue){
        assertThat(locator).containsText(expectedValue);
}
    public static void assertThatHasText(Locator locator,String expectedValue){
        assertThat(locator).hasText(expectedValue);
    }
    public static void assertThatIsEnabled(Locator locator,String expectedValue){
        assertThat(locator).isEnabled();
    }
    public static void assertEqualsIgnoreCase(String Actual, String Expected) {
        if (Actual.trim().equalsIgnoreCase(Expected.trim())) {
            writeLogError(Actual + "is  matching with the " + Expected + "  value");

        } else {
            writeLogInfo(Actual + "is not matching with the " + Expected + "  value");
            throw new AssertionError(Actual + "is not matching with the " + Expected + "  value");

        }
    }

    //element visible or disable or enable methods
    public static boolean isDisplayed(Locator locator) {
        waitForPagefullyLoaded();
        return locator.isVisible();
    }

    public static boolean isEnabled(Locator locator) {
        waitForPagefullyLoaded();
        return locator.isEnabled();
    }

    public static boolean isChecked(Locator locator) {
        waitForPagefullyLoaded();
        return locator.isChecked();
    }

    public static boolean isDisabled(Locator locator) {
        waitForPagefullyLoaded();
        return locator.isDisabled();
    }

    //windows Handling

    public static Page switchToPage(Locator locator) {
        NEWPAGE = Page().waitForPopup(() -> {
            locator.click();
        });
        NEWPAGE.waitForLoadState();
        writeLogInfo("opened new window and titile of the window is :" + NEWPAGE.title());
        return NEWPAGE;
    }
    public static Page switchToPage(String selector) {
        NEWPAGE = Page().waitForPopup(() -> {
            Page().click(selector);
        });
        NEWPAGE.waitForLoadState();
        writeLogInfo("opened new window and titile of the window is :" + NEWPAGE.title());
        return NEWPAGE;
    }

    public static void closeWindow(Page page) {
        writeLogInfo("Closed the window, whose title is : " + page.title());
        page.close();
    }

    public static Page switchToWindowByTitle(Locator locator, String windowTitle) {
        switchToPage(locator);
        List<Page> pages = getContext().pages();
        for (int i = 0; i < pages.stream().count(); i++) {
            if ((pages.get(i).title().trim()).equalsIgnoreCase(windowTitle.trim())) {
                focusedPage = pages.get(i);
                writeLogInfo("now the window with the title : " + focusedPage.title());
                break;
            }
        }
        return focusedPage;
    }
    public static Page switchToWindowByTitle(String windowTitle) {
        Page().click("//html");
        List<Page> pages = getContext().pages();
        for (int i = 0; i < pages.stream().count(); i++) {
            if ((pages.get(i).title().trim()).equalsIgnoreCase(windowTitle.trim())) {
                focusedPage = pages.get(i);
                writeLogInfo("now the window with the title : " + focusedPage.title());
                break;
            }
        }
        focusedPage.waitForLoadState();
        return focusedPage;
    }

    public static Page switchToWindowByTitle(String selector, String windowTitle) {
        switchToPage(selector);
        List<Page> pages = getContext().pages();
        for (int i = 0; i < pages.stream().count(); i++) {
            if ((pages.get(i).title().trim()).equalsIgnoreCase(windowTitle.trim())) {
                focusedPage = pages.get(i);
                writeLogInfo("now the window with the title : " + focusedPage.title());
                break;
            }
        }
        return focusedPage;
    }

    public static String getParentWindowTitle() {
        parentWindowTitle = Page().title();
        return parentWindowTitle;
    }



}
