package com.github.metalloid.webdriver.browsers;

import com.github.metalloid.webdriver.options.OptionsCollector;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class Browser<Driver extends WebDriver, Capabilities extends MutableCapabilities> {
    private Class<Driver> driverClass;

    Browser(Class<Driver> driverClass) {
        this.driverClass = driverClass;
    }

    public WebDriver createRemoteInstance() {
        return createRemoteInstance(null);
    }

    public WebDriver createRemoteInstance(@Nullable Capabilities options) {
        if (options == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName(getSeleniumBrowserName());

            return createRemoteWebDriver(desiredCapabilities);
        } else {
            return createRemoteWebDriver(new DesiredCapabilities(options));
        }
    }

    public WebDriver createLocalInstance() {
        return createLocalInstance(null);
    }

    public WebDriver createLocalInstance(@Nullable Capabilities options) {
        try {
            if (options == null) {
                return driverClass.getConstructor().newInstance();
            } else {
                return driverClass.getConstructor(options.getClass()).newInstance(options);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e.getCause().getMessage());
        }
    }

    private static WebDriver createRemoteWebDriver(org.openqa.selenium.Capabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL(OptionsCollector.HUB_URL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSeleniumBrowserName() {
        switch (OptionsCollector.getBrowserName()) {
            case CHROME:
                return BrowserType.CHROME;
            case FIREFOX:
                return BrowserType.FIREFOX;
            case INTERNET_EXPLORER:
                return BrowserType.IE;
            case EDGE:
                return BrowserType.EDGE;
        }

        throw new RuntimeException("browser not implemented");
    }
}
