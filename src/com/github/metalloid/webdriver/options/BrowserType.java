package com.github.metalloid.webdriver.options;

public enum BrowserType {
    LOCAL,
    REMOTE;

    public static BrowserType parse(String browserType) {
        if (browserType == null || browserType.equals("local")) {
            return LOCAL;
        } else if (browserType.equals("remote")) {
            return REMOTE;
        } else {
            throw new IllegalArgumentException("Argument browser.type should be [local] or [remote]!");
        }
    }
}
