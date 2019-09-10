package test.java;

import com.github.metalloid.webdriver.WebDriverPool;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTests {

    @Test(expected = IllegalStateException.class)
    public void initializeChromeWithoutChromeDriver() {
        System.setProperty("browser.name", "chrome");
        WebDriverPool.get();
    }

    @Test
    public void initializeChrome() {
        System.setProperty("browser.name", "chrome");
        System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver.exe");
        WebDriver driver = WebDriverPool.get();
        Assert.assertNotNull(driver);
        Assert.assertTrue(WebDriverPool.hasStoredInstance());
        WebDriverPool.closeSession();
        Assert.assertFalse(WebDriverPool.hasStoredInstance());
    }

    @Test
    public void initializeChromeWithOptions() {
        System.setProperty("browser.name", "chrome");
        System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);

        WebDriverPool.registerOptions(chromeOptions);

        WebDriver driver = WebDriverPool.get();
        Assert.assertNotNull(driver);
        Assert.assertTrue(WebDriverPool.hasStoredInstance());
        WebDriverPool.closeSession();
        Assert.assertFalse(WebDriverPool.hasStoredInstance());
    }
}
