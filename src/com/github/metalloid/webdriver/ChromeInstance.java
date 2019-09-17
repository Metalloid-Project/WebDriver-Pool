package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class ChromeInstance extends Instance implements LocalInstance, RemoteInstance {
    private WebDriver driver;
    private WebDriverOptions<ChromeOptions> webDriverOptions;

    @Override
    public void setOptions(WebDriverOptions options) {
        if (options == null && HEADLESS) {
            webDriverOptions = new WebDriverOptions<>();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setHeadless(true);

            webDriverOptions.put(chromeOptions);
        } else {
            this.webDriverOptions = options;
        }
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    public BrowserInstance createLocalInstance() {
        driver = createLocal(ChromeDriver.class, webDriverOptions);
        return this;
    }

    @Override
    public BrowserInstance createRemoteInstance() {
        driver = createRemoteInstance(webDriverOptions);
        return this;
    }
}
