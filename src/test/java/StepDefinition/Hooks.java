package StepDefinition;

import io.cucumber.java.Before;
import utilities.*;

import java.io.IOException;

import static utilities.myBrowser.*;

public class Hooks {

    @Before
    public static void before() throws IOException {
        InitiatingBrowser();
    }
    public static void after() throws IOException {
        getPage().close();
        getBrowser().close();
        getPlaywright().close();
    }
}
