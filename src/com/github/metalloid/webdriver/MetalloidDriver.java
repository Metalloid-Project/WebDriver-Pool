package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;

public abstract class MetalloidDriver implements WebDriver {
    protected WebDriver driver;

    public MetalloidDriver(WebDriver driver) {
        this.driver = driver;
    }
}
