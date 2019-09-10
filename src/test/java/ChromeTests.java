package test.java;

import com.github.metalloid.webdriver.WebDriverPool;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTests {

    @Test
    public void initializeChrome() {
        Assert.assertFalse(WebDriverPool.hasStoredInstance());
        System.setProperty("browser.name", "chrome");
        WebDriver driver = WebDriverPool.get();
        Assert.assertNotNull(driver);
        Assert.assertTrue(WebDriverPool.hasStoredInstance());
        WebDriverPool.closeSession();
        Assert.assertFalse(WebDriverPool.hasStoredInstance());
    }

    @Test
    public void initializeChromeWithOptions() {
        Assert.assertFalse(WebDriverPool.hasStoredInstance());
        System.setProperty("browser.name", "chrome");
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
