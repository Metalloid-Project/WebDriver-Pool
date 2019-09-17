package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;

interface BrowserInstance {
    void setOptions(WebDriverOptions options);
    WebDriver getWebDriver();
}
