package pwPrcatice;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class nth_element extends CSSRelativeLocators_basedLayout{
    static Page page=null;
    public static void main(String[] args) {
        page=getPage();
        enterValueonNth("yakubsajahanpasha@gmail.com");
        closebrowser();
    }
    public static void enterValueonNth(String Text){
        Locator Email =page.locator(".userform input >> nth=0");
        Email.fill(Text);
    }
}
