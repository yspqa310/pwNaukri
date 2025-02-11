package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utilities.genericMethods;

public class naukriLoginPage extends genericMethods {
    genericMethods gm = new genericMethods();
    Locator lgn = Page().locator("//a[@title='Jobseeker Login']");
    Locator userName = Page().locator("(//label[contains(text(),'Username')]//following::input)[1]");
    Locator password = Page().locator("(//label[contains(text(),'Username')]//following::input)[2]");
    Locator loginBtn = Page().locator("//button[@type='submit' and text()='Login']");
    Locator pomElement = Page().locator("//*[@id='lazyKeySkills']/div/div/div[2]/div/span[text()='Page Object Model']");
    Locator pomEdit = Page().locator("(//span[text()='Key skills'])[2]/../span[@class='edit icon']");
    Locator pomCross = Page().locator("//form/div[2]/div[1]/div/div[1]/div[1]/div[1]/div/span[text()='Page Object Model']/../a");
    Locator SaveBtn = Page().locator("//button[text()='Save']");
    Locator addPOM = Page().locator("//span[text()='Page Object Model']");
    Locator POM_plus = Page().locator("//span[text()='Page Object Model']/../em");
    Locator addSkillsTxtBX = Page().locator("//input[@placeholder='Add skills']");
    Locator matchSkill = Page().locator("//div[@class='Sbtn' and text()='Page Object Model']");
    Locator NavBar = Page().locator("//div[@class='nI-gNb-drawer__icon']");
    Locator logOut = Page().locator("//a[@title='Logout']");
    Locator viewProfile = Page().locator("//a[text()='View']");

    public void userLoginIntoNaukri() throws InterruptedException {
        waitForPagefullyLoaded();
        String username = getProperty("username");
        String passWord = getProperty("password");
        click(lgn);
        enterTextByLocator(userName, username);
        enterTextByLocator(password, passWord);
        click(loginBtn);
    }

    public void updateResume() {
        uploadFileFromProjectDirectory("//input[@value='Update resume']", "");
        writeLogInfo("Successfully updated resume");
    }

    public void updateSkill(int attempts) throws InterruptedException {
        click(viewProfile);
        waitForPagefullyLoaded();
        waitForcefully(10000);
        for (int i = 0; i <= attempts; i++) {
            if (pomElement.isVisible()) {
                click(pomEdit);
                click(pomCross);
                click(SaveBtn);
                waitForcefully(10000);
                writeLogInfo("Successfully deleted Page Object Model");
            } else {
                click(pomEdit);
                if (addPOM.isVisible()) {
                    click(POM_plus);
                    click(SaveBtn);
                    waitForcefully(10000);
                    writeLogInfo("Successfully Added Page Object Model");
                } else {
                    enterTextByLocator(addSkillsTxtBX, "Page Object Model");
                    Page().click("//html");
                    click(SaveBtn);
                    waitForcefully(10000);
                    writeLogInfo("Successfully Added Page Object Model");
                }
            }
        }
    }

    public void Logout() throws InterruptedException {
        click(NavBar);
        click(logOut);
        writeLogInfo("Logged out");
    }
}
