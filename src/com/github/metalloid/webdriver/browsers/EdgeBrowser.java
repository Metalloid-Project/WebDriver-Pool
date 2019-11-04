package com.github.metalloid.webdriver.browsers;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeBrowser extends Browser<EdgeDriver, EdgeOptions> {

    public EdgeBrowser() {
        super(EdgeDriver.class);
    }
}
