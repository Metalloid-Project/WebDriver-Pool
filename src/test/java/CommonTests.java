package test.java;

import com.github.metalloid.webdriver.WebDriverPool;
import org.junit.Test;

public class CommonTests {

    @Test(expected = IllegalStateException.class)
    public void checkIfExceptionIsThrownWhenBrowserNameIsNotProvided() {
        WebDriverPool.get();
    }
}
