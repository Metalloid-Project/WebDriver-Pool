package com.github.metalloid.webdriver.browsers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser extends Browser<ChromeDriver, ChromeOptions> {

    public ChromeBrowser() {
        super(ChromeDriver.class);
    }
}
