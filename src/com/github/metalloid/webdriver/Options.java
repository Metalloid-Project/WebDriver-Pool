package com.github.metalloid.webdriver;

import org.openqa.selenium.MutableCapabilities;

public interface Options<T extends MutableCapabilities> {
    T getOptions();
}
