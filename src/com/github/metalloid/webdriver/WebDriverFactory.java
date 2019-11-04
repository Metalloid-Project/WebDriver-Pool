package com.github.metalloid.webdriver;

import com.github.metalloid.webdriver.browsers.ChromeBrowser;
import com.github.metalloid.webdriver.browsers.EdgeBrowser;
import com.github.metalloid.webdriver.browsers.FirefoxBrowser;
import com.github.metalloid.webdriver.browsers.InternetExplorerBrowser;
import com.github.metalloid.webdriver.options.BrowserName;
import com.github.metalloid.webdriver.options.BrowserType;
import com.github.metalloid.webdriver.options.OptionsCollector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import static com.github.metalloid.webdriver.options.BrowserName.*;
import static com.github.metalloid.webdriver.options.BrowserType.LOCAL;
import static com.github.metalloid.webdriver.options.BrowserType.REMOTE;

class WebDriverFactory {

    static WebDriver createInstance(WebDriverOptions options) {
        BrowserName browserName = OptionsCollector.getBrowserName();
        BrowserType browserType = OptionsCollector.getBrowserType();

        if (browserName == CHROME && browserType == LOCAL) {
            return new ChromeBrowser().createLocalInstance((ChromeOptions) options.get());
        } else if (browserName == CHROME && browserType == REMOTE) {
            return new ChromeBrowser().createRemoteInstance((ChromeOptions) options.get());
        } else if (browserName == FIREFOX && browserType == LOCAL) {
            return new FirefoxBrowser().createLocalInstance((FirefoxOptions) options.get());
        } else if (browserName == FIREFOX && browserType == REMOTE) {
            return new FirefoxBrowser().createRemoteInstance((FirefoxOptions) options.get());
        } else if (browserName == INTERNET_EXPLORER && browserType == LOCAL) {
            return new InternetExplorerBrowser().createLocalInstance((InternetExplorerOptions) options.get());
        } else if (browserName == INTERNET_EXPLORER && browserType == REMOTE) {
            return new InternetExplorerBrowser().createRemoteInstance((InternetExplorerOptions) options.get());
        } else if (browserName == EDGE && browserType == LOCAL) {
            return new EdgeBrowser().createLocalInstance((EdgeOptions) options.get());
        } else if (browserName == EDGE && browserType == REMOTE) {
            return new EdgeBrowser().createRemoteInstance((EdgeOptions) options.get());
        } else {
            throw new RuntimeException("Could not instantiate WebDriver");
        }
    }
}
