package StepDefinition;

import Pages.naukri.naukriPO;
import io.cucumber.java.en.*;

import java.awt.*;
import java.io.IOException;

public class naukriSteps extends naukriPO {
    @Given("user is logs into naukri")
    public void userlogin() throws InterruptedException, IOException, AWTException {
        userLogin();
        writeLogInfo("user logged in successfully");
    }

    @And("user updates skill set")
    public void userUpdateSkill() throws InterruptedException {
        updateSkillSet(100);
    }

    @And("user updates resume")
    public void userUpdateResume() throws InterruptedException, AWTException {
        updateResume();
        writeLogInfo("Successfully Uploaded Resume");
    }

    @And("user delete existing resume")
    public void userDeleteResume() throws InterruptedException, AWTException {
        deleteResume();
        writeLogInfo("Successfully Uploaded Resume");
    }


    @Then("user log outs")
    public void userLogOut() {
        userLogouts();
    }
}
