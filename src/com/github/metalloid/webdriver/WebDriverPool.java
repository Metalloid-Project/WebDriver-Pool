package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;

public class WebDriverPool {
    private static final HashMap<Thread, WebDriver> POOL = new HashMap<>();

    public static WebDriver get() {
        Thread thread = Thread.currentThread();
        WebDriver driver = POOL.get(thread);

        if (driver == null) {
            String browserName = System.getProperty("browser.name");
            switch (browserName) {
                case "chrome":
                case "CHROME":
                    driver = new ChromeDriver();
                    break;
                case "ff":
                case "firefox":
                case "Firefox":
                case "FIREFOX":
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                case "internet explorer":
                case "Internet Explorer":
                case "INTERNET EXPLORER":
                    driver = new InternetExplorerDriver();
                    break;
                case "edge":
                case "EDGE":
                    driver = new EdgeDriver();
                    break;
            }

            POOL.put(thread, driver);
        }

        return driver;
    }
}
