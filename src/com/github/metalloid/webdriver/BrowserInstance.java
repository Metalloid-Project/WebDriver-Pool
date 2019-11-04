package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;

@Deprecated
interface BrowserInstance {
    void setOptions(WebDriverOptions options);
    WebDriver getWebDriver();
}
