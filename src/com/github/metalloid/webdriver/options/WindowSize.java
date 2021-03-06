package com.github.metalloid.webdriver.options;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

class WindowSize {
    private static final String EXCEPTION_MESSAGE = "Window size should be in format [0000x0000] or [maximized]! Provided: [%s]";

    private final Dimension dimension;

    private WindowSize(@Nullable Dimension dimension) {
        this.dimension = dimension;
    }

    static WindowSize getInstance(String windowSize) {
        if (windowSize == null) {
            return new WindowSize(null);
        } else if (windowSize.matches("(\\d*)x(\\d*)")) {
            String[] dimensions = windowSize.split("[x]");

            int width;
            int height;

            try {
                width = Integer.parseInt(dimensions[0]);
                height = Integer.parseInt(dimensions[1]);
            } catch (NumberFormatException e) {
                throw buildException(windowSize);
            }

            return new WindowSize(new Dimension(width, height));
        } else if (windowSize.equals("maximized")) {
            return new WindowSize(null);
        } else {
            throw buildException(windowSize);
        }
    }

    void apply(WebDriver driver) {
        if (dimension == null) {
            driver.manage().window().maximize();
        } else {
            driver.manage().window().setSize(dimension);
        }
    }

    static private IllegalArgumentException buildException(String providedValue) {
        return new IllegalArgumentException(String.format(EXCEPTION_MESSAGE, providedValue));
    }
}
