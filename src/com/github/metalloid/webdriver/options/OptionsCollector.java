package com.github.metalloid.webdriver.options;

import java.util.HashMap;

public class OptionsCollector {
    public static final Boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless"));
    private static final String WINDOW_SIZE = System.getProperty("window.size");
    public static final String BROWSER_NAME = System.getProperty("browser.name");
    public static final String HUB_URL = System.getProperty("hub.url");
    public static final Boolean CLOSE_BROWSER_BY_DEFAULT = Boolean.parseBoolean(System.getProperty("close.browser.by.default"));
    private static String BROWSER_TYPE = System.getProperty("browser.type");
    public static final String CHROME_OPTIONS_CLASS = System.getProperty("chrome.options");
    public static final String FIREFOX_OPTIONS_CLASS = System.getProperty("firefox.options");
    public static final String INTERNET_EXPLORER_OPTIONS_CLASS = System.getProperty("internet.explorer.options");
    public static final String EDGE_OPTIONS_CLASS = System.getProperty("edge.options");

    public static BrowserName getBrowserName() {
        return BrowserName.parse(BROWSER_NAME);
    }

    public static WindowSize getWindowSize() {
        return WindowSize.getInstance(WINDOW_SIZE);
    }

    public static BrowserType getBrowserType() {
        return BrowserType.parse(BROWSER_TYPE);
    }
}
