package Pages.naukri;

import com.microsoft.playwright.*;
import utilities.genericMethods;

import java.awt.*;
import java.io.IOException;

public class naukriPO extends genericMethods {
    //PAGE Locators
    Locator txtBxUserName = Page().locator("#usernameField");
    Locator txtBxPassWord = Page().locator("#passwordField");
    Locator btnLogin = Page().locator("//button[text()='Login']");
    Locator btnViewProfile = Page().locator("//a[.='View profile']");
    Locator btnEditIcon = Page().locator("//*[@id='lazyKeySkills']/div/div/div[1]/span[@class='edit icon']");
    Locator txtSkillPOM = Page().locator("//*[@id='lazyKeySkills']/div/div/div[2]/div/span[18]");
    Locator btnSkillRemove = Page().locator("//*//span[.='Page Object Model']/../a");
    Locator txtBXAddSkill = Page().locator("//*[@id='keySkillSugg']");
    Locator btnSave = Page().locator("#saveKeySkills");
    Locator btnProfileImage = Page().locator("//img[@alt='naukri user profile img']");
    Locator btnLogout = Page().locator("//a[.='Logout']");

    public void updateSkillSet(int maxAttempts) throws InterruptedException {
        boolean flag;
        int i;
        for (i = 0; i <= maxAttempts; i++) {
            click(btnEditIcon);
            try {
                flag = txtSkillPOM.isVisible();
            } catch (PlaywrightException exception) {
                flag = false;
            }
            if (flag) {
                waitForPagefullyLoaded();
                click(btnSkillRemove);
                click(btnSave);
                writeLogError("This " + i + " th Profile is got Removed with POM Skill for ");
                waitForcefully(10000);
            } else {
                enterTextByLocator(txtBXAddSkill, "Page Object Model");
                clickOnBlank();
                click(btnSave);
                writeLogError("This " + i + " th Profile is got Added with POM Skill for ");
                waitForcefully(10000);
            }
        }
        writeLogError("Profile is got updated " + maxAttempts + " times today");
    }


    //Locators for update cv
    Locator btnUpdateResume = Page().locator("//input[@value='Update resume']");
    Locator btnDltResume = Page().locator("//i[.='deleteOneTheme']");
    Locator btnDlt = Page().locator("(//div[@class='action right-align']//button[.='Delete'])[2]");
    Locator btnCmpltProfile = Page().locator("//a[.='Complete profile']");
    Locator btnUpldProfile = Page().locator("//span[.='Upload resume']");

    public void deleteResume() throws InterruptedException {
        waitForPagefullyLoaded();
        click(btnDltResume);
        click(btnDlt);
        writeLogInfo("Resume dettached successfully");
        waitForcefully(3000);
    }

    public void uploadResume() throws InterruptedException, AWTException {
        click(btnCmpltProfile);
        click(btnUpldProfile);
        uploadFile("Resume.pdf");
        writeLogInfo("uploaded resume successfully");
    }


    public void updateResume() throws InterruptedException, AWTException {
        click(btnUpdateResume);
        uploadFile("Resume.pdf");
        waitForcefully(10000);
    }

    public void userLogouts() {
        waitForPagefullyLoaded();
        click(btnProfileImage);
        click(btnLogout);
    }

    public void userLogin() throws InterruptedException, IOException, AWTException {
        boolean viewPrflVisible;
        String uname = GetProperty("username");
        String pwd = GetProperty("password");
        enterTextByLocator(txtBxUserName, uname);
        enterTextByLocator(txtBxPassWord, pwd);
        click(btnLogin);
        waitForcefully(10000);
        try {
            viewPrflVisible = btnViewProfile.isVisible();
        } catch (PlaywrightException e) {
            viewPrflVisible = false;
        }
        if (viewPrflVisible) {
            click(btnViewProfile);
            waitForcefully(5000);
        } else {
            uploadResume();
        }
    }
}
