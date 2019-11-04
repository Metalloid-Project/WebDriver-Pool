package com.github.metalloid.webdriver.browsers;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class InternetExplorerBrowser extends Browser<InternetExplorerDriver, InternetExplorerOptions> {

    public InternetExplorerBrowser() {
        super(InternetExplorerDriver.class);
    }
}
