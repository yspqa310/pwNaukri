package pwPrcatice;

import com.microsoft.playwright.Locator;

public class Handling_popUps_playwright extends CSSRelativeLocators_basedLayout {
    public static void main(String[] args) {
        getPage();
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
        page.onDialog(dialog -> {
            String value = dialog.message();
            System.out.println(value);
            dialog.accept("shaik yakub pasha");
        });

        Locator alerts = page.locator("(//button[text()])");

        Locator messege = page.locator("#result");


        for (int i = 0; i < alerts.count(); i++) {
            alerts.nth(i).click();
            System.out.println(messege.textContent());
        }
        closebrowser();
    }

}
