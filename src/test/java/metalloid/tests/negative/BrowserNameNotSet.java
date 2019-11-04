package test.java.metalloid.tests.negative;

import com.github.metalloid.webdriver.WebDriverPool;
import org.junit.Test;

public class BrowserNameNotSet {

    @Test(expected = IllegalArgumentException.class)
    public void assertIllegalArgumentExceptionIsThrown() {
        WebDriverPool.get();
    }
}
