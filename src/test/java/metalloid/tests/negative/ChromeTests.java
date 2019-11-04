package test.java.metalloid.tests.negative;

import com.github.metalloid.webdriver.WebDriverPool;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ChromeTests {

    @Test(expected = IllegalArgumentException.class)
    public void registerFirefoxOptionsToChromeWebDriver() {
        System.setProperty("close.browser.by.default", "true");
        System.setProperty("browser.name", "chrome");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        WebDriverPool.registerOptions(firefoxOptions);
        WebDriverPool.get();
    }
}
