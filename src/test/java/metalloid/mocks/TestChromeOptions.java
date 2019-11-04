package test.java.metalloid.mocks;

import com.github.metalloid.webdriver.Options;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestChromeOptions implements Options<ChromeOptions> {

    @Override
    public ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        return chromeOptions;
    }
}
