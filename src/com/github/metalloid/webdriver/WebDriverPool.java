package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebDriverPool {
    private static final HashMap<Thread, WebDriver> POOL = new HashMap<>();
    private static final HashMap<Thread, Object> OPTIONS = new HashMap<>();
    private static final HashMap<Thread, Class<? extends WebDriver>> WRAPPERS = new HashMap<>();

    public static void registerWrapper(Class<? extends WebDriver> wrapperClass) {
        WRAPPERS.put(Thread.currentThread(), wrapperClass);
    }

    public static void registerOptions(ChromeOptions chromeOptions) {
        OPTIONS.put(Thread.currentThread(), chromeOptions);
    }

    public static void registerOptions(FirefoxOptions firefoxOptions) {
        OPTIONS.put(Thread.currentThread(), firefoxOptions);
    }

    public static void registerOptions(InternetExplorerDriver internetExplorerDriver) {
        OPTIONS.put(Thread.currentThread(), internetExplorerDriver);
    }

    public static void registerOptions(EdgeOptions edgeOptions) {
        OPTIONS.put(Thread.currentThread(), edgeOptions);
    }

    public static void registerOptions(DesiredCapabilities desiredCapabilities) {
        OPTIONS.put(Thread.currentThread(), desiredCapabilities);
    }

    public static WebDriver get() {
        Thread thread = Thread.currentThread();
        WebDriver driver = POOL.get(thread);

        if (driver == null) {
            String browserName = System.getProperty("browser.name");
            Object browserOptions = OPTIONS.get(Thread.currentThread());

            switch (browserName.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver((ChromeOptions) browserOptions);
                    break;
                case "ff":
                case "firefox":
                    driver = new FirefoxDriver((FirefoxOptions) browserOptions);
                    break;
                case "ie":
                case "internet explorer":
                    driver = new InternetExplorerDriver((InternetExplorerOptions) browserOptions);
                    break;
                case "edge":
                    driver = new EdgeDriver((EdgeOptions) browserOptions);
                    break;
                case "remote":
                    try {
                        driver = new RemoteWebDriver(new URL(System.getProperty("webdriver.hub.url")), (DesiredCapabilities) browserOptions);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }

            driver = wrap(driver);

            POOL.put(thread, driver);
        }

        return driver;
    }

    public static void closeSession() {
        Thread thread = Thread.currentThread();
        WebDriver driver = POOL.get(thread);

        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignore) {}
        }

        POOL.remove(thread);
        OPTIONS.remove(thread);
        WRAPPERS.remove(thread);
    }

    private static WebDriver wrap(WebDriver driver) {
        Class<? extends WebDriver> wrapperClass = WRAPPERS.get(Thread.currentThread());
        if (wrapperClass != null) {
            try {
                return wrapperClass.getConstructor(WebDriver.class).newInstance(driver);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Wrapper constructor must have WebDriver as an argument!");
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            return driver;
        }
    }
}
