package com.github.metalloid.webdriver.options;

public enum BrowserName {
    CHROME,
    FIREFOX,
    INTERNET_EXPLORER,
    EDGE;

    public static BrowserName parse(String browserName) {
        switch (browserName) {
            case "chrome": return CHROME;
            case "ff":
            case "firefox": return FIREFOX;
            case "ie":
            case "internet explorer": return INTERNET_EXPLORER;
            case "edge": return EDGE;
            default:
                throw new IllegalArgumentException(String.format("Browser name [%s] is not supported! Possible names: [chrome] [ff] [firefox] [ie] [internet explorer] [edge]", OptionsCollector.BROWSER_NAME));
        }
    }
}
