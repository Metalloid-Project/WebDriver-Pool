package com.github.metalloid.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class WebDriverPool {
    private static final HashMap<Thread, WebDriver> POOL = new HashMap<>();
    private static final HashMap<Thread, Class<? extends MetalloidDriver>> WRAPPERS = new HashMap<>();
    private static final HashMap<Thread, WebDriverOptions> OPTIONS = new HashMap<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(WebDriverPool::clean));

        new Thread(() -> {
            while (true) {
                clean();
            }
        });
    }

    private static void clean() {
        POOL.keySet().forEach(t -> {
            if (!t.isAlive()) {
                closeSession(t);
            }
        });
    }

    public static void registerWrapper(Class<? extends MetalloidDriver> wrapperClass) {
        WRAPPERS.put(Thread.currentThread(), wrapperClass);
    }

    public static void registerOptions(ChromeOptions chromeOptions) {
        OPTIONS.put(Thread.currentThread(), new WebDriverOptions<ChromeOptions>().put(chromeOptions));
    }

    public static void registerOptions(FirefoxOptions firefoxOptions) {
        OPTIONS.put(Thread.currentThread(), new WebDriverOptions<FirefoxOptions>().put(firefoxOptions));
    }

    public static void registerOptions(InternetExplorerOptions internetExplorerOptions) {
        OPTIONS.put(Thread.currentThread(), new WebDriverOptions<InternetExplorerOptions>().put(internetExplorerOptions));
    }

    public static void registerOptions(EdgeOptions edgeOptions) {
        OPTIONS.put(Thread.currentThread(), new WebDriverOptions<EdgeOptions>().put(edgeOptions));
    }

    public static WebDriver get() {
        Thread thread = Thread.currentThread();
        WebDriver driver = POOL.get(thread);

        if (driver != null) {
            return driver;
        } else {
            String browserName = System.getProperty("browser.name");
            if (browserName == null) throw new IllegalStateException("Use System.setProperty(\"browser.name\"); to initialize browser by its name");

            String browserType = System.getProperty("browser.type");
            WebDriverOptions options = OPTIONS.get(Thread.currentThread());

            if (browserType != null && browserType.equals("remote")) {
                driver = WebDriverCreator.createRemoteInstance(options);
            } else {
                driver = WebDriverCreator.createLocalInstance(options);
            }

            POOL.put(thread, driver);

            return driver;
        }
    }

    public static MetalloidDriver getCustom() {
        return wrap(get());
    }

    public static void closeSession(Thread thread) {
        WebDriver driver = POOL.get(thread);

        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignore) {}
        }

        POOL.remove(thread);
        OPTIONS.remove(thread);
        WRAPPERS.remove(thread);
    }

    public static boolean hasStoredInstance() {
        return POOL.get(Thread.currentThread()) != null;
    }

    private static MetalloidDriver wrap(WebDriver driver) {
        Class<? extends WebDriver> wrapperClass = WRAPPERS.get(Thread.currentThread());
        if (wrapperClass != null) {
            try {
                return (MetalloidDriver) wrapperClass.getConstructor(WebDriver.class).newInstance(driver);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Wrapper constructor must have WebDriver as an argument!");
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("You did not specify custom WebDriver. Use `registerWrapper` and pass your class as an argument");
        }
    }
}
