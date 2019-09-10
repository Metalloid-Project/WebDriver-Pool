package test.java;

import com.github.metalloid.webdriver.WebDriverPool;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxTests {

    @Test(expected = IllegalStateException.class)
    public void initializeFirefoxWithoutFirefoxDriver() {
        System.setProperty("browser.name", "ff");
        WebDriverPool.get();
    }

    @Test
    public void initializeFirefox() {
        System.setProperty("browser.name", "ff");
        System.setProperty("webdriver.gecko.driver", "src/test/java/geckodriver.exe");
        WebDriver driver = WebDriverPool.get();
        Assert.assertNotNull(driver);
        Assert.assertTrue(WebDriverPool.hasStoredInstance());
        WebDriverPool.closeSession();
        Assert.assertFalse(WebDriverPool.hasStoredInstance());
    }

    @Test
    public void initializeFirefoxWithOptions() {
        System.setProperty("browser.name", "ff");
        System.setProperty("webdriver.gecko.driver", "src/test/java/geckodriver.exe");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);

        WebDriverPool.registerOptions(firefoxOptions);

        WebDriver driver = WebDriverPool.get();
        Assert.assertNotNull(driver);
        Assert.assertTrue(WebDriverPool.hasStoredInstance());
        WebDriverPool.closeSession();
        Assert.assertFalse(WebDriverPool.hasStoredInstance());
    }
}
