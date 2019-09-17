package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;

class WebDriverCreator {
    private static final String BROWSER_NAME = System.getProperty("browser.name");
    private static String BROWSER_TYPE = System.getProperty("browser.type");

    static WebDriver createInstance(WebDriverOptions<?> options) {
        Instance instance;

        switch (BROWSER_NAME.toLowerCase()) {
            case "chrome":
                instance = new ChromeInstance();
                break;
            case "ff":
            case "firefox":
                instance = new FirefoxInstance();
                break;
            case "ie":
            case "internet explorer":
                instance = new InternetExplorerInstance();
                break;
            case "edge":
                instance = new EdgeInstance();
                break;
            default:
                throw new IllegalArgumentException(String.format("Browser name [%s] is not supported! Possible names: [chrome] [ff] [firefox] [ie] [internet explorer] [edge]", BROWSER_NAME));
        }

        instance.setOptions(options);

        if (BROWSER_TYPE == null) BROWSER_TYPE = "local";

        switch (BROWSER_TYPE.toLowerCase()) {
            case "remote":
                return instance.createRemoteInstance().getWebDriver();
            case "local":
                return instance.createLocalInstance().getWebDriver();
        }

        return null;
    }
}
