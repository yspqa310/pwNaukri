package StepDefinition;

import Pages.naukri.naukriPO;
import io.cucumber.java.en.*;

import java.io.IOException;

public class naukriSteps extends naukriPO {
    @Given("user is logs into naukri")
    public void userlogin() throws IOException {
        userLogin();
        writeLogInfo("user logged in successfully");
    }

    @And("user updates skill set")
    public void userUpdateSkill() throws InterruptedException {
        updateSkillSet(100);
    }

    @Then("user log outs")
    public void userLogOut() {
        userLogouts();
    }
}
