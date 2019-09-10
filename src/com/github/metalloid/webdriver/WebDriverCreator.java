package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

class WebDriverCreator {
    private static final String BROWSER_NAME = System.getProperty("browser.name");

    static WebDriver createInstance(WebDriverOptions<?> options) {
        switch (BROWSER_NAME.toLowerCase()) {
            case "chrome":
                return browser(ChromeDriver.class, options);
            case "ff":
            case "firefox":
                return browser(FirefoxDriver.class, options);
            case "ie":
            case "internet explorer":
                return browser(InternetExplorerDriver.class, options);
            case "edge":
                return browser(EdgeDriver.class, options);
            case "remote":
                try {
                    return remoteWebDriver(options, new URL(System.getProperty("webdriver.hub.url")));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
        }

        throw new RuntimeException("browser not implemented");
    }

    static WebDriver createInstance() {
        return createInstance(null);
    }

    private static RemoteWebDriver remoteWebDriver(WebDriverOptions<?> options, URL url) {
        return (RemoteWebDriver) browser(RemoteWebDriver.class, options, url);
    }

    private static WebDriver browser(Class<? extends WebDriver> webDriverClass, WebDriverOptions<?> options) {
        return browser(webDriverClass, options, null);
    }

    private static WebDriver browser(Class<? extends WebDriver> webDriverClass, WebDriverOptions<?> options, URL url) {
        try {
            if (options != null) {
                Object option = options.get();

                if (url != null) {
                    return webDriverClass.getConstructor(URL.class, option.getClass()).newInstance(url, option);
                } else {
                    return webDriverClass.getConstructor(option.getClass()).newInstance(option);
                }
            } else {
                return webDriverClass.getConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e.getCause().getMessage());
        }
    }
}
