package com.github.metalloid.webdriver;

import org.openqa.selenium.MutableCapabilities;

import java.util.Objects;

class WebDriverOptions<T extends MutableCapabilities> {
    private MutableCapabilities capabilities;

    WebDriverOptions<T> put(MutableCapabilities capabilities) {
        this.capabilities = Objects.requireNonNull(capabilities);
        return this;
    }

    T get() {
        return (T) capabilities;
    }
}
