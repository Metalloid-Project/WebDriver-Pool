package com.github.metalloid.webdriver.browsers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBrowser extends Browser<FirefoxDriver, FirefoxOptions> {

    public FirefoxBrowser() {
        super(FirefoxDriver.class);
    }
}
