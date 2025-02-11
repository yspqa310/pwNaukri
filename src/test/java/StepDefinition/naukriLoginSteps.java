package StepDefinition;

import Pages.naukriLoginPage;
import io.cucumber.java.en.*;
import utilities.genericMethods;

public class naukriLoginSteps extends genericMethods {
    naukriLoginPage lp = new naukriLoginPage();

    @Given("user enter username and password and click login")
    public void userLogins() throws InterruptedException {
        lp.userLoginIntoNaukri();
        writeLogInfo("user successfully logged into the naukri portal");
    }

    @Then("user updates skill set")
    public void updateSkillSet() throws InterruptedException {
        lp.updateSkill(4);
    }

    @And("user updates Resume")
    public void updateResume() {
        writeLogInfo("");
    }

}
