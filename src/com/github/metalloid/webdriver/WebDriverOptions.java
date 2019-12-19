package com.github.metalloid.webdriver;

import org.openqa.selenium.MutableCapabilities;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class WebDriverOptions<T extends MutableCapabilities> {
    private T capabilities;

    public WebDriverOptions<T> put(T capabilities) {
        this.capabilities = Objects.requireNonNull(capabilities);
        return this;
    }

    public WebDriverOptions<T> put(String capabilitiesClass) {
        Options<T> options;
        try {
            Class<?> clazz = Class.forName(capabilitiesClass);

            options = (Options<T>) clazz.getConstructor().newInstance();

            return put(options.getOptions());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public T get() {
        return capabilities;
    }
}
