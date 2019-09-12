package com.github.metalloid.webdriver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

class WebDriverCreator {
    private static final String BROWSER_NAME = System.getProperty("browser.name");
    private static final String HUB_URL = System.getProperty("webdriver.hub.url");

    static WebDriver createLocalInstance(WebDriverOptions<?> options) {
        switch (BROWSER_NAME.toLowerCase()) {
            case "chrome":
                return createLocal(ChromeDriver.class, options);
            case "ff":
            case "firefox":
                return createLocal(FirefoxDriver.class, options);
            case "ie":
            case "internet explorer":
                return createLocal(InternetExplorerDriver.class, options);
            case "edge":
                return createLocal(EdgeDriver.class, options);
        }

        throw new RuntimeException("browser not implemented");
    }

    static WebDriver createRemoteInstance(WebDriverOptions<?> options) {
        if (options == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName(getSeleniumBrowserName());

            return createRemote(desiredCapabilities);
        } else {
            return createRemote(new DesiredCapabilities(options.get()));
        }
    }

    private static WebDriver createLocal(Class<? extends WebDriver> webDriverClass, WebDriverOptions<?> options) {
        try {
            if (options == null) {
                return webDriverClass.getConstructor().newInstance();
            } else {
                Object capabilities = options.get();
                return webDriverClass.getConstructor(capabilities.getClass()).newInstance(capabilities);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e.getCause().getMessage());
        }
    }

    private static WebDriver createRemote(Capabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL(HUB_URL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSeleniumBrowserName() {
        switch (BROWSER_NAME.toLowerCase()) {
            case "chrome":
                return BrowserType.CHROME;
            case "ff":
            case "firefox":
                return BrowserType.FIREFOX;
            case "ie":
            case "internet explorer":
                return BrowserType.IE;
            case "edge":
                return BrowserType.EDGE;
        }

        throw new RuntimeException("browser not implemented");
    }
}
