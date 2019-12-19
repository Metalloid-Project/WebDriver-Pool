package com.github.metalloid.webdriver.options;

import com.github.metalloid.webdriver.WebDriverOptions;

import javax.annotation.Nullable;

public class OptionsFactory {

    public static WebDriverOptions<?> getOptions(@Nullable WebDriverOptions options) {
        //registered options
        if (options != null) {
            return options;
        } else {
            return OptionsApplicator.apply();
        }
    }
}
