package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Optional;

@Deprecated
class FirefoxInstance extends Instance implements LocalInstance, RemoteInstance {
    private WebDriver driver;
    private WebDriverOptions<FirefoxOptions> webDriverOptions;

    @Override
    public void setOptions(WebDriverOptions options) {
        Optional<WebDriverOptions<?>> optionalOfCustomOptions = this.getCustomOptionClass();
        if (optionalOfCustomOptions.isPresent()) {
            this.webDriverOptions = (WebDriverOptions<FirefoxOptions>) optionalOfCustomOptions.get();
        } else {
            if (options == null && HEADLESS) {
                webDriverOptions = new WebDriverOptions<>();

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(true);

                webDriverOptions.put(firefoxOptions);
            } else {
                this.webDriverOptions = options;
            }
        }
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    public BrowserInstance createLocalInstance() {
        driver = createLocal(FirefoxDriver.class, webDriverOptions);
        return this;
    }

    @Override
    public BrowserInstance createRemoteInstance() {
        driver = createRemoteInstance(webDriverOptions);
        return this;
    }
}
