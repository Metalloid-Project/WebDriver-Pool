package test.java.metalloid.tests.positive;

import com.github.metalloid.webdriver.WebDriverPool;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTests {

    @Test
    public void openAndCloseChrome() {
        System.setProperty("close.browser.by.default", "true");
        System.setProperty("headless", "true");
        System.setProperty("browser.name", "chrome");
        WebDriverPool.get();
    }

    @Test
    public void registerChromeOptions() {
        System.setProperty("close.browser.by.default", "true");
        System.setProperty("browser.name", "chrome");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        WebDriverPool.registerOptions(chromeOptions);
        WebDriverPool.get();
    }

    @Test
    public void useOptionsClassToRegisterOptions() {
        System.setProperty("close.browser.by.default", "true");
        System.setProperty("browser.name", "chrome");
        System.setProperty("chrome.options", "test.java.metalloid.mocks.TestChromeOptions");
        WebDriverPool.get();
    }

    @Test
    public void setWindowSizeWithProperties() {
        System.setProperty("close.browser.by.default", "true");
        System.setProperty("browser.name", "chrome");
        System.setProperty("window.size", "1280x960");
        System.setProperty("headless", "true");
        WebDriverPool.get();
    }
}
