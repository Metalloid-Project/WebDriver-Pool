package com.github.metalloid.webdriver.options;

import com.github.metalloid.webdriver.WebDriverOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.Objects;

public class OptionsApplicator {

    public static void apply(WebDriver driver) {
        OptionsCollector.getWindowSize().apply(driver);
    }

    public static WebDriverOptions<ChromeOptions> apply(ChromeOptions options) {
        Objects.requireNonNull(options);
        options.setHeadless(OptionsCollector.HEADLESS);
        return new WebDriverOptions<ChromeOptions>().put(options);
    }

    public static WebDriverOptions<FirefoxOptions> apply(FirefoxOptions options) {
        Objects.requireNonNull(options);
        options.setHeadless(OptionsCollector.HEADLESS);
        return new WebDriverOptions<FirefoxOptions>().put(options);
    }

    public static WebDriverOptions apply() {
        WebDriverOptions options = null;

        //options from <? extends Options.class> or <properties>
        switch(OptionsCollector.getBrowserName()) {
            case CHROME:
                if (OptionsCollector.CHROME_OPTIONS_CLASS != null) {
                    options = new WebDriverOptions<ChromeOptions>().put(OptionsCollector.CHROME_OPTIONS_CLASS);
                } else {
                    options = OptionsApplicator.apply(new ChromeOptions());
                }
                break;
            case FIREFOX:
                if (OptionsCollector.FIREFOX_OPTIONS_CLASS != null) {
                    options = new WebDriverOptions<FirefoxOptions>().put(OptionsCollector.FIREFOX_OPTIONS_CLASS);
                } else {
                    options = OptionsApplicator.apply(new FirefoxOptions());
                }
                break;
            case INTERNET_EXPLORER:
                if (OptionsCollector.INTERNET_EXPLORER_OPTIONS_CLASS != null) {
                    options = new WebDriverOptions<InternetExplorerOptions>().put(OptionsCollector.INTERNET_EXPLORER_OPTIONS_CLASS);
                }
                break;
            case EDGE:
                if (OptionsCollector.EDGE_OPTIONS_CLASS != null) {
                    options = new WebDriverOptions<EdgeOptions>().put(OptionsCollector.EDGE_OPTIONS_CLASS);
                }
                break;
        }

        return options;
    }
}
