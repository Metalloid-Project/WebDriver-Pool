package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

class InternetExplorerInstance extends Instance implements LocalInstance, RemoteInstance {
    private WebDriver driver;
    private WebDriverOptions webDriverOptions;

    @Override
    public void setOptions(WebDriverOptions options) {
        this.webDriverOptions = options;
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    public BrowserInstance createLocalInstance() {
        driver = createLocal(InternetExplorerDriver.class, webDriverOptions);
        return this;
    }

    @Override
    public BrowserInstance createRemoteInstance() {
        driver = createRemoteInstance(webDriverOptions);
        return this;
    }
}
