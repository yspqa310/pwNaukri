package utilities;

import java.io.IOException;

public class genericMethods extends myBrowser {
    public void launchURL(String URL) throws IOException {
        getPage().navigate(URL);
        System.out.println(URL);
        System.out.println(getPage().title());
    }
}
