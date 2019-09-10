package com.github.metalloid.webdriver;

import org.openqa.selenium.MutableCapabilities;

class WebDriverOptions<T extends MutableCapabilities> {
    private MutableCapabilities capabilities;

    WebDriverOptions<T> put(MutableCapabilities capabilities) {
        this.capabilities = capabilities;
        return this;
    }

    T get() {
        return (T) capabilities;
    }
}
